package Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Factory.FactoryEntityManager;
import Model.Matriculacion;
import Repository.MatriculacionRepositoryImpl;

@Service
public class ServicioMatriculacionImpl implements ServicioMatriculacion{
	@Autowired
	private MatriculacionRepositoryImpl mr;
	@Autowired
	private FactoryEntityManager fem;
	
	
//	public ServicioMatriculacionImpl(FactoryEntityManager fem) {
//		this.fem = fem;
//		this.mr = new MatriculacionRepositoryImpl(fem.getEntityManger());
//	}

	@Override
	public boolean insertarMatriculacion(Matriculacion m) {
		return mr.saveMatriculacion(m);
	}

	@Override
	public boolean eliminarMatriculacion(int id) {
		return mr.deleteMatriculacion(id);
	}

	@Override
	public boolean actualizarMatriculacion(Matriculacion m) {
		return mr.actualizarMatriculacion(m);
	}

}
