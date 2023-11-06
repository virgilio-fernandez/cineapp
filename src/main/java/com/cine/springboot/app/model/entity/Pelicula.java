package com.cine.springboot.app.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table (name="peliculas")
public class Pelicula implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	@Column(length = 100)
	private String titulo;
	private int duracion;
	
	@Column(length = 1)
	private String clasificacion;
	@Column(length = 25)
	private String genero;
	@Column(length = 50)
	private String imagen = "cinema.png"; // imagen por default
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaEstreno;
	private Boolean estado;
	// @Transient // ignorar este atributo durante la persistencia
	// Relacion Uno a Uno -> Una pelicula tiene un registro de detalle
	@OneToOne(cascade = { CascadeType.ALL})
	@JoinColumn(name = "idDetalle") // foreignKey en la tabla de Peliculas
	private Detalle detalle;
	
/*	@JoinTable(
	        name = "peliculas_tipos",
	        joinColumns = @JoinColumn(name = "id_pelicula", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="id_tipo", nullable = false)
	    )
	 @ManyToMany(cascade = CascadeType.ALL)
	private List<TipoPelicula> tipos;
*/

/*	
	@ManyToOne
	@JoinColumn(name = "idTipo") // foreignKey en la tabla de tipos
	private TipoPelicula tipo;
*/	
/*	@OneToMany
	@JoinColumn(name = "idTipo") // foreignKey en la tabla de tipos
	private TipoPelicula tipo;
*/
	/* En realidad en la aplicacion, no se ocupa la consulta de TODOS los horarios por idPelicula.
	 * La consulta que se ocupa es TODOS LOS HORARIOS POR FECHA para una determinada pelicula.
	 * Por eso, dejamos comentada dicha relacion, aqui en la clase Pelicula.
	 * Con esto nos evitamos un left outer join Horarios on pelicula.id=horarios.idPelicula 
	 */
	// Relacion Uno a Muchos -> Una pelicula tiene muchos horarios
//	@OneToMany(mappedBy = "pelicula", fetch = FetchType.EAGER)
//	private List<Horario> horarios;

	/**
	 * Constructor sin parametros
	 */
	public Pelicula() {
//		tipos  = new ArrayList<TipoPelicula>();

	}
/*	
	public TipoPelicula getTipo() {
		return tipo;
	}

	public void setTipo(TipoPelicula tipo) {
		this.tipo = tipo;
	}
*/
	
	public int getId() {
		return id;
	}

	

	
	

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	
	

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}



	private static final long serialVersionUID = 1L;

	
	
	

}
