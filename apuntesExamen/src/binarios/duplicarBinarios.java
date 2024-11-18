package binarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import txt.lecturaYEscritura;

public class duplicarBinarios {
	public final static String DATOSFILEIN = "imagen.jpg";
	public final static String DATOSFILEOUT = "imagen_copia.jpg";

	public static void main(String[] args) {
		try {
			FileInputStream fIS = new FileInputStream(new File(lecturaYEscritura.RUTADATOS + DATOSFILEIN));
			FileOutputStream fOS = new FileOutputStream(new File(lecturaYEscritura.RUTADATOS + DATOSFILEOUT));
			fOS.write(fIS.readAllBytes());
//			int leido;
//			while((leido = fIS.read())!= -1) {
//				fOS.write(leido);
//			}
			fIS.close();
			fOS.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura o escritura");
			e.printStackTrace();
		}

	}
}
