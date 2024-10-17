package com.ppii.proyectofinal.carrofactura;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ppii.proyectofinal.carrofactura.dto.CarroComprasDTO;
import com.ppii.proyectofinal.carrofactura.dto.DetalleFacturaDTO;
import com.ppii.proyectofinal.carrofactura.dto.ElementoCarroDTO;
import com.ppii.proyectofinal.carrofactura.dto.FacturaDTO;
import com.ppii.proyectofinal.pelicula.Pelicula;

@Component
public class CarroFacturaMapper {
	public Factura aFactura(CarroCompras carro) {
		Factura factura = Factura.builder()
				.usuario(carro.getUsuario())
				.precioTotal(carro.getPrecioTotal())
				.fechaFactura(LocalDate.now())
				.build();
		
		factura.setDetalles(carro.getElementos().stream()
				.map(elemento -> this.aDetalleFactura(elemento))
				.toList());
		
		return factura;
	}
	
	public DetalleFactura aDetalleFactura(ElementoCarro elemento) {
		DetalleFactura detalle = DetalleFactura.builder()
				.pelicula(elemento.getPelicula())
				.unidades(elemento.getUnidades())
				.subTotal(elemento.getSubTotal())
				.build();
		return detalle;
	}
	
	public DetalleFactura DTOaDetalleFactura(DetalleFacturaDTO dto) {
		DetalleFactura detalle = DetalleFactura.builder()
				.unidades(dto.getUnidades())
				.subTotal(dto.getSubTotal())
				.build();
		
		Pelicula pelicula = Pelicula.builder()
				.id(dto.getPeliculaId())
				.nombre(dto.getPeliculaNombre())
				.formato(dto.getPeliculaFormato())
				.build();
		
		pelicula.setCategorias(null);
		
		detalle.setPelicula(pelicula);
		
		return detalle;
	}
	
	public CarroComprasDTO aCarroComprasDTO(CarroCompras c) {
		return CarroComprasDTO.builder()
				.id(c.getId())
				.precioTotal(c.getPrecioTotal())
				.cantidadDeElementos(c.getElementos().size())
				.build();
	}
	
	public FacturaDTO aFacturaDTO(Factura f) {
		return FacturaDTO.builder()
				.id(f.getId())
				.precioTotal(f.getPrecioTotal())
				.fechaFactura(f.getFechaFactura())
				.cantidadDeDetalles(f.getDetalles().size())
				.build();
	}
	
	public DetalleFacturaDTO aDetalleFacturaDTO(DetalleFactura detalle) {
		DetalleFacturaDTO dto = DetalleFacturaDTO.builder()
				.id(detalle.getId())
				.peliculaId(detalle.getPelicula().getId())
				.peliculaNombre(detalle.getPelicula().getNombre())
				.peliculaFormato(detalle.getPelicula().getFormato())
				.unidades(detalle.getUnidades())
				.subTotal(detalle.getSubTotal())
				.build();
		return dto;
	}

	public ElementoCarroDTO aElementoCarroDTO(ElementoCarro e) {
		return ElementoCarroDTO.builder()
				.id(e.getId())
				.peliculaId(e.getPelicula().getId())
				.peliculaNombre(e.getPelicula().getNombre())
				.peliculaFormato(e.getPelicula().getFormato())
				.unidades(e.getUnidades())
				.subTotal(e.getSubTotal())
				.build();
	}
	
	public ElementoCarro DTOaElementoCarro(ElementoCarroDTO e) {
		ElementoCarro elemento = ElementoCarro.builder()
				.unidades(e.getUnidades())
				.subTotal(e.getSubTotal())
				.build();
		
		Pelicula pelicula = Pelicula.builder()
				.id(e.getPeliculaId())
				.nombre(e.getPeliculaNombre())
				.formato(e.getPeliculaFormato())
				.build();
		
		pelicula.setCategorias(null);
		
		elemento.setPelicula(pelicula);
		
		return elemento;
	}
}
