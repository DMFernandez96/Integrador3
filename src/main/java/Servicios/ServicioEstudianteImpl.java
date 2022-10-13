package Servicios;

import Model.Carrera;
import Model.Estudiante;
import Model.Matriculacion;
import Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Random;

@Service
public class ServicioEstudianteImpl implements ServicioEstudiante{

	@Qualifier("estudianteReporsitory")
	@Autowired
	private EstudianteRepository er;

	public ServicioEstudianteImpl(@Qualifier("estudianteReporsitory") EstudianteRepository er) {
		this.er = er;
	}

	@Override
	public void insertarEstudiante(Estudiante e) {er.save(e);}

	@Override
	public List<Estudiante> obtenerAllEstudiantes() {
		return er.findAll();
	}

	@Override
	public void eliminarEstudiante(Long id) {er.deleteById(id);}
	
	public boolean actualizarEstudiante(Estudiante e) {
		return er.actualizarEstudiante(e);
	}

	@Override
	public List<Estudiante> getEstudiantesPorGenero(char genero) {
		return er.getEstudiantesPorGenero(genero);
	}

	@Override
	public List<Estudiante> getEstudiantesOrdenadoPorApellidoYNombre() {
		return er.getEstudiantesOrdenadoPorApellidoYNombre();
	}
	
	@Override
	public List<Estudiante> getEstudiantesPorCiudad(String nombreCarrera, String ciudad) {
		return er.getEstudiantesPorCiudad(nombreCarrera, ciudad);
	}
	
	public Estudiante getEstudiantePorNroLibreta(int lu) {
		return er.getEstudiantePorNroLibreta(lu);
	}



}
