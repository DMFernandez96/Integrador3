package Integrador3.Servicios;

import Integrador3.Model.Estudiante;
import Integrador3.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioEstudianteImpl implements ServicioEstudiante{

	@Autowired
	private EstudianteRepository er;

	public ServicioEstudianteImpl(EstudianteRepository er) {
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
	
	@Override
	public Estudiante actualizarEstudiante(Long id, Estudiante newEstudiante) {
		if(id!=null)
			return er.findById(id)
					.map(oldEstudiante -> {
						oldEstudiante.setDni(newEstudiante.getDni());
						oldEstudiante.setNombre(newEstudiante.getNombre());
						oldEstudiante.setApellido(newEstudiante.getApellido());
						oldEstudiante.setEdad(newEstudiante.getEdad());
						oldEstudiante.setCiudad(newEstudiante.getCiudad());
						oldEstudiante.setGenero(newEstudiante.getGenero());
						oldEstudiante.setMatriculaciones(newEstudiante.getMatriculaciones());
						return er.save(oldEstudiante);
					})
					.orElseGet(() -> {
						return er.save(newEstudiante);
					});
		return null;
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
	@Override
	public Estudiante getEstudiantePorNroLibreta(int lu) {
		return er.getEstudiantePorNroLibreta(lu);
	}



}
