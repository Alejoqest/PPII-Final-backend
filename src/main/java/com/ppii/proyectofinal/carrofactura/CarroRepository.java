package com.ppii.proyectofinal.carrofactura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CarroRepository extends JpaRepository<CarroCompras, Long> {

	CarroCompras findByUsuarioId(Long UsuarioId);
}
