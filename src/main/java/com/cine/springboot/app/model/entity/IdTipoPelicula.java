package com.cine.springboot.app.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IdTipoPelicula implements Serializable {
   
	public int idTipo, idPelicula;
	
	

	public int getIdTipo() {
		return idTipo;
	}



	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}



	public int getIdPelicula() {
		return idPelicula;
	}



	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}



	private static final long serialVersionUID = 1L;
}