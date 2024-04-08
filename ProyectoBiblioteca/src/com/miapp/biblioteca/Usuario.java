package com.miapp.biblioteca;

import java.util.ArrayList;

public class Usuario {
	
	//Attributes
	private String nombre;
	private String id;
	private ArrayList<Libro> librosPrestados;
	private ArrayList<String> notificaciones;
		
	
	//Constructor
	public Usuario(String nombre, String id) {
		this.nombre = nombre;
		this.id = id;
		this.librosPrestados = new ArrayList<>();
		this.notificaciones = new ArrayList<>();
	}


	//Constructor
	public Usuario() {
		this.librosPrestados = new ArrayList<>();
		this.notificaciones = new ArrayList<>();
	}


	//Getters y Setters	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ArrayList<Libro> getLibrosPrestados() {
		return librosPrestados;
	}
	
	
	public ArrayList<String> getNotificaciones() {
		return notificaciones;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", id=" + id + /*", librosPrestados=" + librosPrestados +*/ "]";
	}

}
