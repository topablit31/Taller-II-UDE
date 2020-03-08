package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorDespliegue;

public class Cliente {

	public static void main(String[] args) {

			IFachada fachada;
			try {
				fachada = (IFachada)Naming.lookup("//localhost:1099/fachada");
				ArrayList<VOJugadorDespliegue> VOJugD = fachada.listarJugadores();
				System.out.println(VOJugD);
				for(int i=0;i<VOJugD.size();i++)
				{
					VOJugadorDespliegue jugadorDespliegue=VOJugD.get(i);
					System.out.println(jugadorDespliegue.getNombre());
				}
			} catch (MalformedURLException | RemoteException | NotBoundException | JugadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		

	}
	

}
