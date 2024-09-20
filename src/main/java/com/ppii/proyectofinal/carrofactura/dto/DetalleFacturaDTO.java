package com.ppii.proyectofinal.carrofactura.dto;

import com.ppii.proyectofinal.pelicula.FormatoPelicula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleFacturaDTO {
	private Long id;
	private Long peliculaId;
	private String peliculaNombre;
	private FormatoPelicula peliculaFormato;
	private int unidades;
	private double subTotal;
}
