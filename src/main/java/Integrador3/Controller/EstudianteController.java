package Integrador3.Controller;

import Integrador3.Model.Estudiante;
import Integrador3.Servicios.ServicioCarreraImpl;
import Integrador3.Servicios.ServicioEstudianteImpl;
import Integrador3.Servicios.ServicioMatriculacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	public EstudianteController(ServicioEstudianteImpl svcEstudiante) {
		this.svcEstudiante = svcEstudiante;
	}

	@Autowired
	private ServicioEstudianteImpl svcEstudiante;

	@PostMapping(value= "/insertar")	
	public void insertarEstudiante(@RequestBody Estudiante e) {
		this.svcEstudiante.insertarEstudiante(e);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarEstudiante(@PathVariable Long id) {this.svcEstudiante.eliminarEstudiante(id);}
	
	@PutMapping(value="/actualizar/{id}")
	public boolean actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante e) {
		this.svcEstudiante.actualizarEstudiante(id, e);
		return true;
	};
	
//	@PostMapping(value="/altaEstudiante")

	@RequestMapping("/")
	public List<Estudiante> getAllEstudiantes(){
		return this.svcEstudiante.obtenerAllEstudiantes();
	}
	
	@GetMapping(value= "/genero/{genero}")
	public List<Estudiante> getEstudiantesPorGenero(@PathVariable char genero){
		return this.svcEstudiante.getEstudiantesPorGenero(genero);
	}
	
	@GetMapping(value= "/libreta/{nroLibreta}")
	public Estudiante getEstudianteLibreta(@PathVariable int nroLibreta) {
		return this.svcEstudiante.getEstudiantePorNroLibreta(nroLibreta);
	}
	
	@GetMapping(value="/ordenados")
	public List<Estudiante> getEstudianteOrdenado(){
		return this.svcEstudiante.getEstudiantesOrdenadoPorApellidoYNombre();
	}
	
//	@GetMapping(value= "/{carrera}/{ciudad}")
//	public List<Estudiante> getEstudiantePorCiudad(@PathVariable String carrera,@PathVariable String ciudad){
//		return this.se.getEstudiantesPorCiudad(carrera, ciudad);
//	}
}
