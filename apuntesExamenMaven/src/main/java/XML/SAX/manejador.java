package XML.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class manejador extends DefaultHandler {
	// Al inicio de cada elemento lo abre con <nombre> y si tiene atributos, les
	// añade
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		System.out.print("<" + qName);
		// si tuviera atributos, les añadiría
		if (attributes.getLength() > 0) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(" " + attributes.getLocalName(i) + "=" + attributes.getValue(i));
			}
		}
		System.out.print(">");
	}

	// finaliza el elemento con el </ nombre >
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		System.out.print("</" + qName + ">");

	}

	// rellena de texto, es decir lo de en medio
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		System.out.print(new String(ch, start, length));
	}

}
