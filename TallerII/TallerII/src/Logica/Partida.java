package Logica;

import java.io.Serializable;

public class Partida implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numPartida;
	private int numSecreto;
	private boolean finalizada;
	private int cantIntentos;
	private int puntajeFinal;
	
	//Constructor
	public Partida (int numP)
	{
		numPartida = numP;
		numSecreto = (int) (Math.random()*1000+1);
		finalizada = false;
		cantIntentos = 0;
		puntajeFinal = 0;
	}
	
	public int getNumPartida() 
	{
		return numPartida;
	}

	public void setNumPartida(int numPartida) {
		this.numPartida = numPartida;
	}

	public int getNumSecreto() {
		return numSecreto;
	}

	public void setNumSecreto(int numSecreto) {
		this.numSecreto = numSecreto;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public int getCantIntentos() {
		return cantIntentos;
	}

	public void setCantIntentos(int cantIntentos) {
		this.cantIntentos = cantIntentos;
	}

	public int getPuntajeFinal() {
		return puntajeFinal;
	}

	public void setPuntajeFinal(int puntajeFinal) {
		this.puntajeFinal = puntajeFinal;
	}
	
	//Suma un intento a la partida
	public void sumarUnIntento ()
	{
		cantIntentos++;
	}

	
}
