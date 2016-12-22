package br.com.tmsfasdom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.tmsfasdom.config.HeaderHttpInterceptor;
import br.com.tmsfasdom.model.RequestUser;

@Controller
public class HomeController {

	public static final String host = "http://localhost:8080";
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return "redirect:/login";
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return "redirect:/login";
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));
		return "index";
	}

	@GetMapping("/login")
	public ModelAndView login() {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject("formLogin", new RequestUser());
		mav.addObject("message", "");
		return mav;
	}

	@PostMapping("/login")
	public ModelAndView loginPost(HttpServletRequest request, @ModelAttribute("formlogin") RequestUser userRequest, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		try{		
		ResponseEntity<String> response = restTemplate.postForEntity(host + "/api/login",
				userRequest, String.class);
		if (response.getHeaders().getFirst("X-AUTH-TOKEN") != null) {
			request.getSession().setAttribute("X-AUTH-TOKEN", response.getHeaders().getFirst("X-AUTH-TOKEN"));
			return new ModelAndView("index");
		}
		else 
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formLogin", userRequest);
			mav.addObject("message", "Erro no Login");
			return mav;
		}
		}
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formLogin", userRequest);
			mav.addObject("message", "Erro no Login");
			return mav;		
		}
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		if (request.getSession() != null)
			request.getSession().invalidate();
		return new ModelAndView("login", "formLogin", new RequestUser());
	}

	
}
