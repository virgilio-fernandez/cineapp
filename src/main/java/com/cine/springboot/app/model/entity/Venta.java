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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="ventas")
public class Venta implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date fecha;
/*	@Column(length = 10)
	private String estado = "Pagada";
*/
	private boolean estado= true;
	//@Temporal(TemporalType.TIME)
//	private String hora;
	//@Temporal(TemporalType.TIME)
	@Column(length =12)
	private String hora;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
/*	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_venta")
	private List<DetalleVenta> items;
*/
	@OneToMany(mappedBy = "venta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DetalleVenta> detalleVenta;
	
	private Double total;
	private Double efectivo;
	private Double cambio;
	
	public Venta() {
//		this.items = new ArrayList<DetalleVenta>();
		this. detalleVenta = new ArrayList<DetalleVenta>();
	}
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/*	public List<DetalleVenta> getItems() {
		return items;
	}
	public void setItems(List<DetalleVenta> items) {
		this.items = items;
	}
	
	public void addItemVenta(DetalleVenta item) {
		this.items.add(item);
	}

	public Double getTotal2() {
		Double total = 0.0;

		int size = items.size();

		for (int i = 0; i < size; i++) {
			total += items.get(i).calcularImporte();
		}
		return total;
	}
*/	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}


	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}
	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	
	public Double getEfectivo() {
		return efectivo;
	}
	public void setEfectivo(Double efectivo) {
		this.efectivo = efectivo;
	}
	public Double getCambio() {
		return cambio;
	}
	public void setCambio(Double cambio) {
		this.cambio = cambio;
	}


	private static final long serialVersionUID = 1L;
}
