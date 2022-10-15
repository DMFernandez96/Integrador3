package Integrador3.Servicios;

import Integrador3.Model.Carrera;
import Integrador3.Model.Estudiante;
import Integrador3.Model.Matriculacion;
import Integrador3.Repository.MatriculacionRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMatriculacionImpl implements ServicioMatriculacion{



	public ServicioMatriculacionImpl(ServicioEstudianteImpl svcEstudiante, MatriculacionRepository repository, ServicioCarreraImpl svcCarrera) {
		this.repository = repository;
		this.svcCarrera = svcCarrera;
		this.svcEstudiante = svcEstudiante;
	}

	@Autowired
	private MatriculacionRepository repository;
	@Autowired
	private ServicioEstudianteImpl svcEstudiante;
	@Autowired
	private ServicioCarreraImpl svcCarrera;
	
	@Override
	public List<Matriculacion> obtenerAllMatriculaciones() {
		return repository.findAll();
	}
	
	@Override
	public void insertarMatriculacion(Matriculacion m) {
		 repository.save(m);
	}
	@Override
	public void eliminarMatriculacion(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Matriculacion actualizarMatriculacion(Long id, Matriculacion newMatriculacion) {
		return repository.findById(id)
				.map(oldMatriculacion -> {
					oldMatriculacion.setCarrera(newMatriculacion.getCarrera());
					oldMatriculacion.setEstudiante(newMatriculacion.getEstudiante());
					oldMatriculacion.setInscripcion(newMatriculacion.getInscripcion());
					return repository.save(oldMatriculacion);
				})
				.orElseGet(() -> {
					return repository.save(newMatriculacion);
				});
	}

	public Optional<Matriculacion> obtenerMatriculacionId(Long id){
		return repository.findById(id);
	}


	public Optional<Matriculacion> crearMatriculacion(Carrera c, Estudiante e) {
		Matriculacion mat = null;
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

			mat = new Matriculacion(e,c,anioRandomEgreso,anioRandomIngreso);

			e.agregarMatriculacion(mat);
			c.agregarMatriculacion(mat);

			svcCarrera.actualizarCarrera(c.getId_carrera(), c);
			if(e.getId_estudiante()!=null){
				svcEstudiante.actualizarEstudiante(e.getId_estudiante(), e);
			} else {
				svcEstudiante.insertarEstudiante(e);
			}
			repository.save(mat);
		}
		return Optional.of(mat);
	}

	public int generateRandomInt(int min, int max){
		return (int) Math.floor((Math.random() * (max+1 -min)) +min);}

}
