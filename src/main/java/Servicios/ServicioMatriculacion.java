package Servicios;

import Model.Matriculacion;

public interface ServicioMatriculacion {
	
	public void insertarMatriculacion(Matriculacion m);
	public void eliminarMatriculacion(Long id);
	public boolean actualizarMatriculacion(Matriculacion m);

}
