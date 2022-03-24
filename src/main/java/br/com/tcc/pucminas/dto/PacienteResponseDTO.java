package br.com.tcc.pucminas.dto;

import br.com.tcc.pucminas.model.Agendamento;

public class PacienteResponseDTO {

	private Long id;
	private String nome;
	private String cpf;
	private Agendamento proximoAgendamento;
}
