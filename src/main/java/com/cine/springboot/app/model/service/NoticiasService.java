package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.INoticiaDao;
import com.cine.springboot.app.model.entity.Noticia;

@Service
public class NoticiasService implements INoticiaService {

	@Autowired
	private INoticiaDao noticiaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Noticia> listar() {
		// TODO Auto-generated method stub
		return (List<Noticia>) noticiaDao.findAll();
	}
	@Transactional(readOnly = true)
	@Override
	public List<Noticia> listarActivas() {
		// TODO Auto-generated method stub
		return (List<Noticia>) noticiaDao.listarActivas();
	}

	@Transactional()
	@Override
	public void guardar(Noticia noticia) {
		// TODO Auto-generated method stub
		noticiaDao.save(noticia);
	}

	@Transactional(readOnly = true)
	@Override
	public Noticia buscarPorId(int id) {
		// TODO Auto-generated method stub
		return noticiaDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void borrar(int id) {
		// TODO Auto-generated method stub
		noticiaDao.deleteById(id);
	}


}
