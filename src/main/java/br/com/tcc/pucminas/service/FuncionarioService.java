package br.com.tcc.pucminas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.model.Funcionario;
import br.com.tcc.pucminas.repository.FuncionarioRepository;
import br.com.tcc.pucminas.service.exception.EntityNotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository funcionarioRepo;
	
	public List<Funcionario> buscarTodos() {
		return funcionarioRepo.findAll();
	}
	
	public Funcionario buscarPorId(Long id) {
		return funcionarioRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada pelo id " + id));
	}

	public Funcionario inserir(Funcionario paciente) {
		return funcionarioRepo.save(paciente);
	}

	@Transactional
	public void excluir(Long idFuncionario) {
		Funcionario f = funcionarioRepo.findById(idFuncionario)
				.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada pelo id " + idFuncionario));
		f.setDeletado(true);
		funcionarioRepo.save(f);
	}

	@Transactional
	public Funcionario atualizar(Long idFuncionario, Funcionario funcionario) {
		Funcionario f = funcionarioRepo.findById(idFuncionario)
				.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada pelo id " + idFuncionario));
		funcionario.setId(f.getId());
		return funcionarioRepo.save(funcionario);
	}
	
	
}
