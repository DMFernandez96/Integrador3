package Integrador3.Controller;

import Integrador3.Model.Matriculacion;
import Integrador3.Servicios.ServicioMatriculacionImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculaciones")
public class MatriculacionController {

	public MatriculacionController(ServicioMatriculacionImpl sm) {
		this.sm = sm;
	}

	@Autowired
	private ServicioMatriculacionImpl sm;
	
	@RequestMapping("/")
	public List<Matriculacion> getAllMatriculaciones(){
		return this.sm.obtenerAllMatriculaciones();
	}
	
	@PostMapping(value="/insertar")
	public void insertarMatriculacion(@RequestBody Matriculacion m) {this.sm.insertarMatriculacion(m);}
	
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
