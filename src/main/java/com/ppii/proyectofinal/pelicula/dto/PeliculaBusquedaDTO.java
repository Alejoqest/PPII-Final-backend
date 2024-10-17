package com.ppii.proyectofinal.pelicula.dto;

import com.ppii.proyectofinal.pelicula.FormatoPelicula;

import lombok.Builder;
import lombok.Data;

/*
 * DTO que se usa cuando para mostrar resultados de la busqueda de una pelicula;
 */

@Data
@Builder
public class PeliculaBusquedaDTO {
	private long id;
	private String nombre;
	private double precio;
	private int ano;
	private FormatoPelicula formato;
}
