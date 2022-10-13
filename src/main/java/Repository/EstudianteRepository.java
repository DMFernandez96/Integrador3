package Repository;

import Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
	
//	public boolean saveEstudiante(Estudiante e); //dar de alta un estudiante
//	public boolean deleteEstudiante(int id);
//	public List<Estudiante> getAllEstudiantes();

	public boolean actualizarEstudiante(Estudiante e);
	public Estudiante getEstudiantePorNroLibreta(int lu);
	public List<Estudiante> getEstudiantesPorGenero(char genero);
	public List<Estudiante> getEstudiantesOrdenadoPorApellidoYNombre();//criterio de ordenamiento
	public List<Estudiante> getEstudiantesPorCiudad(String nombreCarrera,String ciudad);

}
