package com.ppii.proyectofinal.pelicula.dto;

import java.util.List;

import com.ppii.proyectofinal.categoria.Categoria;
import com.ppii.proyectofinal.pelicula.FormatoPelicula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaCreacionDTO {
	private long id;
	private String nombre;
	private int stock;
	private double precio;
	private String descripcion;
	private FormatoPelicula formato;
	private List<Categoria> categorias;
}