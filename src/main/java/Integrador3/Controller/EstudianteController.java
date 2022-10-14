package Integrador3.Controller;

import Integrador3.Model.Estudiante;
import Integrador3.Servicios.ServicioEstudianteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	public EstudianteController(ServicioEstudianteImpl se) {
		this.se = se;
	}

	@Autowired
	private ServicioEstudianteImpl se;

	@PostMapping(value= "/insertar")	
	public void insertarEstudiante(@RequestBody Estudiante e) {
		this.se.insertarEstudiante(e);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarEstudiante(@PathVariable Long id) {this.se.eliminarEstudiante(id);}
	
	@PutMapping(value="/actualizar/{id}")
	public boolean actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante e) {
		this.se.actualizarEstudiante(id, e);
		return true;
	};
	
//	@PostMapping(value="/altaEstudiante")

	@RequestMapping("/")
	public List<Estudiante> getAllEstudiantes(){
		return this.se.obtenerAllEstudiantes();
	}
	
	@GetMapping(value= "/genero/{genero}")
	public List<Estudiante> getEstudiantesPorGenero(@PathVariable char genero){
		return this.se.getEstudiantesPorGenero(genero);
	}
	
	@GetMapping(value= "/libreta/{nroLibreta}")
	public Estudiante getEstudianteLibreta(@PathVariable int nroLibreta) {
		return this.se.getEstudiantePorNroLibreta(nroLibreta);
	}
	
	@GetMapping(value="/ordenados")
	public List<Estudiante> getEstudianteOrdenado(){
		return this.se.getEstudiantesOrdenadoPorApellidoYNombre();
	}
	
//	@GetMapping(value= "/{carrera}/{ciudad}")
//	public List<Estudiante> getEstudiantePorCiudad(@PathVariable String carrera,@PathVariable String ciudad){
//		return this.se.getEstudiantesPorCiudad(carrera, ciudad);
//	}
}
