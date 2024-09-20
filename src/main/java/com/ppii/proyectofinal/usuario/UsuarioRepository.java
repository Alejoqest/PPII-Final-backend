package com.ppii.proyectofinal.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	boolean existsByNombre(String nombre);
	boolean existsByIdOrEmail(@Nullable Long id, @Nullable String email);
	Optional<Usuario> findByNombre(String nombre);
	Optional<Usuario> findByEmail(String nombre);
	boolean existsByEmail(String email);
	void deleteByIdOrEmail(@Nullable Long id, @Nullable String email);
	
	/**
	 * 
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param apodo
	 * @param rol
	 * @param pageable
	 * @return
	 */
	List<Usuario> findAllByNombreContainingOrApellidoContainingOrEmailContainingOrApodoContainingAndRol(
			@Nullable String nombre, 
			@Nullable String apellido, 
			@Nullable String email, 
			@Nullable String apodo, 
			@Nullable RolUsuario rol, 
			Pageable pageable
	);
}
