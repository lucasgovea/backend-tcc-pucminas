package br.com.tcc.pucminas.resources;

import java.time.ZonedDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.tcc.pucminas.resources.exception.StandardError;
import br.com.tcc.pucminas.resources.exception.ValidationError;
import br.com.tcc.pucminas.service.exception.ConfirmacaoEmailException;
import br.com.tcc.pucminas.service.exception.EntityNotFoundException;
import br.com.tcc.pucminas.service.exception.HorarioIndisponivelException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimestamp(ZonedDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setError("Entidade nao encontrada");
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError error = new ValidationError();
		error.setTimestamp(ZonedDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Falha ao validar campos");
		error.setError("Entidade Invalida");
		error.setPath(request.getRequestURI());
		
		List<FieldError> fieldsErrors = e.getBindingResult().getFieldErrors();
		fieldsErrors.forEach(r -> {
			error.getErrors().put(r.getField(), r.getDefaultMessage());
		});
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> duplicateEmailException(DataIntegrityViolationException e, HttpServletRequest request) {
		
		StandardError error = new StandardError();
		error.setTimestamp(ZonedDateTime.now());
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage("Restricao Violada");
		error.setError("Restricao Violada");
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		
    }
	
	@ExceptionHandler(HorarioIndisponivelException.class)
	public ResponseEntity<StandardError> handleHorarioIndisponivelException(HorarioIndisponivelException e, HttpServletRequest request) {
		
		StandardError error = new StandardError();
		error.setTimestamp(ZonedDateTime.now());
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(e.getMessage());
		error.setError("Horario Indisponivel");
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		
	}
	
	@ExceptionHandler(ConfirmacaoEmailException.class)
	public ResponseEntity<StandardError> handleConfirmacaoEmailException(ConfirmacaoEmailException e, HttpServletRequest request) {
		
		StandardError error = new StandardError();
		error.setTimestamp(ZonedDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setError("Falha ao enviar email");
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
}
