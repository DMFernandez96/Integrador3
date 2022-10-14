package Integrador3.DTO;

/**
 * 
 * @author Grupo1
 * @version Unique Version
 * @category DTO
 * 
 */

public class CarreraDTO{

	/**
	 * Identificador unico
	 */
	private Long idCarrera;
	
	/**
	 * Nombre de la carrera
	 */
	private String nombreCarrera;
	
	/**
	 * Cantidad de Inscriptos en la carrera
	 */
	private int cantInscriptos;
	
	/**
	 * Cantidad de Egresados de la carrera
	 */
	private int cantEgresados;
	
	/**
	 * Anio de inscripcion de la carrera
	 */
	private int anio;

	
	/**
	 * Constructor
	 */
	public CarreraDTO() {
	}
		

	public CarreraDTO(String nombreCarrera, Long idCarrera, int anio, int cantInscriptos, int cantEgresados) {

		super();
		this.idCarrera = idCarrera;
		this.nombreCarrera = nombreCarrera;
		this.anio= anio;
		this.cantInscriptos = cantInscriptos;
		this.cantEgresados = cantEgresados;
		this.anio= anio;

	}

	
	//getters y setters
	/**
	 * Obtener el id de la carrera
	 * @return idCarrera
	 */
	public Long getIdCarrera() {
		return idCarrera;
	}
	
	/**
	 * Setear nuevo valor a idCarrera
	 * @param idCarrera nuevo id para la carrera
	 */
	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}
	
	/**
	 * Obtener el nombre de la carrera
	 * @return nombreCarrera
	 */
	public String getNombreCarrera() {
		return nombreCarrera;
	}
	
	/**
	 * Cambiar nombre de la carrera
	 * @param nombreCarrera nuevo nombre de la carrera
	 */
	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	
	/**
	 * Obtener cantidad de inscriptos
	 * @return cantInscriptos 
	 */
	public int getCantInscriptos() {
		return cantInscriptos;
	}
	
	/**
	 * Cambiar la cantidad de inscriptos 
	 * @param cantInscriptos nueva cantidad de inscriptos
	 */
	public void setCantInscriptos(int cantInscriptos) {
		this.cantInscriptos = cantInscriptos;
	}
	
	/**
	 * Obtener cantidad de egresados
	 * @return cantEgresados
	 */
	public int getCantEgresados() {
		return cantEgresados;
	}
	
	/**
	 * Cambiar la cantidad de egresados
	 * @param cantEgresados nueva cantidad de egresados
	 */
	public void setCantEgresados(int cantEgresados) {
		this.cantEgresados = cantEgresados;
	}

	/**
	 * Obtener el anio de inscripcion inscripcion
	 * @return anioInscripcion
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Cambiar el anio de inscripcion
	 * @param inscripcion nuevo da
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}



	/**
	 * Convierte el objeto a string
	 * @return stringCarrera
	 */
	@Override
	public String toString() {
		return "CarreraDTO [idCarrera=" + idCarrera + ", nombreCarrera=" + nombreCarrera + ", cantInscriptos="
				+ cantInscriptos + ", cantEgresados=" + cantEgresados + ", anio=" + anio +
				"]";

	}


	public void sumarInscripto() {
		this.cantInscriptos++;
	}
	
	public void sumarEgresado() {
		this.cantEgresados++;

	}
	
}
