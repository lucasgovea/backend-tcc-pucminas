package br.com.tcc.pucminas.resources.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ValidationError extends StandardError{

	Map<String, String> errors = new HashMap<>();
	
}
