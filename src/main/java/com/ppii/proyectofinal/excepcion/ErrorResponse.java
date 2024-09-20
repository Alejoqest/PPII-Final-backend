package com.ppii.proyectofinal.excepcion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private String mensaje;
	private String detalles;
}
