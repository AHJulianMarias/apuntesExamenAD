package XML.DOM;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import utilidades.Utilidades;

public class leerArchivoyWebDOM {

	public static void main(String[] args) throws SAXException, IOException, URISyntaxException {
		// TODO Auto-generated method stub
		URI dir = new URI("https://acortar.link/dO4FNx");
		URL url = dir.toURL();
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB;
		try {
			dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(Utilidades.RUTADATOS + "ejercicio10.xml");
			Document docWeb = dB.parse(url.openStream());
			TransformerFactory tF = TransformerFactory.newInstance();
			Transformer t = tF.newTransformer();

			// DOCUMENTO
			t.transform(new DOMSource(doc), new StreamResult(System.out));
			// DOCUMENTO WEB
//			t.transform(new DOMSource(docWeb), new StreamResult(System.out));

		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
