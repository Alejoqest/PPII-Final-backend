package com.ppii.proyectofinal.pelicula.dto;

import com.ppii.proyectofinal.pelicula.FormatoPelicula;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeliculaDetallesDTO {
	private Long id;
	private String nombre;
	private FormatoPelicula formato;
	private String descripcion;
	private int ano;
	private double precio;
	private int stock;
}
