package practica_03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


// TODO: Auto-generated Javadoc
/**
 * The Class GenerateTestFile.
 */
public class GenerateTestFile {
	
	/** The capacity. */
	private static int capacity = 100; //Capacidad de la mochila
	
	/** The size. */
	private static int size = 10;
	
	/** The ruta. */
	private static String ruta = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "practica3" + File.separator;


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		for (int i = 1; i < 3000000; i*=6) {
			generateTestFile(i);
		}
		//generateTestFile(100000); // variando el numero de elementos
		//generateTestFile("p11", 90, 3000); //Cambiar el nombre la capacidad() y el tamaño 
	}

	/**
	 * Generate test file.
	 *
	 * @param nombreBaseArch the nombre base arch
	 * @param capacidad the capacidad
	 * @param size the size
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void generateTestFile(String nombreBaseArch, int capacidad, int size) throws IOException {
		File fis = new File(ruta+nombreBaseArch+"_c.txt");
		PrintWriter pw = new PrintWriter(new FileWriter(fis));
		pw.println(capacidad);
		pw.close();
		
		//Generar los pesos
		fis = new File(ruta+nombreBaseArch+"_w.txt");
		pw = new PrintWriter(new FileWriter(fis));
		for (int i = 0; i < size; i++) {
			pw.println((int)(Math.random()*7)+1);
		}
		pw.close();
		
		//Generar los valores
		fis = new File(ruta+nombreBaseArch+"_p.txt");
		pw = new PrintWriter(new FileWriter(fis));
		for (int i = 0; i < size; i++) {
			pw.println((int)(Math.random()*70)+4);
		}
		pw.close();
	}
	
	/**
	 * Generate test file.
	 *
	 * @param size the size
	 */
	public static void generateTestFile(int size) {
		File fis = new File(ruta+size+"test.txt");
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fis));
			pw.println(capacity);
			for (int i = 1; i <= size; i++) {
				pw.println("Elemento0"+i+" "+((int)(Math.random()*15)+1)+" "+((int)(Math.random()*150)+15));
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
}
