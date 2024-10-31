package interfaces;

import java.util.ArrayList;

import model.Producto;

public interface ProductoInterface {
	// Registrar Producto
	public int registrar(Producto p);
	// Actualizar Producto
	public int actualizarProducto(Producto p);
	// Eliminar Producto
	public int eliminarProducto(Producto p);
	// Listado de todos los productos
	public ArrayList<Producto> listado();
	// Consultar un Producto por su tipo
	public ArrayList<Producto> listado(int tipo);
	// listado por nombre 
	public ArrayList<Producto> listadoXNombre(String nombre);
}
