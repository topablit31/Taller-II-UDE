package Persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Logica.Fachada;

public class Persistencia {

	public static void respaldar(String nomArch, Fachada fachada) throws PersistenciaException
	{
		try { // Abro el archivo y creo un flujo de comunicaci�n hacia �l
			FileOutputStream f = new FileOutputStream(nomArch);
			ObjectOutputStream o = new ObjectOutputStream(f);
			// Escribo el arreglo de veh�culos en el archivo a trav�s del flujo
			o.writeObject(fachada);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException(1);
			
		}
	}

	public static Fachada recuperar(String nomArch) throws PersistenciaException, ClassNotFoundException {
		try { 
			// Abro el archivo y creo un flujo de comunicaci�n hacia �l
			FileInputStream f = new FileInputStream(nomArch);
			ObjectInputStream o = new ObjectInputStream(f);
			// Leo el arreglo de veh�culos desde el archivo a trav�s del flujo
			Fachada fachada = (Fachada) o.readObject();
			o.close();
			f.close();
			return fachada;
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException(2);
		}
	}
}
