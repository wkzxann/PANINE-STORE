package hilo;

import vista.FrmPreLoader;

public class HiloBarra extends Thread{
	@Override
	public void run() {
		for(int i = 0; i <= 100; i++) {
			FrmPreLoader.prbCarga.setValue(i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Error en pausa de la barra: " + e.getMessage());
			}
		}
	}

}
