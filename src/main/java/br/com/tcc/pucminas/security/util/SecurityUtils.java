package br.com.tcc.pucminas.security.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.tcc.pucminas.model.UsuarioSecurity;

public class SecurityUtils {

	public static boolean hasRole(String role) {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.map(Authentication::getAuthorities).map(AuthorityUtils::authorityListToSet)
				.filter(permissao -> permissao.contains(role)).isPresent();
	}
	
	public static UsuarioSecurity getUsuarioSecurity() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		return (UsuarioSecurity) principal;
	}
	
}
