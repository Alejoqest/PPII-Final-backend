package com.ppii.proyectofinal.excepcion;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class RecursoNoEncontradoExcepcion extends RuntimeException {
	
	private String objetoNombre; 
	
	private String campoNombre;
	
	private String campoValor;

	public RecursoNoEncontradoExcepcion(String objetoNombre, String campoNombre, String campoValor) {
		super(String.format("%s no encontrado con %s : %s", objetoNombre, campoNombre, campoValor));
		this.objetoNombre = objetoNombre;
		this.campoNombre = campoNombre;
		this.campoValor = campoValor;
	}
}