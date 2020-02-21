package Logica;

public class JugadorException extends Exception
{
	
	private String mensaje;
	
	
	public JugadorException (int id)
	{
		
		switch (id)
		{
	case 1:
		 mensaje = "Error, codigo incorrecto";
	case 2:
		 mensaje = "No hay jugadores a mostrar";
	case 3:
		mensaje =  "No existe el jugador en el Sistema";
	case 4:
		mensaje = "Ya existe un jugador con ese nombre";
	case 5:
		mensaje = "Ya tienes una partida en curso";
	case 6:
		mensaje = "No tienes ninguna partida en curso";
	case 7:
		mensaje = "El jugador no tiene ninguna partida en curso";
	default:
		mensaje = "Error no identificado";
		}
		
	}

}