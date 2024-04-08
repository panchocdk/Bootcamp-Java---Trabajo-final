package com.miapp.biblioteca.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.biblioteca.Libro;

public class LibroServicio {
	
	private ArrayList<Libro> biblioteca; //ArrayList que contiene los libros

	
	public LibroServicio(ArrayList<Libro> biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	
	//Crear un nuevo libro
	public void crearLibro(String titulo, String autor, String isbn, String genero) {
		Libro nuevoLibro = new Libro();
		
		nuevoLibro.setTitulo(titulo);
		nuevoLibro.setAutor(autor);
		nuevoLibro.setIsbn(isbn);
		nuevoLibro.setGenero(genero);
		nuevoLibro.setDisponible(true);
				
		biblioteca.add(nuevoLibro);
		System.out.println();
		System.out.println("Libro creado exitosamente");
	}
	
	
	//Leer todos los libros
	public ArrayList<Libro> obtenerTodosLosLibros() {
		return biblioteca;
	}
	
	
	//Actualizar un libro
	public void actualizarLibro(String isbn, String nuevoTitulo, String nuevoAutor, String nuevoGenero) {
		for (Libro libro : biblioteca) {
			if (libro.getIsbn().equals(isbn)) {
				libro.setTitulo(nuevoTitulo);
				libro.setAutor(nuevoAutor);
				libro.setGenero(nuevoGenero);
				System.out.println();
				System.out.println("Libro actualizado exitosamente");
			}
		}
	}
	
	
	//Eliminar un libro
	public void eliminarLibro(String isbn) {
		Iterator<Libro> it = biblioteca.iterator();
		while (it.hasNext()) {
			if (it.next().getIsbn().equals(isbn)) {
				it.remove();
			}
		}
	}
	
	
	//Buscar un libro por Isbn
	public Libro buscarLibroPorISBN(String isbn) {
		for (Libro libro : biblioteca) {
			if (libro.getIsbn().equals(isbn)) {
				return libro;
			}
		}
		return null;
	}
	
	
	//Buscar libros por titulo
	public ArrayList<Libro> buscarLibrosPorTitulo(String titulo) {
		ArrayList<Libro> librosEncontrados = new ArrayList<>();
		for (Libro libro : biblioteca) {
			//if (libro.getTitulo().equalsIgnoreCase(titulo)) {
			if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
				librosEncontrados.add(libro);
			}
		}
		return librosEncontrados;
	}
	
	
	//Verificar la disponibilidad de un libro
	public boolean verificarDisponibilidad(Libro libro) {
		return libro.isDisponible();
	}
	
	
	//Dejar una calificacion
	public void calificar(String isbn, int calificacion) {
		for (Libro libro : biblioteca) {
			if (libro.getIsbn().equals(isbn)) {
				libro.getCalificaciones().add(calificacion);
				System.out.println();
				System.out.println("Gracias por dejar su calificacion");
			}
		}
	}
	
	
	//Dejar una reseña
	public void reseñar(String isbn, String reseña) {
		for (Libro libro : biblioteca) {
			if (libro.getIsbn().equals(isbn)) {
				libro.getReseñas().add(reseña);
				System.out.println();
				System.out.println("Gracias por dejar su reseña");
			}
		}
	}
	 
	 	
	//Mostrar promedio de calificaciones
	public double promedioCalificaciones(Libro libro) {
		int suma = 0;
		for (int calificacion : libro.getCalificaciones()) {
			suma += calificacion;
		}
		if (libro.getCalificaciones().size() > 0) {
			return (double) suma / libro.getCalificaciones().size();
		} else {
			return 0;
		}
	}
	
	
	//Mostrar las calificaciones
	public ArrayList<Integer> calificacionesPorLibro(Libro libro) {
		return libro.getCalificaciones();
	}
	
	
	//Mostrar las reseñas
	public ArrayList<String> reseñasPorLibro(Libro libro) {
		return libro.getReseñas();
	}    
}
