package com.ppii.proyectofinal.usuario.dto;

import lombok.Data;

@Data
public class UsuarioContraseñaDTO {
	private Long id;
	private String Contrasena;
	private String viejaContrasena;
}
