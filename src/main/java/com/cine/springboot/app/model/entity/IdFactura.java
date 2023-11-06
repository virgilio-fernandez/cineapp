package com.cine.springboot.app.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IdFactura implements Serializable{
	
	public int idVenta, idDosificacion;

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdDosificacion() {
		return idDosificacion;
	}

	public void setIdDosificacion(int idDosificacion) {
		this.idDosificacion = idDosificacion;
	}
	
	private static final long serialVersionUID = 1L;

}
