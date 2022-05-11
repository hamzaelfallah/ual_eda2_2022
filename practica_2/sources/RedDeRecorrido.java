package org.eda2.practica2;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Clase RedDeRecorrido.
 */
public class RedDeRecorrido {

	/**inf. */
	private final double INF = Double.MAX_VALUE;
	
	/** directed. */
	private boolean directed;
	
	/** graph. */
	private HashMap<String, HashMap<String, Double>> graph;//Key=Vertice, Key2=, Vertice, Value=Peso
	
	/** source. */
	private String source;
	
	/**
	 * Metodo Constructor por defecto.
	 */
	public RedDeRecorrido() {
		this.directed = true;
		this.graph = new HashMap<String, HashMap<String,Double>>();
	}
	
	/**
	 * RedDeRecorrido recibe dos parametros.
	 *
	 * @param file
	 * @param source
	 */
	public RedDeRecorrido(String file, String source) {
		this();
		this.source = source;
		this.cargarDatos(file);
		
	}
	
	/**
	 * Metodo Constructor que recibe un parametro.
	 *
	 * @param file
	 */
	public RedDeRecorrido(String file) {
		this(file, null);
	}
	
	/**
	 * Metodo cargarDatos 
	 * Recibe la ruta completa del archivo 
	 * Hace l comprobacion si es un grafo dirigido o no y lee el número de vértices, mientras que la línea sea vértice la mete en la estructura como clave.
	 * Despues de terminar de leer los vértices lee el número de aristas y añade la arista con su peso asociado con el método addEdge.
     * Si no se establece un vértice de origen se escoge un aleatoriamente.
	 *
	 * @param file
	 */
	public void cargarDatos(String file) {
		Scanner sc = null;
		String line = "";
		String[] tokens = null;
		try {
			sc = new Scanner(new File(file));
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		int contV = 0;
		int contEd = 0;
		String cab = sc.nextLine();
		if(cab.equals("0")) {
			this.directed = false;
		}
		if(cab.equals("1")){
			this.directed = true;
		}
		int numVertex = Integer.parseInt(sc.nextLine());
		int numEdge = 0;
		while (sc.hasNextLine()){
			line = sc.nextLine().trim();
			if (line.isEmpty()) continue;
			if(contV++ < numVertex && line.length() == 1) {
				this.addVertex(line);
			}if(contV == numVertex) {
				numEdge = Integer.parseInt(sc.nextLine());
				contEd++;
				continue;
			}
			if(contEd != 0) {
				if(contEd++ <= numEdge) {
					tokens = line.split(" ");
					this.addEdge(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
				}
			}
		}
		sc.close();
		if(this.source == null) { 
			ArrayList<String> aux = new ArrayList<String>(this.graph.keySet());
			Collections.shuffle(aux);
			this.source = aux.get(0);
		}
	}

	/**
	 * Metodo getGraph.
	 *
	 * @return graph
	 */
	public HashMap<String, HashMap<String, Double>> getGraph() {
		return graph;
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
	 * Metodo getDirected.
	 *
	 * @return directed
	 */
	public boolean getDirected() {
  		return this.directed;
  	}
	
	/**
	 * Metodo setDirected.
	 *
	 * @param directed
	 */
	public void setDirected(boolean directed) {
  		this.directed = directed;
  	}

  	/**
     * Metodo addEedge 
	 * Recibe tres parametros (dos vertices origen y destino y su peso).
     * Hace la llamada al metodo addVertex dos veces y le pasa los vertices y añade a la estructura graph la arista (v1, v2), 
     * Hace la comprobacion de que el grafo no es dirigido y entonces añade la arista en el otro sentido (v2, v1)
     *
     * @param v1
     * @param v2 
     * @param w 
     * @return true, si se ha llevado a cabo.
     */
    public boolean addEdge(String v1, String v2, double w) {
  		addVertex(v1);
  		addVertex(v2);
  		this.graph.get(v1).put(v2, w);
  		if (!this.directed) {
        	this.graph.get(v2).put(v1, w);
       	}
    	return true;
  	}
	
    /**
     *  Metodo Prim.
     *  
     * @return  ListaAristas 
     */
	public ListaAristas prim(){
		if(source == null || !this.graph.containsKey(source)) return null;
		
		HashMap<String, Double> weights = new HashMap<String, Double>();
		HashMap<String, String> branches = new HashMap<String, String>();
		HashSet<String> remain = new HashSet<String>();
		ListaAristas result = new ListaAristas(); 
		String from = null;
		
		for (String v : this.graph.keySet()) {
			remain.add(v);
		}
		remain.remove(source);
		
		for (String v : remain) {
			Double weight = getWeight(source, v);
			if(weight != null) { 
				branches.put(v, source);
				weights.put(v, weight);
			}else { 
				branches.put(v, null);
				weights.put(v, INF);
			}
			
		}
		branches.put(source, source);
		weights.put(source, 0.0);
		
		while(!remain.isEmpty()) {
			
			double min = INF;
			from = null;
			for (String v : remain) {
				double weight = weights.get(v);
				if(weight < min) { 
					min = weight;
					from = v;
				}
			}
			
			if(from == null) break; 
			remain.remove(from); 
			String aux = branches.get(from); 
			result.addArista(aux, from, getWeight(aux, from)); 
			
			for (String to : remain) {
				Double weight = getWeight(from, to);
				if(weight != null && weight < weights.get(to)) {
					weights.put(to, weight);
					branches.put(to, from);
				}
			}
		}

		return result;
		
	}

    /**
     * Metodo primPQ.
     *
     * @return ListaAristas
     */
    public ListaAristas primPQ(){
		String source = this.source;
		if(source == null || !this.graph.containsKey(source)) return null;

		HashSet<String> remain = new HashSet<String>();
		PriorityQueue<Arista> pq = new PriorityQueue<Arista>();
		ListaAristas result = new ListaAristas();
		String from = source;
		String to;
		Arista aux;
		Double weight;
		
		for (String v : this.graph.keySet()) {
			remain.add(v);
		}
		remain.remove(source);
		
		while(!remain.isEmpty()) {
			for (Entry<String, Double> it : this.graph.get(from).entrySet()) {
				to = it.getKey();
				weight = it.getValue();
				if(remain.contains(to)) {
					aux = new Arista(from, to, weight);
					pq.add(aux);
				}
			}
			do {
				aux = pq.poll();
				from = aux.getSource();
				to = aux.getDest();
				weight = aux.getWeight();
			} while(!remain.contains(to));
			remain.remove(to);
			result.addArista(aux.getSource(), aux.getDest(), aux.getWeight());
			from = to;
		}
		return result;
	}

    /**
     * Metodo kruskal. 
     *
     * @return ListaAristas 
     */
    public ListaAristas kruskal(){
		String source = this.source;
		if(source == null || !this.graph.containsKey(source)) return null;
		
		TreeMap<String, String> branches = new TreeMap<String, String>();
		TreeMap<String, Double> remain = new TreeMap();
		ListaAristas result = new ListaAristas();
		
		for (String v : graph.keySet()) {
			remain.put(v, INF);
		}
		boolean isFirst = true;
		while(!remain.isEmpty()) {
			String minKey = remain.firstKey();
			if(isFirst) {
				minKey = source;
				isFirst = false;
			}
			Double minValue = INF;
			for (Entry<String, Double> e : remain.entrySet()) {
				if(e.getValue() < minValue) {
					minValue = e.getValue();
					minKey = e.getKey();
				}
			}
			remain.remove(minKey);
			
			for (Entry<String, Double> it : graph.get(minKey).entrySet()) {
				String to = it.getKey();
				Double dActual = getWeight(branches.get(to), to);
				dActual = dActual == null ? INF : dActual;
				
				if(remain.containsKey(to) && it.getValue() < INF && it.getValue() < dActual) {
					remain.put(to, it.getValue());
					branches.put(to, minKey);
				}
			}
		}
		
		for (Entry<String, String> it : branches.entrySet()) {
			String to = it.getKey();
			String from = it.getValue();
			result.addArista(from, to, getWeight(from, to));
			
		}
		return result;
	}

	/**
	 * Metodo addVertex que recibe por parametro un vertice.
	 * Hace la comprobacion que el vertice no esta en la estructura graph y lo mete junto a una estructura vacia como valor.
	 *
	 * @param v 
	 * @return true, si se ha llevado a cabo
	 */
	public boolean addVertex(String v) {
		if (this.graph.containsKey(v)) return false;
        this.graph.put(v, new HashMap<String, Double>());
        return true;
	}

	/**
	 * Metodo getWeight recibe una arista y devuelve su peso.
	 *
	 * @param v1 
	 * @param v2 
	 * @return weight
	 */
	public Double getWeight(String v1, String v2) {
		return containsEdge(v1, v2) ? this.graph.get(v1).get(v2) : null;
	}
	
	/**
	 * Metodo containsEdge rebice una arista y compruba si esta.
	 *
	 * @param v1 
	 * @param v2 
	 * @return true, se ha llevado a cabo
	 */
	public boolean containsEdge(String v1, String v2) {
		return this.graph.containsKey(v1) && this.graph.get(v1).containsKey(v2);
	}
	

	/**
	 * Metodo numberOfVertex.
	 *
	 * @return int, devuelve el numero de vertices
	 */
	public int numberOfVertex() {
		return this.graph.size();
	}

	/**
	 * Metodo numberOfEdges.
	 *
	 * @return int, devuelve el numero de aristas
	 */
	public int numberOfEdges() {
		int count = 0;
  		for (HashMap<String, Double> itMap : this.graph.values())
  			count += itMap.size();
  		return count;
	}

}
