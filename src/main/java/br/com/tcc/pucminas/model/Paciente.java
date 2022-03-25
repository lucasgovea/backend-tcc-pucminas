package br.com.tcc.pucminas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Where(clause = "deletado = false")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome é obrigatorio")
	private String nome;
	
	@Column(unique=true)
	@CPF(message = "CPF invalido")
	private String cpf;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;
	
	
	@Embedded
	@NotNull(message = "Telefone é obrigatorio")
	@Valid
	private Telefone telefone;
	
	@Email(message = "Email inválido")
	@NotEmpty(message = "Email é obrigatorio")
	private String email;
	
	@JsonIgnore
	private boolean deletado;
	
	@Embedded
	private Endereco endereco;
}

