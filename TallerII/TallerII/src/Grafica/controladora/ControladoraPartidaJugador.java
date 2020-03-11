package Grafica.controladora;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;


import Grafica.Jugador.FrmPartidaJugador;
import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorLogin;
import ValueObjects.VOPartida;
import ValueObjects.VOPartidaEsMayorMenor;

public class ControladoraPartidaJugador {
	
	private FrmPartidaJugador vista;
	private IFachada fachada;

	public ControladoraPartidaJugador(FrmPartidaJugador vista) {

		this.vista = vista;

		try {

			fachada = (IFachada) Naming.lookup("//localhost:1099/fachada");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void abandonarPartida(VOJugadorLogin credencial) {
		try {
			fachada.abandonarPartida(credencial);
			vista.abandonar();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void iniciarNuevaPartida(VOJugadorLogin credencial) {
		try {
			fachada.iniciarNuevaPartida(credencial);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void realizarUnIntento(VOJugadorLogin credencial, int numero) {
		VOPartidaEsMayorMenor respuesta=null;
		try {
			respuesta= fachada.realizarUnIntento(credencial, numero);
			
			if(respuesta.isFinalizada())
				vista.gano();
			else
				vista.mostrarResultado(respuesta);
		} catch (RemoteException e) {
			vista.errorMensaje("Problema de conexion");
			e.printStackTrace();
		} catch (JugadorException e) {
			vista.errorMensaje(e.getMensaje());
			e.printStackTrace();
		} catch (InterruptedException e) {
			vista.errorMensaje(e.getMessage());
			e.printStackTrace();
		}
	
	}

	
		
	

	
	
}
