package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "boletos")
public class Boleto implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;
	
	@Temporal(TemporalType.TIME)
	private Date hora;
	
//	private int numeroBoleto;
   
	@OneToOne
	@JoinColumn(name = "idHorario") 
	private Horario horario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idDetalleVenta")
	private DetalleVenta detalleVenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}
/*
	public int getNumeroBoleto() {
		return numeroBoleto;
	}

	public void setNumeroBoleto(int numeroBoleto) {
		this.numeroBoleto = numeroBoleto;
	}
*/
	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
