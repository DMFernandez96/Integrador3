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


	@Qualifier("servicioEstudianteImpl")
	@Autowired
	private ServicioEstudianteImpl se;

	public EstudianteController(@Qualifier("servicioEstudianteImpl") ServicioEstudianteImpl se) {
		this.se = se;
	}

	@PostMapping(value= "/insertar")	
	public void insertarEstudiante(@RequestBody Estudiante e) {
		this.se.insertarEstudiante(e);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarEstudiante(Long id) {this.se.eliminarEstudiante(id);}
	
//	@PutMapping(value="/actualizar")
//	public boolean actualizarEstudiante(@RequestBody Estudiante e) {
//		return this.se.actualizarEstudiante(e);
//	};
	

	
	@PostMapping(value="/altaEstudiante")


	@RequestMapping("/")
	public List<Estudiante> getAllEstudiantes(){
		return this.se.obtenerAllEstudiantes();
	}
	
	@GetMapping(value= "/{genero}")
	public List<Estudiante> getEstudiantesPorGenero(@PathVariable char genero){
		return this.se.getEstudiantesPorGenero(genero);
	}
	
	@GetMapping(value= "/{lu}")
	public Estudiante getEstudianteLibreta(@PathVariable("lu") int nroLibreta) {
		return this.se.getEstudiantePorNroLibreta(nroLibreta);
	}
	
	@GetMapping(value="/ordenados")
	public List<Estudiante> getEstudianteOrdenado(){
		return this.se.getEstudiantesOrdenadoPorApellidoYNombre();
	}
	
	@GetMapping(value= "/{carrera}/{ciudad}")
	public List<Estudiante> getEstudiantePorCiudad(@PathVariable String carrera,@PathVariable String ciudad){
		return this.se.getEstudiantesPorCiudad(carrera, ciudad);
	}


}
