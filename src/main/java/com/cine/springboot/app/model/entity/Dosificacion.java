package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="dosificacion")
public class Dosificacion implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaEmision;
	
	@Column(length = 60)
	private String llaveDosificacion;
	private int nAutorizacion;
	private int nInicio;
	private int nFin;

	private Boolean estado;
	
	//@ManyToOne
	//@JoinColumn(name = "nit_cine")
	//private Cine cine;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getLlaveDosificacion() {
		return llaveDosificacion;
	}

	public void setLlaveDosificacion(String llaveDosificacion) {
		this.llaveDosificacion = llaveDosificacion;
	}

	public int getnAutorizacion() {
		return nAutorizacion;
	}

	public void setnAutorizacion(int nAutorizacion) {
		this.nAutorizacion = nAutorizacion;
	}

	public int getnInicio() {
		return nInicio;
	}

	public void setnInicio(int nInicio) {
		this.nInicio = nInicio;
	}

	public int getnFin() {
		return nFin;
	}

	public void setnFin(int nFin) {
		this.nFin = nFin;
	}



	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	
}
