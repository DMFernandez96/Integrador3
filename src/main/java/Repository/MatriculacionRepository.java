package Repository;

import Model.Matriculacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculacionRepository extends JpaRepository<Matriculacion,Long> {

//	public boolean saveMatriculacion(Matriculacion m);
//	public boolean deleteMatriculacion(int id);

	public boolean actualizarMatriculacion(Matriculacion m);
}
