package model;

public class Cliente {
	private String IdCliente;
	private String nombre;
	private String apellido;
	private int dni;
	private int telefono;
	private String direccion;
	private String fechanac;

	// Constructor vacio
	public Cliente() {
	}
	// Constructor usando sus campos
	public Cliente(String idCliente, String nombre, String apellido, int dni, int telefono, String direccion,
			String fechanac) {
		IdCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechanac = fechanac;
	}
		
	// Metodo ToString	
	@Override
	public String toString() {
		return "Cliente [IdCliente=" + IdCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", telefono=" + telefono + ", direccion=" + direccion + ", fechanac=" + fechanac + "]";
	}
	
	// Get and set
	public String getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFechanac() {
		return fechanac;
	}
	public void setFechanac(String fechanac) {
		this.fechanac = fechanac;
	}
	
	
	
}
