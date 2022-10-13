package Servicios;

import Model.Matriculacion;
import Repository.MatriculacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMatriculacionImpl implements ServicioMatriculacion{
	@Autowired
	private MatriculacionRepository mr;

	


	@Override
	public void insertarMatriculacion(Matriculacion m) {
		 mr.save(m);
	}

	@Override
	public void eliminarMatriculacion(Long id) {mr.deleteById(id);}

	@Override
	public boolean actualizarMatriculacion(Matriculacion m) {
		return mr.actualizarMatriculacion(m);
	}

}
