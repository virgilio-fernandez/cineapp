package com.cine.springboot.app.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import com.cine.springboot.app.model.dao.IBannerDao;
import com.cine.springboot.app.model.entity.Banner;





@Service
public class BannerService implements IBannersService{

	
	@Autowired
	private IBannerDao bannerDao;

	@Override
	public void guardar(Banner banner) {
		bannerDao.save(banner);
		
	}

	@Override
	public List<Banner> listar() {
		// TODO Auto-generated method stub
		return (List<Banner>) bannerDao.findAll();
	}

	@Override
	public List<Banner> listarActivos() {
		// TODO Auto-generated method stub
		return bannerDao.findByEstadoOrderByIdDesc(true);
	}

	@Override
	public void eliminar(int idBanner) {
		bannerDao.deleteById(idBanner);
		
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		// TODO Auto-generated method stub
		return bannerDao.findById(idBanner).orElse(null);
	}
	
}