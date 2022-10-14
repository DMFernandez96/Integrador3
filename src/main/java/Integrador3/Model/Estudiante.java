package Integrador3.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Grupo1
 * @version Unique Version
 * @category Model
 * 
 */
@Entity
@Data
public class Estudiante {

	/**
	 * Identificador unico
	 */
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_estudiante;

    /**
     * Dni del estudiente
     */
    @Column(name = "dni")
	@GeneratedValue
    private int dni;

    /**
     * Nuemero de libreta
     */
    @Column
    @GeneratedValue
    private int nrolibreta;

   	/**
    * Nombre del estudiante
    */
    @Column(name = "nombre",nullable = false)
    private String nombre;

    /**
     * Apellido del estudiante
     */
    @Column(name = "apellido",nullable = false)
    private String apellido;

    /**
     * Edad del estudiante
     */
    @Column(name = "edad",nullable = false)
    private int edad;

    /**
     * Genero del estudiante
     */
    @Column(name = "genero",nullable = false)
    private char genero;

    /**
     * Ciudad del estudiante
     */
    @Column(name = "ciudad",nullable = false)
    private String ciudad;

    /**
     * Lista de matriculaciones del estudiante
     */
    @OneToMany (mappedBy = "estudiante",fetch = FetchType.LAZY)
    private List<Matriculacion> matriculaciones;

    /**
     * Constructor
     * @param dni identificador del estudiante
     * @param lu libreta del estudiante
     * @param nombre nombre del estudiante
     * @param apellido apellido del estudiante
     * @param edad edad del estudiante
     * @param genero genero del estudiante
     * @param ciudad ciudad dele studiante
     */
    public Estudiante( int dni, int lu,String nombre, String apellido, int edad ,char genero, String ciudad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.ciudad = ciudad;
		this.dni = dni;
		this.nrolibreta = lu;
		this.matriculaciones = new ArrayList<Matriculacion>() ;
	}

    /**
     * Constructor
     */
	public Estudiante() {
	}

	/**
	 * Obtener identificador del estudiente
	 * @return id_estudiante
	 */
	public Long getId_estudiante() {
		return id_estudiante;
	}
	
	/**
	 * Obtener DNI del estudiante
	 * @return dni
	 */	
	public int getDni() {
		return dni;
	}

	/**
	 * Cambiar dni del estudiante
	 * @param dni nuevo dni
	 */
	public void setDni(int dni) {
		this.dni = dni;
	}

	/**
	 * Obtener numbero de libreta
	 * @return nroLibrete
	 */
	public int getNrolibreta() {
		return nrolibreta;
	}

	/**
	 * Obtener nombre del estudiante
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambiar nombre del estudiante
	 * @param nombre nuevo nombre del estudiante
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener apellido del estudiante
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Cambiar apellido del estudiante
	 * @param apellido nuevo apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtener edad del estudiante
	 * @return edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Cambiar edad del estudiante
	 * @param edad nueva edad del estudiante
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Obtener genero del estudiante
	 * @return genero
	 */
	public char getGenero() {
		return genero;
	}

	/**
	 * Cambiar genero del estudiante
	 * @param genero nuevo genero del estudiante
	 */
	public void setGenero(char genero) {
		this.genero = genero;
	}
	
	/**
	 * Obtener ciudad del estudiante
	 * @return cuidad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Cambiar ciudad del estudiante
	 * @param ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtener matriculaciones del estudiante
	 * @return matriculaciones
	 */
	public List<Matriculacion> getMatriculaciones() {
		return matriculaciones;
	}

	/**
	 * Agregar matriculacion a la lista de matriculaciones
	 * @param mat nueva matriculacion
	 */
	public void agregarMatriculacion(Matriculacion mat){
		matriculaciones.add(mat);
	}

	/**
	 * Convierte el objeto a string
	 * @return stringestudiante
	 */
	@Override
	public String toString() {
		return "Estudiante [id_estudiante= " + id_estudiante + ", dni= " + dni + ", nrolibreta= " + nrolibreta
				+ ", nombre= " + nombre + ", apellido= " + apellido + ", edad=" + edad + ", genero= " + genero
				+ ", ciudad= " + ciudad +", carrera= "+ this.getCarrera()+" ]";
	}

	/**
	 * Obtener la carrera
	 * @return nombrecarrera
	 */
	private String getCarrera() {
		if(matriculaciones.size() > 0) {
			for(Matriculacion m: matriculaciones) {
				return m.getNombreCarrera();
			}
		}
		return null;
	}
	
	

}
