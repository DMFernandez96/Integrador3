package Integrador3.Repository;

import Integrador3.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
	
//	public boolean saveEstudiante(Estudiante e); //dar de alta un estudiante
//	public boolean deleteEstudiante(int id);
//	public List<Estudiante> getAllEstudiantes();
//	@Query()
//	boolean actualizarEstudiante(Estudiante e);

	@Query("SELECT e FROM Estudiante e WHERE e.nrolibreta=:nrolibreta")
	Estudiante getEstudiantePorNroLibreta(int nrolibreta);

	@Query("SELECT e FROM Estudiante e WHERE e.genero=:genero")
	List<Estudiante> getEstudiantesPorGenero(char genero);

	@Query("SELECT e FROM Estudiante e ORDER BY e.apellido, e.nombre ASC")
	List<Estudiante> getEstudiantesOrdenadoPorApellidoYNombre();//criterio de ordenamiento

	@Query("SELECT e FROM Estudiante e "
			+ "INNER JOIN Matriculacion m ON e.id_estudiante = m.estudiante.id_estudiante "
			+ "INNER JOIN Carrera c ON c.id_carrera  = m.carrera.id_carrera "
			+ "WHERE c.nombre = :nombreCarrera "
			+ "AND e.ciudad  = :ciudad")
	List<Estudiante> getEstudiantesPorCiudad(String nombreCarrera,String ciudad);

}
