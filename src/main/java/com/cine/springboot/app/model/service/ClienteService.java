package com.cine.springboot.app.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cine.springboot.app.model.dao.IClienteDao;
import com.cine.springboot.app.model.entity.Cliente;

@Service
public class ClienteService implements IClienteService{
	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Cliente tipo) {
		// TODO Auto-generated method stub
		clienteDao.save(tipo);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente buscarPorId(int id) { 
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void borrarFisico(int id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Cliente buscarPorNit(String nit) {
		// TODO Auto-generated method stub
		return clienteDao.findByNit(nit);
	}
	
	@Transactional()
	public void  eliminar(int id) {
	    
	     jdbcTemplate.update("update clientes set estado=false where id=?;",id);
	     
	 }

}
