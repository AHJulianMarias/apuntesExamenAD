package XML.SAX;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import utilidades.Utilidades;

public class ejercicioRecetaDeterminada {
	public final static String FICHEROTRABAJO = "ejercicio12SAX.xml";

	public static void main(String[] args) {
		try {
			SAXParserFactory sPF = SAXParserFactory.newInstance();
			SAXParser sP = sPF.newSAXParser();
			String receta = "Pollo al curry";
			System.out.println("Receta para hacer " + receta);
			sP.parse(new InputSource(Utilidades.RUTADATOS + FICHEROTRABAJO),
					new ejercicioManejadorRecetaDeterminada(receta));

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
