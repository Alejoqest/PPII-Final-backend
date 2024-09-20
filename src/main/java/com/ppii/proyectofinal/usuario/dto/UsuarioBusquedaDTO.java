package com.ppii.proyectofinal.usuario.dto;

import com.ppii.proyectofinal.usuario.RolUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioBusquedaDTO {
	private Long id;
	private String apodo;
	private String email;
	private RolUsuario rol;
}
