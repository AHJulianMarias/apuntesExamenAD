package configuracion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

public class CRUDconfiguracion {
	public final static String RUTADATOS = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
			+ System.getProperty("file.separator") + "data" + System.getProperty("file.separator");
	public final static String DATOSFILE = "configuracion.props";
	public static Scanner scanner = new Scanner(System.in);
	private static FileOutputStream out;
	private static FileInputStream in;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int option;
		do {
			System.out.println("----- Menú -----");
			System.out.println("1. Iniciar configuracion");
			System.out.println("2. Leer configuracion");
			System.out.println("3. Cambiar configuracion");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opción: ");
			option = scanner.nextInt();

			switch (option) {
			case 1:
				System.out.println(
						iniciarPropiedades() ? "Configuracion guardada" : "Problema iniciando la configuración");
				break;
			case 2:
				leerConfiguracion();
				break;
			case 3:
				System.out.println(modificarConfiguracion() ? "Configuración modificada correctamente"
						: "Error modificando la configuración");
				break;
			case 4:
				System.out.println("Saliendo...");
				break;

			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (option != 4);
	}

	public static boolean iniciarPropiedades() {

		try {
			out = new FileOutputStream(RUTADATOS + DATOSFILE);

			Properties p = new Properties();
			p.setProperty("user", "usuario");
			p.setProperty("password", "micontrasena");
			p.setProperty("server", "localhost");
			p.setProperty("port", "3306");
			p.setProperty("date", "11-08-2022");
			p.setProperty("power", "true");

			p.store(out, "Configuracion guardada");
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static void leerConfiguracion() {

		try {
			in = new FileInputStream(RUTADATOS + DATOSFILE);
			Properties pLeer = new Properties();
			pLeer.load(in);
			pLeer.list(System.out);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean modificarConfiguracion() {
		try {
			// true indica que se agrega
			out = new FileOutputStream(RUTADATOS + DATOSFILE, true);
			in = new FileInputStream(RUTADATOS + DATOSFILE);
			Properties pLeer = new Properties();

			pLeer.load(in);
			System.out.println("MODIFICANDO CONFIGURACION");
			System.out.println("Valor a sumar al puerto");
			int puerto = scanner.nextInt();
			puerto += Integer.valueOf(pLeer.getProperty("port"));
			String puertostr = String.valueOf(puerto);

			System.out.println("Nuevo mes en la fecha");
			int mes = Integer.valueOf(scanner.next());
			String fecha = pLeer.getProperty("date");
			pLeer.setProperty("port", puertostr);
			LocalDate fechaActual = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			fechaActual = fechaActual.withMonth(mes);
			pLeer.setProperty("date", fechaActual.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			try {
				pLeer.store(out, "Cambiado");
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return false;
	}
}
