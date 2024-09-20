package com.ppii.proyectofinal.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/describir/lista")
	public List<Categoria> getAllCategorias() {
		return service.obtenerCategorias();
	}
	
	@GetMapping("/buscar/{categorias}")
	public List<Categoria> getCategoriasBusqueda(String categorias) {
		return service.buscarCategorias(categorias, 1);
	}
	
	@DeleteMapping({"/eliminar/{id}",
		"/eliminar/{nombre}"})
	public void deleteCategoria(Long id, String nombre) {
		service.eliminarCategorias();
	}
	
}
