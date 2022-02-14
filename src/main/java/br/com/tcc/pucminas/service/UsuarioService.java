package br.com.tcc.pucminas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.model.Usuario;
import br.com.tcc.pucminas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	public List<Usuario> buscarTodos() {
		return usuarioRepo.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepo.findById(id).orElse(null);
	}
	
	public Usuario buscarPorLogin(String login) {
		return usuarioRepo.findByLogin(login).orElse(null);
	}
}
