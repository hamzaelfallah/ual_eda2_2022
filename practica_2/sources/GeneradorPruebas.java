package org.eda2.practica2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * Clase GeneradorPruebas.
 */
public class GeneradorPruebas {
	
	/** m. */
	private static RedDeRecorrido grafo = new RedDeRecorrido(); 
	
	/** n. */
	private static int N = 2000; 
	
	/** ruta. */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "practica2" + File.separator;
	
	/** cab. */
	private static String cab = "0";

	/**
	 * Metodo Main
	 *
	 * @param args 
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void main(String[] args) throws FileNotFoundException {

		crearGrafo();
		generarArchivosPruebas(grafo.numberOfVertex());
	}

	/**
	 * Metodo crearGrafo.
	 * 
	 * Hace la creacion un objeto de RedDeCarretera 
	 * Establecer un punto de origen y un grafo como no dirigido.
	 * Inicializa los variables (tiempo inicial y un final) y las estructuras necesarias.
	 * Establecer un numero mínimo y un maximo de aristas.
	 * Esablecer aristas que se añadiran por iteracion.
	 * Hace la creacion de un grafo con las aristas minimas para que sea conexo y las aristas no incluidas.
	 * Si son consecutivos se añaden a "red" y si no a la estructura auxiliar.
	 */
	private static void crearGrafo() {
		int incrementar = 4;
		
		int minAristas = N - 1;
		int maxAristas = N * (N - 1) / 2;
		int contador = (maxAristas - minAristas) * incrementar / 100;
		LinkedList<Arista> aux = new LinkedList<Arista>();
		
		
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				double weigth = (int)(Math.random()*100)+10;
				if(i+1 == j) {
					grafo.addEdge(i+"", j+"", weigth);
				}else {
					aux.add(new Arista(i+"", j+"", weigth));
				}
			}
		}
		
		boolean stop = false;
		do {
			stop = aux.isEmpty();
			for (int i = 0; i < contador && !aux.isEmpty(); i++) {
				Arista arista = aux.poll();
				grafo.addEdge(arista.getSource(), arista.getDest(), arista.getWeight());
			}
			
		}while(!stop);
	}
	
	/**
	 * Metodo generarArchivosPruebas.
	 * Hace la creacion de un archivo 
	 * Escribe los verticesy las aristas con su peso correspondiente.
	 *
	 * @param tam
	 */
	public static void generarArchivosPruebas(int tam) {
		File fis = new File(ruta+tam+"vertices.txt");
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fis));
			pw.println(cab);
			pw.println(grafo.numberOfVertex());
			for (String vertex : grafo.getGraph().keySet()) {
				pw.println(vertex);
			}
			pw.println(grafo.numberOfEdges());
			for (Entry<String, HashMap<String, Double>> it : grafo.getGraph().entrySet()) {
				for (Entry<String, Double> it2 : it.getValue().entrySet()) {
					pw.println(it.getKey()+" "+it2.getKey()+" "+it2.getValue());
				}
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
