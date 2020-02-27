package Logica;

import java.io.Serializable;

public class Monitor implements Serializable{
	private int cantLectores;
	private boolean escribiendo;

	public void comienzoLectura() throws InterruptedException {

		while (escribiendo) {

			try {
				this.wait();
			} catch (InterruptedException e) { // no hago nada }
			}
		}

		cantLectores++;
	}

	public void terminoLectura() {
		cantLectores--;
		//notify();
	}

	public void comienzoEscritura() throws InterruptedException {
		while (cantLectores > 0) {

			try {
				this.wait();
			} catch (InterruptedException e) { // no hago nada }
			}
		}
		
			
			escribiendo = true;
		
	}

	public void terminoEscritura() throws InterruptedException {
		
		escribiendo = false;
		//notify();
	}

}
