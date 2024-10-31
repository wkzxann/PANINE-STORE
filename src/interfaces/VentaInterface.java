package interfaces;

import java.util.ArrayList;

import model.Boleta;
import model.DetalleBoleta;

public interface VentaInterface {
	// Obtendremos el siguiente número de boleta
	public String generaNumBoleta();
	// Guardara ñas tablas cabecera y detalle
	public int realizarVenta(Boleta b, ArrayList<DetalleBoleta> db);

}
