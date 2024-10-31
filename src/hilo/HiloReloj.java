package hilo;

import java.text.SimpleDateFormat;
import java.util.Date;

import vista.FrmPrincipal;

public class HiloReloj extends Thread{
	@Override
	public void run() {
		while (true) {		// While es un bucle infinito
			// Captura la hora del sistema
			Date hora = new Date(); // Capturará la fecha y hora
			// Mostrar en el lbl
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
			FrmPrincipal.lblReloj.setText(sdf.format(hora));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en pausa: " + e.getMessage());
			}
		}
	}

}
