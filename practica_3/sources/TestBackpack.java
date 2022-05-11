package practica_03;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Class TestBackpack.
 */
public class TestBackpack {

	/** The ruta. */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "practica3" + File.separator;
	
	/** The element B. */
	private static ElementBackpack elementB;
	
	/** The name file base. */
	private static String nameFileBase = "p11";
	
	/** The file name. */
	private static String fileName = "100000";

	/** The em. */
	private static ElementBackpack em;


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
			
		
		System.out.println("<----Variamos solamente el numero de elementos--->");
//		//Medir los tiempo de los algoritmos con el mismo numero de elementos y misma capacidad de mochila.
		System.out.println("=========== Algoritmo de Greedy aplicada a mochila ==========");
		System.out.println("Capacidad\tNumElementos\tTGreedMochila");
		for (int i = 0; i < 1000000; i=i*2) {
			generateElements(i);
			measureTimeBackpackGreedy();
			if(i==0) i++;
		}
		
		System.out.println();
		System.out.println("=========== Algoritmo capacidad ilimitada aplicado a Mochila ==========");
		System.out.println("Capacidad\tNumElementos\tTcapacidadIlimitada");
		for (int i = 0; i < 1000000; i=i*2) {
			generateElements(i);
			measureTimeBackpackUnlimitedCapacity();
			if(i==0) i++;
		}
		
		System.out.println();
		System.out.println("=========== Mochila ==========");
		System.out.println("Capacidad\tNumElementos\tTMochila");
		for (int i = 0; i < 1000000; i=i*2) {
			generateElements(i);
			measureTimeBackPack();
			if(i==0) i++;
		}
		
	}

	/**
	 * Measure time back pack.
	 */
	private static void measureTimeBackPack() {
		long start = 0;
		long end = 0;
		
		System.out.print(em.getCapacity()+"\t\t"+em.getElements().size());
		start = System.currentTimeMillis();
		em.backpack();
		end = System.currentTimeMillis();
		System.out.println("\t\t"+(end-start));
	
	}
	
	/**
	 * Measure time backpack unlimited capacity.
	 */
	private static void measureTimeBackpackUnlimitedCapacity() {
		long start = 0;
		long end = 0;
		
		System.out.print(em.getCapacity()+"\t\t"+em.getElements().size());
		start = System.currentTimeMillis();
		em.backpackUnlimitedCapacity();
		end = System.currentTimeMillis();
		System.out.println("\t\t"+(end-start));
	
	}
	
	/**
	 * Measure time backpack greedy.
	 */
	private static void measureTimeBackpackGreedy() {
		long start = 0;
		long end = 0;
		
		System.out.print(em.getCapacity()+"\t\t"+em.getElements().size());
		start = System.currentTimeMillis();
		em.backpackGreedy();
		end = System.currentTimeMillis();
		System.out.println("\t\t"+(end-start));
	
	}
	
	/**
	 * Generar eelementos.
	 *
	 * @param size the size
	 */
	public static void generateElements(int size) {
		em = new ElementBackpack();
		for (int i = 0; i <= size; i++) {
			double peso = (int)(Math.random()*100)+1;
			double valor = (int)(Math.random()*70)+1;
			Element e = new Element("Element"+i, peso, valor);
			em.add(e);
		}
		em.setCapacity(1000);
	}
	

}
