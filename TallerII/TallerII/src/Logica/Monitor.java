package Logica;


public class Monitor {

	private static int cantLectores;
	private static boolean escribiendo;
	
	

	public Monitor() {
		
		Monitor.cantLectores = 0;
		Monitor.escribiendo = false;
	}

	public synchronized void comienzoLectura() throws InterruptedException {

		while (escribiendo) {
				this.wait();
		}

		cantLectores++;
	}

	public synchronized void terminoLectura() {
		cantLectores--;
		notifyAll();
	}

	public synchronized void comienzoEscritura() throws InterruptedException {
		while (cantLectores > 0 || escribiendo) {

				this.wait();

		}

		escribiendo = true;

	}

	public synchronized void terminoEscritura() {

		escribiendo = false;
		notifyAll();
	}

}
