package XML.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ejercicioManejadorRecetaDeterminada extends DefaultHandler {

	private String receta = "";
	private boolean encontrado = false;

	public ejercicioManejadorRecetaDeterminada(String receta) {
		super();
		this.receta = receta;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		if (qName.equalsIgnoreCase("receta")) {
			for (int i = 0; i < attributes.getLength(); i++) {
				if (attributes.getValue(i).equalsIgnoreCase(this.receta)) {
					encontrado = true;
					break;
				} else {
					encontrado = false;
				}
			}
		}
		if (qName.equalsIgnoreCase("ingrediente") && encontrado) {
			System.out.print("<" + qName);
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(" " + attributes.getLocalName(i) + "=" + attributes.getValue(i));
			}
			System.out.println("/>");
		}
	}
}
