package br.com.tcc.pucminas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.pucminas.model.Funcionario;
import br.com.tcc.pucminas.service.FuncionarioService;

@RequestMapping("/api/funcionarios")
@RestController
public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Funcionario>> listarFuncionarios() {
		List<Funcionario> funcionarios = funcionarioService.buscarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
	}
	
	@GetMapping("/{idFuncionario}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long idFuncionario) {
		Funcionario funcionario = funcionarioService.buscarPorId(idFuncionario);
		return ResponseEntity.status(HttpStatus.OK).body(funcionario);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Funcionario> inserir(@RequestBody @Valid Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.inserir(funcionario));
	}
	
	@DeleteMapping("/{idFuncionario}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Void> excluir(@PathVariable Long idFuncionario) {
		funcionarioService.excluir(idFuncionario);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/{idFuncionario}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long idFuncionario, @RequestBody @Valid Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(funcionarioService.atualizar(idFuncionario, funcionario));
	}
	
	
}

