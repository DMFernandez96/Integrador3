package Integrador3.Controller;

import Integrador3.Model.Carrera;
import Integrador3.Model.Estudiante;
import Integrador3.Model.Matriculacion;
import Integrador3.Servicios.ServicioCarreraImpl;
import Integrador3.Servicios.ServicioEstudianteImpl;
import Integrador3.Servicios.ServicioMatriculacionImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculaciones")
public class MatriculacionController {

	public MatriculacionController(ServicioMatriculacionImpl svcMatriculacion) {
		this.svcMatriculacion = svcMatriculacion;
	}

	@Autowired
	private ServicioMatriculacionImpl svcMatriculacion;
	
	@RequestMapping("/")
	public List<Matriculacion> getAllMatriculaciones(){
		return this.svcMatriculacion.obtenerAllMatriculaciones();
	}
	
	@PostMapping(value="/insertar")
	public void insertarMatriculacion(@RequestBody Matriculacion m) {
		this.svcMatriculacion.insertarMatriculacion(m);
	}

	@RequestMapping("/{id}")
	public Optional<Matriculacion> getById(@PathVariable Long id){
		return this.svcMatriculacion.obtenerMatriculacionId(id);
	};

	@PostMapping(value="/crearMatriculacion")
	public void crearMatriculacion(@RequestBody Carrera c, @RequestBody Estudiante e) {
		this.svcMatriculacion.crearMatriculacion(c,e);
	}
	
//	@DeleteMapping(value = "/eliminar/{id}")
//	public void eliminarMatriculacion(@PathVariable Long id) {
//		this.sm.eliminarMatriculacion(id);
//	}
//	
//	@PutMapping(value="/actualizar/{id}" )
//	public boolean actualizarMatriculacion(@PathVariable Long id, @RequestBody Matriculacion m) {
//		return this.sm.actualizarMatriculacion(m);
//	}


}
