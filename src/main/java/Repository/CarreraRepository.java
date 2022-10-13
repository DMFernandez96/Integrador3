package Repository;

import Model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera,Long> {
	
//	public boolean saveCarrera(Carrera c);
//	public boolean deleteCarrera(int id);
//	public Carrera getCarrera(int id);
//	@Query("SELECT c FROM Carrera c")
//	public List<Carrera> getAllCarreras();

	public boolean actualizarCarrera(Carrera c);

	@Query("SELECT c FROM Carrera c "
			+ "LEFT OUTER JOIN Matriculacion m ON c.id_carrera = m.carrera.id_carrera "
			+ "GROUP BY c.id_carrera,c.nombre ,c.duracion "
			+ "HAVING COUNT(c.id_carrera) > 0 "
			+ "ORDER BY COUNT(c.id_carrera) DESC ")
	public List<Carrera> getCarrerasConEstudiantes();

	@Query("SELECT c FROM Carrera c "
			+ "LEFT OUTER JOIN Matriculacion m ON c.id_carrera = m.carrera.id_carrera "
			+ "GROUP BY c.id_carrera,c.nombre ,c.duracion "
			+ "HAVING COUNT(c.id_carrera) > 0 "
			+ "ORDER BY c.nombre ASC ")
	public List<Carrera> getCarrerasOrdenadoNombre();

}
