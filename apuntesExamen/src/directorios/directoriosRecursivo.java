package directorios;

import java.io.File;
import java.util.Scanner;

public class directoriosRecursivo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingresa el nombre del directorio o fichero que quieres revisar");
		String nombreArchivo = sc.nextLine();
		File archivo = new File(nombreArchivo);
		if (archivo.exists()) {
			if (archivo.isDirectory()) {
				contDir(archivo, 0);

			} else {
				System.out
						.println("Tamaño " + archivo.length() + "\n¿Se puede leer? " + (archivo.canRead() ? "Si" : "No")
								+ "\n¿Se puede modificar? " + (archivo.canWrite() ? "Si" : "No")
								+ "\n¿Se puede ejecutar? " + (archivo.canExecute() ? "Si" : "No"));
			}
		} else {
			System.out.println("El archivo no existe");
		}
		sc.close();
	}

	public static void contDir(File file, int nivel) {
		File[] listaArchivos = file.listFiles();
		if (listaArchivos != null) {
			for (File a : listaArchivos) {
				System.out.println(" ".repeat(nivel) + a.getName());
				if (a.isDirectory()) {
					contDir(a, nivel + 1);
				}
			}
		}
	}

}
