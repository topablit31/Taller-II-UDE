package ValueObjects;

import Logica.SecuenciaDePartidas;

/**
 * @author nacho
 *
 */
public class VOJugadorRankingGlobal
{
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
