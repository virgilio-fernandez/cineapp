package com.cine.springboot.app;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cine.springboot.app.auth.handler.loginExitoso;
import com.cine.springboot.app.model.service.DatosUsuarioService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private loginExitoso successHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private DatosUsuarioService userDetailsService;
/*	@Autowired 
	private DataSource dataSource;
*/	 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/buscar","/uploads/**","/img-peliculas/**","/img-banner/**","/consultarHorarios/**","/proximamente","/consultarDetalle/**","/listarNoticias").permitAll()
		
		.antMatchers("/usuarios/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/banner/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/clientes/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/descuentos/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/dosificacion/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/horarios/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/informacion").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/guardar").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/noticia/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/peliculas/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/reportes/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/salas/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/tipos/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/usuarios/**").hasAnyRole("ADMINISTRADOR") 
		.antMatchers("/ventas/**").hasAnyRole("CAJERO") 
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.successHandler(successHandler)
		.loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
		
	}



	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder)throws Exception {
	
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	

}
