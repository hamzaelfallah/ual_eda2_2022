package practica1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSolucionNBA {
	
	
	private static String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "practica1" + File.separator ;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCargarArchivo() {
		SolucionNBA.cargarArchivo(filePath+"NbaStats.csv");

	}

	@Test
	void testBestPlayer() {
		String s = "[Wilt Chamberlain* => 1153, Kareem Abdul-Jabbar* => 1076, "
				+ "Michael Jordan* => 1075, George Gervin* => 1059, LeBron James => 1034, "
				+ "Karl Malone* => 1005, Karl-Anthony Towns => 965, Kevin Durant => 935, "
				+ "Oscar Robertson* => 925, Jerry West* => 854]"; 
		assertEquals(s, SolucionNBA.bestPlayer().toString());
		
		SolucionNBA.cargarArchivo(filePath+"100000ASC.csv");
		SolucionNBA.top = 5;
		
		String prueba1= "[Jugador0099999 => 99999, Jugador0099998 => 99998, Jugador0099997 => 99997, Jugador0099996 => 99996, Jugador0099995 => 99995]";
		assertEquals(prueba1, SolucionNBA.bestPlayer().toString());
		
		SolucionNBA.cargarArchivo(filePath+"100000DSC.csv");
		assertEquals(prueba1, SolucionNBA.bestPlayer().toString());
		
		SolucionNBA.cargarArchivo(filePath+"10000DSC.csv");
		SolucionNBA.top = 20;
		prueba1= "[Jugador009999 => 9999, Jugador009998 => 9998, Jugador009997 => 9997, Jugador009996 => 9996, "
				+ "Jugador009995 => 9995, Jugador009994 => 9994, Jugador009993 => 9993, Jugador009992 => 9992, Jugador009991 => 9991, Jugador009990 => 9990, Jugador009989 => 9989, Jugador009988 => 9988, Jugador009987 => 9987, Jugador009986 => 9986, Jugador009985 => 9985, Jugador009984 => 9984, "
				+ "Jugador009983 => 9983, Jugador009982 => 9982, Jugador009981 => 9981, Jugador009980 => 9980]";
		assertEquals(prueba1, SolucionNBA.bestPlayer().toString());
		
		
	}

}
