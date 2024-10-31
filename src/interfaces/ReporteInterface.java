package interfaces;

import java.util.ArrayList;

import model.Reportes;

public interface ReporteInterface {
	// Reporte por codCliente
	public ArrayList<Reportes> listado(String codCliente);
	// Reporte por Fecha
	public ArrayList<Reportes> listadoXFecha(String fecha);
}
