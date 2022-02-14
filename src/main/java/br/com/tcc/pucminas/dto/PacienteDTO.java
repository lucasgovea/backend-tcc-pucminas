package br.com.tcc.pucminas.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {

	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	
}
