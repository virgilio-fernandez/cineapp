package com.cine.springboot.app.model.service;

import java.sql.Time;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IFacturaDao;
import com.cine.springboot.app.model.entity.Factura;
import com.cine.springboot.app.model.entity.Usuario;
import com.cine.springboot.app.model.entity.Venta;

@Service
public class FacturaService implements IFacturaService {
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional()
	@Override
	public void save(Factura factura) {
		// TODO Auto-generated method stub
		facturaDao.save(factura);
	}
	
	@Transactional()
	public void  guardar(int idDosificacion, int idVenta, Date fecha, int numero, int idInformacion ) {
		    
	     jdbcTemplate.update("insert into facturas(id_dosificacion, id_venta, fecha, numero, id_informacion) values (?,?,?,?,?)",idDosificacion,idVenta,fecha,numero, idInformacion );
	     
	 }

	@Override
	public Factura buscarFacturaPorVenta(int venta) {
		// TODO Auto-generated method stub
		return (Factura) facturaDao.buscarPorId(venta);
	}
	
/*	@Transactional(readOnly = true)
	public Factura buscarFacturaPorVenta(int idVenta) {
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Factura.class);
		String xsql = "select * from facturas   where id_venta=?;";
		return (Factura) this.jdbcTemplate.queryForObject(xsql, new Object[] { idVenta }, bprm);
	}
*/
}
