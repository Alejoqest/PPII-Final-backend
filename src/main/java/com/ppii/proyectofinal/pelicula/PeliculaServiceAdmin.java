package com.ppii.proyectofinal.pelicula;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ppii.proyectofinal.categoria.Categoria;
import com.ppii.proyectofinal.categoria.CategoriaRepository;
import com.ppii.proyectofinal.excepcion.RecursoNoEncontradoExcepcion;
import com.ppii.proyectofinal.excepcion.RecursoYaExistenteExcepcion;
import com.ppii.proyectofinal.service.ServiceInterface;

@Service
public class PeliculaServiceAdmin implements ServiceInterface {
	
	private static final String DIR_PATH = "C:/Users/Bangho/EPP/ProyectoFinal/proyecto-final/public/pelicula/";
	
	//private static final String DIR_PATH = System.getProperty("user.dir").replace("\\", "/") + "/public/img/";
	
	private static final String NOT_FOUND = "NOTFOUND.jpg";
	
	@Autowired
	private PeliculaRepository pRepository;
	
	@Autowired
	private CategoriaRepository cRepository;
	
	public Pelicula generarPelicula(Pelicula pelicula, MultipartFile portada) {
		if (pelicula.getNombre() == null || pelicula.getFormato() == null) throw new NullPointerException("No hay parametros necesarios");
		if (pRepository.existsByNombreAndFormato(pelicula.getNombre(), pelicula.getFormato())) 
			throw new RecursoYaExistenteExcepcion("Pelicula", "nombre y formato", pelicula.getNombre()+"-"+pelicula.getFormato());
		/*if (pelicula.getNombre() == null || pelicula.getFormato() == null || 
				pRepository.existsByNombreAndFormato(pelicula.getNombre(), pelicula.getFormato())) {
			throw new RecursoYaExistenteExcepcion("Pelicula", "nombre y formato", pelicula.getNombre()+"-"+pelicula.getFormato());
		}*/
		
		String FILEPATH = this.agregarImagen((pelicula.getNombre()+"-"+pelicula.getFormato()), portada);
		
		pelicula.setCategorias(
				this.administrarCategorias(pelicula.getCategorias())
				);
		
		pelicula.setPortada(
				PortadaPelicula.builder()
				.URL(FILEPATH)
				.build()
		);
		
		pRepository.save(pelicula);
		
		return pRepository.findByNombreAndFormato(pelicula.getNombre(), pelicula.getFormato());
	}
	
	public Pelicula actualizarPelicula(Pelicula pelicula, MultipartFile portada) {
		if (pelicula.getId() == null) throw new NullPointerException("El valor de Id esta vacio");
		
		Optional<Pelicula> peliculaOg = pRepository.findById(pelicula.getId());
		
		if (peliculaOg.isEmpty()) throw new RecursoNoEncontradoExcepcion("Pelicula", "id", Long.toString(pelicula.getId()));
		
		boolean hayDiferencia = (!pelicula.getNombre().equals(peliculaOg.get().getNombre()) ||
				pelicula.getFormato() != peliculaOg.get().getFormato());
		
		if (hayDiferencia &&
				pRepository.existsByNombreAndFormato(pelicula.getNombre(), pelicula.getFormato())) {
			throw new RecursoYaExistenteExcepcion("Pelicula", "nombre y formato", pelicula.getNombre()+"-"+pelicula.getFormato());
		}
		
		String FILEPATH = this.agregarImagen((pelicula.getNombre()+"-"+pelicula.getFormato()), portada);
		
		if (hayDiferencia) this.eliminarImagen(peliculaOg.get().getPortada().getURL());
		
		PortadaPelicula portadaOg = peliculaOg.get().getPortada();

		if (!FILEPATH.contains(NOT_FOUND)) portadaOg.setURL(FILEPATH);
		
		pelicula.setPortada(portadaOg);
		pelicula.setCategorias(this.administrarCategorias(pelicula.getCategorias()));
		
		pRepository.save(pelicula);
		
		return pRepository.findByNombreAndFormato(pelicula.getNombre(), pelicula.getFormato());
	}
	
	public void eleminarPelicula(long id) {
		if (!pRepository.existsById(id)) {
			throw new RecursoNoEncontradoExcepcion("Pelicula", "id", Long.toString(id));
		}
		
		pRepository.deleteById(id);;
	}

	private List<Categoria> administrarCategorias(List<Categoria> categorias) {
		if (categorias.isEmpty()) return categorias;
		
		List<Categoria> sinId = categorias.stream()
				.filter(c -> c.getId() == null)
				.collect(Collectors.toList());
		
		categorias.removeAll(sinId);
		
		sinId.removeAll(
			sinId.stream()
			.filter(c -> this.cRepository.existsByNombreIgnoreCase(c.getNombre()))
			.toList()
		);
			
		if (!categorias.isEmpty()) categorias = cRepository.findAllById(categorias.stream().map(e -> e.getId()).toList());
		
		categorias.addAll(sinId);
		
		return categorias;
	}

	@Override
	public String agregarImagen(String nombre, MultipartFile imagen) {
		if (imagen == null) return (DIR_PATH + NOT_FOUND);
		
		nombre = nombre.replace(" ", "_");
		
		String nombreArchivo = imagen.getOriginalFilename();
		
		String extension = Optional.ofNullable(nombreArchivo)
				.filter(n -> n.contains("."))
				.map(e -> e.substring(nombreArchivo.lastIndexOf(".") + 1))
				.get();
		String FILEPATH = DIR_PATH + nombre + "." + extension;
		
		try {
			Files.write(Paths.get(FILEPATH), imagen.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return (nombre + "." + extension);
		
		//return FILEPATH;
	}

	@Override
	public void eliminarImagen(String imagenDir) {
		if (imagenDir.contains(NOT_FOUND)) return;
		
		Path OLDFILEPATH = Paths.get(imagenDir);
		
		try {
			Files.delete(OLDFILEPATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
