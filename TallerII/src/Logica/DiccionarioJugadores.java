package Logica;

import Logica.Jugador;
import java.util.*;


public class DiccionarioJugadores 
{

	private TreeMap<String, Jugador>jugadores;

	//Constructor
	public DiccionarioJugadores() 
	{
		jugadores=new TreeMap<String, Jugador>();
	}
	
	public void insert (String cod,Jugador jug) 
	{
		jugadores.put(cod, jug) ;
	}
	
	public boolean member(String nombre)
	{
		return jugadores.containsKey(nombre);
	}
	
	public Jugador find(String nombre)
	{
		return jugadores.get(nombre);
	}
	
	public boolean empty()
	{
		return jugadores.isEmpty();
	}
	public Iterator <Jugador> devolverIteradorJugador ()
	{
		LinkedList <Jugador> iter = (LinkedList<Jugador>) jugadores.values();
		return iter.iterator();//Se uso iterator de JAVA
	}
	
	//Retorna un iterador con los Jugadores
/*	public LinkedList <Jugador> Iterador ()
	{
		LinkedList <Jugador> a = (LinkedList<Jugador>) jugadores.values();
		return a;
	}
	*/
	//Actualiza el jugador en el Diccionario
	/*public void ActualizarJugador (String cod,Jugador jug)
	{
		jugadores.replace(cod, jug);
	}*/
	
	
}