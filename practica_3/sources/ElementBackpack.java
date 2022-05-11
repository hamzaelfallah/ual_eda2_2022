package practica_03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class ElementBackpack.
 */
public class ElementBackpack {

	/** The elements. */
	private List<Element> elements;
	
	/** The weight final. */
	private double weightFinal;
	
	/** The ganancia final. */
	private double gananciaFinal;
	
	/** The capacity. */
	private int capacity;

	/** The ruta. */
	private String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "practica3"
			+ File.separator;
	
	/**
	 * Instantiates a new element backpack.
	 */
	public ElementBackpack() {
		
	}

	/**
	 * Instantiates a new element backpack.
	 *
	 * @param fileName the file name
	 * @param type the type
	 */
	public ElementBackpack(String fileName, boolean type) {
		
		if(type) {
			loadFile(fileName);
		}else {
			loadFileV2(fileName);
		}

	}

	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * Gets the weight final.
	 *
	 * @return the weight final
	 */
	public double getWeightFinal() {
		return weightFinal;
	}

	/**
	 * Gets the ganancia final.
	 *
	 * @return the ganancia final
	 */
	public double getGananciaFinal() {
		return gananciaFinal;
	}

	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Load file.
	 *
	 * @param nameFileBase the name file base
	 */
	public void loadFile(String nameFileBase) {
		File f = new File(ruta + nameFileBase + "_c.txt");
		String line = "";
		this.elements = new ArrayList<Element>();
		Scanner sc = null;
		int i = 1;
		try {
			sc = new Scanner(f);
			this.capacity = Integer.parseInt(sc.nextLine().trim());
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		f = new File(ruta + nameFileBase + "_w.txt");
		try {
			sc = new Scanner(f);
			// Crear objetos sólo con los pesos y agregarlos a la lista
			while (sc.hasNextLine()) {
				line = sc.nextLine().trim();
				Element e = new Element("E0" + i, Double.parseDouble(line), 0);
				this.elements.add(e);
				i++;
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		f = new File(ruta + nameFileBase + "_p.txt");
		try {
			i = 0;
			sc = new Scanner(f);
			// Obtener cada objeto de nuestra lista y agregarle el valor
			while (sc.hasNextLine()) {
				line = sc.nextLine().trim();
				this.elements.get(i).setGanancia(Double.parseDouble(line));
				i++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load file V 2.
	 *
	 * @param name the name
	 */
	public void loadFileV2(String name) {
		File f = new File(ruta+name+"test.txt");
		String line = "";
		this.elements = new ArrayList<Element>();
		try {
			Scanner sc =  new Scanner(f);
			String[] items = null;
			this.capacity = Integer.parseInt(sc.nextLine().trim());
			while(sc.hasNextLine()){
				line = sc.nextLine().trim();
				items = line.split(" ");
				double weight = Double.parseDouble(items[1]);
				double ganancia = Double.parseDouble(items[2]);
				Element e = new Element(items[0],weight,ganancia);
				elements.add(e);
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Backpack.
	 *
	 * @return the list
	 */
	public List<Element> backpack() {
		this.elements.sort(new ElementWeightComparator()); // Ordenar ascendente por peso
		int row = this.elements.size() + 1;
		int column = this.capacity + 1;
		double[][] A = new double[row][column];// Matriz
		for (int i = 1; i <= this.elements.size(); i++) {
			for (int j = 1; j <= this.capacity; j++) {
				if (this.elements.get(i - 1).getWeight() <= j) {
					A[i][j] = Math.max(A[i - 1][j], this.elements.get(i - 1).getGanancia()
							+ A[i - 1][j - (int) this.elements.get(i - 1).getWeight()]);
				} else {
					A[i][j] = A[i - 1][j];
				}
			}
		}
		return objects(A); // Recupera los elementos que formarán parte de la mochila
	}

	/**
	 * Objects.
	 *
	 * @param A the a
	 * @return the list
	 */
	public List<Element> objects(double[][] A) {
		List<Element> result = new ArrayList<Element>();
		this.gananciaFinal = A[A.length - 1][A[0].length - 1];// Valor máximo de la mochila
		this.weightFinal = 0;
		int j = A[0].length - 1; // Columna de partida
		for (int i = A.length - 1; i > 0; i--) { // En cada iteración se sube de fila
			if (A[i][j] != A[i - 1][j]) {// Si la celda superior es distinta, lo escogemos...
				result.add(this.elements.get(i - 1));
				this.weightFinal += this.elements.get(i - 1).getWeight();
				j -= this.elements.get(i - 1).getWeight();// Se desplaza la columna a la izquierda el peso del objeto
			}
		}
		return result;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return this.elements.size();
	}

	/**
	 * Mcd.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the int
	 */
	private int mcd(int a, int b) {
		while (b > 0) {
			int aux = b;
			b = a % b;
			a = aux;
		}
		return a;
	}

	/**
	 * Mcd.
	 *
	 * @return the int
	 */
	public int mcd() {
		int cap = this.capacity;
		for (int i = 0; i < this.elements.size(); i++) {
			cap = mcd(cap, (int) this.elements.get(i).getWeight());
			if (cap == 1)
				return 1;
		}
		return cap;
	}

	/**
	 * Backpack unlimited capacity.
	 *
	 * @return the list
	 */
	public List<Element> backpackUnlimitedCapacity() {
		this.elements.sort(new ElementWeightComparator()); // Ordenar ascendente por peso
		int n = this.elements.size();
		double[] array = new double[this.capacity + 1]; // Array
		for (int i = 0; i <= this.capacity; i++) {
			for (int j = 0; j < n; j++) {
				if (this.elements.get(j).getWeight() <= i) {
					array[i] = Math.max(array[i],
							array[i - (int) this.elements.get(j).getWeight()] + this.elements.get(j).getGanancia());
				}
			}
		}
		return this.elementos(array);// ¿Como recuperamos los objetos?
	}

	/**
	 * Elementos.
	 *
	 * @param array the array
	 * @return the list
	 */
	private List<Element> elementos(double[] array) {
		this.gananciaFinal = 0;
		this.weightFinal = 0;
		List<Element> result = new ArrayList<Element>();
		int cap = this.capacity; // Capacidad restante
		double pesoMin = this.elements.get(0).getWeight(); // Peso menor
		int size = this.elements.size();
		while (cap >= pesoMin) { // Puede quedar espacio sin ocupar
			double gananciaMax = 0;
			int index = -1; // Posición del objeto ganador
			for (int i = size - 1; i >= 0; i--) { // Para cada objeto...
				if (cap - this.elements.get(i).getWeight() >= 0) { // Valoraremos solo los objetos que quepan en el
																	// espacio restante
					double aux = array[cap - (int) this.elements.get(i).getWeight()]
							+ this.elements.get(i).getGanancia();
					if (aux > gananciaMax) {
						gananciaMax = aux;
						index = i;
					}
				}
			}
			if (index == -1)
				break; // Si no se encuentra un candidato factible, se finaliza la búsqueda
			this.gananciaFinal += this.elements.get(index).getGanancia();
			this.weightFinal += this.elements.get(index).getWeight();
			result.add(this.elements.get(index));
			cap -= this.elements.get(index).getWeight(); // Decrementamos el espacio restante de la mochila
		}
		return result;
	}

	/**
	 * Decimals num.
	 *
	 * @param d the d
	 * @return the int
	 */
	private int decimalsNum(double d) {
		String s = Double.toString(Math.abs(d));
		int partInt = s.indexOf('.');
		if (partInt == -1)
			return 0;
		int partDec = s.length() - partInt - 1;
		return partDec;
	}

	/**
	 * Max num decimals.
	 *
	 * @return the int
	 */
	private int maxNumDecimals() {
		int max = decimalsNum(this.capacity);
		for (Element e : this.elements) {
			int n = decimalsNum(e.getWeight());
			max = n > max ? n : max;
		}
		return (int) Math.pow(10, max);
	}

	/**
	 * Backpack greedy.
	 *
	 * @return the list
	 */
	public List<Element> backpackGreedy() {
		this.elements.sort(new ElementWeightComparator()); // Orden descendente por relacion valor-peso
		this.gananciaFinal = 0;
		this.weightFinal = 0;
		List<Element> result = new ArrayList<Element>();
		for (Element e : this.elements) {
			if (this.weightFinal + e.getWeight() <= this.capacity) { // Si cabe entero
				e.setAmount(1);
				result.add(e);
				this.weightFinal += e.getWeight();
				this.gananciaFinal += e.getGanancia();
				if (this.weightFinal == this.capacity)
					break; // Si se alcanza la capacidad máxima
			} else {// Si no cabe entero
				e.setAmount((this.capacity - this.weightFinal) / e.getWeight());
				result.add(e);
				this.weightFinal += e.getWeight() * e.getAmount();
				this.gananciaFinal += e.getGanancia() * e.getAmount();
				break; // Ya no cabe mas
			}
		}
		return result;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String s = "LIST OF ELEMENTS";
		for (Element e : this.elements) {
			s += "\n" + e;
		}
		return s + "\nCAPACITY: " + this.capacity;
	}

	/**
	 * Results.
	 *
	 * @param lista the lista
	 * @return the string
	 */
	public String results(List<Element> lista) {
		String s = "LIST OF ELEMENTS WHIT CAPACITY: " + this.capacity;
		for (Element e : lista) {
			s += "\n" + e;
		}
		return s + "\nGF = " + this.gananciaFinal + "\tPT = " + this.weightFinal;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Adds the.
	 *
	 * @param e the e
	 */
	public void add(Element e) {
		this.elements= new ArrayList<Element>();
		this.elements.add(e);
		
	}

}
