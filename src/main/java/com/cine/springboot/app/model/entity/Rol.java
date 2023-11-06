package com.cine.springboot.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "rol" }) })
public class Rol implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 25)
	private String rol;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String authority) {
		this.rol = authority;
	}

	

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	 * private int id;
	 * 
	 * @Column(length = 20) private String nombre;
	 * 
	 * @Column(length = 10) private String estatus;
	 * 
	 * 
	 * public int getId() { return id; }
	 * 
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * 
	 * public String getNombre() { return nombre; }
	 * 
	 * 
	 * public void setNombre(String nombre) { this.nombre = nombre; }
	 * 
	 * 
	 * public String getEstatus() { return estatus; }
	 * 
	 * 
	 * public void setEstatus(String estatus) { this.estatus = estatus; }
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
