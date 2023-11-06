package com.cine.springboot.app.model.entity;

import java.io.Serializable;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table (name="tipoPelicula")
public class TipoPelicula implements Serializable{
/*	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
*//*	@Id
	@ManyToOne
	@JoinColumn(name = "idTipo") // foreignKey en la tabla de tipos
	private Tipo tipo;
	@Id
	@ManyToOne
	@JoinColumn(name = "idPelicula") // foreignKey en la tabla de tipos
	private Pelicula idPelicula;
*/
	    @EmbeddedId
	    private IdTipoPelicula clave;

	    @JoinColumn(name="idPelicula", insertable=true, updatable=true)
	    @MapsId("idPelicula")
	    @ManyToOne
		private Pelicula pelicula;

	    @JoinColumn(name="idTipo", insertable=true, updatable=true)
	    @MapsId("idTipo")
	    @ManyToOne
		private Tipo tipo;

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	public IdTipoPelicula getClave() {
		return clave;
	}
	public void setClave(IdTipoPelicula clave) {
		this.clave = clave;
	}

	private static final long serialVersionUID = 1L;

}
