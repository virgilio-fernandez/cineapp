package com.cine.springboot.app.model.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IUsuarioDao;

import com.cine.springboot.app.model.entity.Rol;
import com.cine.springboot.app.model.entity.Usuario;


@Service("jpaUserDetailsService")
public class DatosUsuarioService implements UserDetailsService{
	
	@Autowired
	private IUsuarioService usuarioService;
	

	
	private Logger logger = LoggerFactory.getLogger(DatosUsuarioService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  Usuario usuario = usuarioService.buscarPorUsername(username);
		 
	        
	        if(usuario == null) {
	        	logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
	        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
	        }
	        
	        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	        
	        for(Rol role: usuario.getRoles()) {
	        	logger.info("Role: ".concat(role.getRol()));
	        	roles.add(new SimpleGrantedAuthority(role.getRol()));
	        }
	        
	        if(roles.isEmpty()) {
	        	logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
	        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
	        }
	        System.out.println(usuario.getUsername()+" "+ usuario.getPassword()+" "+  usuario.getEstado()+" "+roles.toString());
			return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEstado(), true, true, true, roles);

	}

}
