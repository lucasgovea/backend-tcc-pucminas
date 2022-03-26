package br.com.tcc.pucminas.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalDTO {

	@NotBlank(message="Nome é obrigatorio")
	private String nome;
	
	@CPF(message = "CPF invalido")
	private String cpf;
	
	@NotBlank(message="TipoProfissional é obrigatorio")
	private String tipoProfissional;

	@NotBlank(message="Senha é obrigatorio")
	private String senha;
	
}
