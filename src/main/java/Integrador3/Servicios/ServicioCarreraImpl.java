package Integrador3.Servicios;

import Integrador3.DTO.CarreraDTO;
import Integrador3.Model.Carrera;
import Integrador3.Model.Matriculacion;
import Integrador3.Comparator.ComparadorDTO;
import Integrador3.Repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCarreraImpl implements ServicioCarrera {

	@Autowired
	private CarreraRepository cr;

	@Override
	public void insertarCarrera(Carrera c) {cr.save(c);}
	@Override
	public List<Carrera> listarCarreras() {
		return cr.findAll();
	}
	@Override
	public void eliminarCarrera(Long id) {
		cr.deleteById(id);
	}

	@Override
	public Carrera actualizarCarrera(Long id, Carrera newCarrera) {
			return cr.findById(id)
					.map(oldCarrera -> {
						oldCarrera.setNombre(newCarrera.getNombre());
						oldCarrera.setDuracion(newCarrera.getDuracion());
						oldCarrera.setMatriculaciones(newCarrera.getMatriculaciones());
						return cr.save(oldCarrera);
					})
					.orElseGet(() -> {
						return cr.save(newCarrera);
					});
	}

	@Override
	public List<Carrera> getCarrerasConEstudiantes() {
		return cr.getCarrerasConEstudiantes();
	}
	@Override
	public Optional<Carrera> getCarrera(Long id) {
		return cr.findById(id);
	}
	@Override
	public  List<CarreraDTO> getReporteCarreras() {

		List<Carrera> carreras = cr.getCarrerasOrdenadoNombre();
		List<CarreraDTO> reporte = new ArrayList<>();


		int cantCarreras = carreras.size();
		int cantMatri = 0;
		int cantEgresados = 0;

		for(Carrera c: carreras) {

			List<Matriculacion> matriculaciones = c.getMatriculaciones();
			List<CarreraDTO> temporal = new ArrayList<>();
			List<Integer> años = new ArrayList<>();
			cantMatri += matriculaciones.size();

			for(Matriculacion m: matriculaciones) {

				int añoInscripcion = m.getInscripcion();
				int añoEgresado = m.getAnioGraduado();


				if(años.contains(añoInscripcion)) {
					temporal.get(años.indexOf(añoInscripcion)).sumarInscripto();
					if(añoEgresado!=0) {
						if(años.contains(añoEgresado)) {
							cantEgresados++;
							temporal.get(años.indexOf(añoEgresado)).sumarEgresado();
						}else {
							CarreraDTO nuevo = new CarreraDTO(c.getNombre(),c.getId_carrera(),m.getAnioGraduado(),0,1);
							cantEgresados++;
							temporal.add(nuevo);
							años.add(m.getAnioGraduado());
						}
					}
				} else {
					CarreraDTO nuevo = new CarreraDTO(c.getNombre(),c.getId_carrera(),m.getInscripcion(),1,0);
					temporal.add(nuevo);
					años.add(m.getInscripcion());
					if(añoEgresado!=0) {
						if(años.contains(añoEgresado)) {
							cantEgresados++;
							temporal.get(años.indexOf(añoEgresado)).sumarEgresado();
						} else {
							CarreraDTO nuevo2 = new CarreraDTO(c.getNombre(),c.getId_carrera(),m.getAnioGraduado(),0,1);
							cantEgresados++;
							temporal.add(nuevo2);
							años.add(m.getAnioGraduado());
						}
					}
				}
			}
			// ordenar temporal por año
			temporal.sort(new ComparadorDTO());
			reporte.addAll(temporal);
	}

		System.out.println("MatriculacionesTotales: "+ cantMatri+", totalCarreras: "+ cantCarreras+", totalEgresados= "+ cantEgresados);
		return reporte;

		}


	}

