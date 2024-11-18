package serializacion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/*
 * Ella usa tipo Date y new SimpleDateFormat en el toString
 * 
 * 
 * */

public class Persona_v2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date nacimiento;
	private int edad;

	public Persona_v2(String nombre, String apellido1, String apellido2, Date nacimiento) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nacimiento = nacimiento;
		this.edad = calcularEdad(nacimiento);
	}

	/*
	 * private int calcularEdad(Date nacimiento) { // Convertir Date a LocalDate
	 * LocalDate fechaNacimiento = nacimiento.toInstant()
	 * .atZone(ZoneId.systemDefault()) .toLocalDate(); LocalDate fechaActual =
	 * LocalDate.now();
	 * 
	 * // Calcular la diferencia en años return Period.between(fechaNacimiento,
	 * fechaActual).getYears(); }
	 */
	private int calcularEdad(Date nacimiento) {

		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaNacimiento = Calendar.getInstance();
		fechaNacimiento.setTime(nacimiento);
		int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
		if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNacimiento.get(Calendar.DAY_OF_YEAR)) {
			edad--;
		}

		return edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	@Override
	public String toString() {
		return "Nombre: " + this.nombre + "\nApellidos: " + this.apellido1 + " " + this.apellido2 + "\nEdad: "
				+ this.edad;
	}
}