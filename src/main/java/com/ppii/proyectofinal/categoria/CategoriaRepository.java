package com.ppii.proyectofinal.categoria;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	boolean existsByNombreIgnoreCase(String nombre);
	
	List<Categoria> findAllByNombreIgnoreCase(List<String> nombre);

	List<Categoria> findAllByNombreIgnoreCaseContaining(String nombre, Pageable pageable);
	
	List<Categoria> findAllByNombreContainingAndNombreNotIn(String nombre, List<String> nombres, Pageable pageable);
}
