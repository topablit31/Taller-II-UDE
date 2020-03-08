package Logica;

import java.io.Serializable;

public class Jugador implements Comparable<Jugador>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String codIngreso;
	private int puntajeTotal;
	private int cantPartidasFinalizadas;
	private boolean partidaEnCurso;
	private SecuenciaDePartidas partidas;
	private int cociente;
	

	//Constructor
	public Jugador(String nombre, String codIngreso) {
		this.nombre = nombre;
		this.codIngreso = codIngreso;
		puntajeTotal=0;
		cantPartidasFinalizadas=0;
		partidaEnCurso=false;
		partidas=new SecuenciaDePartidas();
		cociente=0;
	}
	/*
	public Jugador (String nom, String cod, int punt, int cantPart, boolean enCurso, SecuenciaDePartidas parts, int coci)
	{
		nombre = nom;
		codIngreso = cod;
		puntajeTotal = punt;
		cantPartidasFinalizadas = cantPart;
		partidaEnCurso = enCurso;
		partidas = parts;
		cociente = coci;
	}*/

	//Valida si el codigo ingresado es el correcto
	public boolean validarCodigoIngreso(String codigoIngreso)
	{
		return codigoIngreso.equals(this.codIngreso);
	}
	
	//Modificacion del codigo de ingreso del Jugador
	public boolean cambiarCodigoIngreso(String codigoIngresoViejo,String codigoIngresoNuevo)
	{
		if(validarCodigoIngreso(codigoIngresoViejo))
		{
			this.codIngreso=codigoIngresoNuevo;
			return true;
		}
		else
			return false;
	}
	
	public String getCodIngreso ()
	{
		return codIngreso;
	}

	public String getNombre() 
	{
		return nombre;
	}



	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getPuntajeTotal() 
	{
		return puntajeTotal;
	}



	public void setPuntajeTotal(int puntajeTotal)
	{
		this.puntajeTotal = puntajeTotal;
	}



	public int getCantPartidasFinalizadas()
	{
		return cantPartidasFinalizadas;
	}



	public void setCantPartidasFinalizadas(int cantPartidasFinalizadas)
	{
		this.cantPartidasFinalizadas = cantPartidasFinalizadas;
	}



	public boolean isPartidaEnCurso() 
	{
		return partidaEnCurso;
	}



	public void setPartidaEnCurso(boolean partidaEnCurso) 
	{
		this.partidaEnCurso = partidaEnCurso;
	}



	public SecuenciaDePartidas getPartidas()
	{
		return partidas;
	}



	public void setPartidas(SecuenciaDePartidas partidas) 
	{
		this.partidas = partidas;
	}



	public int getCociente()
	{
		return cociente;
	}



	public void setCociente(int cociente) 
	{
		this.cociente = cociente;
	}
	
	//Seuma una partida a la cantidad de partidas finalizadas del jugador
	public void SumarUnaPartidaFinalizada ()
	{
		cantPartidasFinalizadas++;
	}
	@Override
	public int compareTo(Jugador jugador) {
		
		return this.puntajeTotal-jugador.getPuntajeTotal();
	}



}
