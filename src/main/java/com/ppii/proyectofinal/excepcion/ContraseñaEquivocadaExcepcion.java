package com.ppii.proyectofinal.excepcion;

@SuppressWarnings("serial")
public class ContraseñaEquivocadaExcepcion extends RuntimeException {

	public ContraseñaEquivocadaExcepcion() {
		super("La contraseña ingresada no es correcta.");
	}
	
}
