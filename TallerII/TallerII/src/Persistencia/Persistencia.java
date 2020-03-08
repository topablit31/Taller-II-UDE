package Persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Logica.DiccionarioJugadores;

public class Persistencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void respaldar(String nomArch, DiccionarioJugadores jugadores) throws PersistenciaException
	{
		try { // Abro el archivo y creo un flujo de comunicación hacia él
			FileOutputStream f = new FileOutputStream(nomArch);
			ObjectOutputStream o = new ObjectOutputStream(f);
			// Escribo el treeMap jugadores en el archivo a través del flujo
			o.writeObject(jugadores);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException(1);
			
		}
	}

	public static DiccionarioJugadores recuperar(String nomArch) throws PersistenciaException, ClassNotFoundException {
		try { 
			// Abro el archivo y creo un flujo de comunicación hacia él
			FileInputStream f = new FileInputStream(nomArch);
			ObjectInputStream o = new ObjectInputStream(f);
			// Leo el treeMap de jugadores desde el archivo a través del flujo
			DiccionarioJugadores jugadores = (DiccionarioJugadores) o.readObject();
			o.close();
			f.close();
			return jugadores;
		} catch (IOException e) {
			//e.printStackTrace();
			throw new PersistenciaException(2);
		}
	}
}
