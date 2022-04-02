package br.com.tcc.pucminas.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EnumType;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.dto.ProfissionalDTO;
import br.com.tcc.pucminas.dto.ProfissionalFilterDTO;
import br.com.tcc.pucminas.model.Perfil;
import br.com.tcc.pucminas.model.PerfilEnum;
import br.com.tcc.pucminas.model.Profissional;
import br.com.tcc.pucminas.model.TipoProfissional;
import br.com.tcc.pucminas.model.Usuario;
import br.com.tcc.pucminas.repository.PerfilRepository;
import br.com.tcc.pucminas.repository.ProfissionalRepository;
import br.com.tcc.pucminas.service.exception.EntityNotFoundException;
import br.com.tcc.pucminas.specification.ProfissionalSpecification;

@Service
public class ProfissionalService {
	
	private final ProfissionalRepository profissionalRepo;
	private final PerfilRepository perfilRepo;
	
	public ProfissionalService(ProfissionalRepository profissionalRepo,
								PerfilRepository perfilRepo) {
		this.profissionalRepo = profissionalRepo;
		this.perfilRepo = perfilRepo;
	}

	public List<Profissional> buscarTodos() {
		return profissionalRepo.findAll();
	}
	
	public Profissional buscarPorId(Long id) {
		return profissionalRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Profissional nao encontrada pelo id " + id));
	}

	public Profissional inserir(ProfissionalDTO dto) {
		Set<Perfil> perfis = new HashSet<>();
				perfis.add(perfilRepo.findById(PerfilEnum.ADMIN.getId()).orElse(null));
				
		
		Profissional p = Profissional.builder()
			.atuacao(Stream.of(TipoProfissional.values()).filter(e-> e.getCodigo().equals(Integer.parseInt(dto.getTipoProfissional())))
					.findFirst()
					.orElse(null))
			.cpf(dto.getCpf())
			.nome(dto.getNome())
			.usuario(Usuario.builder()
						.login(dto.getCpf())
						.senha(new BCryptPasswordEncoder().encode(dto.getSenha()))
						.perfis(perfis).build()
						)
			.build();
		
		return profissionalRepo.save(p);
	}

	@Transactional
	public void excluir(Long idProfissional) {
		Profissional p = profissionalRepo.findById(idProfissional)
				.orElseThrow(() -> new EntityNotFoundException("Profissional nao encontrada pelo id " + idProfissional));
		p.setDeletado(true);
		profissionalRepo.save(p);
	}

	@Transactional
	public Profissional atualizar(Long idProfissional, ProfissionalDTO dto) {
		Profissional p = profissionalRepo.findById(idProfissional)
				.orElseThrow(() -> new EntityNotFoundException("Profissional nao encontrada pelo id " + idProfissional));
		p.setCpf(dto.getCpf());
		p.setNome(dto.getNome());
		p.setAtuacao(TipoProfissional.valueOf(Integer.valueOf(dto.getTipoProfissional())).orElseThrow(() -> new EntityNotFoundException("Tipo de Profissional não encontrado")));
		p.getUsuario().setLogin(dto.getCpf());
		return p;
	}

	public List<Profissional> buscarFiltranto(ProfissionalFilterDTO dadosBusca) {
		return profissionalRepo.findAll(new ProfissionalSpecification(dadosBusca));
	}
	
	
	
}
