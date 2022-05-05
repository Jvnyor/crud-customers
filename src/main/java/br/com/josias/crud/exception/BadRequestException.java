package br.com.josias.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(HttpStatus status, String reason) {
		super(status, reason);
	}

}
