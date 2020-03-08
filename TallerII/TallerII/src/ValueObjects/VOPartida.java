package ValueObjects;

import java.io.Serializable;

public class VOPartida implements Serializable {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int numPartida;
private int numSecreto;
private boolean finalizada;
private int cantIntentos;
private int puntajeFinal;

public VOPartida (int numP, int numS, boolean fin, int cantIn, int Punt)
{
	numPartida = numP;
	numSecreto = numS;
	finalizada = fin;
	cantIntentos = cantIn;
	puntajeFinal = Punt;
}

public int getVONumPartida() {
	return numPartida;
}

public int getVONumSecreto() {
	return numSecreto;
}

public boolean isVOFinalizada() {
	return finalizada;
}

public int getVOCantIntentos() {
	return cantIntentos;
}

public int getVOPuntajeFinal() {
	return puntajeFinal;
}

@Override
public String toString() {
	return "VOPartida [numPartida=" + numPartida + ", numSecreto=" + numSecreto + ", finalizada=" + finalizada
			+ ", cantIntentos=" + cantIntentos + ", puntajeFinal=" + puntajeFinal + "]";
}





}