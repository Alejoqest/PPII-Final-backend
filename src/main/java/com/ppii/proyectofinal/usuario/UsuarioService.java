package com.ppii.proyectofinal.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ppii.proyectofinal.carrofactura.CarroCompras;
import com.ppii.proyectofinal.excepcion.ContraseñaEquivocadaExcepcion;
import com.ppii.proyectofinal.excepcion.RecursoNoEncontradoExcepcion;
import com.ppii.proyectofinal.excepcion.RecursoYaExistenteExcepcion;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	public Usuario cargarUsuarioPorEmail(String email) {
		return repo.findByEmail(email).orElseThrow(() -> 
			new RecursoNoEncontradoExcepcion("Usuario", "email", email)
		);
	}
	
	public Usuario cargarUsuarioPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> 
			new RecursoNoEncontradoExcepcion("Usuario", "id", Long.toString(id))
		);
	}
	
	public List<Usuario> cargarUsuarios(int numPag) {
		Pageable paginaMuestra = PageRequest.of((numPag - 1), 16);
		return repo.findAll(paginaMuestra).getContent();
	}
	
	public List<Usuario> cargarPaginaUsuario(int numPag, String string, RolUsuario rol) {
		Pageable paginaMuestra = PageRequest.of((numPag - 1), 16);
		return repo.findAllByNombreContainingOrApellidoContainingOrEmailContainingOrApodoContainingAndRol(string, string, string, string, rol, paginaMuestra);
	}

	public void registrarUsuario(Usuario intento) {
		if (intento.getEmail() == null || repo.existsByEmail(intento.getEmail())) {
			throw new RecursoYaExistenteExcepcion("Usuario", "email", intento.getEmail());
		}
		
		if (intento.getContraseña() == null) throw new NullPointerException("Falta contraseña");
		
		intento.setContraseña(this.cifrarContraseña(intento.getContraseña()));
		
		intento.setCarro(CarroCompras.builder()
				.usuario(intento)
				.precioTotal(0)
				.build());
		
		intento.setDinero(0);
		
		repo.save(intento);
	}
	
	public Usuario actualizarUsuario(Usuario usuario) {
		if (usuario.getId() == null || !repo.existsById(usuario.getId())) {
			throw new RecursoNoEncontradoExcepcion("Usuario", "id", Long.toString(usuario.getId()));
		}
		
		Usuario oldInfo = this.cargarUsuarioPorId(usuario.getId());
		
		if (usuario.getContraseña() != null) {
			usuario.setContraseña(this.cifrarContraseña(usuario.getContraseña()));
		}
		
		if (!oldInfo.getEmail().equals(usuario.getEmail()) && repo.existsByEmail(usuario.getEmail())) 
			throw new RecursoYaExistenteExcepcion("Usuario", "email", usuario.getEmail());
		
		repo.save(usuario);
		
		return this.cargarUsuarioPorEmail(usuario.getEmail());
	}
	
	public void iniciarSesion(Usuario intento) {
		if ((intento.getEmail() == null || intento.getContraseña() == null) || !repo.existsByEmail(intento.getEmail())) {
			throw new RecursoNoEncontradoExcepcion("Usuario", "email", intento.getEmail());
		}
		
		Usuario user = repo.findByEmail(intento.getEmail()).get();
		
		if (!passEncoder.matches(intento.getContraseña(), user.getContraseña())) 
			throw new ContraseñaEquivocadaExcepcion();
		
		intento.setContraseña(cifrarContraseña(intento.getContraseña()));
	}
	
	public Usuario cambiarContraseña(Long id, String vieja, String nueva) {
		if (id == null || vieja == null || nueva == null || !repo.existsById(id)) {
			throw new RecursoNoEncontradoExcepcion("Usuario", "id", Long.toString(id));
		}
		
		Usuario usuario = repo.findById(id).get();
		
		if (!passEncoder.matches(vieja, usuario.getContraseña())) throw new ContraseñaEquivocadaExcepcion();
		
		usuario.setContraseña(cifrarContraseña(nueva));
		
		repo.save(usuario);
		
		return this.cargarUsuarioPorEmail(usuario.getEmail());
	}
	
	public Usuario ModificarDinero(Usuario usuario) {
		repo.save(usuario);
		return repo.findById(usuario.getId()).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Usuario", "id", Long.toString(usuario.getId())));
	}
	
	public void eliminarUsuario(Long id) {
		if (!repo.existsById(id)) throw new RecursoNoEncontradoExcepcion("Usuario", "id", Long.toString(id));
		repo.deleteById(id);
	}
	
	private String cifrarContraseña(String contraseña) {
		return passEncoder.encode(contraseña);
	}
	
}
