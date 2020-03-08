package ValueObjects;

import java.io.Serializable;

public class VOJugadorDespliegue implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int puntajeTotal;
	private int cantPartidasFinalizadas;
	private int cociente;
	
	public VOJugadorDespliegue(String nombre, int puntajeTotal, int cantPartidasFinalizadas,
			int cociente) {

		this.nombre = nombre;
		this.puntajeTotal = puntajeTotal;
		this.cantPartidasFinalizadas = cantPartidasFinalizadas;
		this.cociente = cociente;
	}

	
	public String getNombre() {
		return nombre;
	}

	public int getPuntajeTotal() {
		return puntajeTotal;
	}

	public int getCantPartidasFinalizadas() {
		return cantPartidasFinalizadas;
	}

	public int getCociente() {
		return cociente;
	}


	@Override
	public String toString() {
		return  nombre + ", " + cociente ;
	}
	
	
}
