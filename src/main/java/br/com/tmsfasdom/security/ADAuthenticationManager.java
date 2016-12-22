package br.com.tmsfasdom.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.tmsfasdom.model.RetornoAD;



public class ADAuthenticationManager implements AuthenticationManager{

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
        String password = auth.getCredentials().toString();
        AuxAD ldapContxCrtn = new AuxAD();
		RetornoAD retornoAD = ldapContxCrtn.validaUsuarioAD("10.61.236.250", username, password, "cmdo.policiamilitar.sp.gov.br");
		if(retornoAD.getMensagem() == AuxAD.SUCESSO)
		{       
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(retornoAD.getPrimeiroNome(), password, grantedAuths);
		}
		else
		{
			throw new AuthenticationCredentialsNotFoundException(retornoAD.getMensagem());
		}
		
	}

}
