package ValueObjects;

import java.io.Serializable;

public class VOPartidaEsMayorMenor implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numPartida;
	private boolean finalizada;
	private int cantIntentos;
	private int puntajeFinal;
	private String mensaje;
	
	public VOPartidaEsMayorMenor (int num, boolean fin, int cantIn, int punt, String msg)
	{
		numPartida = num;
		finalizada = fin;
		cantIntentos = cantIn;
		puntajeFinal = punt;
		mensaje = msg;
	}
	
	public int getNumPartida() 
	{
		return numPartida;
	}
	public boolean isFinalizada() 
	{
		return finalizada;
	}
	public int getCantIntentos() 
	{
		return cantIntentos;
	}
	public int getPuntajeFinal() 
	{
		return puntajeFinal;
	}
	public String getMensaje() 
	{
		return mensaje;
	}
	
	
}
