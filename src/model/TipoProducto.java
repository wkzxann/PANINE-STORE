package model;

public class TipoProducto {
	private int idtipo;
	private String descripcion;
	
	//ToString
	@Override
	public String toString() {
		return "TipoProducto [idtipo=" + idtipo + ", descripcion=" + descripcion + "]";
	}
	
	// Get and Set
	public int getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
