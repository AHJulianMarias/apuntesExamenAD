package txt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class lecturaYEscritura {
	public final static String RUTADATOS = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
			+ System.getProperty("file.separator") + "data" + System.getProperty("file.separator");
	public final static String DATOSFILE = "3_Ejercicio.csv";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader bfRd = new BufferedReader(new FileReader(RUTADATOS + DATOSFILE));
			String linea;
			while ((linea = bfRd.readLine()) != null) {
				System.out.println(linea);
			}
			bfRd.close();
			PrintWriter writer = new PrintWriter(new FileWriter(RUTADATOS + DATOSFILE, true));
			writer.println("Anibal;Herran;DAM;2");
			writer.close();

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe");
		} catch (IOException e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}

	}

}
