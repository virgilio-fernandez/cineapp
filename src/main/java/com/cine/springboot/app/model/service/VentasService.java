package com.cine.springboot.app.model.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cine.springboot.app.model.dao.IVentasDao;
import com.cine.springboot.app.model.entity.Dosificacion;
import com.cine.springboot.app.model.entity.Factura;
import com.cine.springboot.app.model.entity.Venta;

@Service
public class VentasService implements IVentaService {

	@Autowired
	private IVentasDao ventaDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	@Override
	public List<Venta> listar() {
		// TODO Auto-generated method stub
		return (List<Venta>) ventaDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Venta venta) {
		// TODO Auto-generated method stub
		ventaDao.save(venta);
	}

	@Transactional(readOnly = true)
	@Override
	public Venta buscarPorId(int id) {
		// TODO Auto-generated method stub
		return ventaDao.findById(id).orElse(null);
	}

	/// modificar la consulta que muestre la ultima venta filtradas por vendedorç/*
	/*
	 * *
	 * *(non-Javadoc)
	 * @see com.cine.springboot.app.model.service.IVentaService#buscarVenta()
	 */
	@Transactional(readOnly = true)
	public Venta buscarVenta() {
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Venta.class);
		String xsql = "select * from ventas order by  id desc limit 1;";
		return (Venta) this.jdbcTemplate.queryForObject(xsql, new Object[] {}, bprm);
	}

	@Transactional(readOnly = true)
	public Factura buscarFactura(int idDosificacion) {
		BeanPropertyRowMapper bprm = new BeanPropertyRowMapper(Factura.class);
		String xsql = "select * from facturas   where id_dosificacion=?  order by   fecha desc limit 1;";
		return (Factura) this.jdbcTemplate.queryForObject(xsql, new Object[] { idDosificacion }, bprm);
	}

	
	@Transactional()
	@Override
	public void adicionarBoleto(Date fecha, String hora,int idDetalle, int idHorario) {

		jdbcTemplate.update("insert into boletos(fecha, hora, id_detalle_venta, id_horario) values (?,?,?,?)", fecha,hora,idDetalle,idHorario);

	}
	
	@Transactional()
	public void  anular(int id) {
	    
	     jdbcTemplate.update("update ventas set estado=false where id=?;",id);
	     
	 }
	
	@Transactional()
	@Override
	public void borrarFisico(int id) {
		// TODO Auto-generated method stub
		ventaDao.deleteById(id);
	}
	
}
