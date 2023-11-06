package com.cine.springboot.app.Utils;

import java.text.DecimalFormat;

public class Reporte {
	
	private int vid;
	private String vestado;
	private String vfecha;
	private String vhora;
	private int dvid;
	private String dvcantidad;
	private String dvpunit;
	private String dvsubtotal;
	private String ptitulo;
	private String tnombre;
	private String taudio;
	private String username;
	private String unombre;
	private String uapellido1;
	private String uapellido2;
	private String cnombre;
	private String capellido1;
	private String capellido2;
	private String dnombre;
	private String dporcentaje;
	
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getVfecha() {
		return vfecha;
	}
	public void setVfecha(String vfecha) {
		this.vfecha = vfecha;
	}
	public String getVhora() {
		return vhora;
	}
	public void setVhora(String vhora) {
		this.vhora = vhora;
	}
	public int getDvid() {
		return dvid;
	}
	public void setDvid(int dvid) {
		this.dvid = dvid;
	}
	public String getDvcantidad() {
		return dvcantidad;
	}
	public void setDvcantidad(String dvcantidad) {
		this.dvcantidad = dvcantidad;
	}
	public String getDvpunit() {
		return dvpunit;
	}
	public void setDvpunit(String dvpunit) {
		this.dvpunit = dvpunit;
	}
	public String getDvsubtotal() {
		return dvsubtotal;
	}
	public void setDvsubtotal(String dvsubtotal) {
		this.dvsubtotal = dvsubtotal;
	}
	public String getPtitulo() {
		return ptitulo;
	}
	public void setPtitulo(String ptitulo) {
		this.ptitulo = ptitulo;
	}
	public String getTnombre() {
		return tnombre;
	}
	public void setTnombre(String tnombre) {
		this.tnombre = tnombre;
	}
	public String getTaudio() {
		return taudio;
	}
	public void setTaudio(String taudio) {
		this.taudio = taudio;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUnombre() {
		return unombre;
	}
	public void setUnombre(String unombre) {
		this.unombre = unombre;
	}
	public String getUapellido1() {
		return uapellido1;
	}
	public void setUapellido1(String uapellido1) {
		this.uapellido1 = uapellido1;
	}
	public String getUapellido2() {
		return uapellido2;
	}
	public void setUapellido2(String uapellido2) {
		this.uapellido2 = uapellido2;
	}
	public String getCnombre() {
		return cnombre;
	}
	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}
	public String getCapellido1() {
		return capellido1;
	}
	public void setCapellido1(String capellido1) {
		this.capellido1 = capellido1;
	}
	public String getCapellido2() {
		return capellido2;
	}
	public void setCapellido2(String capellido2) {
		this.capellido2 = capellido2;
	}
	@Override
	public String toString() {
		return "Reporte [vid=" + vid + ", vfecha=" + vfecha + ", vhora=" + vhora + ", dvid=" + dvid + ", dvcantidad="
				+ dvcantidad + ", dvpunit=" + dvpunit + ", dvsubtotal=" + dvsubtotal + ", ptitulo=" + ptitulo
				+ ", tnombre=" + tnombre + ", taudio=" + taudio + ", username=" + username + ", unombre=" + unombre
				+ ", uapellido1=" + uapellido1 + ", uapellido2=" + uapellido2 + ", cnombre=" + cnombre + ", capellido1="
				+ capellido1 + ", capellido2=" + capellido2 + "]";
	}
	public String getVestado() {
		return vestado;
	}
	public void setVestado(String vestado) {
		this.vestado = vestado;
	}
	public String getDnombre() {
		return dnombre;
	}
	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}
	public String getDporcentaje() {
		return dporcentaje;
	}
	public void setDporcentaje(String dporcentaje) {
		this.dporcentaje = dporcentaje;
	}
	
	
	
	
	

}
