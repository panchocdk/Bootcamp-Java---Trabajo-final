package com.miapp.biblioteca.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.service.LibroServicio;
import com.miapp.biblioteca.service.UsuarioServicio;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Libro> biblioteca = new ArrayList<>();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		LibroServicio libroServicio = new LibroServicio(biblioteca);
		UsuarioServicio usuarioServicio = new UsuarioServicio(usuarios);
		
		Scanner scan = new Scanner(System.in);
		
		int opcion;
		
		do {
			System.out.println("=== Biblioteca Virtual ===");
			System.out.println("1. Crear Libro");
			System.out.println("2. Actualizar Libro");
			System.out.println("3. Buscar Libro por ISBN");
			System.out.println("4. Buscar Libro por Titulo");
			System.out.println("5. Listar Libros");
			System.out.println("6. Eliminar Libro");
			System.out.println("7. Prestamos");
			System.out.println("8. Devoluciones");
			System.out.println("9. Crear Usuario");
			System.out.println("10. Actualizar Usuario");
			System.out.println("11. Buscar Usuario por ID");
			System.out.println("12. Listar Usuarios");
			System.out.println("13. Eliminar Usuario");
			System.out.println("14. Libros Prestados por Usuario");
			System.out.println("15. Calificar un libro");
			System.out.println("16. Reseñar un libro");
			System.out.println("17. Mostrar las calificaciones de un libro");
			System.out.println("18. Mostrar las reseñas de un libro");
			System.out.println("19. Mostrar notificaciones por usuario");
			System.out.println("0. Salir");
			System.out.println();
			System.out.print("Seleccione una opcion: ");
			opcion = scan.nextInt();
			scan.nextLine(); //para consumir el salto de linea
			
			switch (opcion) {
			case 1: 
				//Crear Libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el titulo: ");
				String titulo = scan.nextLine();
				System.out.print("Ingrese el autor: ");
				String autor = scan.nextLine();
				System.out.print("Ingrese ISBN: ");
				String isbn = scan.nextLine();
				System.out.print("Ingrese el genero: ");
				String genero = scan.nextLine();
				libroServicio.crearLibro(titulo, autor, isbn, genero);
				continuar();
				break;
			case 2: 
				//Actualizar Libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el ISBN del libro a actualizar: ");
				String isbnAct = scan.nextLine();
				if (libroServicio.buscarLibroPorISBN(isbnAct) != null) {
					System.out.print("Ingrese nuevo titulo: ");
					String nuevoTitulo = scan.nextLine();
					System.out.print("Ingrese nuevo autor: ");
					String nuevoAutor = scan.nextLine();
					System.out.print("Ingrese nuevo genero: ");
					String nuevogenero = scan.nextLine();
					libroServicio.actualizarLibro(isbnAct, nuevoTitulo, nuevoAutor, nuevogenero);
				} else {
					System.out.println("No hay libro con ese ISBN");
				}
				continuar();
				break;
			case 3:
				//Buscar libro por ISBN
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el ISBN del libro a buscar: ");
				String isbnBus = scan.nextLine();
				Libro libroIsbn = libroServicio.buscarLibroPorISBN(isbnBus);
				if (libroIsbn != null) {
					System.out.println();
					System.out.println("Libro encontrado: " + libroIsbn.getTitulo());
				} else {
					System.out.println();
					System.out.println("Libro no encontrado");
				}
				continuar();
				break;
			case 4: 
				//Buscar libros por titulo
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el titulo a buscar: ");
				String tituloBus = scan.nextLine();
				ArrayList<Libro> librosPorTitulo = libroServicio.buscarLibrosPorTitulo(tituloBus);
				if (!librosPorTitulo.isEmpty()) {
					System.out.println();
					System.out.println("Libros encontrados: ");
					for (Libro libro : librosPorTitulo) {
						System.out.println(libro.getTitulo());
					}
				} else {
					System.out.println();
					System.out.println("Ningun libro encontrado con ese titulo");
				}
				continuar();
				break;
			case 5:
				//Listar libros
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				ArrayList<Libro> listarLibros = libroServicio.obtenerTodosLosLibros();
				System.out.println();
				System.out.println("Inventario: ");
				for (Libro libro : listarLibros) {
					System.out.println(libro.getTitulo() + " (" + libro.getIsbn() + ")");
				}
				continuar();
				break;
			case 6:
				//Eliminar libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el ISBN del libro a eliminar: ");
				String isbnDel = scan.nextLine();
				Libro libroDel = libroServicio.buscarLibroPorISBN(isbnDel);
				if (libroDel != null) {
					if (libroDel.isDisponible()) {
						libroServicio.eliminarLibro(isbnDel);
						System.out.println();
						System.out.println("Libro eliminado correctamente.");
					} else {
						System.out.println();
						System.out.println("No se puede eliminar un libro prestado");
					}
				} else {
					System.out.println();
					System.out.println("No existe libro con ese ISBN");
				}
				continuar();
				break;
			case 7: 
				//Prestar un libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el identificador de usuario: ");
				String idUsuario = scan.nextLine();
				Usuario usuarioPrestamo = usuarioServicio.buscarUsuarioPorId(idUsuario);
				if (usuarioPrestamo != null) {
					System.out.print("Ingrese el ISBN del libro a prestar: ");
					String isbnPrestamo = scan.nextLine();
					Libro libroPrestamo = libroServicio.buscarLibroPorISBN(isbnPrestamo);
					if (libroPrestamo != null) {
						if (libroServicio.verificarDisponibilidad(libroPrestamo)) {
							usuarioServicio.prestarLibro(usuarioPrestamo, libroPrestamo);
							usuarioServicio.generarNotificacion(usuarioPrestamo, isbnPrestamo);
							System.out.println();
							System.out.println("Prestamo exitoso. Libro prestado a " + usuarioPrestamo.getNombre());
						} else {
							System.out.println();
							System.out.println("El libro no esta disponible para prestamo");
						}
					} else {
						System.out.println();
						System.out.println("No se encontro libro con ese ISBN");
					}
				} else {
					System.out.println();
					System.out.println("Usuario no encontrado");
				}
				continuar();
				break;
			case 8: 
				//Devolver un libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el identificador de usuario: ");
				String idUsuario1 = scan.nextLine();
				Usuario usuarioDev = usuarioServicio.buscarUsuarioPorId(idUsuario1);
				if (usuarioDev != null) {
					System.out.print("Ingrese el ISBN del libro a devolver: ");
					String isbnDev = scan.nextLine();
					Libro libroDev = libroServicio.buscarLibroPorISBN(isbnDev);
					if (libroDev != null) {
						usuarioServicio.devolverLibro(usuarioDev, libroDev);
						usuarioServicio.eliminarNotificacion(usuarioDev, isbnDev);
						System.out.println();
						System.out.println("Devolucion exitosa. Libro devuelto por " + usuarioDev.getNombre());
					} else {
						System.out.println();
						System.out.println("Libro no encontrado");
					}
				} else {
					System.out.println();
					System.out.println("Usuario no encontrado");
				}
				continuar();
				break;
			case 9:
				//Crear un usuario
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el nombre: ");
				String nombre = scan.nextLine();
				System.out.print("Ingrese el identificador: ");
				String id = scan.nextLine();
				usuarioServicio.crearUsuario(nombre, id);
				continuar();
				break;
				
			case 10: 
				//Actualizar usuario
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el identificador del usuario a actualizar: ");
				String idAct = scan.nextLine();
				if (usuarioServicio.buscarUsuarioPorId(idAct) != null) {
					System.out.print("Ingrese nuevo nombre: ");
					String nuevoNombre = scan.nextLine();
					usuarioServicio.actualizarUsuario(idAct, nuevoNombre);
				} else {
					System.out.println("No hay usuario con ese identificador");
				}
				continuar();
				break;
			case 11: 
				//Buscar usuario por ID
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el identificador del usuario a buscar: ");
				String idBus = scan.nextLine();
				Usuario usuarioID = usuarioServicio.buscarUsuarioPorId(idBus);
				if (usuarioID != null) {
					System.out.println();
					System.out.println("Usuario encontrado: " + usuarioID.getNombre());
				} else {
					System.out.println();
					System.out.println("Usuario no encontrado");
				}
				continuar();
				break;
			case 12:
				//Listar todos los usuarios
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				ArrayList<Usuario> listarUsuarios = usuarioServicio.obtenerTodosLosUsuarios();
				System.out.println();
				System.out.println("Usuarios: ");
				for (Usuario usuario : listarUsuarios) {
					System.out.println(usuario.getNombre() + " (" + usuario.getId() + ")");
				}
				continuar();
				break;
			case 13: 
				//Eliminar un usuario
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el identificador del usuario a eliminar: ");
				String idDel = scan.nextLine();
				Usuario usuarioDel = usuarioServicio.buscarUsuarioPorId(idDel);
				if (usuarioDel != null) {
					if (usuarioDel.getLibrosPrestados().isEmpty()) {
						usuarioServicio.eliminarUsuario(idDel);
						System.out.println();
						System.out.println("Usuario eliminado correctamente.");
					} else {
						System.out.println();
						System.out.println("No se puede eliminar un usuario que debe libros prestados");
					}
				} else {
					System.out.println();
					System.out.println("No existe usuario con ese identificador.");
				}
				continuar();
				break;
			case 14: 
				//Listado de libros prestado por usuario
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el identificador del usuario a consultar prestamos: ");
				String idPrest = scan.nextLine();
				Usuario usuarioLibros = usuarioServicio.buscarUsuarioPorId(idPrest); 
				if (usuarioLibros != null) {
					ArrayList<Libro> listarLibrosPorUsuario = usuarioServicio.obtenerLibrosPrestados(usuarioLibros);
					System.out.println();
					System.out.println("Usuario: " + usuarioLibros.getNombre());
					System.out.println();
					System.out.println("Libros prestados: ");
					for (Libro libro : listarLibrosPorUsuario) {
						System.out.println(libro.getTitulo() + " (" + libro.getIsbn() + ")");
					}
				} else {
					System.out.println();
					System.out.println("No se encontro usuario.");
				}				
				continuar();
				break;
			case 15: 
				//Calificar un libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el ISBN del libro a calificar: ");
				String isbnCal = scan.nextLine();
				Libro libroCal = libroServicio.buscarLibroPorISBN(isbnCal);
				if (libroCal != null) {
					do {
						System.out.println();
						System.out.print("Ingrese una calificacion entera de 1 a 5: ");
						try {
							int calificacion = scan.nextInt();
							if (calificacion >= 1 && calificacion <= 5) {
								libroServicio.calificar(isbnCal, calificacion);
								scan.nextLine();
								break;
							} else {
								System.out.println();
								System.out.println("Error: Por favor ingrese un numero entre 1 y 5");
							}
						} catch (InputMismatchException e) {
							System.out.println();
							System.out.println("Error: Por favor ingrese un entero valido");
							scan.nextLine();
						}
					} while (true);
				} else {
					System.out.println();
					System.out.println("No existe libro con ese ISBN");
				}
				continuar();
				break;
			case 16: 
				//Reseñar un libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el ISBN del libro a reseñar: ");
				String isbnRes = scan.nextLine();
				Libro libroRes = libroServicio.buscarLibroPorISBN(isbnRes);
				if (libroRes != null) {
					System.out.println();
					System.out.println("Ingrese una reseña para el libro: " + libroRes.getTitulo());
					String reseña = scan.nextLine();
					libroServicio.reseñar(isbnRes, reseña);
				} else {
					System.out.println();
					System.out.println("No existe libro con ese ISBN");
				}
				continuar();
				break;
			case 17: 
				//Mostrar todas las calificaciones de un libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el ISBN del libro: ");
				String isbnListaCal = scan.nextLine();
				Libro libroListaCal = libroServicio.buscarLibroPorISBN(isbnListaCal);
				if (libroListaCal != null) {
					System.out.println();
					ArrayList<Integer> calificaciones = libroServicio.calificacionesPorLibro(libroListaCal);
					System.out.println(libroListaCal.getTitulo());
					System.out.println();
					double promedio = libroServicio.promedioCalificaciones(libroListaCal);
					System.out.println("Promedio de calificaciones: " + promedio);
					System.out.println();
					System.out.println("Calificaciones: ");
					for (Integer integer : calificaciones) {
						System.out.println(integer);
					}
				}
				continuar();
				break;
			case 18: 
				//Mostrar todas las reseñas de un libro
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el ISBN del libro: ");
				String isbnListaRes = scan.nextLine();
				Libro libroListaRes = libroServicio.buscarLibroPorISBN(isbnListaRes);
				if (libroListaRes != null) {
					System.out.println();
					ArrayList<String> reseñas = libroServicio.reseñasPorLibro(libroListaRes);
					System.out.println(libroListaRes.getTitulo());
					System.out.println();
					System.out.println("Reseñas: ");
					for (String lReseña : reseñas) {
						System.out.println("*** " + lReseña + " ***");
					}
				}
				continuar();
				break;
			case 19: 
				//Notificaciones de un usuario
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.print("Ingrese el identificador de usuario: ");
				String idUsuarioPres = scan.nextLine();
				Usuario usuarioPrest = usuarioServicio.buscarUsuarioPorId(idUsuarioPres);
				if (usuarioPrest != null) {
					System.out.println();
					ArrayList<String> notificaciones = usuarioServicio.obtenerNotificaciones(usuarioPrest);
					System.out.println(usuarioPrest.getNombre());
					System.out.println();
					System.out.println("Notificaciones: ");
					for (String nota : notificaciones) {
						System.out.println("*** " + nota + " ***");
					}
				}
				continuar();
				break;
			case 0:
				//Salir
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.println("Gracias por usar la Biblioteca Virtual. Hasta pronto!");
				break;
			default:
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				System.out.println("Opcion no valida. Intente de nuevo");
				continuar();
			}
		} while (opcion !=0);

	}
	
	public static void continuar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Presione ENTER para continuar");
		scanner.nextLine();
		System.out.println();
		System.out.println("**********************************************");
		System.out.println();
	}
}
