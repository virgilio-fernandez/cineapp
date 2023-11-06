package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IBoletosDao;
import com.cine.springboot.app.model.entity.Boleto;

@Service
public class BoletosService implements IBoletosService {
	
	@Autowired
	private IBoletosDao boletosDao;

	@Transactional()
	@Override
	public List<Boleto> listarPorId(int idVenta) {
		// TODO Auto-generated method stub
		return boletosDao.listarPorId(idVenta);
	}

}
