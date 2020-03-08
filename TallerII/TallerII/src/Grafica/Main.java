package Grafica;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Logica.IFachada;
import Logica.Jugador;
import Logica.JugadorException;
import Logica.Partida;
import Logica.SecuenciaDePartidas;
import Persistencia.PersistenciaException;
import ValueObjects.VOJugadorDespliegue;
import ValueObjects.VOJugadorLogin;

public class Main {

	public static void main(String[] args) throws JugadorException, ClassNotFoundException {

		IFachada f;
		try {
			f = (IFachada)Naming.lookup("//localhost:1099/fachada");
			Jugador j1 = new Jugador("carlos", "aaaaaaa");
			Jugador j2 = new Jugador("Nacho", "bbbbbb");
			Jugador j3 = new Jugador("Gabi", "cccccc");
			
			VOJugadorLogin Voj2 = new VOJugadorLogin(j2.getNombre(), j2.getCodIngreso());
			VOJugadorLogin Voj3 = new VOJugadorLogin(j3.getNombre(), j3.getCodIngreso());
			SecuenciaDePartidas partidas = new SecuenciaDePartidas();
			
			VOJugadorLogin Voj1 = new VOJugadorLogin(j1.getNombre(), j1.getCodIngreso());
			f.iniciarNuevaPartida(Voj1);
			f.abandonarPartida(Voj1);
			/*f.registrarNuevoJugador(Voj2);
			f.registrarNuevoJugador(Voj3); */
			
			
			
			f.guardarCambios();
			
			
			ArrayList<VOJugadorDespliegue> VOJugD = f.listarJugadores();
			for(int i=0;i<VOJugD.size();i++)
			{
				VOJugadorDespliegue jugadorDespliegue=VOJugD.get(i);
				System.out.println(jugadorDespliegue.getNombre());
			}
			
			f.guardarCambios();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		} catch (PersistenciaException e) {
			System.out.println(e.getMensaje());
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
