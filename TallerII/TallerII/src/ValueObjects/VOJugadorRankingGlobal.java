package ValueObjects;

import java.io.Serializable;

/**
 * @author nacho
 *
 */
public class VOJugadorRankingGlobal implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int posicion;
	private String nombre;
	private int puntajeTotal;
	private int cantPartidasFinalizadas;
	private int cociente;
	
	public VOJugadorRankingGlobal (int pos, String nom, int punt, int cantPart, int coc)
	{
		posicion = pos;
		nombre = nom;
		puntajeTotal = punt;
		cantPartidasFinalizadas = cantPart;
		cociente = coc;
	}

	public int getPosicion() 
	{
		return posicion;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public int getPuntajeTotal() 
	{
		return puntajeTotal;
	}

	public int getCantPartidasFinalizadas() 
	{
		return cantPartidasFinalizadas;
	}

	public int getCociente() 
	{
		return cociente;
	}
	
	
}
