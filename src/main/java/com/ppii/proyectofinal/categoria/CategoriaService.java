package com.ppii.proyectofinal.categoria;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> obtenerCategorias() {
		return repository.findAll();
	}
	
	public List<Categoria> buscarCategorias(String categorial, int numPag) {
		Pageable paginaMuestra = PageRequest.of((numPag - 1), 10);
		
		List<String> categoriasAnt = Stream.of(categorial.split(",", -1)).collect(Collectors.toList());
		
		String categoria = categoriasAnt.get((categoriasAnt.size() - 1));
		
		categoriasAnt.remove(categoria);
		
		return repository.findAllByNombreContainingAndNombreNotIn(categoria, categoriasAnt, paginaMuestra);
	}
	
	public void eliminarCategorias() {
		
	}
}
