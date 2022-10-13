package Controller;


import Model.Carrera;
import Model.Estudiante;
import Model.Matriculacion;
import Servicios.ServicioEstudiante;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;


@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
	public EstudianteController(@Qualifier("servicioEstudiante") ServicioEstudiante se,@Qualifier("carreraController") CarreraController cc,@Qualifier("matriculacionController") MatriculacionController mc) {
		this.se = se;
		this.cc = cc;
		this.mc = mc;
	}
	@Qualifier("servicioEstudiante")
	@Autowired
	private ServicioEstudiante se;
	@Qualifier("carreraController")
	@Autowired
	private CarreraController cc;
	@Qualifier("matriculacionController")
	@Autowired
	private MatriculacionController mc;

	@PostMapping(value= "/insertar")	
	public void insertarEstudiante(@RequestBody Estudiante e) {
		this.se.insertarEstudiante(e);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarEstudiante(Long id) {this.se.eliminarEstudiante(id);}
	
	@PutMapping(value="/actualizar")
	public boolean actualizarEstudiante(@RequestBody Estudiante e) {
		return this.se.actualizarEstudiante(e);
	};
	
	public int generateRandomInt(int min, int max){
        return (int) Math.floor((Math.random() * (max+1 -min)) +min);
    }
	
	@PostMapping(value="/altaEstudiante")
	public boolean altaEstudiante(@RequestBody Estudiante e, @RequestBody Carrera c) {
		if(e != null && c != null) {
			Random random = new Random();
			//40% chance of true
			boolean chances40true = (random.nextInt(5) < 2) ? true : false;
			int anioRandomIngreso=0;//null
			int anioRandomEgreso = 0; 	
			int duracionTemp= c.getDuracion();		

			if(chances40true) {
				anioRandomIngreso= generateRandomInt(2010, 2022-duracionTemp);
				anioRandomEgreso= generateRandomInt(2022-duracionTemp, 2022);
			}else {
				anioRandomIngreso=generateRandomInt(2010, 2022);
			}
				
//			Matriculacion mat = new Matriculacion(e,c,anioRandomEgreso,anioRandomIngreso);
			Matriculacion mat = mc.crearMatriculacion(e,c,anioRandomEgreso,anioRandomIngreso);

//			if(cc.actualizarCarrera(c) && this.insertarEstudiante(e)) {
				mc.insertarMatriculacion(mat);
//				return true;
//			}
		}
		return false;
	}

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
	
	
	public char generarGenero() {
		Random random = new Random();
		//50% chance of true
		boolean chances50true = (random.nextInt(4) < 2) ? true : false;
		if(chances50true) {
			return 'f';
		}
		return 'm';
	}

	@PostMapping(value= "/cargar")
	public void cargarEstudiantes() {
		List<Carrera> carreras = cc.leerCarreras();
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/assets/estudiantes100.csv"));
			System.out.println("Estoy cargando los estudiantes...");
			for(CSVRecord row: parser) {
				Estudiante tmp = new Estudiante(parseInt(row.get("dni")),parseInt(row.get("nrolibreta")),row.get("nombre"),row.get("apellido"),parseInt(row.get("edad")),generarGenero(),row.get("ciudad"));
				altaEstudiante(tmp, carreras.get((int) (Math.random()*20+1)));
			}
			System.out.println("No se me da nada mal");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
