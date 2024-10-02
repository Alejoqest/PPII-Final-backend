package com.ppii.proyectofinal.pelicula;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

/**
 * Repositorio de {@link Pelicula} que extiende a {@link JpaRepository}
 * 
 * @author Alejo Quevedo
 */
@Repository
@Transactional
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
	
	public boolean existsByNombreAndFormato(String nombre, FormatoPelicula formato);
	
	public Pelicula findByNombreAndFormato(String nombre, FormatoPelicula formato);
	
	public boolean existsByIdAndStockGreaterThanEqual(Long id, int stock);
	
	/**
	 * Encuentra una {@link Pelicula} por su nombre, ignorando mayusculas, y formato que esta poseea.  
	 * 
	 * @param nombre no debe ser {@literal null}
	 * @param formato no debe ser {@literal null}
	 * @return La pelicula que cumpla con los parametros o {@literal null} si no se encuentra nada
	 */
	public Pelicula findByNombreIgnoreCaseAndFormato(String nombre, FormatoPelicula formato);
	
	public List<Pelicula> findAllByCategoriasId(String categoria, Pageable pageable);
	
	public List<Pelicula> findAllByNombreContaining(String nombre, Pageable pageable);
	
	public List<Pelicula> findTop5ByOrderByIdDesc();
	
	/*public List<Pelicula> findAllByNombreContainingAndStockGreaterThanEqual(String nombre, int stock, Pageable pageable);

	public List<Pelicula> findAllByNombreContainingAndCategoriasIdAndStockGreaterThanEqual(String nombre, Long categoriaId, int stock, Pageable pageable);

	public List<Pelicula> findAllByNombreContainingAndFormatoAndStockGreaterThanEqual(String nombre, FormatoPelicula formato, int stock, Pageable pageable);*/
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContaining(int stock, String nombre, Pageable pageable);
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndFormato(int stock, String nombre, FormatoPelicula formato, Pageable pageable);
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndCategoriasId(int stock, String nombre, Long categoriaId, Pageable pageable);
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndFormatoAndCategoriasId(int stock, String nombre, FormatoPelicula formato, Long categoriaId, Pageable pageable);
		
	public long countByStockGreaterThanEqualAndNombreIgnoreCaseContaining(int stock, String nombre);
	
	public long countByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndFormato(int stock, String nombre, FormatoPelicula formato);
	
	public long countByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndCategoriasId(int stock, String nombre, Long categoriaId);
	
	public long countByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndFormatoAndCategoriasId(int stock, String nombre, FormatoPelicula formato, Long categoriaId);
	
}
