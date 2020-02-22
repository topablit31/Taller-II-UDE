package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import Logica.IFachada;
import Persistencia.Persistencia;
import Persistencia.PersistenciaException;

public class Servidor {

	public static void main(String[] args) {
		try
		{ // pongo a correr el rmiregistry
		LocateRegistry.createRegistry(1099);
		IFachada fachada= Persistencia.recuperar("respaldar.dat");
		
		System.out.println ("Antes de publicarlo");
		Naming.rebind("//localhost:1099/fachada", fachada);
		System.out.println ("Luego de publicarlo");
		}
		catch (RemoteException e)
		{ e.printStackTrace(); }
		catch (MalformedURLException e)
		{ e.printStackTrace(); } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
