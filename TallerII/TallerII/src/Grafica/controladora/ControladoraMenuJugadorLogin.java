package Grafica.controladora;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import Grafica.Jugador.FrmMenuJugadorLogin;
import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorLogin;

public class ControladoraMenuJugadorLogin {
	
	private FrmMenuJugadorLogin vista;
	private IFachada fachada;
	
	
	public ControladoraMenuJugadorLogin(FrmMenuJugadorLogin vista) {
		
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
	


	public boolean logueoOK(VOJugadorLogin login)  {
		try {
			return fachada.logearseParaJugar(login);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
