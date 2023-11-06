package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

//@IdClass (IdFactura.class)  
@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	
	
	
	@EmbeddedId
	private IdFactura clave;

	@JoinColumn(name = "idVenta", insertable = true, updatable = true)
	@MapsId("idVenta")
	@ManyToOne
	private Venta venta;

	@JoinColumn(name = "idDosificacion", insertable = true, updatable = true)
	@MapsId("idDosificacion")
	@ManyToOne
	private Dosificacion dosificacion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private int numero;
	
	@OneToOne
	@JoinColumn(name = "id_informacion")
	private InformacionCine cine;
	
	

	

	public InformacionCine getCine() {
		return cine;
	}



	public void setCine(InformacionCine cine) {
		this.cine = cine;
	}



	public IdFactura getClave() {
		return clave;
	}



	public void setClave(IdFactura clave) {
		this.clave = clave;
	}



	public Venta getVenta() {
		return venta;
	}



	public void setVenta(Venta venta) {
		this.venta = venta;
	}



	public Dosificacion getDosificacion() {
		return dosificacion;
	}



	public void setDosificacion(Dosificacion dosificacion) {
		this.dosificacion = dosificacion;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	






	@Override
	public String toString() {
		return "Factura [clave=" + clave + ", venta=" + venta + ", dosificacion=" + dosificacion + ", fecha=" + fecha
				+ ", numero=" + numero + ", cine=" + cine + "]";
	}










	private static final long serialVersionUID = 1L;

}
