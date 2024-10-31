package model;

public class Reportes {
	private String codbol;
	private String fecha;
	private String nomCliente;
	private String nomvendedor;
	private double importe;
	
	// Constructor
	public Reportes(String codbol, String fecha, String nomCliente, String nomvendedor, double importe) {
		this.codbol = codbol;
		this.fecha = fecha;
		this.nomCliente = nomCliente;
		this.nomvendedor = nomvendedor;
		this.importe = importe;
	}
	
	// Constructor vacio
	public Reportes() {
	}
	
	// Metodo Get and Set
	public String getCodbol() {
		return codbol;
	}
	public void setCodbol(String codbol) {
		this.codbol = codbol;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNomCliente() {
		return nomCliente;
	}
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
	public String getNomVendedor() {
		return nomvendedor;
	}
	public void setNomVendedor(String nomvendedor) {
		this.nomvendedor = nomvendedor;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}

}
