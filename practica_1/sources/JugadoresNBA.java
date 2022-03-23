package practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JugadoresNBA {

	private static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "practica1" + File.separator;
	private static String c = "﻿#;SeasonStart;PlayerName;PlayerSalary;Pos;Age;Tm;FG%;PTS";

	public static void main(String[] args) {
		
		long start, end;
		System.out.println("N\tTiempo");
		for (int i = 1; i < 1984584574; i *= 2) {
			generarJugadoresDSC(i, 100);
			start = System.nanoTime();
			SolucionNBA.bestPlayer();
			end = System.nanoTime();
			System.out.println(i + "\t" + (end - start));
		}
	}

	public static void generarJugadoresASC(int size, int top) {
		SolucionNBA.nba = new ArrayList<Player>();
		for (int i = 0; i < size; i++) {
			Player Jugador = new Player("Jugador00" + i, "", "", i);
			SolucionNBA.nba.add(Jugador);
		}
		System.out.println(SolucionNBA.nba);
		SolucionNBA.top = top;
	}

	public static void generarJugadoresDSC(int size, int top) {
		SolucionNBA.nba = new ArrayList<Player>();
		for (int i = size; i > 0; i--) {
			Player Jugador = new Player("Jugador00" + i, "", "", i);
			SolucionNBA.nba.add(Jugador);
		}
		SolucionNBA.top = top;
	}

}
