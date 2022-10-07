package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.Matriculacion;
import Servicios.ServicioMatriculacion;

@RestController
@RequestMapping("/matriculaciones")
public class MatriculacionController {
	@Autowired
	private ServicioMatriculacion sm;
	
	@PostMapping(value="/insertar")
	public boolean insertarMatriculacion(@RequestBody Matriculacion m) {
		return this.sm.insertarMatriculacion(m);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public boolean eliminarMatriculacion(@PathVariable int id) {
		return this.sm.eliminarMatriculacion(id);
	}
	
	@PutMapping(value="/actualizar" )
	public boolean actualizarMatriculacion(@RequestBody Matriculacion m) {
		return this.sm.actualizarMatriculacion(m);
	}

}
