package com.ppii.proyectofinal.carrofactura.dto;

import com.ppii.proyectofinal.pelicula.FormatoPelicula;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleFacturaDTO {
	private Long id;
	private Long peliculaId;
	private String peliculaNombre;
	private FormatoPelicula peliculaFormato;
	private int unidades;
	private double subTotal;
}
