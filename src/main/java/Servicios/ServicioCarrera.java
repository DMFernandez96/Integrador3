package Servicios;

import java.util.List;

import DTO.CarreraDTO;
import Model.Carrera;

public interface ServicioCarrera {

	public boolean insertarCarrera(Carrera c);
	public List<Carrera> listarCarreras();
	public boolean eliminarCarrera(int id);
	public boolean actualizarCarrera(Carrera c);
	public Carrera getCarrera(int id);
	public List<Carrera> getCarrerasConEstudiantes();
	public List<CarreraDTO> getReporteCarreras();
}
