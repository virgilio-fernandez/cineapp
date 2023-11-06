package com.cine.springboot.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="informacionCine")
public class InformacionCine implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	
	@Column(length = 20)
	private String nit;
	@Column(length = 60)
	private String nombre;
	@Column(length = 80)
	private String direccion;
	@Column(length = 60)
	private String email;
	@Column(length = 60)
	private String telefono;
	@Column(length = 50)
	private String logo;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLogo() {
		return logo;
	} 

	public void setLogo(String logo) {
		this.logo = logo;
	}

	private static final long serialVersionUID = 1L;

}
