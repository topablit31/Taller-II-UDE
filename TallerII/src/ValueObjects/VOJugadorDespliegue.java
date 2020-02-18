package ValueObjects;

public class VOJugadorDespliegue 
{
	private String nombre;
	private String codIngreso;
	private int puntajeTotal;
	private int cantPartidasFinalizadas;
	private int cociente;
	
	public VOJugadorDespliegue (String nom, String cod, int punt, int cantPart, int coc)
	{
		nombre = nom;
		codIngreso = cod;
		puntajeTotal = punt;
		cantPartidasFinalizadas = cantPart;
		cociente = coc;
	}
	
	public String getVONombre() 
	{
		return nombre;
	}
	
	public String getVOCodIngreso() 
	{
		return codIngreso;
	}
	
	public int getVOPuntajeTotal() 
	{
		return puntajeTotal;
	}
	
	public int getVOCantPartidasFinalizadas() 
	{
		return cantPartidasFinalizadas;
	}
	
	public int getVOCociente() 
	{
		return cociente;
	}
	
	

}
