package XML.DOM;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utilidades.Persona;
import utilidades.Utilidades;

public class xmlDesdeBinario {
	private static ObjectInputStream dataIn;
	private static ObjectOutputStream dataOut;
	private static DocumentBuilderFactory dBf = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder dB;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int option;
		do {
			System.out.println("----- Menú -----");
			System.out.println("1. Escribir objeto");
			System.out.println("2. Leer archivo xml");
			System.out.println("3. Leer archivo binario");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opción: ");
			option = scanner.nextInt();

			switch (option) {
			case 1:
				try {
					escribirXML();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Personas leídas del archivo:");
				LeerXML();
				break;
			case 3:
				leerObjeto();
				break;
			case 4:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (option != 4);

		scanner.close();
		try {
			if (dataOut != null)
				dataOut.close();
			if (dataIn != null)
				dataIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void escribirXML() throws ParserConfigurationException {
		dB = dBf.newDocumentBuilder();
		Document doc = dB.newDocument();
		try {

			Element elementoRaiz = doc.createElement("personas");
			doc.appendChild(elementoRaiz);
			dataIn = new ObjectInputStream(
					new FileInputStream(new File(Utilidades.RUTADATOS + "ejercicio6persona.dat")));

			while (true) {
				try {
					Persona leido = (Persona) dataIn.readObject();
					Element elementoPersona = doc.createElement("persona");
					Element elementoNombre = doc.createElement("nombre");
					Element elementoAp1 = doc.createElement("Apellido1");
					Element elementoAp2 = doc.createElement("Apellido2");
					Element nacimiento = doc.createElement("nacimiento");
					// TEXTO
					elementoNombre.setTextContent(leido.getNombre());
					elementoAp1.setTextContent(leido.getApellido1());
					elementoAp2.setTextContent(leido.getApellido2());
					nacimiento.setTextContent(leido.getNacimiento().toString());
					elementoPersona.appendChild(elementoAp1);
					elementoPersona.appendChild(elementoAp2);
					elementoPersona.appendChild(nacimiento);
//					elementoPersona.setAttribute("nombre", leido.getNombre());
//					elementoPersona.setAttribute("Apellido1", leido.getApellido1());
//					elementoPersona.setAttribute("Apellido2", leido.getApellido2());

					// ATRIBUTO
					elementoPersona.setAttribute("nacimiento", leido.getNacimiento().toString());

					elementoRaiz.appendChild(elementoPersona);
				} catch (IOException | ClassNotFoundException e) {
					System.out.println("Final del archivo.");
					break;
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				TransformerFactory tF = TransformerFactory.newInstance();
				Transformer t;
				t = tF.newTransformer();
				t.transform(new DOMSource(doc),
						new StreamResult(new File(Utilidades.RUTADATOS + "ejercicioCreado3.xml")));
				dataIn.close();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void LeerXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Cargamos en memoria el doc XML
			Document doc = builder.parse(new File(Utilidades.RUTADATOS + "ejercicioCreado3.xml"));
			LeeNodoAtributos(doc.getDocumentElement(), 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void leerObjeto() {
		try {
			dataIn = new ObjectInputStream(
					new FileInputStream(new File(Utilidades.RUTADATOS + "ejercicio6persona.dat")));
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

//	public static void LeeNodo(Node nodo, int nivel) {
//
//		if (nodo.getNodeType() == Node.TEXT_NODE) {
//			System.out.println(" ".repeat(nivel + 1) + nodo.getTextContent().toString());
//		} else if (nodo.getNodeType() == Node.ELEMENT_NODE) {
//			System.out.println(" ".repeat(nivel) + "<" + nodo.getNodeName() + ">");
//			NodeList listaNodos = nodo.getChildNodes();
//			for (int i = 0; i < listaNodos.getLength(); i++) {
//				LeeNodo(listaNodos.item(i), nivel + 1);
//			}
//			System.out.println(" ".repeat(nivel) + "</" + nodo.getNodeName() + ">");
//		}
//
//	}

	// CON ATRIBUTOS
	public static void LeeNodoAtributos(Node nodo, int nivel) {

		if (nodo.getNodeType() == Node.TEXT_NODE) {
			System.out.println(" ".repeat(nivel + 1) + nodo.getTextContent().toString());
		} else if (nodo.getNodeType() == Node.ELEMENT_NODE) {
			System.out.print(" ".repeat(nivel) + "<" + nodo.getNodeName());
			NamedNodeMap mapAtributos = nodo.getAttributes();
			if (mapAtributos.getLength() > 0) {
				for (int i = 0; i < mapAtributos.getLength(); i++) {
					System.out.print(
							" " + mapAtributos.item(i).getNodeName() + "=" + mapAtributos.item(i).getNodeValue());
				}

			}
			if (nodo.getChildNodes().getLength() > 0) {
				System.out.println(">");
				NodeList listaNodos = nodo.getChildNodes();
				for (int i = 0; i < listaNodos.getLength(); i++) {
					LeeNodoAtributos(listaNodos.item(i), nivel + 1);
				}
				System.out.println(" ".repeat(nivel) + "</" + nodo.getNodeName() + ">");
			} else {
				System.out.println("/>");
			}

		} else if (nodo.getNodeType() == Node.ATTRIBUTE_NODE) {
			NamedNodeMap mapAtributos = nodo.getAttributes();
			for (int i = 0; i < mapAtributos.getLength(); i++) {
				System.out.print(mapAtributos.item(i).getNodeName() + "=" + mapAtributos.item(i).getNodeValue());
				LeeNodoAtributos(mapAtributos.item(i), nivel + 1);
			}

		}

	}

}
