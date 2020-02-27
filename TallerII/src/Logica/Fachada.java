package Logica;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import org.omg.CORBA.portable.ApplicationException;

import Logica.DiccionarioJugadores;
import ValueObjects.VOJugadorDespliegue;
import ValueObjects.VOJugadorLogin;
import ValueObjects.VOJugadorRankingGlobal;
import ValueObjects.VOPartida;
import ValueObjects.VOPartidaEsMayorMenor;
import Logica.JugadorException;
import Logica.PartidaException;

import Logica.Monitor;

/*import ValueObjects.VOJugadorLogin;*/

public class Fachada extends UnicastRemoteObject implements IFachada, Serializable
{

	private DiccionarioJugadores jugadores;
	private Monitor monitor;
	
	// Constructor
	public Fachada() throws RemoteException{
		jugadores = new DiccionarioJugadores();
		monitor=new Monitor();
	}

	// Registra un nuevo jugador en el Diccionario
	public synchronized void registrarNuevoJugador(VOJugadorLogin voJugador) throws JugadorException,RemoteException, InterruptedException
	{
		monitor.comienzoEscritura();
		String nombre = voJugador.getNombre();
		String codigoIngreso = voJugador.getCodIngreso();
		if (!jugadores.member(nombre))
		{
			Jugador jugador = new Jugador(nombre, codigoIngreso);
			jugadores.insert(nombre, jugador);
			monitor.terminoEscritura();
		} 
		else 
		{	
			monitor.terminoEscritura();
			throw new JugadorException(4);
		}
	}

	// Retorna un ArrayList con los Jugadores que estan en el Diccionario, ordenados
	// alfabeticamente
	public synchronized ArrayList<VOJugadorDespliegue> listarJugadores() throws JugadorException, RemoteException, InterruptedException 
	{	
		monitor.comienzoLectura();
		boolean vacio = jugadores.empty();
		ArrayList<VOJugadorDespliegue> jugadoresAMostrar = new ArrayList<VOJugadorDespliegue>();
		if (vacio) 
		{	
			monitor.terminoLectura();
			throw new JugadorException(2);
		} 
		else 
		{
			Iterator<Jugador> iter = jugadores.devolverIteradorJugador();
			while (iter.hasNext())
			{
				Jugador jugador = iter.next();
				String nom = jugador.getNombre();
				int punt = jugador.getPuntajeTotal();
				int cantPart = jugador.getCantPartidasFinalizadas();
				int coc = jugador.getCociente();
				
				VOJugadorDespliegue jugAMostrar = new VOJugadorDespliegue(nom, punt, cantPart, coc);
				jugadoresAMostrar.add(jugAMostrar);
			}
			monitor.terminoLectura();
			return jugadoresAMostrar;
		}
	}

	// Retorna un ArrayList con las partidas de un jugador, ordenadas de menor a
	// mayor numero de Partida
	public synchronized ArrayList<VOPartida> listarPartidasDeUnJugador(String nom) throws JugadorException, PartidaException,RemoteException, InterruptedException 
	{
		// ArrayList<VOJugadorDespliegue> jugadoresAMostrar = new
		// ArrayList<VOJugadorDespliegue>();
		monitor.comienzoLectura();
		ArrayList<VOPartida> partidasAMostrar = new ArrayList<VOPartida>();
		boolean existe = jugadores.member(nom);
		if (!existe) 
		{	
			monitor.terminoLectura();
			throw new JugadorException(3);
		} 
		else 
		{
			Jugador jug = jugadores.find(nom);
			SecuenciaDePartidas sec = jug.getPartidas();
			boolean vacia = sec.esVacia();
			if (vacia) 
			{	
				monitor.terminoLectura();
				throw new PartidaException();
			} 
			else 
			{
				Iterator<Partida> iterator = sec.devolverIteratorPartida();
				int i = 1;
				while (iterator.hasNext()) 
				{
					Partida part = iterator.next();
					int numS = part.getNumSecreto();
					boolean fin = part.isFinalizada();
					int cantInt = part.getCantIntentos();
					int punt = part.getPuntajeFinal();
					VOPartida partAMostrar = new VOPartida(i, numS, fin, cantInt, punt);
					partidasAMostrar.add(partAMostrar);
					i++;
				}
				monitor.terminoLectura();
				return partidasAMostrar;
			}
		}
	}

	// Guarda los cambios hechos en el sistema
	public void guardarCambios()throws RemoteException 
	{

	}

	// Verifica que el nombre y el codigo de Ingreso sean validos
	public synchronized boolean logearseParaJugar(VOJugadorLogin jug) throws JugadorException,RemoteException, InterruptedException
	{	
		monitor.comienzoLectura();
		String nom = jug.getNombre();
		String cod = jug.getCodIngreso();
		boolean existe = jugadores.member(nom);
		if (!existe) 
		{	
			monitor.terminoLectura();
			throw new JugadorException(3);
		} 
		else 
		{
			Jugador player = jugadores.find(nom);

			if (!player.validarCodigoIngreso(cod)) 
			{	
				monitor.terminoLectura();
				throw new JugadorException(1);
			} 
			else 
			{	
				monitor.terminoLectura();
				return true;
			}
		}
	}

	// Inicia una nueva partida para el Jugador.
	public synchronized VOPartida iniciarNuevaPartida(VOJugadorLogin jug) throws JugadorException,RemoteException, InterruptedException
	{	
			monitor.comienzoEscritura();
			Jugador player = jugadores.find(jug.getNombre());
			if (player.isPartidaEnCurso()) 
			{	
				monitor.terminoEscritura();
				throw new JugadorException(5);
			} 
			else 
			{
				SecuenciaDePartidas sec = player.getPartidas();
				int numPar = sec.RetornarNumeroPartidaMasAlta() + 1;
				Partida part = new Partida(numPar);
				sec.InsBack(part);
				int numS = part.getNumSecreto();
				boolean fin = part.isFinalizada();
				int cantIn = part.getCantIntentos();
				int punt = part.getPuntajeFinal();
				VOPartida partAMostrar = new VOPartida(numPar, numS, fin, cantIn, punt);
				monitor.terminoEscritura();
				return partAMostrar;// Porque se retorna partida??
			}
	}

	// Retorna la partida en curso que el Jugador tiene
	public synchronized VOPartida visualizarPartidaEnCurso(VOJugadorLogin jug) throws JugadorException,RemoteException, InterruptedException
	{	
			monitor.comienzoLectura();
			Jugador player = jugadores.find(jug.getNombre());
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso) 
			{	
				monitor.terminoLectura();
				throw new JugadorException(6);
			} 
			else 
			{
				Partida part = player.getPartidas().RetornarUltimaPartida();
				int numP = part.getNumPartida();
				int numS = part.getNumSecreto();
				boolean fin = part.isFinalizada();
				int cantIn = part.getCantIntentos();
				int punt = part.getPuntajeFinal();

				VOPartida partidaAMostrar = new VOPartida(numP, numS, fin, cantIn, punt);
				monitor.terminoLectura();
				return partidaAMostrar;
			} 
	}

	// Retorna la Partida, verificando si el jugador acerto o no el numero Secreto
	public synchronized VOPartidaEsMayorMenor realizarUnIntento(VOJugadorLogin jug, int posibleNumSecreto) throws JugadorException,RemoteException, InterruptedException 
	{	
			monitor.comienzoEscritura();
			Jugador player = jugadores.find(jug.getNombre());
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso) 
			{	
				monitor.terminoEscritura();
				throw new JugadorException(6);
			} 
			else 
			{
				SecuenciaDePartidas sec = player.getPartidas();
				Partida part = sec.RetornarUltimaPartida();
				String message;
				VOPartidaEsMayorMenor partAMostrar;
				int numP = part.getNumPartida();
				part.sumarUnIntento();
				int cantInt = part.getCantIntentos();
				int numS = part.getNumSecreto();

				if (posibleNumSecreto < numS) 
				{
					message = "El numero secreto es menor al numero ingresado";
					partAMostrar = new VOPartidaEsMayorMenor(numP, false, cantInt, 0, message);
					monitor.terminoLectura();
				} 
				else 
				{
					if (posibleNumSecreto > numS) 
					{
						message = "El numero secreto es mayor al numero ingresado";
						partAMostrar = new VOPartidaEsMayorMenor(numP, false, cantInt, 0, message);
					} 
					else 
					{
						int puntFinal = (int) Math.ceil(1000 / cantInt);
						part.setPuntajeFinal(puntFinal);
						message = "El numero es COOOOORRECTOO";
						partAMostrar = new VOPartidaEsMayorMenor(numP, true, cantInt, puntFinal, message);
						player.SumarUnaPartidaFinalizada();
						int puntActualJugador = player.getPuntajeTotal() + puntFinal;
						int cantPartidas = player.getCantPartidasFinalizadas();
						int nuevoCociente = (int) Math.ceil(puntActualJugador / cantPartidas);
						player.setPartidaEnCurso(false);
						player.setPuntajeTotal(puntActualJugador);
						player.setCociente(nuevoCociente);
					}
				}
				monitor.terminoEscritura();
				return partAMostrar;
			}
	}

	// Retorna el abandono de la partida en curso del Jugador
	public synchronized VOPartidaEsMayorMenor abandonarPartida(VOJugadorLogin jug) throws JugadorException,RemoteException, InterruptedException
	{		
			monitor.comienzoEscritura();
			Jugador player = jugadores.find(jug.getNombre());
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso) 
			{
				monitor.terminoEscritura();
				throw new JugadorException(6);
			} 
			else 
			{
				SecuenciaDePartidas sec = player.getPartidas();
				Partida part = sec.RetornarUltimaPartida();

				part.sumarUnIntento();
				int cantIn = part.getCantIntentos();
				int puntFinal = 0;
				int numP = part.getNumPartida();
				part.setPuntajeFinal(puntFinal);
				part.setFinalizada(true);
				String message = "Ha abandonado la partida";
				VOPartidaEsMayorMenor partAMostrar = new VOPartidaEsMayorMenor(numP, true, cantIn, puntFinal, message);

				player.SumarUnaPartidaFinalizada();
				int puntActualJugador = player.getPuntajeTotal() + puntFinal;
				int cantPartidas = player.getCantPartidasFinalizadas();
				int nuevoCociente = (int) Math.ceil(puntActualJugador / cantPartidas);
				player.setPartidaEnCurso(false);
				player.setPuntajeTotal(puntActualJugador);
				player.setCociente(nuevoCociente);

				monitor.terminoEscritura();
				return partAMostrar;
			}
	}

	// Retorna un ArrayList del Ranking Global de Jugadores, ordenado por su
	// cociente
	public synchronized ArrayList<VOJugadorRankingGlobal> rankingGlobal() throws JugadorException,RemoteException, InterruptedException
	{	
		monitor.comienzoLectura();
		boolean vacio = jugadores.empty();
		Iterator<Jugador> iterator = jugadores.devolverIteradorJugador();
		ArrayList<VOJugadorRankingGlobal> lista = new ArrayList<VOJugadorRankingGlobal>();
		int i = 1;
		if (!vacio) 
		{
			while (iterator.hasNext()) 
			{
				Jugador jugador = iterator.next();
				String nombre = jugador.getNombre();
				int puntaje = jugador.getPuntajeTotal();
				int cantidad = jugador.getCantPartidasFinalizadas();
				int cociente = jugador.getCociente();
				VOJugadorRankingGlobal VOJugador = new VOJugadorRankingGlobal(i, nombre, puntaje, cantidad, cociente);
				lista.add(VOJugador);
				i++;
			}
		} 
		else 
		{	
			monitor.terminoLectura();
			throw new JugadorException(2);
		}
		monitor.terminoLectura();
		return lista;
	}

}
