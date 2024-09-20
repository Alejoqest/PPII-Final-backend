package com.ppii.proyectofinal.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecursoNoEncontradoExcepcion.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handleRecursoNoEncontradoExcepcion(RecursoNoEncontradoExcepcion excepcion, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(excepcion.getMessage(), webRequest.getDescription(false)); 
		return errorResponse;
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handleNullPointer(NullPointerException excepcion, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(excepcion.getMessage(), webRequest.getDescription(false)); 
		return errorResponse;
	}
	
	@ExceptionHandler(RecursoYaExistenteExcepcion.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorResponse handleRecursoYaExistenteExcepcion(RecursoYaExistenteExcepcion excepcion, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(excepcion.getMessage(), webRequest.getDescription(false));
		return errorResponse;
	}
	
	@ExceptionHandler(RecursoInsuficienteExcepcion.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorResponse handleRecursoInsuficienteExcepcion(RecursoInsuficienteExcepcion excepcion, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(excepcion.getMessage(), webRequest.getDescription(false));
		return errorResponse;
	}
	
	@ExceptionHandler(ContraseñaEquivocadaExcepcion.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ErrorResponse handleContraseñaEquivocadaExcepcion(ContraseñaEquivocadaExcepcion excepcion, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(excepcion.getMessage(), webRequest.getDescription(false));
		return errorResponse;
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleGlobalExpeption(Exception excepcion, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(excepcion.getMessage(), webRequest.getDescription(false));
		return errorResponse;
	}

}
