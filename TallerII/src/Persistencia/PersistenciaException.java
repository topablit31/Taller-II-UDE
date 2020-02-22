package Persistencia;

public class PersistenciaException extends Exception {

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