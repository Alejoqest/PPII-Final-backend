package com.ppii.proyectofinal.pelicula;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
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
	
	/*public List<Pelicula> findAllByNombreContainingAndCategoriasId(String nombre, Long categoriaId, Pageable pageable);
	
	public List<Pelicula> findAllByNombreContainingAndFormato(String nombre, FormatoPelicula formato, Pageable pageable);
	
	public List<Pelicula> findAllByNombreContainingAndFormatoAndCategoriasId(String nombre, FormatoPelicula formato, Long categoriaId, Pageable pageable);*/
		
	// Separador de los metodos de busqueda con stock disponible
	
	public List<Pelicula> findAllByNombreContainingAndStockGreaterThanEqual(String nombre, int stock, Pageable pageable);

	public List<Pelicula> findAllByNombreContainingAndCategoriasIdAndStockGreaterThanEqual(String nombre, Long categoriaId, int stock, Pageable pageable);

	public List<Pelicula> findAllByNombreContainingAndFormatoAndStockGreaterThanEqual(String nombre, FormatoPelicula formato, int stock, Pageable pageable);

	/**
	 * Encontrar una {@link Pelicula} por el nombre, formato, categoria, y stock disponible (paginable).
	 * 
	 * @param nombre Se fija si el nombre de una pelicula contiene la candena de texto ingresada ignorando mayusculas.
	 * @param formato Se busca si hay una pelicula que tenga el formato deseado.
	 * @param categoriaId Si hay una 
	 * @param stock Se va a buscar una pelicula donde el stock sea mayor que el parametro ingresado
	 */
	public List<Pelicula> findAllByNombreContainingAndFormatoAndCategoriasIdAndStockGreaterThanEqual(String nombre, FormatoPelicula formato, Long categoriaId, int stock, Pageable pageable);
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContaining(int stock, String nombre, Pageable pageable);
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndFormato(int stock, String nombre, FormatoPelicula formato, Pageable pageable);
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndCategoriasId(int stock, String nombre, Long categoriaId, Pageable pageable);
	
	public List<Pelicula> findAllByStockGreaterThanEqualAndNombreIgnoreCaseContainingAndFormatoAndCategoriasId(int stock, String nombre, FormatoPelicula formato, Long categoriaId, Pageable pageable);
	/**
	 * Devuelve una {@link List} de elementos de {@link Pelicula}, cuyos valores coincidan con los parametros de busqueda, con la restriccion puesta por el objecto {@link Pageable}.
	 * 
	 * @param nombre 
	 * @param stock
	 * @param formato
	 * @param categoriaId
	 * @param pageable
	 * @return
	 */
	public List<Pelicula> findAllByNombreContainingIgnoreCaseAndStockGreaterThanEqualAndFormatoAndCategoriasId(@Nullable String nombre, int stock, @Nullable FormatoPelicula formato, @Nullable Long categoriaId, Pageable pageable);
	
	@Query("SELECT p FROM Pelicula p JOIN p.categorias c "
			+ "WHERE (p.stock >= :stock) AND "
			+ "(:nombre IS NULL OR UPPER(p.nombre) LIKE UPPER(%:nombre%)) AND "
			+ "(:formato IS NULL OR p.formato = :formato) AND "
			+ "(:categoriaId IS NULL OR c.id = :categoriaId)")
	public List<Pelicula> findAll(
			@Param("stock") int stock, 
			@Param("nombre") String nombre, 
			@Param("formato") FormatoPelicula formato, 
			@Param("categoriaId") Long categoriaId, 
			Pageable pageable);
	
	@Query("SELECT COUNT(p) FROM Pelicula p JOIN p.categorias c "
			+ "WHERE (p.stock >= :stock) AND "
			+ "(:nombre IS NULL OR UPPER(p.nombre) LIKE UPPER(%:nombre%)) AND "
			+ "(:formato IS NULL OR p.formato = :formato) AND "
			+ "(:categoriaId IS NULL OR c.id = :categoriaId)")
	public Long countAll(
			@Param("stock") int stock, 
			@Param("nombre") String nombre, 
			@Param("formato") FormatoPelicula formato, 
			@Param("categoriaId") Long categoriaId);

	
	/**
	 * 
	 * @param nombre
	 * @param stock
	 * @param formato
	 * @param categoriaId
	 * @return
	 */
	public long countByNombreContainingAndStockGreaterThanEqualAndFormatoAndCategoriasId(String nombre, int stock, @Nullable FormatoPelicula formato, @Nullable Long categoriaId);
	
	
}
