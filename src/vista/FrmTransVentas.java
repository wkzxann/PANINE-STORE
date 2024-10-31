package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimiento.GestionVenta;
import model.Boleta;
import model.Cliente;
import model.DetalleBoleta;

import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

public class FrmTransVentas extends JInternalFrame {

	private JPanel contentPane;
	private JPanel panelDatosCliente;
	private JPanel panelDatosBoleta;
	private JLabel lblNomCliente;
	private JLabel lblCodigoCli;
	private JLabel lblDireccion;
	
	private JPanel panelDatosProducto;
	private JLabel lblNombreMarket;
	private JTable tblSalida;
	private JScrollPane scrollPane;
	private JButton btnFinalizarCompra;
	private JButton btnNuevaCompra;
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JLabel lblCodigo;
	private JLabel lblNomProducto;
	private JLabel lblPrecioProducto;
	private JLabel lblStockProducto;
	private JLabel lblNumBoleta;
	private JLabel lblFecha;
	private JTextField txtNumBoleta;
	private JTextField txtFecha;
	//
	public static JTextField txtCodigoCliente;
	public static JTextField txtNomCliente;
	public static JTextField txtDirecCliente;
	public static JButton btnBuscarCliente;
	public static JTextField txtCodProd;
	public static JButton btnBuscarProducto;
	public static JTextField txtNombreProd;
	public static JTextField txtPrecioProd;
	public static JTextField txtStockProd;
	private JButton btnAgregarProducto;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	
	
	DefaultTableModel modelo = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTransVentas frame = new FrmTransVentas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmTransVentas() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ventas");
		setBounds(100, 100, 484, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelDatosCliente = new JPanel();
		panelDatosCliente.setBackground(new Color(188, 143, 143));
		panelDatosCliente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos del Cliente", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		panelDatosCliente.setBounds(10, 100, 448, 94);
		contentPane.add(panelDatosCliente);
		panelDatosCliente.setLayout(null);
		
		lblNomCliente = new JLabel("Nombre: ");
		lblNomCliente.setBounds(10, 58, 54, 14);
		panelDatosCliente.add(lblNomCliente);
		
		txtNomCliente = new JTextField();
		txtNomCliente.setEditable(false);
		txtNomCliente.setBounds(61, 54, 161, 20);
		panelDatosCliente.add(txtNomCliente);
		txtNomCliente.setColumns(10);
		
		lblCodigoCli = new JLabel("Codigo:");
		lblCodigoCli.setBounds(10, 29, 46, 14);
		panelDatosCliente.add(lblCodigoCli);
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setEditable(false);
		txtCodigoCliente.setColumns(10);
		txtCodigoCliente.setBounds(61, 26, 112, 20);
		panelDatosCliente.add(txtCodigoCliente);
		
		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(222, 30, 59, 14);
		panelDatosCliente.add(lblDireccion);
		
		txtDirecCliente = new JTextField();
		txtDirecCliente.setEditable(false);
		txtDirecCliente.setColumns(10);
		txtDirecCliente.setBounds(291, 26, 147, 20);
		panelDatosCliente.add(txtDirecCliente);
		
		btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.setIcon(new ImageIcon(FrmTransVentas.class.getResource("/img/3844432_magnifier_search_zoom_icon.png")));
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscarCliente(e);
			}
		});
		btnBuscarCliente.setBounds(301, 60, 137, 23);
		panelDatosCliente.add(btnBuscarCliente);
		
		panelDatosBoleta = new JPanel();
		panelDatosBoleta.setBackground(new Color(188, 143, 143));
		panelDatosBoleta.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Boleta de Venta", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatosBoleta.setBounds(289, 11, 169, 81);
		contentPane.add(panelDatosBoleta);
		panelDatosBoleta.setLayout(null);
		
		lblNumBoleta = new JLabel("N\u00FAm:");
		lblNumBoleta.setBounds(10, 21, 46, 14);
		panelDatosBoleta.add(lblNumBoleta);
		
		lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(10, 46, 46, 14);
		panelDatosBoleta.add(lblFecha);
		
		txtNumBoleta = new JTextField();
		txtNumBoleta.setText(obtenerNumBol());
		txtNumBoleta.setEditable(false);
		txtNumBoleta.setBounds(55, 18, 104, 20);
		panelDatosBoleta.add(txtNumBoleta);
		txtNumBoleta.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setText(obtenerFecha());
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(55, 43, 104, 20);
		panelDatosBoleta.add(txtFecha);
		
		panelDatosProducto = new JPanel();
		panelDatosProducto.setBackground(new Color(188, 143, 143));
		panelDatosProducto.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos del Producto", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatosProducto.setBounds(10, 196, 448, 106);
		contentPane.add(panelDatosProducto);
		panelDatosProducto.setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(10, 21, 54, 14);
		panelDatosProducto.add(lblCodigo);
		
		txtCodProd = new JTextField();
		txtCodProd.setEditable(false);
		txtCodProd.setColumns(10);
		txtCodProd.setBounds(60, 18, 86, 20);
		panelDatosProducto.add(txtCodProd);
		
		btnBuscarProducto = new JButton("Buscar Producto");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscarProducto(e);
			}
		});
		btnBuscarProducto.setIcon(new ImageIcon(FrmTransVentas.class.getResource("/img/3844432_magnifier_search_zoom_icon.png")));
		btnBuscarProducto.setBounds(284, 17, 154, 23);
		panelDatosProducto.add(btnBuscarProducto);
		
		lblNomProducto = new JLabel("Nombre: ");
		lblNomProducto.setBounds(10, 49, 61, 14);
		panelDatosProducto.add(lblNomProducto);
		
		txtNombreProd = new JTextField();
		txtNombreProd.setEditable(false);
		txtNombreProd.setBounds(60, 46, 86, 20);
		panelDatosProducto.add(txtNombreProd);
		txtNombreProd.setColumns(10);
		
		lblPrecioProducto = new JLabel("Precio: ");
		lblPrecioProducto.setBounds(166, 21, 46, 14);
		panelDatosProducto.add(lblPrecioProducto);
		
		lblStockProducto = new JLabel("Stock: ");
		lblStockProducto.setBounds(166, 49, 46, 14);
		panelDatosProducto.add(lblStockProducto);
		
		txtPrecioProd = new JTextField();
		txtPrecioProd.setEditable(false);
		txtPrecioProd.setColumns(10);
		txtPrecioProd.setBounds(213, 18, 61, 20);
		panelDatosProducto.add(txtPrecioProd);
		
		txtStockProd = new JTextField();
		txtStockProd.setEditable(false);
		txtStockProd.setColumns(10);
		txtStockProd.setBounds(213, 46, 61, 20);
		panelDatosProducto.add(txtStockProd);
		
		btnAgregarProducto = new JButton("Add Producto");
		btnAgregarProducto.setIcon(new ImageIcon(FrmTransVentas.class.getResource("/img/622414_buy_cart_shopping_basket_ecommerce_icon.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAgregarProducto(e);
			}
		});
		btnAgregarProducto.setBounds(284, 72, 154, 23);
		panelDatosProducto.add(btnAgregarProducto);
		
		lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setBounds(10, 78, 61, 14);
		panelDatosProducto.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(70, 75, 86, 20);
		panelDatosProducto.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		lblNombreMarket = new JLabel("Minimarket - PanineStore");
		lblNombreMarket.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombreMarket.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMarket.setBounds(10, 11, 269, 14);
		contentPane.add(lblNombreMarket);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 307, 448, 120);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio");
		modelo.addColumn("Importe");
		scrollPane.setViewportView(tblSalida);
		
		btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setIcon(new ImageIcon(FrmTransVentas.class.getResource("/img/2205221_bill_buy_cash_money_pay_icon.png")));
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnFinalizarCompra(e);
			}
		});
		btnFinalizarCompra.setBounds(283, 466, 152, 23);
		contentPane.add(btnFinalizarCompra);
		
		btnNuevaCompra = new JButton("Nueva Compra");
		btnNuevaCompra.setIcon(new ImageIcon(FrmTransVentas.class.getResource("/img/134224_add_plus_new_icon.png")));
		btnNuevaCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNuevaCompra(e);
			}
		});
		btnNuevaCompra.setBounds(39, 466, 152, 23);
		contentPane.add(btnNuevaCompra);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(328, 438, 46, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(372, 435, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		lblNewLabel = new JLabel("Urb. Jes\u00FAs Villase\u00F1or # 3 ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 29, 169, 14);
		contentPane.add(lblNewLabel);
		
		lblTelefono = new JLabel("Telefono: 645 0000");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setBounds(55, 45, 169, 14);
		contentPane.add(lblTelefono);
		
		lblRuc = new JLabel("RUC 20109064188");
		lblRuc.setHorizontalAlignment(SwingConstants.CENTER);
		lblRuc.setBounds(55, 63, 169, 14);
		contentPane.add(lblRuc);
	}
	// Declaracion Global
	Boleta b = new Boleta();
	double total = 0;
	ArrayList<DetalleBoleta> carro = new ArrayList<DetalleBoleta>();
	private JLabel lblNewLabel;
	private JLabel lblTelefono;
	private JLabel lblRuc;
	
	protected void actionPerformedBtnBuscarCliente(ActionEvent e) {
		DlgCliente c = new DlgCliente();
		c.setVisible(true);
		btnBuscarCliente.setEnabled(false);
	}
	protected void actionPerformedBtnBuscarProducto(ActionEvent e) {
		DlgProducto p = new DlgProducto();
		p.setVisible(true);
		btnBuscarProducto.setEnabled(false);
	}
	protected void actionPerformedBtnAgregarProducto(ActionEvent e) {
		String codProd = leerCodigoProducto();
		String nombreProd = leerNombreProducto();
		double precio = leerPrecio();
		int stock = leerStock();
		int cantidad = leerCantidad();
		double importe = cantidad * precio;
		total += importe;
		
		if (codProd == null || nombreProd == null || precio == -1 || stock == -1 || cantidad == -1 || importe == -1)
			return;
		
		
		if (cantidad > stock) {
			alerta("Stock insuficiente...");
			return;
		}
		
		//
		modelo.addRow(new Object[] {
				txtCodProd.getText(),txtNombreProd.getText(),txtCantidad.getText(), "S/. " + txtPrecioProd.getText(), String.format("S/.%.2f", importe)
		});
		
		//
		txtTotal.setText(String.format("S/%.2f", total));
		
		// Agregamos los datos a nuestro ArrayList de Detalle
		DetalleBoleta db = new DetalleBoleta();
		db.setIdproducto(codProd);
		db.setCantidad(cantidad);
		db.setPrecioventa(precio);
		db.setImporte(importe);
		db.setNomprod(nombreProd);
		carro.add(db);
	}

	protected void actionPerformedBtnNuevaCompra(ActionEvent e) {
		carro.clear();
		total = 0;
		txtCodigoCliente.setText("");
		txtNomCliente.setText("");
		txtDirecCliente.setText("");
		txtCodProd.setText("");
		txtPrecioProd.setText("");
		txtStockProd.setText("");
		txtNombreProd.setText("");
		txtTotal.setText("");
		txtCantidad.setText("");
		txtCantidad.requestFocus();
		txtNumBoleta.setText(obtenerNumBol());
		modelo.setRowCount(0);
	}
	protected void actionPerformedBtnFinalizarCompra(ActionEvent e) {
		// Datos de la cabecera de boleta
		String numbol = obtenerNumBol();
		String fechabol = obtenerFecha();
		String codcliente = leerCodCliente();
		int codvendedore = leerCodVendedor();
		double totalbol = total;
		
		// Validacion
		if (codcliente.isEmpty() || txtCodProd.getText().isEmpty() || carro.isEmpty()) {
			alerta("Verifique datos");
		} else {
			alerta("Venta exitosa");
		}

		b.setNumbol(numbol);
		b.setFecha(fechabol);
		b.setCodcliente(codcliente);
		b.setCodvendedor(codvendedore);
		b.setTotalbol(totalbol);
		
		GestionVenta gv = new GestionVenta();
		int ok = gv.realizarVenta(b, carro);
		
		if (ok == 0) {
			alerta("Error en realizar la venta");
			return;
		} 
		BoletaVenta();
	}
	
	void BoletaVenta() {
		String nomarchivo = "boletas/" + b.getNumbol() + ".pdf";
		try {
			Document doc = new Document();
			
			FileOutputStream fos = new FileOutputStream(nomarchivo);
			PdfWriter pdfw = PdfWriter.getInstance(doc, fos);
			doc.open();
			
			doc.add(new Paragraph("****************************************************************************************************************"));
			Paragraph titulo = new Paragraph("BOLETA DE VENTA ELECTRONICA " + b.getNumbol());
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			doc.add(new Paragraph("Nombre Cliente:        " + txtNomCliente.getText()));
			doc.add(new Paragraph("Direc Cliente:             " + txtDirecCliente.getText()));
			doc.add(new Paragraph("Fecha de Venta:        " + b.getFecha()));
			doc.add(new Paragraph("Monto Total:              " + String.format("S/%.2f", b.getTotalbol())));
			doc.add(new Paragraph("****************************************************************************************************************"));
			Paragraph titulo2 = new Paragraph("DETALLE DE LA VENTA");
			titulo2.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo2);
			for (DetalleBoleta d : carro) {
				doc.add(new Paragraph(d.getIdproducto() + "        " + d.getNomprod() + "             " + String.format("S/%.2f", d.getPrecioventa())));
			}
			doc.add(new Paragraph("****************************************************************************************************************"));
			
			doc.close();
			
			Desktop.getDesktop().open(new File(nomarchivo));
			
		} catch (Exception e) {
			alerta("Error al crear PDF: " + e.getMessage());
		}	
	}

	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	private int leerCodVendedor() {
		return FrmLogin.p.getIdPersonal();
	}

	private String leerCodCliente() {
		return txtCodigoCliente.getText();
	}

	private String obtenerFecha() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	private String obtenerNumBol() {
		// Llamamos a la gestion GestionVenta para que retorne el n�mero de boleta que sigue
		GestionVenta gv = new GestionVenta();
		return gv.generaNumBoleta();
	}
	
	private int leerCantidad() {
		if(txtCantidad.getText().equals("")) {
			alerta("Ingrese Cantidad");
			return -1;
		}
		return Integer.parseInt(txtCantidad.getText());
	}

	private int leerStock() {
		if (txtStockProd.getText().isEmpty()) {
			return -1;
		}
		return Integer.parseInt(txtStockProd.getText());
	}

	private double leerPrecio() {
		if (txtPrecioProd.getText().isEmpty()) {
			return -1;
		}
		return Double.parseDouble(txtPrecioProd.getText());
	}

	private String leerNombreProducto() {
		return txtNombreProd.getText();
	}

	private String leerCodigoProducto() {
		if (txtCodProd.getText().isEmpty()) {
			alerta("Debe ingresar un Producto");
			return null;
		}
		return txtCodProd.getText();
	}
}
