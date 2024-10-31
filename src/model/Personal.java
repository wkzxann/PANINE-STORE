package model;

public class Personal {
	private int IdPersonal;
	private String nombre;
	private String apellido;
	private int tipoPersonal;
	private int dni;
	private int telefono;
	private String usuario;
	private String clave;
	
	// Constructor
	public Personal(int idPersonal, String nombre, String apellido, int tipoPersonal, int dni, int telefono,
			String usuario, String clave) {
		this.IdPersonal = idPersonal;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoPersonal = tipoPersonal;
		this.dni = dni;
		this.telefono = telefono;
		this.usuario = usuario;
		this.clave = clave;
	}
	
	// Constructor 
	public Personal() {
		super();
	}
	
	// ToString
	@Override
	public String toString() {
		return "Personal [IdPersonal=" + IdPersonal + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", tipoPersonal=" + tipoPersonal + ", dni=" + dni + ", telefono=" + telefono + ", usuario=" + usuario
				+ ", clave=" + clave + "]";
	}
	
	// Get and Set
	public int getIdPersonal() {
		return IdPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		IdPersonal = idPersonal;
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

	public int getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(int tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
