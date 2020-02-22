package Grafica;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Logica.Fachada;
import Logica.Jugador;
import Logica.JugadorException;
import Persistencia.Persistencia;
import Persistencia.PersistenciaException;
import ValueObjects.VOJugadorDespliegue;
import ValueObjects.VOJugadorLogin;

public class Main {

	public static void main(String[] args) throws JugadorException {

		Fachada f;
		try {
			f = new Fachada();
			Jugador j1 = new Jugador("Pablo", "aaaaaaa");
			Jugador j2 = new Jugador("Nacho", "bbbbbb");
			VOJugadorLogin Voj1 = new VOJugadorLogin(j1.getNombre(), j1.getCodIngreso());
			VOJugadorLogin Voj2 = new VOJugadorLogin(j2.getNombre(), j2.getCodIngreso());

			f.registrarNuevoJugador(Voj1);
			f.registrarNuevoJugador(Voj2);
			Persistencia.respaldar("respaldar.dat",f);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		} catch (PersistenciaException e) {
			System.out.println(e.getMensaje());
			e.printStackTrace();
		}
		

	}

}
