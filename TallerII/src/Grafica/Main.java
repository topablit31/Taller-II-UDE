package Grafica;

import java.util.ArrayList;

import Logica.Fachada;
import Logica.Jugador;
import ValueObjects.VOJugadorDespliegue;
import ValueObjects.VOJugadorLogin;

public class Main {

	public static void main(String[] args) {

		Fachada f = new Fachada();
		Jugador j1 = new Jugador("Pablo", "aaaaaaa");
		Jugador j2 = new Jugador("Nacho", "bbbbbb");
		VOJugadorLogin Voj1 = new VOJugadorLogin(j1.getNombre(), j1.getCodIngreso());
		VOJugadorLogin Voj2 = new VOJugadorLogin(j2.getNombre(), j2.getCodIngreso());

		f.registrarNuevoJugador(Voj1);
		f.registrarNuevoJugador(Voj2);

		ArrayList<VOJugadorDespliegue> VOJugD = f.listarJugadores();
		System.out.println(VOJugD);

	}

}
