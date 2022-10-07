package Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Factory.FactoryEntityManager;
import Model.Estudiante;
import Repository.EstudianteRepositoryImpl;

@Service
public class ServicioEstudianteImpl implements ServicioEstudiante{
	@Autowired
	private EstudianteRepositoryImpl er;
	@Autowired
	private FactoryEntityManager fem;
	
//	public ServicioEstudianteImpl(FactoryEntityManager fem) {
//		this.fem = fem;
//		this.er = new EstudianteRepositoryImpl(this.fem.getEntityManger());
//	};
	
	@Override
	public boolean insertarEstudiante(Estudiante e) {
		return er.saveEstudiante(e);
	}

	@Override
	public List<Estudiante> obtenerAllEstudiantes() {
		return er.getAllEstudiantes();
	}

	@Override
	public boolean eliminarEstudiante(int id) {
		return er.deleteEstudiante(id);
	}
	
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
