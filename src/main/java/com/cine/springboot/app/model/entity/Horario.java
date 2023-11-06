/**
 *  Clase de modelo que representa un horario para una determinada pelicula.
 */
package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "horarios")
public class Horario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date hora;
	private double precio;
	private int asientosDisponibles;
	
	private Boolean estado;
	
  
/*	
	@JoinColumn(name="idPelicula", insertable=true, updatable=true)
    @OneToOne
	private Pelicula pelicula;

    @JoinColumn(name="idTipo", insertable=true, updatable=true)
    @OneToOne
	private Tipo tipo;
 */

	// Relacion Muchos a Uno -> Muchos horarios para una pelicula
	@ManyToOne()
	 @JoinColumns({
	        @JoinColumn(name="idTipo", referencedColumnName="idTipo"),
	        @JoinColumn(name="idPelicula", referencedColumnName="idPelicula")
	    })
	private TipoPelicula peliculaTipo;
	//	@JoinColumn(name = "idPelicula") // foreignKey en la tabla de Horarios

	
	@ManyToOne
	@JoinColumn(name = "idSala") // foreignKey en la tabla de Horarios
	private Sala sala;

	/**
	 * Constructor sin parametros
	 */
	public Horario() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

/*	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
*/
	
	public double getPrecio() {
		return precio;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	public Sala getSala() {
		return sala;
	}
	
	
    public TipoPelicula getPeliculaTipo() {
		return peliculaTipo;
	}

	public void setPeliculaTipo(TipoPelicula peliculaTipo) {
		this.peliculaTipo = peliculaTipo;
	}

	
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	


	
	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}





	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}





	private static final long serialVersionUID = 1L;
}
