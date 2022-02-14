package br.com.tcc.pucminas.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.pucminas.dto.AuthResponse;
import br.com.tcc.pucminas.security.jwt.JwtUtils;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody Map<String, Object> payload) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(payload.get("username"), payload.get("password")));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		AuthResponse auth = new AuthResponse();
		auth.setUsuario(jwtUtils.getUserNameFromJwtToken(jwt));
		auth.setToken(jwt);
		auth.setPerfis(authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList()));
		

		return ResponseEntity.ok(auth);
	}
		
}