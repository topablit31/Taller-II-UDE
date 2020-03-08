package ValueObjects;

import java.io.Serializable;

public class VOJugadorLogin implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String codIngreso;
	
	public VOJugadorLogin (String nom, String cod)
	{
		nombre = nom;
		codIngreso = cod;
	}
	
	public String getNombre ()
	{
		return nombre;
	}
	
	public String getCodIngreso ()
	{ 
		return codIngreso;
	}
}
