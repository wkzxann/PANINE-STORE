package interfaces;

import java.util.ArrayList;

import model.Boleta;
import model.DetalleBoleta;

public interface VentaInterface {
	// Obtendremos el siguiente n�mero de boleta
	public String generaNumBoleta();
	// Guardara �as tablas cabecera y detalle
	public int realizarVenta(Boleta b, ArrayList<DetalleBoleta> db);

}
