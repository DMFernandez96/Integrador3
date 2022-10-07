package Factory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author Grupo1
 * @version Unique Version
 * @category Factory
 * 
 */
public class FactoryEntityManager {
	
	/**
	 * Administrador de la fabrica de entidades
	 */
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
	
	/**
	 * Entidad
	 */
	@PersistenceContext(unitName="rd")
    EntityManager em = emf.createEntityManager();
    
	/**
	 * Constructor
	 */
    public FactoryEntityManager(){};
    
    /**
     * Obtener el administrador de entidades
     * @return em
     */
    public EntityManager getEntityManger() {
    	return this.em;
    }

    /**
     * Apagar la fabrica
     */
	public void apagar() {
		em.close();
		emf.close();
	}
    
    // Se podrian crear metodos para administrar el entityManager, todos los servicios usarian la misma fabrica, obtendrian el mismo entityManager, 
    // a menos que se cree distintos metodos y se podria limpíar, borrar y abrir nuevos segun se quiera.
}
