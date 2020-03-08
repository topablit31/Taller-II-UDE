package Logica;

import java.io.Serializable;

public class PartidaException extends Exception implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	
	public PartidaException ()
	{
		mensaje = "El jugador no tiene partidas";
	}


	public String getMensaje() {
		return mensaje;
	}
	
	
	
}