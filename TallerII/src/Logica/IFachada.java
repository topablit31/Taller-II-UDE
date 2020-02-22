package Logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import ValueObjects.VOJugadorDespliegue;
import ValueObjects.VOJugadorLogin;
import ValueObjects.VOJugadorRankingGlobal;
import ValueObjects.VOPartida;
import ValueObjects.VOPartidaEsMayorMenor;

public interface IFachada extends Remote{
	public void registrarNuevoJugador(VOJugadorLogin voJugador) throws JugadorException, RemoteException;
	// Retorna un ArrayList con los Jugadores que estan en el Diccionario, ordenados
	// alfabeticamente
	public ArrayList<VOJugadorDespliegue> listarJugadores() throws JugadorException,RemoteException;

	// Retorna un ArrayList con las partidas de un jugador, ordenadas de menor a
	// mayor numero de Partida
	public ArrayList<VOPartida> listarPartidasDeUnJugador(String nom) throws JugadorException, PartidaException,RemoteException;

	// Guarda los cambios hechos en el sistema
	public void guardarCambios()throws RemoteException;

	// Verifica que el nombre y el codigo de Ingreso sean validos
	public boolean logearseParaJugar(VOJugadorLogin jug) throws JugadorException,RemoteException;

	// Inicia una nueva partida para el Jugador.
	public VOPartida iniciarNuevaPartida(VOJugadorLogin jug) throws JugadorException,RemoteException;
	// Retorna la partida en curso que el Jugador tiene
	public VOPartida visualizarPartidaEnCurso(VOJugadorLogin jug) throws JugadorException,RemoteException;

	// Retorna la Partida, verificando si el jugador acerto o no el numero Secreto
	public VOPartidaEsMayorMenor realizarUnIntento(VOJugadorLogin jug, int posibleNumSecreto) throws JugadorException,RemoteException;

	// Retorna el abandono de la partida en curso del Jugador
	public VOPartidaEsMayorMenor abandonarPartida(VOJugadorLogin jug) throws JugadorException,RemoteException;

	// Retorna un ArrayList del Ranking Global de Jugadores, ordenado por su
	// cociente
	public ArrayList<VOJugadorRankingGlobal> rankingGlobal() throws JugadorException,RemoteException;
}
