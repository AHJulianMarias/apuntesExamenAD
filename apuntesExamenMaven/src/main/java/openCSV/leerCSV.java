package openCSV;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

import utilidades.Utilidades;

public class leerCSV {
	public final static String DATOSFILE = "ejercicio08.csv";

	public static void main(String[] args) {

		// PARA ESCRIBIR SE HACE CON LA CLASE FILE
		CSVReader reader = null;
		try {
			// separador y quotechar
			reader = new CSVReader(new FileReader(Utilidades.RUTADATOS + DATOSFILE), ',', '"');
//			String[] lineas;
//			while ((lineas = reader.readNext()) != null) {
//				mostrarValores(lineas);
//			}
			List<String[]> lineas = reader.readAll();
			for (String[] linea : lineas) {
				mostrarValores(linea);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void mostrarValores(String[] campos) {
		for (String a : campos) {
			System.out.print(a + "|");
		}
		System.out.println();
	}

}
