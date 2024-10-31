package hilo;

import javax.swing.JFrame;

import vista.FrmLogin;

public class HiloConteo extends Thread{
	private JFrame ventana;
	
	public HiloConteo(JFrame ventana) {
		this.ventana = ventana;
	}
	@Override
	public void run() {
		// Código a convertir en un nuevo proceso
		for (int i = 40; i >= 0; i--) {
			FrmLogin.lblTiempo.setText(i + "s");
			// Para "dar" pausa --> Dormir el proceso de conteo
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Error en la pausa >> " + e.getMessage());
			}
			
		}
		ventana.dispose();
	}

}
