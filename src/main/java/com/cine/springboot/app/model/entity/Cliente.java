package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@NotNull
//	private int nit;
	@Column(length = 20)
	private String nit;

//	@NotEmpty
	@Column(length = 40)
	private String nombre;
	@Column(length = 30)
	private String apellido1;
	
	@Column(length = 30)
	private String apellido2;

//	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;
	
	private boolean estado = true;
/*
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Venta> ventas;
*/
	public Cliente() {
//		ventas = new ArrayList<Venta>();
	}

	

	
	public String getNombre() {
		return nombre;
	}

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




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	
	@Override
	public String toString() {
		return nombre;
	}
	

	public String getApellido1() {
		return apellido1;
	}




	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}




	public String getApellido2() {
		return apellido2;
	}




	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public boolean getEstado() {
		return estado;
	}




	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	private static final long serialVersionUID = 1L;
}
