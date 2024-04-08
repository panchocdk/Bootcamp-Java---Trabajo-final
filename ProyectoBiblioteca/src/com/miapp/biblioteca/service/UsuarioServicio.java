package com.miapp.biblioteca.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.Libro;

public class UsuarioServicio {
	
	private ArrayList<Usuario> usuarios; //ArrayList que contiene los usuarios

	public UsuarioServicio(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	//Crear nuevo usuario
	public void crearUsuario(String nombre, String id) {
		Usuario nuevoUsuario = new Usuario();
		
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setId(id);		
		usuarios.add(nuevoUsuario);
		System.out.println();
		System.out.println("Usuario creado exitosamente");
	}
	
	
	//Leer todos los usuarios
	public ArrayList<Usuario> obtenerTodosLosUsuarios() {
		return usuarios;
	}
	
	
	//Actualizar un usuario
	public void actualizarUsuario(String id, String nuevoNombre) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(id)) {
				usuario.setNombre(nuevoNombre);
				System.out.println();
				System.out.println("Usuario actualizado exitosamente");
			}
		}
	}
	
	
	//Eliminar un usuario
	public void eliminarUsuario(String id) {
		Iterator<Usuario> it = usuarios.iterator();
		while (it.hasNext()){
			if (it.next().getId().equals(id)) {
				it.remove();
			}
		}
	}
	
	
	//Prestar un libro a un usuario
	public void prestarLibro(Usuario usuario, Libro libro) {
		if (libro.isDisponible()) {
			usuario.getLibrosPrestados().add(libro);
			libro.setDisponible(false);
		} else {
			System.out.println("El libro no esta disponible para prestamo.");
		}
	}
	
	
	//Busca un usuario por su Id
	public Usuario buscarUsuarioPorId(String id) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(id)) {
				return usuario;
			}
		}
		return null;
	}
	
	
	//Devolver un libro
	public void devolverLibro(Usuario usuario, Libro libro) {
		if (usuario.getLibrosPrestados().contains(libro)) {
			usuario.getLibrosPrestados().remove(libro);
			libro.setDisponible(true);
		} else {
			System.out.println("Este libro no fue prestado por este usuario.");
		}
	}
	
	
	//Libros prestados por un usuario
	public ArrayList<Libro> obtenerLibrosPrestados(Usuario usuario) {
		return usuario.getLibrosPrestados();
	}
	
	
	//Generar una notificacion
	public void generarNotificacion(Usuario usuario, String isbn) {
		String notificacion = "Tienes en prestamo el libro con ISBN: - " + isbn + " -. No olvides devolverlo.";
		usuario.getNotificaciones().add(notificacion);
	}
	
	
	//Notificaciones por usuario
	public ArrayList<String> obtenerNotificaciones(Usuario usuario) {
		return usuario.getNotificaciones();
	}
	
	
	//Eliminar una notificacion
	public void eliminarNotificacion(Usuario usuario, String isbn) {
		ArrayList<String> listaNotificaciones = usuario.getNotificaciones();
		Iterator<String> it = listaNotificaciones.iterator();
		while (it.hasNext()) {
			String string = it.next();
			String[] usuarioNot = string.split(" ");
			for (String string2 : usuarioNot) {
				if (string2.equals(isbn)) {
					it.remove();
					break;
				}
			}
		}
	}
}
