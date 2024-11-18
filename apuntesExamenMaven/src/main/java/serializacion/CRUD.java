package serializacion;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import utilidades.Persona;
import utilidades.Utilidades;

// NUEVO EJERCICIO HACER CON EDAD EN VEZ DE NACIMIENTO Y CAMBIAR EL SERIAL
public class CRUD {
	public final static String DATOSFILE = "ejercicio6persona.dat";
	public final static String DATOSFILE2 = "ejercicio6persona2.dat";
	private static ObjectOutputStream dataOut;
	private static ObjectInputStream dataIn;
	private static ObjectOutputStream dataOut2;
	private static ObjectInputStream dataIn2;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int option;
		do {
			Inicializar();
			System.out.println("----- Menú -----");
			System.out.println("1. Escribir objeto");
			System.out.println("2. Leer archivo");
			System.out.println("3. Traspaso de archivos");
			System.out.println("4. Leer segundo archivo");
			System.out.println("5. Salir");
			System.out.print("Seleccione una opción: ");
			option = scanner.nextInt();

			switch (option) {
			case 1:
				EscribirObjeto(obtenerDatos());
				break;
			case 2:
				System.out.println("Personas leídas del archivo:");
				leerObjeto();
				break;
			case 3:
				System.out.println("Cambiando de un archivo a otro");
				traspasoArchivo();
				break;
			case 4:
				System.out.println("Leyendo el segundo archivo...");
				leerArchivo2();
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (option != 5);

		scanner.close();
		try {
			dataOut.close();
			dataIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Persona obtenerDatos() {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		try {
			System.out.println("Introduce el nombre de la persona");
			String nombre = sc.next();
			System.out.println("Introduce el primer apellido");
			String ape1 = sc.next();
			System.out.println("Introduce el segundo apellido");
			String ape2 = sc.next();
			System.out.println("Introduce la fecha de nacimiento, el formato tiene que ser DD/MM/YYYY");
			String nacimiento = sc.next();
			Date nacimientoD = date.parse(nacimiento);
			Persona objetoPersona = new Persona(nombre, ape1, ape2, nacimientoD);
			return objetoPersona;
		} catch (ParseException e) {
			System.out.println("error de formato de fecha");
		}
		return null;
	}

	public static void Inicializar() {

		File archivoUsado = new File(Utilidades.RUTADATOS + DATOSFILE);
		File archivoReciclado = new File(Utilidades.RUTADATOS + DATOSFILE2);
		try {
			if (archivoUsado.exists() && archivoUsado.length() > 0) {
				dataOut = new MiObjectOutputStream(new FileOutputStream(archivoUsado, true));
				dataOut2 = new MiObjectOutputStream(new FileOutputStream(archivoReciclado, true));
			} else if (archivoUsado.length() == 0) {
				dataOut = new ObjectOutputStream(new FileOutputStream(archivoUsado));
				dataOut2 = new ObjectOutputStream(new FileOutputStream(archivoReciclado, true));
			}

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dataIn = new ObjectInputStream(new FileInputStream(new File(Utilidades.RUTADATOS + DATOSFILE)));
			dataIn2 = new ObjectInputStream(new FileInputStream(new File(Utilidades.RUTADATOS + DATOSFILE2)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void EscribirObjeto(Persona objetoPersona) {
		try {
			dataOut.writeObject(objetoPersona);
			System.out.println("Objeto escrito");
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dataOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void leerObjeto() {
		try {
			while (true) {
				try {
					Persona leido = (Persona) dataIn.readObject();
					System.out.println(leido.toString());
				} catch (EOFException e) {
					System.out.println("Final del archivo.");
					break;

				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error de entrada salida");
			e.printStackTrace();
		}

	}

	public static void traspasoArchivo() {

		while (true) {
			try {
				Persona leido = (Persona) dataIn.readObject();
				Persona_v2 persona2 = new Persona_v2(leido.getNombre(), leido.getApellido1(), leido.getApellido2(),
						leido.getNacimiento());
				dataOut2.writeObject(persona2);
			} catch (EOFException e) {
				System.out.println("Final del archivo.");
				break;
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void leerArchivo2() {
		try {
			while (true) {
				try {
					Persona_v2 leido2 = (Persona_v2) dataIn2.readObject();
					System.out.println(leido2.toString());
				} catch (EOFException e) {
					System.out.println("Final del archivo.");
					break;

				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error de entrada salida");
			e.printStackTrace();
		}

	}

}
