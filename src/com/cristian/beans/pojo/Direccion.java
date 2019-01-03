package com.cristian.beans.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Direccion {
	private String calle;
	private String cpostal;
	
	public Direccion() {
		// TODO Auto-generated constructor stub
	}

	public Direccion(String calle, String cpostal) {
		
		this.calle = calle;
		this.cpostal = cpostal;
	}
	@Autowired
	
	public void setCalle(@Value("san joaquin")String calle) {
		this.calle = calle;
	}
	@Autowired
	public void setCpostal(@Value("0001")String cpostal) {
		this.cpostal = cpostal;
	}

	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", cpostal=" + cpostal + "]";
	}
	
	/*
	 * @value permite enviar valores al constructor de la dependencia
	 * 
	 */
	
}
