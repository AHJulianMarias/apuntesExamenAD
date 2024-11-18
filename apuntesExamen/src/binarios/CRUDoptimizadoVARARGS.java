package binarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

import txt.lecturaYEscritura;

public class CRUDoptimizadoVARARGS {

	public final static String DATOSFILE = "numeros.dat";
	private static RandomAccessFile archivoDat;
	private final static int TAMANIO = 4;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			archivoDat = new RandomAccessFile(new File(lecturaYEscritura.RUTADATOS + DATOSFILE), "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int opcion = 0;

		while (opcion != 6) {
			try {
				System.out.println("1. Escribir en el archivo");
				System.out.println("2. Leer desde el archivo");
				System.out.println("3. Consultar valor en una posición");
				System.out.println("4. Añadir valor al final del archivo");
				System.out.println("5. Modificar valor en una posición");
				System.out.println("6. Salir");
				System.out.print("Elige una opción: ");
				opcion = scanner.nextInt();

				switch (opcion) {
				case 1:
					escribir();
					break;
				case 2:
					leer();
					break;
				case 3:
					System.out.print("Introduce la posición a consultar: ");
					int posicionConsulta = scanner.nextInt();
					leer(posicionConsulta);
					break;
				case 4:
					System.out.print("Introduce el valor a añadir: ");
					int valorAñadir = scanner.nextInt();
					escribir(valorAñadir);
					break;
				case 5:
					System.out.print("Introduce la posición a modificar: ");
					int posicionModificar = scanner.nextInt();
					System.out.print("Introduce el nuevo valor: ");
					int nuevoValor = scanner.nextInt();
					escribir(posicionModificar, nuevoValor);
					break;
				case 6:
					System.out.println("Saliendo del programa...");
					try {
						archivoDat.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.exit(0);
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes introducir un número");
				scanner.next();
			}
		}

		scanner.close();
	}

	/**
	 * escribe del 1 al 100 en un archivo si tiene valor unicamente como parametro
	 * lo añade al final si tiene valor y posicion, modifica la posicion indicada
	 * para reemplazarlo posicion y valor se pasaran como parametros, siendo primero
	 * la posicion y segundo el valor
	 * 
	 * @param si vacio, inicializa el fichero con valores del 1 al 100
	 * @return numero de elementos escritos o 1 en caso de excepcion
	 */
	// varargs
	public static int escribir(int... valores) {

		try {
			if (valores.length == 0) {
				archivoDat.setLength(0);
				int i;
				for (i = 1; i <= 100; i++) {
					archivoDat.writeInt(i);
				}
				return i;
			} else if (valores.length == 1) {

				archivoDat.seek(archivoDat.length());
				archivoDat.writeInt(valores[0]);
				System.out.println("Numero " + valores[0] + " añadido al final del archivo");
				return 0;

			} else if (valores.length == 2) {
				if (valores[0] > archivoDat.length()) {
					return -1;
				}
				archivoDat.seek(valores[0]);
				int numViejo = archivoDat.readInt();
				archivoDat.writeInt(valores[1]);
				System.out.println("Numero " + numViejo + " reemplazado por " + valores[1]);
				return 0;
			} else {
				return -1;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}

	}

	/**
	 * Lee y muestra por consola el archivo entero o el valor de la posicion pasada
	 * como parametro
	 * 
	 * @param lugar
	 * @return Si vacio lee el contenido de todo el fichero y devuelve el numero de
	 *         elementos leidos o -1 en caso de excepcion Si tiene posicion, muestra
	 *         el elemento en esa posicion y devuelve el valor
	 */
	public static int leer(int... posicion) {
		try {
			if (posicion.length == 0) {
				archivoDat.seek(0);
				int i = 0;
				while (archivoDat.getFilePointer() < archivoDat.length()) {
					int numero = archivoDat.readInt();
					System.out.println(numero);
					i++;
				}
				return i;
			} else {
				int posicionA = posicion[0] - 1;
				if (posicionA < 0 || posicionA * 4 > archivoDat.length()) {
					return -1;
				} else {
					archivoDat.seek(posicionA * TAMANIO);
					int num = archivoDat.readInt();
					System.out.println("En la posicion " + posicion[0] + " se encuentra el numero " + num);
					return num;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

}
