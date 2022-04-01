package br.com.tcc.pucminas.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoProfissional {
	
	MEDICO(1, "Médico"), DENTISTA(2, "Dentista"), PSICOLOGO(3, "Psicólogo");
	
	private Integer codigo;
	private String descricao;
	
}
