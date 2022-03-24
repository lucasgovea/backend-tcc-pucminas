package br.com.tcc.pucminas.service.exception;

public class ConfirmacaoEmailException extends RuntimeException{

	private static final long serialVersionUID = 8403911788414821250L;

	public ConfirmacaoEmailException(String msg) {
		super(msg);
	}

}
