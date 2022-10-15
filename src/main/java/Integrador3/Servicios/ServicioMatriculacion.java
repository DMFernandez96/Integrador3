package Integrador3.Servicios;

import java.util.List;
import java.util.Optional;

import Integrador3.Model.Matriculacion;


public interface ServicioMatriculacion {
	
	public List<Matriculacion> obtenerAllMatriculaciones();
	public void insertarMatriculacion(Matriculacion m);
	public void eliminarMatriculacion(Long id);
	public Matriculacion actualizarMatriculacion(Long id, Matriculacion m);

	public Optional<Matriculacion> obtenerMatriculacionId(Long id);
}
