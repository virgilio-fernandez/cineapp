package com.cine.springboot.app.model.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cine.springboot.app.Utils.Reporte;
import com.cine.springboot.app.Utils.Total;
import com.cine.springboot.app.model.entity.Pelicula;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;


@Service
public class ReportesService implements IReporteService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Reporte> ListarVentas(Date fecha1,Date fecha2){
		String sql="select v.id,v.estado,v.fecha,v.hora,dv.id,dv.cantidad,dv.p_unit,dv.subtotal,p.titulo,t.nombre,t.audio, u.username,u.nombre,u.apellido1,u.apellido2, c.nombre, c.apellido1,c.apellido2, d.nombre,d.porcentaje" + 
				" from ventas v inner join detalle_venta dv, horarios h ,peliculas p, tipos t, usuarios u,clientes c, descuentos d" + 
				" where v.fecha>=? and v.fecha<=? and dv.id_venta=v.id" + 
				" and dv.id_horario= h.id and h.id_pelicula=p.id and h.id_tipo=t.id and v.id_usuario=u.id and v.id_cliente=c.id and dv.id_descuento=d.id;";		
		
		List report = this.jdbcTemplate.query(sql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					Reporte r =new Reporte();
					r.setVid(rs.getInt("v.id"));
					r.setVestado(rs.getString("v.estado"));
					r.setVfecha(rs.getString("v.fecha"));
					r.setVhora(rs.getString("v.hora"));
					r.setDvid(rs.getInt("dv.id"));
					r.setDvcantidad(rs.getString("dv.cantidad"));
					r.setDvpunit(rs.getString("dv.p_unit"));
					r.setDvsubtotal(rs.getString("dv.subtotal"));
					r.setPtitulo(rs.getString("p.titulo"));
					r.setTnombre(rs.getString("t.nombre"));
					r.setTaudio(rs.getString("t.audio"));
					r.setUsername(rs.getString("u.username"));
					r.setUnombre(rs.getString("u.nombre"));
					r.setUapellido1(rs.getString("u.apellido1"));
					r.setUapellido2(rs.getString("u.apellido2"));
					r.setCnombre(rs.getString("c.nombre"));
					r.setCapellido1(rs.getString("c.apellido1"));
					r.setCapellido2(rs.getString("c.apellido2"));
					r.setDnombre(rs.getString("d.nombre"));
					r.setDporcentaje(rs.getString("d.porcentaje"));				
					return r;
				}
			},new Object[] {fecha1,fecha2});				
		return report;
	}
	
	
	
	public Total calcularTotal(Date fecha1,Date fecha2){
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Total.class); 
		String sql="select sum(subtotal) as total, sum(cantidad) as cantidad " + 
				"from ventas v, detalle_venta dv " + 
				"where v.fecha>=? and v.fecha<=? and v.id=dv.id_venta;";
		return (Total) this.jdbcTemplate.queryForObject(sql,new Object[] {fecha1,fecha2},bprm);
	}
	

	public List<Reporte> ListarVentasPagadas(Date fecha1,Date fecha2){
		String sql="select v.id,v.estado,v.fecha,v.hora,dv.id,dv.cantidad,dv.p_unit,dv.subtotal,p.titulo,t.nombre,t.audio, u.username,u.nombre,u.apellido1,u.apellido2, c.nombre, c.apellido1,c.apellido2, d.nombre,d.porcentaje" + 
				" from ventas v inner join detalle_venta dv, horarios h ,peliculas p, tipos t, usuarios u,clientes c, descuentos d" + 
				" where v.fecha>=? and v.fecha<=? and dv.id_venta=v.id" + 
				" and dv.id_horario= h.id and h.id_pelicula=p.id and h.id_tipo=t.id and v.id_usuario=u.id and v.id_cliente=c.id and dv.id_descuento=d.id and v.estado=true;";		
		
		List report = this.jdbcTemplate.query(sql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					Reporte r =new Reporte();
					r.setVid(rs.getInt("v.id"));
					r.setVestado(rs.getString("v.estado"));
					r.setVfecha(rs.getString("v.fecha"));
					r.setVhora(rs.getString("v.hora"));
					r.setDvid(rs.getInt("dv.id"));
					r.setDvcantidad(rs.getString("dv.cantidad"));
					r.setDvpunit(rs.getString("dv.p_unit"));
					r.setDvsubtotal(rs.getString("dv.subtotal"));
					r.setPtitulo(rs.getString("p.titulo"));
					r.setTnombre(rs.getString("t.nombre"));
					r.setTaudio(rs.getString("t.audio"));
					r.setUsername(rs.getString("u.username"));
					r.setUnombre(rs.getString("u.nombre"));
					r.setUapellido1(rs.getString("u.apellido1"));
					r.setUapellido2(rs.getString("u.apellido2"));
					r.setCnombre(rs.getString("c.nombre"));
					r.setCapellido1(rs.getString("c.apellido1"));
					r.setCapellido2(rs.getString("c.apellido2"));
					r.setDnombre(rs.getString("d.nombre"));
					r.setDporcentaje(rs.getString("d.porcentaje"));				
					return r;
				}
			},new Object[] {fecha1,fecha2});				
		return report;
	}
	
	public Total calcularTotalPagadas(Date fecha1,Date fecha2){
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Total.class); 
		String sql="select sum(subtotal) as total, sum(cantidad) as cantidad " + 
				"from ventas v, detalle_venta dv " + 
				"where v.fecha>=? and v.fecha<=? and v.id=dv.id_venta and v.estado=true;";
		return (Total) this.jdbcTemplate.queryForObject(sql,new Object[] {fecha1,fecha2},bprm);
	}
	
	
	public List<Reporte> ListarVentasAnuladas(Date fecha1,Date fecha2){
		String sql="select v.id,v.estado,v.fecha,v.hora,dv.id,dv.cantidad,dv.p_unit,dv.subtotal,p.titulo,t.nombre,t.audio, u.username,u.nombre,u.apellido1,u.apellido2, c.nombre, c.apellido1,c.apellido2, d.nombre,d.porcentaje" + 
				" from ventas v inner join detalle_venta dv, horarios h ,peliculas p, tipos t, usuarios u,clientes c, descuentos d" + 
				" where v.fecha>=? and v.fecha<=? and dv.id_venta=v.id" + 
				" and dv.id_horario= h.id and h.id_pelicula=p.id and h.id_tipo=t.id and v.id_usuario=u.id and v.id_cliente=c.id and dv.id_descuento=d.id and v.estado=false;";		
		
		List report = this.jdbcTemplate.query(sql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					Reporte r =new Reporte();
					r.setVid(rs.getInt("v.id"));
					r.setVestado(rs.getString("v.estado"));
					r.setVfecha(rs.getString("v.fecha"));
					r.setVhora(rs.getString("v.hora"));
					r.setDvid(rs.getInt("dv.id"));
					r.setDvcantidad(rs.getString("dv.cantidad"));
					r.setDvpunit(rs.getString("dv.p_unit"));
					r.setDvsubtotal(rs.getString("dv.subtotal"));
					r.setPtitulo(rs.getString("p.titulo"));
					r.setTnombre(rs.getString("t.nombre"));
					r.setTaudio(rs.getString("t.audio"));
					r.setUsername(rs.getString("u.username"));
					r.setUnombre(rs.getString("u.nombre"));
					r.setUapellido1(rs.getString("u.apellido1"));
					r.setUapellido2(rs.getString("u.apellido2"));
					r.setCnombre(rs.getString("c.nombre"));
					r.setCapellido1(rs.getString("c.apellido1"));
					r.setCapellido2(rs.getString("c.apellido2"));
					r.setDnombre(rs.getString("d.nombre"));
					r.setDporcentaje(rs.getString("d.porcentaje"));				
					return r;
				}
			},new Object[] {fecha1,fecha2});				
		return report;
	}
	
	public Total calcularTotalAnuladas(Date fecha1,Date fecha2){
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Total.class); 
		String sql="select sum(subtotal) as total, sum(cantidad) as cantidad " + 
				"from ventas v, detalle_venta dv " + 
				"where v.fecha>=? and v.fecha<=? and v.id=dv.id_venta and v.estado=false;";
		return (Total) this.jdbcTemplate.queryForObject(sql,new Object[] {fecha1,fecha2},bprm); 
	}
	
	public List<Pelicula> ListarPeliculas(Date fecha1,Date fecha2){ 
		String sql="select * from peliculas p where exists (select * from  detalle_venta dv inner join ventas v, horarios h  where dv.id_horario=h.id and h.id_pelicula=p.id and v.fecha>=? and v.fecha<=?);";		
		
		List Pelicula = this.jdbcTemplate.query(sql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					Pelicula p =new Pelicula();	
					p.setId(rs.getInt("id"));
					p.setClasificacion(rs.getString("clasificacion"));
					p.setEstado(rs.getBoolean("estado"));
					p.setFechaEstreno(rs.getDate("fecha_estreno"));
					p.setGenero(rs.getString("genero"));
					p.setDuracion(rs.getInt("duracion"));
					p.setImagen(rs.getString("imagen"));
					p.setTitulo(rs.getString("titulo"));
					//falta ver el detalle pero puede no ser necesario
					
					return p;
				}
			},new Object[] {fecha1,fecha2});				
		return Pelicula;
	}
	
	
	public List<Reporte> ListarVentasPorPelicula(Date fecha1,Date fecha2,int idPelicula){
		String sql="select v.id,v.estado,v.fecha,v.hora,dv.id,dv.cantidad,dv.p_unit,dv.subtotal,p.titulo,t.nombre,t.audio, u.username,u.nombre,u.apellido1,u.apellido2, c.nombre, c.apellido1,c.apellido2, d.nombre,d.porcentaje" + 
				" from ventas v inner join detalle_venta dv, horarios h ,peliculas p, tipos t, usuarios u,clientes c, descuentos d" + 
				" where v.fecha>=? and v.fecha<=? and dv.id_venta=v.id" + 
				" and dv.id_horario= h.id and h.id_pelicula=p.id and h.id_tipo=t.id and v.id_usuario=u.id and v.id_cliente=c.id and dv.id_descuento=d.id and p.id=?;";		
		
		List report = this.jdbcTemplate.query(sql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					Reporte r =new Reporte();
					r.setVid(rs.getInt("v.id"));
					r.setVestado(rs.getString("v.estado"));
					r.setVfecha(rs.getString("v.fecha"));
					r.setVhora(rs.getString("v.hora"));
					r.setDvid(rs.getInt("dv.id"));
					r.setDvcantidad(rs.getString("dv.cantidad"));
					r.setDvpunit(rs.getString("dv.p_unit"));
					r.setDvsubtotal(rs.getString("dv.subtotal"));
					r.setPtitulo(rs.getString("p.titulo"));
					r.setTnombre(rs.getString("t.nombre"));
					r.setTaudio(rs.getString("t.audio"));
					r.setUsername(rs.getString("u.username"));
					r.setUnombre(rs.getString("u.nombre"));
					r.setUapellido1(rs.getString("u.apellido1"));
					r.setUapellido2(rs.getString("u.apellido2"));
					r.setCnombre(rs.getString("c.nombre"));
					r.setCapellido1(rs.getString("c.apellido1"));
					r.setCapellido2(rs.getString("c.apellido2"));
					r.setDnombre(rs.getString("d.nombre"));
					r.setDporcentaje(rs.getString("d.porcentaje"));				
					return r;
				}
			},new Object[] {fecha1,fecha2,idPelicula});				
		return report;
	}
	
	public Total calcularTotalPorPelicula(Date fecha1,Date fecha2,int idPelicula){
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Total.class); 
		String sql="select sum(subtotal) as total, sum(cantidad) as cantidad " + 
				"from ventas v, detalle_venta dv , peliculas p , horarios h " + 
				"where v.fecha>=? and v.fecha<=? and v.id=dv.id_venta  and dv.id_horario= h.id and h.id_pelicula=p.id and p.id=?;";
		return (Total) this.jdbcTemplate.queryForObject(sql,new Object[] {fecha1,fecha2,idPelicula},bprm);
	}
	
	public List<Reporte> ListarVentasPagadasPorPelicula(Date fecha1,Date fecha2,int idPelicula){
		String sql="select v.id,v.estado,v.fecha,v.hora,dv.id,dv.cantidad,dv.p_unit,dv.subtotal,p.titulo,t.nombre,t.audio, u.username,u.nombre,u.apellido1,u.apellido2, c.nombre, c.apellido1,c.apellido2, d.nombre,d.porcentaje" + 
				" from ventas v inner join detalle_venta dv, horarios h ,peliculas p, tipos t, usuarios u,clientes c, descuentos d" + 
				" where v.fecha>=? and v.fecha<=? and dv.id_venta=v.id" + 
				" and dv.id_horario= h.id and h.id_pelicula=p.id and h.id_tipo=t.id and v.id_usuario=u.id and v.id_cliente=c.id and dv.id_descuento=d.id and v.estado=true and p.id=?;";		
		
		List report = this.jdbcTemplate.query(sql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					Reporte r =new Reporte();
					r.setVid(rs.getInt("v.id"));
					r.setVestado(rs.getString("v.estado"));
					r.setVfecha(rs.getString("v.fecha"));
					r.setVhora(rs.getString("v.hora"));
					r.setDvid(rs.getInt("dv.id"));
					r.setDvcantidad(rs.getString("dv.cantidad"));
					r.setDvpunit(rs.getString("dv.p_unit"));
					r.setDvsubtotal(rs.getString("dv.subtotal"));
					r.setPtitulo(rs.getString("p.titulo"));
					r.setTnombre(rs.getString("t.nombre"));
					r.setTaudio(rs.getString("t.audio"));
					r.setUsername(rs.getString("u.username"));
					r.setUnombre(rs.getString("u.nombre"));
					r.setUapellido1(rs.getString("u.apellido1"));
					r.setUapellido2(rs.getString("u.apellido2"));
					r.setCnombre(rs.getString("c.nombre"));
					r.setCapellido1(rs.getString("c.apellido1"));
					r.setCapellido2(rs.getString("c.apellido2"));
					r.setDnombre(rs.getString("d.nombre"));
					r.setDporcentaje(rs.getString("d.porcentaje"));				
					return r;
				}
			},new Object[] {fecha1,fecha2,idPelicula});				
		return report;
	}
	
	public Total calcularTotalPagadasPorPelicula(Date fecha1,Date fecha2, int idPelicula){
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Total.class); 
		String sql="select sum(subtotal) as total, sum(cantidad) as cantidad " + 
				"from ventas v, detalle_venta dv , peliculas p , horarios h " + 
				"where v.fecha>=? and v.fecha<=? and v.id=dv.id_venta  and dv.id_horario= h.id and h.id_pelicula=p.id and p.id=? and v.estado=true;";
		return (Total) this.jdbcTemplate.queryForObject(sql,new Object[] {fecha1,fecha2,idPelicula},bprm);
	}
	
	public List<Reporte> ListarVentasAnuladasPorPelicula(Date fecha1,Date fecha2,int idPelicula){
		String sql="select v.id,v.estado,v.fecha,v.hora,dv.id,dv.cantidad,dv.p_unit,dv.subtotal,p.titulo,t.nombre,t.audio, u.username,u.nombre,u.apellido1,u.apellido2, c.nombre, c.apellido1,c.apellido2, d.nombre,d.porcentaje" + 
				" from ventas v inner join detalle_venta dv, horarios h ,peliculas p, tipos t, usuarios u,clientes c, descuentos d" + 
				" where v.fecha>=? and v.fecha<=? and dv.id_venta=v.id" + 
				" and dv.id_horario= h.id and h.id_pelicula=p.id and h.id_tipo=t.id and v.id_usuario=u.id and v.id_cliente=c.id and dv.id_descuento=d.id and v.estado=false and p.id=?;";		
		
		List report = this.jdbcTemplate.query(sql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					Reporte r =new Reporte();
					r.setVid(rs.getInt("v.id"));
					r.setVestado(rs.getString("v.estado"));
					r.setVfecha(rs.getString("v.fecha"));
					r.setVhora(rs.getString("v.hora"));
					r.setDvid(rs.getInt("dv.id"));
					r.setDvcantidad(rs.getString("dv.cantidad"));
					r.setDvpunit(rs.getString("dv.p_unit"));
					r.setDvsubtotal(rs.getString("dv.subtotal"));
					r.setPtitulo(rs.getString("p.titulo"));
					r.setTnombre(rs.getString("t.nombre"));
					r.setTaudio(rs.getString("t.audio"));
					r.setUsername(rs.getString("u.username"));
					r.setUnombre(rs.getString("u.nombre"));
					r.setUapellido1(rs.getString("u.apellido1"));
					r.setUapellido2(rs.getString("u.apellido2"));
					r.setCnombre(rs.getString("c.nombre"));
					r.setCapellido1(rs.getString("c.apellido1"));
					r.setCapellido2(rs.getString("c.apellido2"));
					r.setDnombre(rs.getString("d.nombre"));
					r.setDporcentaje(rs.getString("d.porcentaje"));				
					return r;
				}
			},new Object[] {fecha1,fecha2,idPelicula});				
		return report;
	}
	
	public Total calcularTotalAnuladasPorPelicula(Date fecha1,Date fecha2,int idPelicula){
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Total.class); 
		String sql="select sum(subtotal) as total, sum(cantidad) as cantidad " + 
				"from ventas v, detalle_venta dv , peliculas p , horarios h " + 
				"where v.fecha>=? and v.fecha<=? and v.id=dv.id_venta  and dv.id_horario= h.id and h.id_pelicula=p.id and p.id=? and v.estado=false;";
		return (Total) this.jdbcTemplate.queryForObject(sql,new Object[] {fecha1,fecha2,idPelicula},bprm);
	}
}
