package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table (name="salas")
public class Sala implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	@Column(length = 50)
	private String nombre;
	private int nAsientos;
	private int tLimpieza;

	private Boolean estado;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getnAsientos() {
		return nAsientos;
	}
	public void setnAsientos(int nAsientos) {
		this.nAsientos = nAsientos;
	}
	public int gettLimpieza() {
		return tLimpieza;
	}
	public void settLimpieza(int tLimpieza) {
		this.tLimpieza = tLimpieza;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	

	private static final long serialVersionUID = 1L;

}
