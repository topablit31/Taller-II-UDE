package Persistencia;

import java.io.Serializable;

public class PersistenciaException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public PersistenciaException(int id) {
		switch (id) {
		case 1:
			mensaje = "Problema de respaldo";
			break;
		case 2:
			mensaje = "Problema de leer";
			break;
		default:
			mensaje = "No se identifica error";
			break;
		}

	}

	public String getMensaje() {
		return mensaje;
	}
	
	
}