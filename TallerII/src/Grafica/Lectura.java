package Grafica;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Logica.Fachada;
import Logica.JugadorException;
import Persistencia.Persistencia;
import Persistencia.PersistenciaException;
import ValueObjects.VOJugadorDespliegue;
public class Lectura {

	public static void main(String[] args) {
		try {
			Fachada f = Persistencia.recuperar("respaldar.dat");
			try {
				ArrayList<VOJugadorDespliegue> VOJugD = f.listarJugadores();
				System.out.println(VOJugD);
				for(int i=0;i<VOJugD.size();i++)
				{
					VOJugadorDespliegue jugadorDespliegue=VOJugD.get(i);
					System.out.println(jugadorDespliegue.getNombre());
				}
			} catch (JugadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException | PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
