package Grafica.controladora;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Grafica.admin.FrmListarJugadoresAdmin;
import Logica.IFachada;
import Logica.JugadorException;
import Logica.PartidaException;
import ValueObjects.VOJugadorDespliegue;
import ValueObjects.VOPartida;

public class ControladoraListarJugadoresAdmin {
	
	private IFachada fachada;
	private FrmListarJugadoresAdmin vista;
	

	public ControladoraListarJugadoresAdmin (FrmListarJugadoresAdmin vista) {

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
	
	public void listarJugadores ()
	{
		try {
			ArrayList<VOJugadorDespliegue> jugadores = fachada.listarJugadores();
			vista.listarJugadores(jugadores.toArray());
			
			
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

}
