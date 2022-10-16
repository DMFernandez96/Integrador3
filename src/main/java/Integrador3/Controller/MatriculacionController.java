package Integrador3.Controller;

import Integrador3.Model.Estudiante;
import Integrador3.Model.Matriculacion;
import Integrador3.Servicios.ServicioMatriculacionImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	}
	
	@PostMapping(value="/crearMatriculacion/{idCarrera}")
	public void crearMatriculacion(@PathVariable Long idCarrera, @RequestBody Estudiante e) {
		this.svcMatriculacion.crearMatriculacion(idCarrera,e);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarMatriculacion(@PathVariable Long id) {
		this.svcMatriculacion.eliminarMatriculacion(id);
	}
	
	@PutMapping(value="/actualizar/{id}" )
	public Matriculacion actualizarMatriculacion(@PathVariable Long id, @RequestBody Matriculacion m) {
		return this.svcMatriculacion.actualizarMatriculacion(id, m);
	}


}
