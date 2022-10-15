package Integrador3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Carrera {
	
	/**
	 * Identificador unico de la carrera
	 */
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_carrera;

    /**
     * Nombre de la carrera
     */
    @Column(nullable=false)
    private String nombre;
    
    /**
     * Duracion de la carrera
     */
    @Column
    private int duracion;

    /**
     * Lista de matriculaciones de la carrera
     */
    @OneToMany(mappedBy = "carrera",fetch = FetchType.EAGER) //cambio temporal por error
    @JsonIgnore
    private List<Matriculacion> matriculaciones;
    
    
    /**
     * Constructor
     * @param nombre nombre de la carrera
     * @param duracion duracion de la carrera
     */
	public Carrera( String nombre, int duracion) {
		this.nombre = nombre;
		this.duracion=duracion;
		this.matriculaciones = new ArrayList<Matriculacion>();
	}

	/**
	 * Constructor
	 */
	public Carrera() {
	}
	
	public int cantMatriculaciones() {
		return this.matriculaciones.size();
	}
	
	
	public List<Matriculacion> getMatriculaciones(){
		return this.matriculaciones;
	}
	

	/**
	 * Oobetener nombre de la carrera
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambiar nombre de la carrera
	 * @param nombre nuevo nombre de la carrera
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener identificador de la carrera
	 * @return
	 */
	public Long getId_carrera() {
		return id_carrera;
	}

	/**
	 * Agregar matriculacion
	 * @param mat nueva matriculacion
	 */
	public void agregarMatriculacion(Matriculacion mat){
		matriculaciones.add(mat);
	}

	/**
	 * Obtener duracion de la carrera
	 * @return duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Cambiar duracion de la carrera
	 * @param duracion nueva duracion de carrera
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * Convierte el objeto a string
	 * @return stringCarrera
	 */
	@Override
	public String toString() {
		return "Carrera [id_carrera=" + id_carrera + ", nombre=" + nombre + ", duracion=" + duracion + ", matriculaciones=" + matriculaciones.size()
				+ "]";
	}
	
	public void setMatriculaciones(List<Matriculacion> matriculaciones2) {
		this.matriculaciones = matriculaciones2;		
	}

}
