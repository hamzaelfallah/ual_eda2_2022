package org.eda2.practica2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase ClaseMain.
 */
public class ClaseMain {

	/** ruta. */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "practica2" + File.separator;

	/** sc. */
	private static Scanner sc = new Scanner(System.in);

	/** archivos. */
	private static String[] archivos = { "graphEDAland.txt", "graphPrimKruskal.txt", "graphEDAlandLarge.txt", "1000vertices.txt",
			"2000vertices.txt"};

	/** fileName. */
	private static String fileName;

	/**
	 * Metodo main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(archivos));
		int opcion;
		do {
			menuSeleccionArchivo();
			int opcion2 = leerOpcion("Seleccione un archivo de entre: 1 al " + a.size() + "", 1, 4);
			if (opcion2 >= 1 && opcion2 <= a.size()) {
				fileName = a.get(opcion2 - 1);
			} else {
				System.out.println("El valor selecionado está fuera de rango vuelve a ejecutarlo!");
				System.exit(-1);
			}
			menuSeleccionAlgoritmos();
			opcion = leerOpcion("Seleccione un algoritmo: ", 1, 3);
		} while (ejecutarAlgoritmo(opcion));
		sc.close();
	}

	/**
	 * Metodo ejecutarAlgoritmo.
	 * posibilidad de la seleccionar el algoritmo a ejecutar.
	 *
	 * @param opcion
	 * @return true, si se ha llevado a cabo
	 */
	private static boolean ejecutarAlgoritmo(int opcion) {
		RedDeRecorrido red = new RedDeRecorrido(ruta + fileName);
		switch (opcion) {
		case 1:
			System.out.println(red.prim());
			break;
		case 2:
			System.out.println(red.primPQ());
			break;
		case 3:
			System.out.println(red.kruskal());
			break;
		default:
			System.out.println("Programa finalizado");
			return false;
		}
		return true;
	}

	/**
	 *  menuSeleccionAlgoritmos.
	 * Imprimir por consola
	 */
	private static void menuSeleccionAlgoritmos() {
		System.out.println("=================================");
		System.out.println("           -- MENU --            ");
		System.out.println("=================================");
		System.out.println("1.- Prim");
		System.out.println("2.- Prim con PQ");
		System.out.println("3.- Kruscal");
		System.out.println("4.- Salir");
		System.out.println("=================================");
	}

	/**
	 * Metodo menuSeleccionArchivo.
	 * Imprimir el menu para leer los archivos
	 */
	private static void menuSeleccionArchivo() {
		System.out.println("=================================");
		System.out.println("           -- MENU --            ");
		System.out.println("=================================");
		System.out.println("1.- Cargar graphEDAland.txt");
		System.out.println("2.- Cargar graphPrimKruskal.txt");
		System.out.println("3.- Cargar graphEDAlandLarge.txt");
		System.out.println("4.- Cargar 1000vertices.txt");
		System.out.println("5.- Cargar 2000vertices.txt");
		System.out.println("6.- Salir");
		System.out.println("=================================");
	}

	/**
	 * Metodo leerOpcion.
	 * Imprimir el mensaje y el intervalo de valores que se puede seleccionar
	 *
	 * @param mensaje
	 * @param min
	 * @param max
	 * @return int
	 */
	private static int leerOpcion(String mensaje, int min, int max) {
		do {
			System.out.println(mensaje);
			try {
				int opcion = Integer.parseInt(sc.nextLine());
				if (opcion >= min || opcion <= max) {
					return opcion;
				}
			} catch (NumberFormatException e) {
				System.out.print("Error: Debe introducir un valor numerico");
			}
			System.out.println("Error: Debe introducir una opcion entre " + min + " y " + max);
		} while (true);
	}

}
