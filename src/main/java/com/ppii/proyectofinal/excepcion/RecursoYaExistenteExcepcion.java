package com.ppii.proyectofinal.excepcion;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class RecursoYaExistenteExcepcion extends RuntimeException {
	private String objetoNombre;
	
	private String campoNombre;
	
	private String campoValor;

	public RecursoYaExistenteExcepcion(String objetoNombre, String campoNombre, String campoValor) {
		super(String.format("Ya existe un registro de %s con %s : %s", objetoNombre, campoNombre, campoValor));
		this.objetoNombre = objetoNombre;
		this.campoNombre = campoNombre;
		this.campoValor = campoValor;
	}
	
}
