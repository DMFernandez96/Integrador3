package Integrador3.Servicios;

import Integrador3.DTO.CarreraDTO;
import Integrador3.Model.Carrera;

import java.util.List;
import java.util.Optional;

public interface ServicioCarrera {

	public void insertarCarrera(Carrera c);
	public List<Carrera> listarCarreras();
	public void eliminarCarrera(Long id);
	public Carrera actualizarCarrera(Long id, Carrera c);
	public Optional<Carrera> getCarrera(Long id);
	public List<Carrera> getCarrerasConEstudiantes();
	public List<CarreraDTO> getReporteCarreras();

	public void guardarCarreras(List<Carrera> carreras);
}
