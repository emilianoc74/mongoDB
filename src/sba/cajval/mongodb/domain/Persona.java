package sba.cajval.mongodb.domain;

import java.io.Serializable;

public class Persona implements Serializable {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	private Long id;

	private String nombre;

	private String apellido;

	private int edad;

	private static final long serialVersionUID = 1L;
}
