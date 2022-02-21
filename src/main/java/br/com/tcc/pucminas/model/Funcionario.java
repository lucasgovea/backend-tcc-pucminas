package br.com.tcc.pucminas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Entity
@Where(clause="deletado = 0")
@Data
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome é obrigatorio")
	private String nome;
	
	@Column(unique=true)
	@CPF(message = "CPF invalido")
	private String cpf;
	
	private String cargo;

	@OneToOne
	private Usuario usuario;
	
	private boolean deletado;
	
	
	
}

