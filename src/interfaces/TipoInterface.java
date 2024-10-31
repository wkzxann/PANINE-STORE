package interfaces;

import java.util.ArrayList;

import model.TipoPersonal;
import model.TipoProducto;

public interface TipoInterface {
	// Listar el Tipo de Personal
	public ArrayList<TipoPersonal> listado();
	// Listar el Tipo de Producto
	public ArrayList<TipoProducto> listadoTipoProducto();

}
