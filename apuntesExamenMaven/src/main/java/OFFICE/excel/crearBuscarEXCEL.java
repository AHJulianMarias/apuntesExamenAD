package OFFICE.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilidades.Utilidades;

public class crearBuscarEXCEL {
	public final static String FICHEROTRABAJO = "vehiculosElectricos.xlsx";
	private static Workbook wb;

	public static void main(String[] args) {
		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(Utilidades.RUTADATOS + FICHEROTRABAJO)));
			buscarEstacionesPorCiudad("salamanca");
//			buscarEstacionesPorMarca("wenea");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void buscarEstacionesPorMarca(String operador) {
		Sheet hoja = wb.getSheetAt(0);
		int numFila = 1;
		Row fila = hoja.getRow(numFila);

		// Muestras localizaciones de los puntos de carga del operador pasado como
		// parametro

		System.out.println("Puntos de recarga de " + operador);

		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(2);
			if (celdaBusqueda != null
					&& celdaBusqueda.getStringCellValue().toLowerCase().contains(operador.toLowerCase())) {
				System.out.println("OPERADOR " + operador.toUpperCase() + " Nombre:"
						+ fila.getCell(0).getStringCellValue() + "(" + fila.getCell(0).getStringCellValue() + ")");
			}
			fila = hoja.getRow(numFila++);

		}

	}

	// crear un metodo que muestre los puntos de recarga de una ciudad en concreto
	// que se pasa como parametro
	// que añada una hoja nueva en el excel con el nombre de la ciudad y sus puntos
	// de carga

	private static void buscarEstacionesPorCiudad(String ciudad) {
		try {

			Sheet hoja = wb.getSheetAt(0);
			Sheet hojaNueva = wb.createSheet(ciudad);

			int numFila = 1, newNumFila = 0;
			Row fila = hoja.getRow(numFila);

			System.out.println("Puntos de recarga de " + ciudad);

			while (fila != null) {
				Cell celdaBusqueda = fila.getCell(1);

				if (celdaBusqueda != null && celdaBusqueda.getStringCellValue().toLowerCase()
						.contains("(" + ciudad.toLowerCase() + ")")) {
					System.out.println(
							"Ciudad " + ciudad.toUpperCase() + " Nombre:" + fila.getCell(0).getStringCellValue()
									+ " Direccion:" + fila.getCell(1).getStringCellValue());
					Row filaCreada = hojaNueva.createRow(newNumFila);
					newNumFila++;
					filaCreada.createCell(0).setCellValue(fila.getCell(0).getStringCellValue());
					filaCreada.createCell(1).setCellValue(fila.getCell(1).getStringCellValue());
				}
				fila = hoja.getRow(numFila++);

			}
			wb.write(new FileOutputStream(new File(Utilidades.RUTADATOS + FICHEROTRABAJO)));
		} catch (IllegalArgumentException e) {
			System.out.println("Esa hoja ya existe, quieres sobreescribir? (S/N)");
			Scanner sc = new Scanner(System.in);
			if (sc.next().toLowerCase().equals("s")) {
				wb.removeSheetAt(wb.getSheetIndex(ciudad));

			}
			buscarEstacionesPorCiudad(ciudad);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
