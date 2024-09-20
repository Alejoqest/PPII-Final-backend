package com.ppii.proyectofinal;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppii.proyectofinal.usuario.dto.UsuarioDatosDTO;
public class JsonTest {
	@Test
	public void test() {
		UsuarioDatosDTO usuario = UsuarioDatosDTO.builder()
				.nombre("Alejo")
				.contrasena("hello")
				.build();
	
		try {
			String result = new ObjectMapper()
					.writeValueAsString(usuario);
			System.out.println(result);
			System.out.println("\nContraseña = "+ usuario.getContrasena());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}


