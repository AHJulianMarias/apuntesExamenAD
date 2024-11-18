package binarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

import txt.lecturaYEscritura;

public class CRUDaccesoAleatorioDAT {
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
					consultar(posicionConsulta);
					break;
				case 4:
					System.out.print("Introduce el valor a añadir: ");
					int valorAñadir = scanner.nextInt();
					añadirFinal(valorAñadir);
					break;
				case 5:
					System.out.print("Introduce la posición a modificar: ");
					int posicionModificar = scanner.nextInt();
					System.out.print("Introduce el nuevo valor: ");
					int nuevoValor = scanner.nextInt();
					modificarValor(posicionModificar, nuevoValor);
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
	 * escribe del 1 al 100 en un archivo
	 * 
	 * @return numero de elementos escritos o 1 en caso de excepcion
	 */
	public static int escribir() {

		try {
			archivoDat.setLength(0);
			int i;
			for (i = 1; i <= 100; i++) {
				archivoDat.writeInt(i);
			}
			return i;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}

	}

	/**
	 * Lee el contenido del archivo
	 * 
	 * @return devuelve el numero de elementos leidos o 0 en caso de excepcion
	 */
	public static int leer() {
		try {
			// puntero
			archivoDat.seek(0);
			int i = 0;
			while (archivoDat.getFilePointer() < archivoDat.length()) {
				int numero = archivoDat.readInt();
				System.out.println(numero);
				i++;

			}
			return i;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * muestra por consola el valor de la posicion indicada
	 * 
	 * @param posicion
	 * @return devuelve el valor de la posicion indicada, 0 en caso de error y -1 si
	 *         la posicion no se encuentra en el archivo
	 */
	public static int consultar(long posicion) {
		long posicionA = posicion - 1;
		try {
			if (posicionA < 0 || posicionA * 4 > archivoDat.length()) {
				return -1;
			} else {
				archivoDat.seek(posicionA * TAMANIO);
				int num = archivoDat.readInt();
				System.out.println("En la posicion " + posicion + " se encuentra el numero " + num);
				return num;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * metodo para añadir un valor al final
	 * 
	 * @param numeroAñadir
	 * @return true si se ha añadido, false si no se ha añadido
	 */
	public static boolean añadirFinal(int numeroAñadir) {
		try {
			// apunta al final
			archivoDat.seek(archivoDat.length());
			archivoDat.writeInt(numeroAñadir);
			System.out.println("Numero " + numeroAñadir + " añadido al final del archivo");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Modifica el valor de la posicion que tu le indiques
	 * 
	 * @param posicionModificar
	 * @param nuevoNumero
	 * @return devuelve 0 si se ha modificado el valor, devuelve 1 si ha saltado una
	 *         excepcion y -1 en caso de que la posicion a modificar no se encuentre
	 *         en el archivo
	 */
	public static int modificarValor(long posicionModificar, int nuevoNumero) {
		posicionModificar = (posicionModificar - 1) * 4;
		try {
			if (posicionModificar > archivoDat.length()) {
				return -1;
			}
			archivoDat.seek(posicionModificar);
			int numViejo = archivoDat.readInt();
			archivoDat.writeInt(nuevoNumero);
			System.out.println("Numero " + numViejo + " reemplazado por " + nuevoNumero);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}

	}

}
