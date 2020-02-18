package Logica;

import java.util.*;

import Logica.DiccionarioJugadores;
import ValueObjects.VOJugadorDespliegue;
import ValueObjects.VOJugadorLogin;
import ValueObjects.VOPartida;
import ValueObjects.VOPartidaEsMayorMenor;

/*import ValueObjects.VOJugadorLogin;*/

public class Fachada 
{

	private DiccionarioJugadores jugadores;

	//Constructor
	public Fachada() 
	{
		DiccionarioJugadores jugadores = new DiccionarioJugadores();
	}
	
	//Registra un nuevo jugador en el Diccionario
	public void registrarNuevoJugador(VOJugadorLogin voJugador) 
	{
		String nombre = voJugador.getNombre();
		String codigoIngreso = voJugador.getCodIngreso();
		if (!jugadores.member(nombre)) 
		{
			Jugador jugador = new Jugador(nombre, codigoIngreso);
			jugadores.insert(nombre, jugador);
		} 
		else 
		{
			/* Exception a hacer */
		}
	}

	//Retorna un ArrayList con los Jugadores que estan en el Diccionario, ordenados alfabeticamente
	public ArrayList<VOJugadorDespliegue> listarJugadores() 
	{
		boolean vacio = jugadores.empty();
		ArrayList<VOJugadorDespliegue> jugadoresAMostrar = new ArrayList<VOJugadorDespliegue>();
		if (vacio) 
		{
			/* Excepcion */
		} 
		else 
		{
			LinkedList <Jugador> iter = new LinkedList <Jugador>();
			iter = jugadores.Iterador();
			int tam = iter.size();
			int i ;
			for (i = 0 ; i<= tam ; i++)
			{
				Jugador jug = iter.poll();
				String nom = jug.getNombre();
				String cod = jug.getCodIngreso();
				int punt = jug.getPuntajeTotal();
				int cantPart = jug.getCantPartidasFinalizadas();
				int coc = jug.getCociente();
				VOJugadorDespliegue jugAMostrar = new VOJugadorDespliegue (nom, cod, punt, cantPart, coc);
				jugadoresAMostrar.add(jugAMostrar);
				
			}
			return jugadoresAMostrar;
		}

	}

	//Retorna un ArrayList con las partidas de un jugador, ordenadas de menor a mayor numero de Partida
	public ArrayList <VOPartida> listarPartidasDeUnJugador (String nom)
	{
		//ArrayList<VOJugadorDespliegue> jugadoresAMostrar = new ArrayList<VOJugadorDespliegue>();
		ArrayList <VOPartida> partidasAMostrar = new ArrayList <VOPartida> ();
		boolean existe = jugadores.member(nom);
		if (!existe)
		{
			/*Excepcion*/
		}
		else
		{
			Jugador jug = jugadores.find(nom);
			SecuenciaDePartidas sec = new SecuenciaDePartidas ();
			sec = jug.getPartidas();
			boolean vacia = sec.esVacia();
			if (vacia)
			{
				/*Exepcion*/
			}
			else
			{
				LinkedList <VOPartida> voParts  = new LinkedList<VOPartida> ();  /*Creo que asi creado esta mal*/
				int tam = sec.RetornarNumeroPartidaMasAlta();
				int i;
				for (i =1 ; i == tam ; i++)	
				{
					Partida part = sec.getPartidas(i);
					int numS = part.getNumSecreto();
					boolean fin = part.isFinalizada();
					int cantInt = part.getCantIntentos();
					int punt = part.getPuntajeFinal();
					VOPartida partAMostrar = new VOPartida (i, numS, fin, cantInt, punt);	
					partidasAMostrar.add(partAMostrar);
				}
				return partidasAMostrar;
			}
		}
	}

	//Guarda los cambios hechos en el sistema
	public void guardarCambios() 
	{

	}

	// Verifica que el nombre y el codigo de Ingreso sean validos
	public boolean logearseParaJugar(VOJugadorLogin jug) 
	{
		String nom = jug.getNombre();
		String cod = jug.getCodIngreso();
		boolean existe = jugadores.member(nom);
		if (!existe) 
		{
			return false;
			/* Exception */
		} 
		else 
		{
			Jugador player = jugadores.find(nom);
			String codPlayer = player.getCodIngreso();
			if (!codPlayer.equals(cod)) 
			{
				/* Exception */
				return false;
			} 
			else 
			{
				return true;
			}
		}
	}

	//Inicia una nueva partida para el Jugador.
	public VOPartida iniciarNuevaPartida (VOJugadorLogin jug)
	{
		String nom = jug.getNombre() ;
		String cod = jug.getCodIngreso();
		boolean existe = jugadores.member(nom) ;
		if (!existe)
		{
			/*Exception*/
		}
		else
		{
			Jugador player = jugadores.find(nom) ;
			String codPlayer = player.getCodIngreso();
			if (!codPlayer.equals(cod))
			{
				/*Exception*/
			}
			else
			{
				if (player.isPartidaEnCurso())
				{
					/*Exception*/
				}
				else
				{	
					SecuenciaDePartidas sec = new SecuenciaDePartidas();
					int numPar = sec.RetornarNumeroPartidaMasAlta()+1;
					Partida part = new Partida (numPar);
					sec.InsBack(part);
					player.setPartidas(sec);
					jugadores.ActualizarJugador(codPlayer, player);
					int numS = part.getNumSecreto();
					boolean fin = part.isFinalizada();
					int cantIn = part.getCantIntentos();
					int punt = part.getPuntajeFinal();
					VOPartida partAMostrar = new VOPartida (numPar, numS, fin, cantIn, punt);
					return partAMostrar;
				}
			}
		}
	}

	//Retorna la partida en curso que el Jugador tiene
	public VOPartida visualizarPartidaEnCurso(VOJugadorLogin jug)
	{
		String nom = jug.getNombre() ;
		String cod = jug.getCodIngreso();
		boolean existe = jugadores.member(nom) ;
		if (!existe)
		{
			/*Exception*/
		}
		else
		{
			Jugador player = jugadores.find(nom) ;
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso)
			{
				//Exeption
			}
			else
			{
				Partida part = player.getPartidas().RetornarUltimaPartida();
				int numP = part.getNumPartida();
				int numS = part.getNumSecreto();
				boolean fin = part.isFinalizada();
				int cantIn = part.getCantIntentos();
				int punt = part.getPuntajeFinal();
				
				VOPartida partidaAMostrar = new VOPartida (numP, numS, fin, cantIn, punt);
				return partidaAMostrar;
			}
			
		}	
	}

	//Retorna la Partida, verificando si el jugador acerto o no el numero Secreto
	public VOPartidaEsMayorMenor realizarUnIntento(VOJugadorLogin jug, int posibleNumSecreto)
	{
		String nom = jug.getNombre() ;
		String cod = jug.getCodIngreso();
		boolean existe = jugadores.member(nom) ;
		if (!existe)
		{
			/*Exception*/
		}
		else
		{
			Jugador player = jugadores.find(nom) ;
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso)
			{
				//Expception
			}
			else
			{
				SecuenciaDePartidas sec = new SecuenciaDePartidas();
				sec = player.getPartidas();
				Partida part = sec.RetornarYEliminarUltimaPartidaDeLaLista();
				int numS = part.getNumSecreto();
				if (posibleNumSecreto < numS)
				{
					part.sumarUnIntento();
					sec.InsBack(part);
					player.setPartidas(sec);
					jugadores.ActualizarJugador(nom, player);
					String message = "El numero secreto es menor al numero ingresado";
					int numP = part.getNumPartida();
					int cantInt = part.getCantIntentos();
					VOPartidaEsMayorMenor partAMostrar = new VOPartidaEsMayorMenor (numP, false, cantInt, 0, message);
					return partAMostrar;
				}
				else
				{
					if (posibleNumSecreto > numS)
					{
						part.sumarUnIntento();
						sec.InsBack(part);
						player.setPartidas(sec);
						jugadores.ActualizarJugador(nom, player);
						String message = "El numero secreto es mayor al numero ingresado";
						int numP = part.getNumPartida();
						int cantInt = part.getCantIntentos();
						VOPartidaEsMayorMenor partAMostrar = new VOPartidaEsMayorMenor (numP, false, cantInt, 0, message);
						return partAMostrar;
					}
					else
					{	
						sec = player.getPartidas();
						part.sumarUnIntento();
						int cantIn = part.getCantIntentos();
						int puntFinal = (int) Math.ceil(1000/cantIn);
						int numP = part.getNumPartida();
						part.setPuntajeFinal(puntFinal);
						part.setFinalizada(true);
						sec.InsBack(part);
						player.setPartidas(sec);
						String message = "El numero es COOOOORRECTOO";
						VOPartidaEsMayorMenor partAMostrar = new VOPartidaEsMayorMenor (numP, true, cantIn, puntFinal, message);
						
						player.SumarUnaPartidaFinalizada();
						int puntFinalJugador = player.getPuntajeTotal() + puntFinal;
						int cantPartidas = player.getCantPartidasFinalizadas();
						int nuevoCociente = (int) Math.ceil(puntFinalJugador / cantPartidas);
						player.setPartidaEnCurso(false);
						player.setPuntajeTotal(puntFinalJugador);
						player.setCociente(nuevoCociente);
						jugadores.ActualizarJugador(nom, player);
						
						return partAMostrar;
					}
				}
			}
		}
	}

	//Retorna el abandono de la partida en curso del Jugador
	public VOPartidaEsMayorMenor abandonarPartida(VOJugadorLogin jug) 
	{
		String nom = jug.getNombre() ;
		String cod = jug.getCodIngreso();
		boolean existe = jugadores.member(nom) ;
		if (!existe)
		{
			/*Exception*/
		}
		else
		{
			Jugador player = jugadores.find(nom) ;
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso)
			{
				//Expception
			}
			else
			{
				SecuenciaDePartidas sec = new SecuenciaDePartidas();
				sec = player.getPartidas();
				Partida part = sec.RetornarYEliminarUltimaPartidaDeLaLista();
				
				part.sumarUnIntento();
				int cantIn = part.getCantIntentos();
				int puntFinal = 0;
				int numP = part.getNumPartida();
				part.setPuntajeFinal(puntFinal);
				part.setFinalizada(true);
				sec.InsBack(part);
				player.setPartidas(sec);
				String message = "Ha abandonado la partida";
				VOPartidaEsMayorMenor partAMostrar = new VOPartidaEsMayorMenor (numP, true, cantIn, puntFinal, message);
				
				player.SumarUnaPartidaFinalizada();
				int puntFinalJugador = player.getPuntajeTotal();
				int cantPartidas = player.getCantPartidasFinalizadas();
				int nuevoCociente = (int) Math.ceil(puntFinalJugador / cantPartidas);
				player.setPartidaEnCurso(false);
				player.setPuntajeTotal(puntFinalJugador);
				player.setCociente(nuevoCociente);
				jugadores.ActualizarJugador(nom, player);
				
				return partAMostrar;	
				}
		}
			
		
	}

	//Retorna un ArrayList del Ranking Global de Jugadores, ordenado por su cociente
	public ArrayList<VOJugadorRankingGlobal> rankingGlobal() 
	{
		boolean vacio = jugadores.empty();
		ArrayList<VOJugadorDespliegue> jugadoresAMostrar = new ArrayList<VOJugadorDespliegue>();
		if (vacio) 
		{
			/* Excepcion */
		} 
		else 
		{
			LinkedList <Jugador> iter = new LinkedList <Jugador>();
			iter = jugadores.Iterador();
			//Aca hay que ordenar el Iterador por cociente... Te lo deje divino fosil
		}
	}

	// Monitor, clasificar requerimientos lectura ó escritura, si modifica datos
	// escritura, sino lectura, primer paso iniciar escritura o lectura, último paso
	// liberar escritura ó lectura
}
