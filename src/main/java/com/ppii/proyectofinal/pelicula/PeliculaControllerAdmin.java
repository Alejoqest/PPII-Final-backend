package com.ppii.proyectofinal.pelicula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ppii.proyectofinal.pelicula.dto.PeliculaBusquedaDTO;
import com.ppii.proyectofinal.pelicula.dto.PeliculaCreacionDTO;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/v1/pelicula/admin")
public class PeliculaControllerAdmin {
	@Autowired
	private PeliculaMapper mapper;
	@Autowired
	private PeliculaServiceAdmin service;

	@PostMapping(value = "/publicar", produces = "application/json")
	public ResponseEntity<PeliculaBusquedaDTO> postPelicula(
			@RequestPart(name = "data", required = true) PeliculaCreacionDTO pelicula, 
			@RequestPart(name = "image", required = false) MultipartFile portada
			) {
		Pelicula resultado = service.generarPelicula(mapper.toEntity(pelicula), portada);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toBusqueda(resultado));
	}

	@PutMapping(value = "/actualizar", produces = "application/json")
	public ResponseEntity<PeliculaBusquedaDTO> putPelicula(
			@RequestPart(name = "data", required = true) PeliculaCreacionDTO pelicula, 
			@RequestPart(name = "image", required = false) MultipartFile portada
			) {
		Pelicula resultado = service.actualizarPelicula(mapper.toEntity(pelicula), portada);
		return ResponseEntity.ok(this.mapper.toBusqueda(resultado));
	}
	
	@DeleteMapping(value = "/eleminar/{id}")
	public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
		service.eleminarPelicula(id);
		return ResponseEntity.noContent().build();
	}
	
}
