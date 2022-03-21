package practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GenerarArchivos {

	private static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "practica1" + File.separator;
	private static String c = "﻿#;SeasonStart;PlayerName;PlayerSalary;Pos;Age;Tm;FG%;PTS";

	public static void main(String[] args) {
		for (int i = 1; i < 1000000; i *= 10) {
			try {
				generarArchASC(i);
				generarArchDSC(i);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	public static void generarArchASC(int size) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(filePath + size + "ASC.csv");
		pw.println(c);
		for (int i = 0; i < size; i++) {
			pw.println(i + ";;Jugador00" + i + ";;;;;100;" + i);
		}
		pw.close();
	}

	public static void generarArchDSC(int size) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(filePath + size + "DSC.csv");
		pw.println(c);
		for (int i = size; i > 0; i--) {
			pw.println(i + ";;Jugador00" + (i-1) + ";;;;;100;" + (i-1));
		}
		pw.close();
	}

}
