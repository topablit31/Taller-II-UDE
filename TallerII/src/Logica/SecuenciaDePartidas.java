package Logica;


import java.util.*;

import ValueObjects.VOPartida;

public class SecuenciaDePartidas
{

	private LinkedList <Partida> partidas;

	//Constructor
	public SecuenciaDePartidas() 
	{
		partidas=new LinkedList<Partida>();
	}

	public 	void InsBack(Partida partida)
	{
		partidas.add(partida); 
	}
	
	public Partida getPartidas(int index) 
	{
		return partidas.get(index);
	}

	public boolean esVacia()
	{
		return partidas.isEmpty();
	}

	
	//Precondicion:No puede estar vacio, retorna la ultima partida de la secuencia
	public Partida RetornarUltimaPartida () //éste metodo no está en el diagrama de implementación (agregar o quitar)
	{
		int cantPartidas = partidas.size();
		if(cantPartidas>0)
		{
			Partida part = partidas.get(cantPartidas-1);//La ultima partida debe resta uno por culpa de la posicion cero
			return part;
		}
		else
			return null; // ver luego, manejo de excepciones, ver todos los null retornados
	}
	
	//Retorna y ELIMINA la ultima partida de la secuencia
	/*public Partida	RetornarYEliminarUltimaPartidaDeLaLista ()
	{
		int cantPartidas = partidas.size();
		if (cantPartidas > 0)
		{
			Partida part = partidas.remove(cantPartidas-1);
			return part;
		}
		else
		{
			return null; // Exception
		}
	} */

	//Retorna el numero de la ultima partida
	public int RetornarNumeroPartidaMasAlta()
	{
		int numPartidas = partidas.size();
		return numPartidas-1;
	}

	public Iterator<Partida> devolverIteratorPartida() {
		return partidas.iterator();
	}

	
	
	
	
}
