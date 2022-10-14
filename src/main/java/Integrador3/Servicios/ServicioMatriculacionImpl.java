package Integrador3.Servicios;

import Integrador3.Model.Carrera;
import Integrador3.Model.Estudiante;
import Integrador3.Model.Matriculacion;
import Integrador3.Repository.MatriculacionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMatriculacionImpl implements ServicioMatriculacion{

	@Autowired
	private MatriculacionRepository mr;

	public ServicioMatriculacionImpl(MatriculacionRepository mr) {
		this.mr = mr;
	}
	
	@Override
	public List<Matriculacion> obtenerAllMatriculaciones() {
		return mr.findAll();
	}
	
	@Override
	public void insertarMatriculacion(Matriculacion m) {
		 mr.save(m);
	}
	@Override
	public void eliminarMatriculacion(Long id) {
		mr.deleteById(id);
	}

	@Override
	public Matriculacion actualizarMatriculacion(Long id, Matriculacion newMatriculacion) {
		return mr.findById(id)
				.map(oldMatriculacion -> {
					oldMatriculacion.setCarrera(newMatriculacion.getCarrera());
					oldMatriculacion.setEstudiante(newMatriculacion.getEstudiante());
					oldMatriculacion.setInscripcion(newMatriculacion.getInscripcion());
					return mr.save(oldMatriculacion);
				})
				.orElseGet(() -> {
					return mr.save(newMatriculacion);
				});
	}


}
