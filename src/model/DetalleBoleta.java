package model;

public class DetalleBoleta {
	private String idproducto;
	private int cantidad;
	private double precioventa;
	private double importe;
	// Para consultar
	private String nomprod;
	
	// Constructor
	public DetalleBoleta(String idproducto, int cantidad, double precioventa, double importe) {
		this.idproducto = idproducto;
		this.cantidad = cantidad;
		this.precioventa = precioventa;
		this.importe = importe;
	}
	
	// Constructor vacio
	public DetalleBoleta() {
	}
	
	// Metodo toString()
	@Override
	public String toString() {
		return "DetalleBoleta [idproducto=" + idproducto + ", cantidad=" + cantidad + ", precioventa=" + precioventa
				+ ", importe=" + importe + "]";
	}
	
	// Get and Set
	public String getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(String idproducto) {
		this.idproducto = idproducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(double precioventa) {
		this.precioventa = precioventa;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getNomprod() {
		return nomprod;
	}
	public void setNomprod(String nomprod) {
		this.nomprod = nomprod;
	}
	
	

}
