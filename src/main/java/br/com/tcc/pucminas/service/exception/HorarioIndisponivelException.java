package br.com.tcc.pucminas.service.exception;

public class HorarioIndisponivelException extends RuntimeException{

	private static final long serialVersionUID = -3504630148475171463L;

	public HorarioIndisponivelException(String msg) {
		super(msg);
	}

}
