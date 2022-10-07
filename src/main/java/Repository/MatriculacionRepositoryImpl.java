package Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import Model.Matriculacion;

@Repository
public class MatriculacionRepositoryImpl implements MatriculacionRepository{
	private EntityManager em;

	public MatriculacionRepositoryImpl(EntityManager em) {
		this.em=em;
	}
	@Override
	public boolean saveMatriculacion(Matriculacion m) {
		em.getTransaction().begin();
		if (m.getIdMatricula()  < 1) {
			em.persist(m);
	        em.getTransaction().commit();
			return true;
		} else {
			m = em.merge(m);
	        em.getTransaction().commit();
			return false;
		}
	}

	@Override
	public boolean deleteMatriculacion(int id) {
		String delete = "DELETE FROM Matriculacion c WHERE e.idMatricula=:id";
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

	@Override
	public boolean actualizarMatriculacion(Matriculacion m) {
		em.getTransaction().begin();
		if(em.contains(m)) {
			em.merge(m);
	        em.getTransaction().commit();
			return true;
		}else {
	        em.getTransaction().commit();
			return false;
		}
	}

}
