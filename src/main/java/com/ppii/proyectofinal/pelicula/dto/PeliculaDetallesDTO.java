package com.ppii.proyectofinal.pelicula.dto;

import com.ppii.proyectofinal.pelicula.FormatoPelicula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaDetallesDTO {
	private Long id;
	private String nombre;
	private FormatoPelicula formato;
	private String descripcion;
	private double precio;
	private int stock;
}
