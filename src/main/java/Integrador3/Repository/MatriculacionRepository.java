package Integrador3.Repository;

import Integrador3.Model.Matriculacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculacionRepository extends JpaRepository<Matriculacion,Long> {

//	boolean actualizarMatriculacion(Matriculacion m);
}
