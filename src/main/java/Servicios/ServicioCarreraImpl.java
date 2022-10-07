package Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Comparator.ComparadorDTO;
import DTO.CarreraDTO;
import Factory.FactoryEntityManager;
import Model.Carrera;
import Model.Matriculacion;
import Repository.CarreraRepositoryImpl;

@Service
public class ServicioCarreraImpl implements ServicioCarrera{
	@Autowired
	private CarreraRepositoryImpl cr;
	@Autowired
	private FactoryEntityManager fem;
	
//	public ServicioCarreraImpl(FactoryEntityManager fem) {
//		this.fem = fem;
//		this.cr = new CarreraRepositoryImpl(fem.getEntityManger());
//	};
//	

	@Override
	public boolean insertarCarrera(Carrera c) {
		return cr.saveCarrera(c);
	}

	@Override
	public List<Carrera> listarCarreras() {
		return cr.getAllCarreras();
	}

	@Override
	public boolean eliminarCarrera(int id) {
		return cr.deleteCarrera(id);
	}
	
	public boolean actualizarCarrera(Carrera c) {
		return cr.actualizarCarrera(c);
	}

	@Override
	public List<Carrera> getCarrerasConEstudiantes() {
		return cr.getCarrerasConEstudiantes();
	}

	@Override
	public Carrera getCarrera(int id) {
		return cr.getCarrera(id);
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

