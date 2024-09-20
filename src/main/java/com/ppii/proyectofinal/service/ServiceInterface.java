package com.ppii.proyectofinal.service;

import org.springframework.web.multipart.MultipartFile;

public interface ServiceInterface {

	/**
	 * 
	 * @param nombre
	 * @param imagen
	 * @return
	 */
	String agregarImagen(String nombre, MultipartFile imagen);
	
	/**
	 * 
	 * @param imagenDir
	 */
	void eliminarImagen(String imagenDir);
}
