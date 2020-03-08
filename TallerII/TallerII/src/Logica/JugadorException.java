package Logica;

import java.io.Serializable;

public class JugadorException extends Exception implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	
	public JugadorException (int id)
	{
		
		switch (id)
		{
	case 1:
		 mensaje = "Error, codigo incorrecto";
		 break;
	case 2:
		 mensaje = "No hay jugadores a mostrar";
		 break;
	case 3:
		mensaje =  "No existe el jugador en el Sistema";
		break;
	case 4:
		mensaje = "Ya existe un jugador con ese nombre";
		break;
	case 5:
		mensaje = "Ya tienes una partida en curso";
		break;
	case 6:
		mensaje = "No tienes ninguna partida en curso";
		break;
	case 7:
		mensaje = "El jugador no tiene ninguna partida en curso";
		break;
	default:
		mensaje = "Error no identificado";
		break;
		}
		
	}


	public String getMensaje() {
		return mensaje;
	}
	
	

}