package Logica;

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

/*import ValueObjects.VOJugadorLogin;*/

public class Fachada {

	private DiccionarioJugadores jugadores;

	// Constructor
	public Fachada() {
		jugadores = new DiccionarioJugadores();
	}

	// Registra un nuevo jugador en el Diccionario
	public void registrarNuevoJugador(VOJugadorLogin voJugador) throws JugadorException {
		String nombre = voJugador.getNombre();
		String codigoIngreso = voJugador.getCodIngreso();
		if (!jugadores.member(nombre)) {
			Jugador jugador = new Jugador(nombre, codigoIngreso);
			jugadores.insert(nombre, jugador);
		} else {
			throw new JugadorException(4);
		}
	}

	// Retorna un ArrayList con los Jugadores que estan en el Diccionario, ordenados
	// alfabeticamente
	public ArrayList<VOJugadorDespliegue> listarJugadores() throws JugadorException {
		boolean vacio = jugadores.empty();
		ArrayList<VOJugadorDespliegue> jugadoresAMostrar = new ArrayList<VOJugadorDespliegue>();
		if (vacio) {
			throw new JugadorException(2);
		} else {
			Iterator<Jugador> iter = jugadores.devolverIteradorJugador();

			while (iter.hasNext()) {
				Jugador jugador = iter.next();
				String nom = jugador.getNombre();

				int punt = jugador.getPuntajeTotal();
				int cantPart = jugador.getCantPartidasFinalizadas();
				int coc = jugador.getCociente();

				VOJugadorDespliegue jugAMostrar = new VOJugadorDespliegue(nom, punt, cantPart, coc);
				jugadoresAMostrar.add(jugAMostrar);
			}

			return jugadoresAMostrar;
		}

	}

	// Retorna un ArrayList con las partidas de un jugador, ordenadas de menor a
	// mayor numero de Partida
	public ArrayList<VOPartida> listarPartidasDeUnJugador(String nom) throws JugadorException, PartidaException {
		// ArrayList<VOJugadorDespliegue> jugadoresAMostrar = new
		// ArrayList<VOJugadorDespliegue>();
		ArrayList<VOPartida> partidasAMostrar = new ArrayList<VOPartida>();
		boolean existe = jugadores.member(nom);
		if (!existe) {
			throw new JugadorException(3);
		} else {
			Jugador jug = jugadores.find(nom);
			SecuenciaDePartidas sec = jug.getPartidas();
			;

			boolean vacia = sec.esVacia();
			if (vacia) {
				throw new PartidaException();
			} else {
				Iterator<Partida> iterator = sec.devolverIteratorPartida();
				int i = 1;
				while (iterator.hasNext()) {
					Partida part = iterator.next();
					int numS = part.getNumSecreto();
					boolean fin = part.isFinalizada();
					int cantInt = part.getCantIntentos();
					int punt = part.getPuntajeFinal();
					VOPartida partAMostrar = new VOPartida(i, numS, fin, cantInt, punt);
					partidasAMostrar.add(partAMostrar);
					i++;
				}

				return partidasAMostrar;
			}
		}
	}

	// Guarda los cambios hechos en el sistema
	public void guardarCambios() {

	}

	// Verifica que el nombre y el codigo de Ingreso sean validos
	public boolean logearseParaJugar(VOJugadorLogin jug) throws JugadorException {
		String nom = jug.getNombre();
		String cod = jug.getCodIngreso();
		boolean existe = jugadores.member(nom);
		if (!existe) {
			throw new JugadorException(3);

		} else {
			Jugador player = jugadores.find(nom);

			if (!player.validarCodigoIngreso(cod)) {
				throw new JugadorException(1);
			} else {
				return true;
			}
		}
	}

	// Inicia una nueva partida para el Jugador.
	public VOPartida iniciarNuevaPartida(VOJugadorLogin jug) throws JugadorException {
		if (logearseParaJugar(jug)) {
			Jugador player = jugadores.find(jug.getNombre());
			if (player.isPartidaEnCurso()) {
				throw new JugadorException(5);
			} else {
				SecuenciaDePartidas sec = player.getPartidas();
				int numPar = sec.RetornarNumeroPartidaMasAlta() + 1;
				Partida part = new Partida(numPar);
				sec.InsBack(part);

				// player.setPartidas(sec);Pierdo toda las partidas
				// jugadores.ActualizarJugador(codPlayer, player); No es necesario porque se
				// recupera el mismo puntero
				int numS = part.getNumSecreto();
				boolean fin = part.isFinalizada();
				int cantIn = part.getCantIntentos();
				int punt = part.getPuntajeFinal();
				VOPartida partAMostrar = new VOPartida(numPar, numS, fin, cantIn, punt);
				return partAMostrar;// Porque se retorna partida??
			}
		} else {
			throw new JugadorException(3);
		}

	}

	// Retorna la partida en curso que el Jugador tiene
	public VOPartida visualizarPartidaEnCurso(VOJugadorLogin jug) throws JugadorException {
		if (logearseParaJugar(jug)) {
			Jugador player = jugadores.find(jug.getNombre());
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso) {
				throw new JugadorException(6);
			} else {
				Partida part = player.getPartidas().RetornarUltimaPartida();
				int numP = part.getNumPartida();
				int numS = part.getNumSecreto();
				boolean fin = part.isFinalizada();
				int cantIn = part.getCantIntentos();
				int punt = part.getPuntajeFinal();

				VOPartida partidaAMostrar = new VOPartida(numP, numS, fin, cantIn, punt);
				return partidaAMostrar;
			}

		} else {
			throw new JugadorException(3);
		}
	}

	// Retorna la Partida, verificando si el jugador acerto o no el numero Secreto
	public VOPartidaEsMayorMenor realizarUnIntento(VOJugadorLogin jug, int posibleNumSecreto) throws JugadorException {
		if (logearseParaJugar(jug)) {
			Jugador player = jugadores.find(jug.getNombre());
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso) {
				throw new JugadorException(6);
			} else {
				SecuenciaDePartidas sec = player.getPartidas();
				Partida part = sec.RetornarUltimaPartida();
				String message;
				VOPartidaEsMayorMenor partAMostrar;
				int numP = part.getNumPartida();
				part.sumarUnIntento();
				int cantInt = part.getCantIntentos();
				int numS = part.getNumSecreto();

				if (posibleNumSecreto < numS) {

					message = "El numero secreto es menor al numero ingresado";
					partAMostrar = new VOPartidaEsMayorMenor(numP, false, cantInt, 0, message);

				} else {
					if (posibleNumSecreto > numS) {

						message = "El numero secreto es mayor al numero ingresado";

						partAMostrar = new VOPartidaEsMayorMenor(numP, false, cantInt, 0, message);

					} else {

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
				return partAMostrar;
			}
		} else {
			throw new JugadorException(3);
		}

	}

	// Retorna el abandono de la partida en curso del Jugador
	public VOPartidaEsMayorMenor abandonarPartida(VOJugadorLogin jug) throws JugadorException {

		if (logearseParaJugar(jug)) {
			throw new JugadorException(3);
		} else {
			Jugador player = jugadores.find(jug.getNombre());
			boolean tieneEnCurso = player.isPartidaEnCurso();
			if (!tieneEnCurso) {
				throw new JugadorException(6);
			} else {
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

				return partAMostrar;
			}
		}

	}

	// Retorna un ArrayList del Ranking Global de Jugadores, ordenado por su
	// cociente
	public ArrayList<VOJugadorRankingGlobal> rankingGlobal() throws JugadorException {
		boolean vacio = jugadores.empty();
		Iterator<Jugador> iterator = jugadores.devolverIteradorJugador();
		ArrayList<VOJugadorRankingGlobal> lista = new ArrayList<VOJugadorRankingGlobal>();
		int i = 1;
		if (!vacio) {
			while (iterator.hasNext()) {
				Jugador jugador = iterator.next();
				String nombre = jugador.getNombre();
				int puntaje = jugador.getPuntajeTotal();
				int cantidad = jugador.getCantPartidasFinalizadas();
				int cociente = jugador.getCociente();
				VOJugadorRankingGlobal VOJugador = new VOJugadorRankingGlobal(i, nombre, puntaje, cantidad, cociente);
				lista.add(VOJugador);
				i++;
			}
		} else {
			throw new JugadorException(2);
		}
		return lista;
	}

	// Monitor, clasificar requerimientos lectura ó escritura, si modifica datos
	// escritura, sino lectura, primer paso iniciar escritura o lectura, último paso
	// liberar escritura ó lectura
}
