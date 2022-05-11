package org.eda2.practica2;

/**
 * Clase Arista.
 */
public class Arista implements Comparable<Arista>{
	
	/** source. */
	private String source;
	
	/** dest. */
	private String destination;
	
	/** weight. */
	private double weight;
	
	/**
	 * Metodo constructor.
	 *
	 * @param source
	 * @param dest
	 * @param weight
	 */
	public Arista(String source, String dest, double weight) {
		this.source = source;
		this.destination = dest;
		this.weight = weight;
	}
	
	/**
	 * Metodo getSource.
	 *
	 * @return source
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * Metodo setSource.
	 *
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * Metodo getDest.
	 *
	 * @return dest
	 */
	public String getDest() {
		return destination;
	}
	
	/**
	 * Metodo setDest.
	 *
	 * @param dest
	 */
	public void setDest(String dest) {
		this.destination = dest;
	}
	
	/**
	 * Metodo getWeight
	 *
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Metodo setWeight
	 *
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Metodo toString.
	 *
	 * @return string
	 */
	@Override
	public String toString() {
		return "Arista: "+this.source +" "+ this.destination + "("+ this.weight +")";
	}

	/**
	 * Metodo compareTo.
	 * Hace la comparacion según el peso de la arista
	 * si tiene el mismo peso, hace la comparacion del origen y el destino de la misma clase con el origen y el destino 
	 * que recibe por parámetro.
	 *
	 * @param o
	 * @return int
	 */
	@Override
	public int compareTo(Arista o) {
		int comp = Double.compare(this.weight, o.weight);
		
		return comp ==0 ? (this.source+"-"+this.destination).compareTo(o.source+"-"+o.destination) : comp;
		
	}
	
	
	
}
