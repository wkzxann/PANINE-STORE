package model;

public class TipoPersonal {
	private int idTipoPersonal;
	private String descripcion;
	
	// ToString
	@Override
	public String toString() {
		return "TipoPersonal [idTipoPersonal=" + idTipoPersonal + ", descripcion=" + descripcion + "]";
	}
	
	// Get and Set
	public int getIdTipoPersonal() {
		return idTipoPersonal;
	}

	public void setIdTipoPersonal(int idTipoPersonal) {
		this.idTipoPersonal = idTipoPersonal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
