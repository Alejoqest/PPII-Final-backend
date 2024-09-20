package com.ppii.proyectofinal.pelicula.dto;

import com.ppii.proyectofinal.pelicula.FormatoPelicula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO que se usa cuando para mostrar resultados de la busqueda de una pelicula;
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaBusquedaDTO {
	
	private long id;
	
	private String nombre;
		
	private double precio;
	
	private FormatoPelicula formato;
	
}
