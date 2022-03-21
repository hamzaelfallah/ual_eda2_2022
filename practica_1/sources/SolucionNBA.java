package practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SolucionNBA {

	public static ArrayList<Player> nba;
	public static int top =10 ;

	private static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "practica1" + File.separator + "NbaStats.csv";

	public static void cargarArchivo(String filePath) {

		try {
			nba = new ArrayList<Player>();
			Scanner sc = new Scanner(new File(filePath));
			String linea = "";
			String[] arrayString;
			Player ultimoJugador = null;
			String ultimoNombreJ = "";
			while (sc.hasNextLine()) {
				linea = sc.nextLine().trim();
				if (linea.isEmpty() || linea.startsWith("﻿#"))
					continue;
				arrayString = linea.split(";");
				if (arrayString.length != 9)
					continue;
				double fg = remplazar(arrayString[7]);
				double pts = remplazar(arrayString[8]);
				if (!arrayString[2].equals(ultimoNombreJ)) {
					ultimoJugador = new Player(arrayString[2], arrayString[6], arrayString[4], (int) (fg * pts / 100));
					nba.add(ultimoJugador);
					ultimoNombreJ = arrayString[2];
				} else {
					ultimoJugador.addPlayer(arrayString[6], arrayString[4], (int) (fg * pts / 100));
				}

			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading data file: file not found");
		}

	}

	private static double remplazar(String s) {

		if (s.isEmpty())
			return 0;
		try {
			double d = Double.parseDouble(s.replace(",", "."));
			return d;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static ArrayList<Player> bestPlayer() {
		if (nba.size() == 0) {
			//System.out.println("No data");
			throw new RuntimeException("no hay datos");
		} else {
			ArrayList<Player> bPlayers = bestPlayer(0, nba.size() - 1);
			return bPlayers;
		}
		
	}

	public static ArrayList<Player> bestPlayer(int inicio, int fin) {
		ArrayList<Player> bPlayers = new ArrayList<Player>();
		if (inicio == fin) {
			bPlayers.add(nba.get(inicio));
		} else {
			int mitad = (inicio + fin) / 2;
			ArrayList<Player> sProblema1 = bestPlayer(inicio, mitad);
			ArrayList<Player> sProblema2 = bestPlayer(mitad + 1, fin);
			int i = 0;
			int j = 0;
			while (bPlayers.size() < top && i <= sProblema1.size() - 1 && j <= sProblema2.size() - 1) {
				if (sProblema1.get(i).getScore() > sProblema2.get(j).getScore()) {
					bPlayers.add(sProblema1.get(i));
					i++;
				} else {
					bPlayers.add(sProblema2.get(j));
					j++;
				}
			}
			while (bPlayers.size() < top && i <= sProblema1.size() - 1) {
				bPlayers.add(sProblema1.get(i));
				i++;
			}
			while (bPlayers.size() < top && j <= sProblema2.size() - 1) {
				bPlayers.add(sProblema2.get(j));
				j++;
			}
		}
		return bPlayers;
	}

	public static void main(String[] args) {
		cargarArchivo(filePath);
		// System.out.println(nba.toString());

		System.out.println("Los mejores jugadores son");
		bestPlayer();

	}

}
