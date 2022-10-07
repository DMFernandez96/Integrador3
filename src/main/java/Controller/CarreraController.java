package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTO.CarreraDTO;
import Model.Carrera;
import Servicios.ServicioCarrera;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

	@Autowired
	private ServicioCarrera sc;
	
	@PostMapping(value="/insertar" )
	public boolean insertarCarrera(@RequestBody Carrera c) {
		return this.sc.insertarCarrera(c);
	};

	@RequestMapping(value="/actualizar" )
	public boolean actualizarCarrera(@RequestBody Carrera c) {
		if (this.sc.actualizarCarrera(c)) {
			return true;
		} 
		else if (this.insertarCarrera(c)) {
			return true;
		};
		return false;
	};
	
	@DeleteMapping(value = "/eliminar/{id}")
	public boolean eliminarCarrera(int id){
		return this.sc.eliminarCarrera(id);
	}
	
	@GetMapping
	public List<Carrera> listarCarreras(){
		return this.sc.listarCarreras();
	}
	
	@GetMapping(value="/{id}")
	public Carrera getCarrera(int id) {
		return this.sc.getCarrera(id);
	}
	
	@GetMapping(value="/conEstudiantes")
	public List<Carrera> getCarrerasConEstudiantes(){
		return this.sc.getCarrerasConEstudiantes();
	}
	
	@GetMapping(value="/reporte")
	public List<CarreraDTO> getReporteCarreras(){
		return this.sc.getReporteCarreras();
	}
}
