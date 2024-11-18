package serializacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//OVERRIDE para evitar el error de que escriba de nuevo una cabecera
public class MiObjectOutputStream extends ObjectOutputStream {

	protected MiObjectOutputStream(FileOutputStream out) throws IOException, SecurityException {
		super(out);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeStreamHeader() throws IOException {
	}
}
