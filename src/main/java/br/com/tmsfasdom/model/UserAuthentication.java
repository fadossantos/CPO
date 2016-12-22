package br.com.tmsfasdom.model;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserAuthentication implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final User user;
	private final RequestUser requestUser;
	private boolean authenticated = true;


	public UserAuthentication(Authentication authentication) {
		this.user = new User(authentication.getName(), authentication.getCredentials().toString(), authentication.getAuthorities());
		this.requestUser = new RequestUser(authentication.getName(), authentication.getCredentials().toString());
	}

	public UserAuthentication(RequestUser _user) {
		this.requestUser = _user;
		Collection<SimpleGrantedAuthority> coll = new ArrayList<SimpleGrantedAuthority>();
		coll.add(new SimpleGrantedAuthority("ROLE_USER"));
		this.user = new User(_user.getCpf(), _user.getSenha(), coll);
	}

	@Override
	public String getName() {
		return user.getUsername();
	
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public RequestUser getDetails() {
		return requestUser;
	}

	@Override
	public Object getPrincipal() {
		return user.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
