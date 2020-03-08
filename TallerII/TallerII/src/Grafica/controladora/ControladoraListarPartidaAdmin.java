package Grafica.controladora;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Grafica.admin.FrmListarPartidaAdmin;
import Logica.IFachada;
import Logica.JugadorException;
import Logica.PartidaException;
import ValueObjects.VOPartida;

public class ControladoraListarPartidaAdmin {

	private IFachada fachada;
	private FrmListarPartidaAdmin vista;

	public ControladoraListarPartidaAdmin(FrmListarPartidaAdmin vista) {

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
	
	public void buscar(String nombre)
	{
		ArrayList<VOPartida> partidas;
		try {
			partidas = fachada.listarPartidasDeUnJugador(nombre);
			vista.listarPartida(partidas.toArray());
		} catch (RemoteException e) {
			vista.errorMensaje(e.getMessage());
			e.printStackTrace();
		} catch (JugadorException e) {
			vista.errorMensaje(e.getMessage());
			e.printStackTrace();
		} catch (PartidaException e) {
			vista.errorMensaje(e.getMensaje());
			e.printStackTrace();
		} catch (InterruptedException e) {
			vista.errorMensaje(e.getMessage());
			
			e.printStackTrace();
		}
		
		
	}

}
