package Controller;

import DTO.CarreraDTO;
import Model.Carrera;
import Servicios.ServicioCarrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
	public CarreraController(@Qualifier("servicioCarrera") ServicioCarrera sc) {
		this.sc = sc;
	}

	@Qualifier("servicioCarrera")
	@Autowired
	private ServicioCarrera sc;

	@PostMapping(value="/insertar" )
	public void insertarCarrera(@RequestBody Carrera c) {this.sc.insertarCarrera(c);}

	@RequestMapping(value="/actualizar" )
	public boolean actualizarCarrera(@RequestBody Carrera c) {
		if (this.sc.actualizarCarrera(c)) {
			return true;
		} 
//		else if (this.insertarCarrera(c)) {
//			return true;
//		};
		return false;
	};
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarCarrera(Long id){
		this.sc.eliminarCarrera(id);
	}
	
	@GetMapping(value="/")
	public List<Carrera> listarCarreras(){
		return this.sc.listarCarreras();
	}
	
	@GetMapping(value="/{id}")
	public Optional<Carrera> getCarrera(Long id) {
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

	public List<Carrera> leerCarreras(){
		List<Carrera> car = new ArrayList<>();
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/assets/carreras20.csv"));
			System.out.println("Estoy cargando las carreras...");
			for(CSVRecord row: parser) {
				Carrera tmp = new Carrera(row.get("nombre"),parseInt(row.get("duracion")));
				car.add(tmp);
			}
			System.out.println("No se me da nada mal");
			return car;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return car;
	}
}
