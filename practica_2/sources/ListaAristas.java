package org.eda2.practica2;

import java.util.ArrayList;

/**
 * Clase ListaAristas.
 */
public class ListaAristas {
	
	/** total. */
	private double total;
	
	/** lista. */
	private ArrayList<Arista> lista;
	
	/**
	 * Metodo Constructor por defecto
	 */
	public ListaAristas() {
		this.total = 0;
		lista = new ArrayList<Arista>();
	}
	

	/**
	 * Metodo getTotal
	 *
	 * @return total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Metodo setTotal
	 *
	 * @param total
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 *  Metodo getLista
	 *
	 * @return lista
	 */
	public ArrayList<Arista> getLista() {
		return lista;
	}

	/**
	 *  Metodo setLista
	 *
	 * @param lista
	 */
	public void setLista(ArrayList<Arista> lista) {
		this.lista = lista;
	}
	
	/**
	 * Metodo addArista.
	 *
	 * @param source
	 * @param destination
	 * @param weight
	 */
	public void addArista(String source, String destination, double weight){
		this.total += weight;
		lista.add(new Arista(source, destination, weight));
	}

	/**
	 * Metodo toString.
	 *
	 * @return string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Conjunto de aristas") ;
		for (Arista arista : lista) {
			sb.append("\n"+arista);
		}
		return sb+"\nTOTAL: "+this.total;
	}
	
}
