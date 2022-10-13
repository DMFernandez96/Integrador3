package Controller;

import Model.Carrera;
import Model.Estudiante;
import Model.Matriculacion;
import Servicios.ServicioMatriculacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculaciones")
public class MatriculacionController {
	public MatriculacionController(@Qualifier("servicioMatriculacion") ServicioMatriculacion sm) {
		this.sm = sm;
	}
	@Qualifier("servicioMatriculacion")
	@Autowired
	private ServicioMatriculacion sm;
	
	@PostMapping(value="/insertar")
	public void insertarMatriculacion(@RequestBody Matriculacion m) {this.sm.insertarMatriculacion(m);}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarMatriculacion(@PathVariable Long id) {
		this.sm.eliminarMatriculacion(id);
	}
	
	@PutMapping(value="/actualizar" )
	public boolean actualizarMatriculacion(@RequestBody Matriculacion m) {
		return this.sm.actualizarMatriculacion(m);
	}


}
