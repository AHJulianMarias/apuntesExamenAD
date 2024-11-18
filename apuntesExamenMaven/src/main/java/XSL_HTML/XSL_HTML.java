package XSL_HTML;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import utilidades.Utilidades;

public class XSL_HTML {
	private static String FICHEROTRABAJO1_XSL = "ejercicio14.xsl";
	private static String FICHEROTRABAJO1_XML = "Ejercicio14.xml";
	private static String FICHEROTRABAJO1_Salida = "index.html";

	public static void main(String[] args) {
		try {
			TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
			Transformer transformador = fabricaTransformador
					.newTransformer(new StreamSource(Utilidades.RUTADATOS + FICHEROTRABAJO1_XSL));
			transformador.transform(new StreamSource(Utilidades.RUTADATOS + FICHEROTRABAJO1_XML),
					new StreamResult(Utilidades.RUTADATOS + FICHEROTRABAJO1_Salida));

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Result destino = new StreamResult("salida.html");
//		transformador.transform(Source sourceXML, Result destinoHTML);

	}
}
