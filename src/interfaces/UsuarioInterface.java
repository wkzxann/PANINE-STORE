package interfaces;

import java.util.ArrayList;

import model.Cliente;

public interface UsuarioInterface {
	//Registrar los Usuarios
	public int registrar(Cliente u);
	// Actualizar Usuarios
	public int actualizarUsuario(Cliente u);
	// Eliminar Usuario
	public int eliminarUsuario(Cliente u);
	// Listar Clientes
	public ArrayList<Cliente> listado();
	// Consultar un cliente por su ID
	public ArrayList<Cliente> listado(String IdCliente);

}
