package interfaces;


import java.util.ArrayList;

import model.Personal;

public interface PersonalInterface {
	// Registrar Personal
	public int registrar(Personal p);
	// Actualizar Personal
	public int actualizarPersonal(Personal p);
	// Eliminar Personal
	public int eliminarPersonal(Personal p);
	// Listar Personal
	public ArrayList<Personal> listado();
	// Validad ingreso de personal
	public Personal validarAcceso(String usuario, String clave);


}
