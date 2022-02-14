package br.com.tcc.pucminas.resources.exception;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class StandardError {

	private ZonedDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
}
