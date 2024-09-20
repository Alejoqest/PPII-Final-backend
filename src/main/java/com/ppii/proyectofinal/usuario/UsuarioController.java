package com.ppii.proyectofinal.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ppii.proyectofinal.usuario.dto.UsuarioBusquedaDTO;
import com.ppii.proyectofinal.usuario.dto.UsuarioDatosDTO;

@RestController
@RequestMapping("api/usuario/")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioMapper mapper;
	
	@GetMapping("mostrar/pagina/{numPag}")
	public List<UsuarioBusquedaDTO> getUsuariosBusqueda(int numPag) {
		List<Usuario> usuarios = service.cargarUsuarios(numPag);
		return usuarios.stream().map(u -> mapper.aBusqueda(u)).toList();
	}
	
	@GetMapping("mostrar/pagina")
	public List<UsuarioBusquedaDTO> getUsuarioBusquedaParam(
			@RequestParam int numPag, 
			@RequestParam(required = false) String string, 
			@RequestParam(required = false) RolUsuario rol) {
		List<Usuario> usuarios = service.cargarPaginaUsuario(numPag, string, rol);
		return usuarios.stream().map(u -> mapper.aBusqueda(u)).toList();
	}
	
	@GetMapping("mostrar/{id}")
	public UsuarioDatosDTO getUsuarioDetalles(@PathVariable Long id) {
		Usuario usuario = service.cargarUsuarioPorId(id);
		return mapper.aDatosSinContraseña(usuario);
	}
	
	@PutMapping("actualizar/")
	public ResponseEntity<UsuarioBusquedaDTO> putUsuario(@RequestBody UsuarioDatosDTO intento) {
		Usuario usuario = service.actualizarUsuario(mapper.deDatosAUsuario(intento));
		return ResponseEntity.ok(mapper.aBusqueda(usuario));
	}
	
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		service.eliminarUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
}
