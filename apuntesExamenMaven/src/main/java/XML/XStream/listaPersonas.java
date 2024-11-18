package XML.XStream;

import java.util.ArrayList;

import utilidades.Persona;

public class listaPersonas {
	private ArrayList<Persona> lista = new ArrayList<Persona>();

	public listaPersonas(ArrayList<Persona> lista) {
		super();
		this.lista = lista;
	}

	public listaPersonas() {
		super();
	}

	public ArrayList<Persona> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Persona> lista) {
		this.lista = lista;
	}

	public void anadirPersona(Persona persona) {
		lista.add(persona);
	}

}
