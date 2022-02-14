package br.com.tcc.pucminas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.pucminas.model.Usuario;
import br.com.tcc.pucminas.security.util.SecurityUtils;
import br.com.tcc.pucminas.service.UsuarioService;

@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Usuario>> list() {
		List<Usuario> usuarios = usuarioService.buscarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
	
	@GetMapping("/{idUsuarioSolicitado}")
	@PreAuthorize("hasAnyAuthority('user', 'admin')")
	public ResponseEntity<?> buscarUsuario(@PathVariable Long idUsuarioSolicitado) {
		if(SecurityUtils.hasRole("admin")) {
			Usuario usuarioSolicitado = usuarioService.buscarPorId(idUsuarioSolicitado);
			return ResponseEntity.status(HttpStatus.OK).body(usuarioSolicitado);
		}else if(SecurityUtils.hasRole("user")) {
			Usuario usuarioLogado = usuarioService.buscarPorLogin(SecurityUtils.getUsuarioSecurity().getUsername());
			if(usuarioLogado.getId().equals(idUsuarioSolicitado)) {
				return ResponseEntity.status(HttpStatus.OK).body(usuarioLogado);
			}
			
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}
}

