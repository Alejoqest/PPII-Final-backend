package com.ppii.proyectofinal.excepcion;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class RecursoInsuficienteExcepcion extends RuntimeException {
	private String campoNombre;
	
	private String campoValor;
	
	private String operacion;
	
	private String valorSuficiente;

	public RecursoInsuficienteExcepcion(String campoNombre, String campoValor, String operacion, String valorSuficiente) {
		super(String.format("El valor de %s es %s y debe ser %s a %s.", campoNombre, campoValor, operacion, valorSuficiente));
		this.campoNombre = campoNombre;
		this.campoValor = campoValor;
		this.operacion = operacion;
		this.valorSuficiente = valorSuficiente;
	}
	
	public RecursoInsuficienteExcepcion(String campoNombre, String operacion) {
		super(String.format("El valor de %s es insuficiente porque es %s a lo que se necesita.", campoNombre, operacion));
		this.campoNombre = campoNombre;
		this.operacion = operacion;
	}

}
