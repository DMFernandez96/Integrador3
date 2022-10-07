package Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Model.Estudiante;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class EstudianteRepositoryImpl implements EstudianteRepository{

	private EntityManager em;
	
	public EstudianteRepositoryImpl(EntityManager em) {
		this.em=em;
	}

	@Override
	public boolean saveEstudiante(Estudiante e) {
		em.getTransaction().begin();
		if (e.getId_estudiante()  < 1) {
			em.persist(e);
	        em.getTransaction().commit();
			return true;
		} else {
			e = em.merge(e);
	        em.getTransaction().commit();
			return false;
		}
	}
	
	@Override
	public boolean deleteEstudiante(int id) {
		String delete = "DELETE FROM Estudiante e WHERE e.id_estudiante=:id";
		try {
			this.em.getTransaction().begin();
			Query query = this.em.createQuery(delete);
			query.setParameter("id", id).executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.getTransaction().commit();
		}
		return false;
	}
	
	public boolean actualizarEstudiante(Estudiante e) {
		em.getTransaction().begin();
		if(em.contains(e)) {
			em.merge(e);
	        em.getTransaction().commit();
			return true;
		}else {
	        em.getTransaction().commit();
			return false;
		}
	}
	
	@Override
	public Estudiante getEstudiantePorNroLibreta(int nrolibreta) {
		String get ="SELECT e FROM Estudiante e WHERE e.nrolibreta=:nrolibreta";
		try {
			em.getTransaction().begin();
			TypedQuery<Estudiante> typedQuery = this.em.createQuery(get,Estudiante.class);
			typedQuery.setParameter("nrolibreta", nrolibreta);
			return typedQuery.getSingleResult();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
	        em.getTransaction().commit();
		}
		return null;
	}

	@Override
	public List<Estudiante> getEstudiantesPorGenero(char genero) {
		String get ="SELECT e FROM Estudiante e WHERE e.genero=:genero";
		try {
			em.getTransaction().begin();
			TypedQuery<Estudiante> typedQuery = this.em.createQuery(get,Estudiante.class);
			typedQuery.setParameter("genero", genero);
			return typedQuery.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
	        em.getTransaction().commit();
		}
		return null;
	}

	@Override
	public List<Estudiante> getEstudiantesOrdenadoPorApellidoYNombre() {
		String get ="SELECT e FROM Estudiante e ORDER BY e.apellido, e.nombre ASC";
		try {
			em.getTransaction().begin();
			TypedQuery<Estudiante> typedQuery = this.em.createQuery(get,Estudiante.class);
			return typedQuery.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
	        em.getTransaction().commit();
		}
		return null;
	}

	@Override
	public List<Estudiante> getAllEstudiantes() {
		String get="SELECT e FROM Estudiante e";
		try {
			em.getTransaction().begin();
			TypedQuery<Estudiante> typedQuery = this.em.createQuery(get, Estudiante.class);
			return typedQuery.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
	        em.getTransaction().commit();
		}
		return null;

	}
	
	@Override
	public List<Estudiante> getEstudiantesPorCiudad(String nombreCarrera,String ciudad) {
		String get = "SELECT e FROM Estudiante e "
				+ "INNER JOIN Matriculacion m ON e.id_estudiante = m.estudiante "
				+ "INNER JOIN Carrera c ON c.id_carrera  = m.carrera "
				+ "WHERE c.nombre = :carrera "
				+ "AND e.ciudad  = :ciudad";
		try {
			em.getTransaction().begin();
			TypedQuery<Estudiante> typedQuery = this.em.createQuery(get, Estudiante.class);
			typedQuery.setParameter("carrera", nombreCarrera);
			typedQuery.setParameter("ciudad", ciudad);
			return typedQuery.getResultList();	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
	        em.getTransaction().commit();
		}
		return null;
	}

}
