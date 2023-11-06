/**
 * Clase de modelo que representa una imagen del Banner (Carousel) de la pagina principal.
 */
package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Banners")
public class Banner implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	
	@Column(length = 100)
	private String titulo; // titulo para indicar el atributo title <img src='' title='?' />
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date fecha; // fecha de publicacion de la imagen
	@Column(length = 70)
	private String archivo; // atributo para guardar el nombre de la imagen
	
	private boolean estado; // posibles valores: Activo, Inactivo 
	
	@ManyToOne()
	@JoinColumn(name = "idUsuario") // foreignKey en la tabla de Peliculas
	private Usuario usuario;
	
	





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	private static final long serialVersionUID = 1L;
}
