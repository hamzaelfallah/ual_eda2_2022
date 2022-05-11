package org.eda2.practica2;


import java.util.LinkedList;

/**
 * Clase TestAlgoritmo.
 */
public class TestAlgoritmo {
	
	/**
	 * Metodo Main.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		calcularTiempos();
	}

	/**
	 * Metodo medirTiempos().
	 * Crea un objeto de RedDeCarretera y establece un punto de origen y un grafo como no dirigido.
	 * Posteriormente pasamos a la inicializacion de variables entre ellas un tiempo inicial y un final 
	 * y las estructuras necesarias.
	 * Establecemos un numero mínimo y un maximo de aristas.
	 * Esablecemos las aristas que se añadiran por iteracion.
	 * Se crea el grafo con las aristas minimas para que sea conexo y las aristas no incluidas.
	 * Si son consecutivos se añaden a "red" y si no a la estructura auxiliar.
	 * 
	 * Mediremos los tiempos para los algoritmos.
	 */
	private static void calcularTiempos() {
		//CONFIGURACION INICIAL
		RedDeRecorrido red = new RedDeRecorrido();
		red.setSource("0");
		red.setDirected(false);
		int N = 5500; 
		int incremento = 5;
		
		int numMinAristas = N - 1;
		int numMaxAristas  = N * (N - 1) / 2;
		int cont  = (numMaxAristas  - numMinAristas) * incremento / 100;
		LinkedList<Arista> aux = new LinkedList<Arista>();
		long start = 0;
		long end = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				double weigth = (int)(Math.random()*100)+1;
				if(i+1 == j) {
					red.addEdge(i+"", j+"", weigth);
				}else {
					aux.add(new Arista(i+"", j+"", weigth));
				}
			}
		}
		
		
		System.out.println("Num Vertices\tNum Aristas\tTPrim\tTPrimPQ\tTKruskal");
		boolean stop = false;
		do {
			stop = aux.isEmpty();
			System.out.print(red.numberOfVertex()+"\t"+red.numberOfEdges());
			
			start = System.currentTimeMillis();
			red.prim();
			end = System.currentTimeMillis();
			System.out.print("\t"+(end-start));
			
			start = System.currentTimeMillis();
			red.primPQ();
			end = System.currentTimeMillis();
			System.out.print("\t"+(end-start));
			
			start = System.currentTimeMillis();
			red.kruskal();
			end = System.currentTimeMillis();
			System.out.println("\t"+(end-start));
			
			for (int i = 0; i < cont  && !aux.isEmpty(); i++) {
				Arista e = aux.poll();
				red.addEdge(e.getSource(), e.getDest(), e.getWeight());
			}
			
		}while(!stop);
		
	}

}
