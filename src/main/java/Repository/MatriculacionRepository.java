package Repository;

import Model.Matriculacion;

public interface MatriculacionRepository {

	public boolean saveMatriculacion(Matriculacion m);
	public boolean deleteMatriculacion(int id);
	public boolean actualizarMatriculacion(Matriculacion m);
}
