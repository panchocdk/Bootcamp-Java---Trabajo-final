package com.miapp.biblioteca;

import java.util.ArrayList;

public class Libro {
	
	//Attributes
	private String titulo;
	private String autor; 
	private String isbn;
	private String genero;
	private boolean disponible;
	private ArrayList<Integer> calificaciones;
	private ArrayList<String> reseñas;
	
	
	//Constructor
	public Libro(String titulo, String autor, String isbn, String genero) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.genero = genero;
		this.disponible = true;
		this.calificaciones = new ArrayList<>();
		this.reseñas = new ArrayList<>();
	}


	//Constructor
	public Libro() {
		this.calificaciones = new ArrayList<>();
		this.reseñas = new ArrayList<>();
	}


	//Getters y Setters
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
	public ArrayList<Integer> getCalificaciones() {
		return calificaciones;
	}
	
	
	public ArrayList<String> getReseñas() {
		return reseñas;
	}


	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn + ", genero=" + genero
				+ ", disponible=" + disponible + "]";
	}
	
		
}
