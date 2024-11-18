package XML.XStream;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import utilidades.CRUD;
import utilidades.Persona;
import utilidades.Utilidades;

/**
 * 
 * NECESITAS AÑADIR DEPENDENCIA
 * 
 * 
 */
public class serializacionYDeserializacionXML {
	private static XStream xS = new XStream(new DomDriver("UTF-8"));
	private static ObjectInputStream oIS = null;
	private static ObjectOutputStream dataOut;
	private static String FICHEROTRABAJO_IN = "ejercicio6persona.dat";
	private static String FICHEROTRABAJO_OUT = "ejercicio15.xml";
	private static String FICHEROTRABAJO_INAMPLIACION = "ejercicio15_in.xml";
	private static listaPersonas lP = null;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int option;
		do {
			CRUD.Inicializar();
			System.out.println("----- Menú -----");
			System.out.println("1. Serializar objeto");
			System.out.println("2. Deserializar objeto");
			System.out.println("3. Salir");
			System.out.print("Seleccione una opción: ");
			option = scanner.nextInt();

			switch (option) {
			case 1:
				System.out.println(serializarXML() ? "Serializado correctamente" : "Error en la serialización");

				break;
			case 2:
				System.out
						.println(deserializar_a_XML() ? "Deserializado correctamente" : "Error en la deserialización");
				break;
			case 3:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (option != 3);

		scanner.close();
		try {
			if (dataOut != null) {
				dataOut.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean serializarXML() {

		listaPersonas lP = new listaPersonas();
		try {
			defineEstructura();
			xS.addPermission(AnyTypePermission.ANY);
			xS.aliasField("primerApellido", Persona.class, "apellido1");
			xS.aliasField("segundoApellido", Persona.class, "apellido2");
			xS.aliasField("nombre", Persona.class, "nombre");
			lP = (listaPersonas) xS.fromXML(new FileInputStream(Utilidades.RUTADATOS + FICHEROTRABAJO_INAMPLIACION));

			for (Persona a : lP.getLista()) {
				CRUD.EscribirObjeto(a);
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	private static boolean deserializar_a_XML() {
		try {
			oIS = new ObjectInputStream(new FileInputStream(Utilidades.RUTADATOS + FICHEROTRABAJO_IN));
			// LOGICA BIEN PERO NO SE PUEDE DESDE UN ARRAYLIST DE JAVA, TIENE QUE SER DESDE
			// UNA CLASE
			lP = new listaPersonas();
			while (true) {
				try {
					Persona leido = (Persona) oIS.readObject();
					lP.anadirPersona(leido);
				} catch (EOFException e) {
					System.out.println("Final del archivo.");
					break;

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

					defineEstructura();
					xS.toXML(lP, new FileOutputStream(Utilidades.RUTADATOS + FICHEROTRABAJO_OUT));
				}
			}
			oIS.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private static void defineEstructura() {
		xS.alias("familia", listaPersonas.class);
		xS.alias("miembro", Persona.class);
		xS.addImplicitCollection(listaPersonas.class, "lista");
		xS.aliasField("primerapellido", Persona.class, "apellido1");
		xS.aliasField("segundoapellido", Persona.class, "apellido2");
		xS.aliasField("name", Persona.class, "nombre");
		xS.useAttributeFor(Persona.class, "nombre");

	}

}
