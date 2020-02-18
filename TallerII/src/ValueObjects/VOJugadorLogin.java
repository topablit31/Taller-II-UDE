package ValueObjects;


public class VOJugadorLogin 
{
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
