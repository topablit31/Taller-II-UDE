package Grafica.controladora;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import Grafica.admin.FrmMenuAdmin;
import Logica.IFachada;
import Persistencia.PersistenciaException;

public class ControladoraMenuAdmin {

	private FrmMenuAdmin vista;
	private IFachada fachada;
	public ControladoraMenuAdmin(FrmMenuAdmin vista) {
		
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
	
	public void listarJugadores()
	{
		vista.listarJugadores();
	}
	
	public void registrarJugador()
	{
		vista.registrarJugador();
	}
	
	public void guardar()
	{
		try {
			fachada.guardarCambios();
			vista.guardar();
		} catch (RemoteException e) {
			vista.errorMensaje(e.getMessage());
		} catch (PersistenciaException e) {
			vista.errorMensaje(e.getMensaje());
		}
		
		
	}

	public void listarPartida() {
		vista.listarPartida();
		
	}
	
}
