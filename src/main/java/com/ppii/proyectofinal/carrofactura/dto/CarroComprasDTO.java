package com.ppii.proyectofinal.carrofactura.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarroComprasDTO {
	private Long id;
	private double precioTotal;
	private int cantidadDeElementos;
}
