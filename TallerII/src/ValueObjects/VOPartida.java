package ValueObjects;

public class VOPartida {


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

}