package br.com.tcc.pucminas.service.exception;

public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -3613746668926768673L;
	
	public EntityNotFoundException(String msg) {
		super(msg);
	}

}
