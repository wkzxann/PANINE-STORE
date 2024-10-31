package model;

public class Producto {
	private String IdProducto;
	private String nombre;
	private int categoria;
	private double precio;
	private int stock;
	
	// Constructor
	public Producto(String idProducto, String nombre, int categoria, double precio, int stock) {
		super();
		IdProducto = idProducto;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	
	// Constructor vacio
	public Producto() {
		super();
	}
	
	// Metodo ToString();
	@Override
	public String toString() {
		return "Producto [IdProducto=" + IdProducto + ", nombre=" + nombre + ", categoria=" + categoria + ", precio="
				+ precio + ", stock=" + stock + "]";
	}
	
	// Metodos Get and Set
	public String getIdProducto() {
		return IdProducto;
	}

	public void setIdProducto(String idProducto) {
		IdProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
