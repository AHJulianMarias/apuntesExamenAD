package XML.DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import utilidades.Utilidades;

public class crearXMLDOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(Utilidades.RUTADATOS + "archivoCreado.xml");
		DocumentBuilderFactory dBf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB;
		try {
			dB = dBf.newDocumentBuilder();
			Document doc = dB.newDocument();
			// Creacion de nodos
			Element raiz = doc.createElement("raiz");
			Element hijo1 = doc.createElement("hijo");
			Element hijo2 = doc.createElement("hijo");
			Text contH1 = doc.createTextNode("Soy el primer hijo");
			Text contH2 = doc.createTextNode("Soy el segundo hijo");
			// creacion del arbol
			doc.appendChild(raiz);
			raiz.appendChild(hijo1);
			raiz.appendChild(hijo2);
			hijo1.appendChild(contH1);
			hijo2.appendChild(contH2);
			// Arbol creado en memoria, toca hacerlo persistente
			TransformerFactory tF = TransformerFactory.newInstance();
			Transformer t = tF.newTransformer();
			t.transform(new DOMSource(doc), new StreamResult(new File(Utilidades.RUTADATOS + "ejercicioCreado.xml")));
		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
