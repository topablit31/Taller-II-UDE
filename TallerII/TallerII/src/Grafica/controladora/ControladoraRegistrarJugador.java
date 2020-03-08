package Grafica.controladora;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import Grafica.admin.FrmMenuAdmin;
import Grafica.admin.FrmRegistrarJugador;
import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorLogin;

public class ControladoraRegistrarJugador {
	
	private FrmRegistrarJugador vista;
	private IFachada fachada;
	
	public ControladoraRegistrarJugador(FrmRegistrarJugador vista) {
		
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

	public void registrarNuevoJugador(VOJugadorLogin registrar) {
		try {
			fachada.registrarNuevoJugador(registrar);
			vista.ok();
		} catch (RemoteException e) {
			vista.errorMensaje(e.getMessage());
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
