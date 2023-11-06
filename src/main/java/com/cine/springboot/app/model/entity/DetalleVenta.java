package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "detalle_venta")
public class DetalleVenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Integer cantidad;
	private Double pUnit;
	private Double subtotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_horario")
	private Horario horario;
	
	@ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name="idVenta")
	private Venta venta;
	
	@OneToOne
	@JoinColumn(name = "id_descuento")
	private Descuento descuento;
	
	@OneToMany(mappedBy = "detalleVenta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Boleto> boletos;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Double getpUnit() {
		return pUnit;
	}


	public void setpUnit(Double pUnit) {
		this.pUnit = pUnit;
	}


	public Double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}


	public Horario getHorario() {
		return horario;
	}


	public void setHorario(Horario horario) {
		this.horario = horario;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	public Descuento getDescuento() {
		return descuento;
	}


	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}


	public List<Boleto> getBoletos() {
		return boletos;
	}


	public void setBoletos(List<Boleto> boletos) {
		this.boletos = boletos;
	}


	private static final long serialVersionUID = 1L;

}