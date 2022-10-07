package Model;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.*;

/**
 * 
 * @author Grupo1
 * @version Unique Version
 * @category Model
 * 
 */
@Entity
public class Matriculacion {
	
	/**
	 * identificador unico de la matriculacion
	 */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idMatricula;

    /**
     * identificador del estudiante
     */
    @ManyToOne
    @JoinColumn(name = "id_estudiante") //FK
    private Estudiante estudiante;

    /**
     * identificador de la carrera
     */
    @ManyToOne
    @JoinColumn(name = "id_carrera") //FK
    private Carrera carrera;
    
    /**
     * anio de graduacion del estudiante
     */
    @Column(name="anio_graduado")
    private int anioGraduado;
    
    /**
     * anio de inscripcion del estudiante
     */
    @Column(name="anioInscripcion")
    private int anioInscripcion;

	public Matriculacion() {
	}

	/**
	 * Constructor
	 * @param estudiante estudiante a matricular
	 * @param carrera carrera para matriculacion
	 * @param graduado estado de graduacion 
	 * @param inscripcion anio de inscripcion 
	 */
	public Matriculacion(Estudiante estudiante, Carrera carrera, int graduado, int inscripcion) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.anioGraduado = graduado;
		this.anioInscripcion = inscripcion;
	}

	/**
	 * Obtener estudiante matriculado
	 * @return estudiante
	 */
	public Estudiante getEstudiante() {
		return estudiante;
	}

	/**
	 * Cambiar estudiante matriculado
	 * @param estudiante nuevo estudiante
	 */
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
	/**
	 * Obtener nombre de la carrera
	 * @return nombrecarrera
	 */
	public String getNombreCarrera() {
		return this.carrera.getNombre();
	}

	/**
	 * Obtener carrera 
	 * @return carrera
	 */
	public Carrera getCarrera() {
		return carrera;
	}

	/**
	 * Cambiar carrera
	 * @param carrera nueva carrera
	 */
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	/**
	 * Obtener estado de graduacion
	 * @return graduado
	 */
	public int getAnioGraduado() {
		return anioGraduado;
	}

	/**
	 * Cambiar estado de graduacion 
	 * @param graduado nuevo estado de graduacion
	 */
	public void setGraduado(int graduado) {
		this.anioGraduado = graduado;
	}

	/**
	 * Obtener id de la matricula
	 * @return idmatricula
	 */
	public int getIdMatricula() {
		return idMatricula;
	}
	
	/**
	 * Obtener anio de Inscripcion
	 * @return anioInscripcion
	 */
	public int getInscripcion() {
		return anioInscripcion;
	}

	/**
	 * Cambiar anio de inscripcion 
	 * @param inscripcion nuevo anio de inscripcion
	 */
	public void setInscripcion(int inscripcion) {
		this.anioInscripcion = inscripcion;
	}

	/**
	 * Convierte el objeto a string
	 * @return stringmatriculacion
	 */
	@Override
	public String toString() {
		return "Matriculacion [idcurso=" + idMatricula + ", estudiante=" + estudiante + ", carrera=" + carrera
				+ ", graduado=" + anioGraduado  + "]";
	}


}
