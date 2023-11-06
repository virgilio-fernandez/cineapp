package com.cine.springboot.app.model.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.springboot.app.model.entity.Banner;

public interface IBannerDao extends JpaRepository<Banner, Integer> {
	
	public List<Banner> findByEstadoOrderByIdDesc(boolean estado);

}
