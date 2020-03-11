package Grafica.controladora;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import Grafica.Jugador.FrmMenuJugador;
import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorLogin;
import ValueObjects.VOPartida;

public class ControladoraMenuJugador {

	private FrmMenuJugador vista;
	private IFachada fachada;

	public ControladoraMenuJugador(FrmMenuJugador vista) {

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
	
	public void iniciarPartida()
	{
		vista.iniciarPartida();
	}
	


}
