package Servicios;

import Model.Matriculacion;

public interface ServicioMatriculacion {
	
	public boolean insertarMatriculacion(Matriculacion m);
	public boolean eliminarMatriculacion(int id);
	public boolean actualizarMatriculacion(Matriculacion m);

}
