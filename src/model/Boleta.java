package model;

public class Boleta {
	private String numbol;
	private String fecha;
	private String codcliente;
	private int codvendedor;
	private double totalbol;

	// Get and Set
	public String getNumbol() {
		return numbol;
	}
	public void setNumbol(String numbol) {
		this.numbol = numbol;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fechanac) {
		this.fecha = fechanac;
	}

	public String getCodcliente() {
		return codcliente;
	}

	public void setCodcliente(String codcliente) {
		this.codcliente = codcliente;
	}

	public int getCodvendedor() {
		return codvendedor;
	}

	public void setCodvendedor(int codvendedor) {
		this.codvendedor = codvendedor;
	}

	public double getTotalbol() {
		return totalbol;
	}

	public void setTotalbol(double totalbol) {
		this.totalbol = totalbol;
	}
	
	

}
