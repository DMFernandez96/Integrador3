package Integrador3.Servicios;

import java.util.List;

import Integrador3.Model.Matriculacion;


public interface ServicioMatriculacion {
	
	public List<Matriculacion> obtenerAllMatriculaciones();
	public void insertarMatriculacion(Matriculacion m);
	public void eliminarMatriculacion(Long id);
	public Matriculacion actualizarMatriculacion(Long id, Matriculacion m);
}
