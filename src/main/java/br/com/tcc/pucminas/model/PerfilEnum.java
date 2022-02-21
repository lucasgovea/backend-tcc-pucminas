package br.com.tcc.pucminas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilEnum {

	ADMIN(1L, "admin");
	
	private Long id;
	private String descricao;
	
}
