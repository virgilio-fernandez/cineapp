package com.cine.springboot.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Detalles")
public class Detalle implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	@Column(length = 100)
	private String director;
	@Column(length = 255)
	private String actores;
	
	@Column(length = 500)
	private String sinopsis;
	@Column(length = 80)
	private String trailer; // url del video de YouTube

	
	public Detalle() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	@Override
	public String toString() {
		return "Detalle [id=" + id + ", director=" + director + ", actores=" + actores + ", sinopsis=" + sinopsis
				+ ", trailer=" + trailer + "]";
	}

	private static final long serialVersionUID = 1L;

}
