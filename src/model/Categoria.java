package model;

public class Categoria {
	private int idTipo;
	private String descripcion;
	
	// Constructor
	public Categoria(int idTipo, String descripcion) {
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}
	
	// CONSTRUCTOR VACIO
	public Categoria() {
	}
	
	// METODO TOSTRING 
	@Override
	public String toString() {
		return "Categoria [idTipo=" + idTipo + ", descripcion=" + descripcion + "]";
	}
	
	// METODOS GET AND SET
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
