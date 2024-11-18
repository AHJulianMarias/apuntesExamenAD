package directorios;

import java.io.File;

public class contDirActual {

	public static void main(String[] args) {

		File directorioActual = new File(System.getProperty("user.dir"));
		File[] hijos = directorioActual.listFiles();
		System.out.println("Nombre del directorio actual: " + directorioActual.getName() + "\nNumero de archivos: "
				+ hijos.length);
		for (File b : hijos) {
			if (b.isDirectory()) {
				System.out.println("D" + b.getName());
			} else if (b.isFile()) {
				System.out.println("F" + b.getName());
			}
		}

		// Con ternaria
		for (File c : hijos) {
			System.out.println(c.getName() + "\t" + (c.isDirectory() ? " D" : " F"));

		}

	}
}
