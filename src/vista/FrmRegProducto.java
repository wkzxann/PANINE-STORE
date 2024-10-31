package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import mantenimiento.GestionCategoria;
import mantenimiento.GestionPersonal;
import mantenimiento.GestionProducto;
import mantenimiento.GestionUsuario;
import model.Categoria;
import model.Personal;
import model.Producto;
import model.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class FrmRegProducto extends JInternalFrame implements ActionListener, MouseListener, KeyListener{

	private JPanel contentPane;
	private JLabel lblStock;
	private JLabel lblPrecio;
	private JLabel lblCategoria;
	private JLabel lblNombre;
	private JLabel lblProducto;
	private JTextField txtIDProducto;
	private JTextField txtNomProducto;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JComboBox cboRegAcEli;
	private JButton btnRegistrar;
	private JButton btnNuevo;
	private JComboBox cboCategoria;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTable tblSalida;
	private JScrollPane scrollPane;
	
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
					FrmRegProducto frame = new FrmRegProducto();
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
	public FrmRegProducto() {
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		setTitle("Registrar - Actualizar - Eliminar Productos");
		setBounds(100, 100, 474, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 114, 62, 14);
		contentPane.add(lblStock);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 89, 62, 14);
		contentPane.add(lblPrecio);
		
		lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setBounds(10, 64, 62, 14);
		contentPane.add(lblCategoria);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 39, 62, 14);
		contentPane.add(lblNombre);
		
		lblProducto = new JLabel("IDProducto");
		lblProducto.setBounds(10, 14, 73, 14);
		contentPane.add(lblProducto);
		
		txtIDProducto = new JTextField();
		txtIDProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean mayuscula = tecla == 'P';
				boolean numeros = tecla >= 48 && tecla <= 57;
				
				if (!(mayuscula || numeros)) {
					e.consume();
				}
				
				if (txtIDProducto.getText().length() >= 5) {
					e.consume();
				}
			}
		});
		txtIDProducto.setEnabled(false);
		txtIDProducto.setColumns(10);
		txtIDProducto.setBounds(82, 11, 100, 20);
		contentPane.add(txtIDProducto);
		
		txtNomProducto = new JTextField();
		txtNomProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean mayuscula = tecla >= 65 && tecla <= 90 || tecla == 'Ñ';
				boolean minuscula = tecla >= 'a' && tecla <= 'z' || tecla == 'ñ';
				boolean tildes = tecla >= 'á' && tecla <= 'ú';
				boolean guion = tecla == 45;
				boolean espacio = tecla == 32;
				
				if (!(mayuscula || minuscula || tildes || guion || espacio)) {
					e.consume();
				}
			}
		});
		txtNomProducto.setEnabled(false);
		txtNomProducto.setColumns(10);
		txtNomProducto.setBounds(82, 36, 100, 20);
		contentPane.add(txtNomProducto);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean numeros = tecla >= 48 && tecla <= 57;
				boolean punto = tecla == '.';
				
				if (!(numeros || punto)) {
					e.consume();
				}
			}
		});
		txtPrecio.setEnabled(false);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(82, 86, 100, 20);
		contentPane.add(txtPrecio);
		
		txtStock = new JTextField();
		txtStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean numeros = tecla >= 48 && tecla <= 57;
				
				if (!(numeros)) {
					e.consume();
				}
			}
		});
		txtStock.setEnabled(false);
		txtStock.setColumns(10);
		txtStock.setBounds(82, 111, 100, 20);
		contentPane.add(txtStock);
		
		cboRegAcEli = new JComboBox();
		cboRegAcEli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedCboRegAcEli(e);
			}
		});
		cboRegAcEli.setModel(new DefaultComboBoxModel(new String[] {"Registrar", "Actualizar", "Eliminar"}));
		cboRegAcEli.setToolTipText("");
		cboRegAcEli.setBounds(219, 10, 100, 23);
		contentPane.add(cboRegAcEli);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(FrmRegProducto.class.getResource("/img/2931176_diskette_guardar_save_disk_drive_icon.png")));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnRegistrar(e);
			}
		});
		btnRegistrar.setBounds(329, 11, 119, 23);
		contentPane.add(btnRegistrar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(FrmRegProducto.class.getResource("/img/134224_add_plus_new_icon.png")));
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNuevo(e);
			}
		});
		btnNuevo.setBounds(329, 86, 119, 23);
		contentPane.add(btnNuevo);
		
		cboCategoria = new JComboBox();
		cboCategoria.setToolTipText("");
		cboCategoria.setBounds(82, 60, 100, 22);
		contentPane.add(cboCategoria);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(FrmRegProducto.class.getResource("/img/172618_update_icon.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(329, 36, 119, 23);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FrmRegProducto.class.getResource("/img/2931168_bin_delete_remove_trash_garbage_icon.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(329, 61, 119, 23);
		contentPane.add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 143, 438, 126);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.addKeyListener(this);
		tblSalida.addMouseListener(this);
		tblSalida.setModel(modelo);
		modelo.addColumn("ID Producto");
		modelo.addColumn("Nombre");
		modelo.addColumn("Categoría");
		modelo.addColumn("Precio");
		modelo.addColumn("Stock");
		tblSalida.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblSalida.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblSalida.getColumnModel().getColumn(2).setPreferredWidth(60);
		tblSalida.getColumnModel().getColumn(3).setPreferredWidth(45);
		tblSalida.getColumnModel().getColumn(4).setPreferredWidth(30);
		scrollPane.setViewportView(tblSalida);
		
		HabilitarEntradas();
		llenaCombo();
		llenarTabla();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	private void llenarTabla() {
		GestionProducto gp = new GestionProducto();
		ArrayList<Producto> lista = gp.listado();
						
		modelo.setRowCount(0);
		for (Producto p : lista) {
			Object datos [] = {p.getIdProducto(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock()};
			modelo.addRow(datos);
		}
	}

	private void HabilitarEntradas() {
		txtIDProducto.setEnabled(false);
		txtNomProducto.setEnabled(false);
		cboCategoria.setEnabled(false);
		txtPrecio.setEnabled(false);
		txtStock.setEnabled(false);
		btnRegistrar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnNuevo.setEnabled(false);
	}
	
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		txtIDProducto.setText("");
		txtNomProducto.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		txtIDProducto.requestFocus();
		cboCategoria.setSelectedIndex(0);
	}
	protected void actionPerformedCboRegAcEli(ActionEvent e) {
		int opcion = cboRegAcEli.getSelectedIndex();
		switch (opcion) {
		case 0: 		// Opcion Registrar
			txtIDProducto.setEnabled(true);
			txtIDProducto.requestFocus();
			txtIDProducto.setText("");
			txtNomProducto.setText("");
			cboCategoria.setSelectedIndex(0);
			txtPrecio.setText("");
			txtStock.setText("");
			// -------
			btnNuevo.setEnabled(true);
			txtNomProducto.setEnabled(true);
			cboCategoria.setEnabled(true);
			txtPrecio.setEnabled(true);
			txtStock.setEnabled(true);
			btnRegistrar.setEnabled(true);
			btnActualizar.setEnabled(false);
			btnEliminar.setEnabled(false);
			
			break;
		case 1:			// Opcion Actualizar
			txtIDProducto.setText("");
			txtIDProducto.setEnabled(true);
			txtIDProducto.requestFocus();
			txtNomProducto.setText("");
			cboCategoria.setSelectedIndex(0);
			txtPrecio.setText("");
			txtStock.setText("");
			// -------
			txtNomProducto.setEnabled(true);
			cboCategoria.setEnabled(true);
			txtPrecio.setEnabled(true);
			txtStock.setEnabled(true);
			btnRegistrar.setEnabled(false);
			btnActualizar.setEnabled(true);
			btnEliminar.setEnabled(false);
			btnNuevo.setEnabled(true);
			break;
		default:		// Opcion Eliminar
			txtIDProducto.setText("");
			txtIDProducto.requestFocus();
			txtIDProducto.setEnabled(true);
			txtIDProducto.setEnabled(true);
			txtNomProducto.setText("");
			cboCategoria.setSelectedIndex(0);
			txtPrecio.setText("");
			txtStock.setText("");
			// -------
			txtNomProducto.setEnabled(false);
			cboCategoria.setEnabled(false);
			txtPrecio.setEnabled(false);
			txtStock.setEnabled(false);
			btnRegistrar.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnEliminar.setEnabled(true);
			btnNuevo.setEnabled(true);
			break;
		}
	}
	void llenaCombo() {
		// Obtener un listado de las categorias, usando metodos gestion
		GestionCategoria gc = new GestionCategoria();
		ArrayList<Categoria> lista = gc.listado();
		// Pasar el listado al combo
		cboCategoria.addItem("Seleccione...");
		for (Categoria c : lista) {
			cboCategoria.addItem(c.getDescripcion());
		}
	}
	
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		// Variables
		int categoria, stock;
		String nombre, IdProducto;
		double precio;
		
		// Entradas
		IdProducto = leerIdProducto();
		nombre = leerNombreProducto();
		categoria = leerCategoria();
		precio = leerPrecio();
		stock = leerStock();
		
		if (IdProducto == null || nombre == null || categoria == -1 || precio == -1 || stock == -1)
			return;
				
		Producto p = new Producto();
		p.setIdProducto(IdProducto);
		p.setNombre(nombre);
		p.setCategoria(categoria);
		p.setPrecio(precio);
		p.setStock(stock);
				
		// Procesos
		GestionProducto gp = new GestionProducto();
		int ok = gp.registrar(p);
		// salidas
		if (ok == 0) {
			alerta("Error al registrar Producto");
		}else {
			alerta("Producto Registrado");
		}
		
		llenarTabla();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		// Variables
		int stock, categoria;
		Double precio;
		String IdProducto, nombreProducto;
						
		// Entradas
		IdProducto = leerIdProducto();
		nombreProducto = leerNombreProducto();
		categoria = leerCategoria();
		precio = leerPrecio();
		stock = leerStock();
						
		Producto p = new Producto();
		p.setIdProducto(IdProducto);
		p.setNombre(nombreProducto);
		p.setCategoria(categoria);
		p.setPrecio(precio);
		p.setStock(stock);

						
		// Proceso de Actualizar
		GestionProducto gp = new GestionProducto();
		int ok = gp.actualizarProducto(p);
						
		// Salida
		if (ok == 0) {
			alerta("Error al Actualizar Producto >> Código no existente");
		} else {
			alerta("Producto Actualizado");
		}
		
		llenarTabla();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		String IdProducto;
		
		// Entradas
		IdProducto = leerIdProducto();
		
		Producto p = new Producto();
		p.setIdProducto(IdProducto);
		
		// Proceso de Eliminar
		GestionProducto gp = new GestionProducto();
		int ok = gp.eliminarProducto(p);
		
		// Salida
		if (ok == 0) {
			alerta("Error al Eliminar Producto >> Código no existente");
		} else {
			alerta("Producto Eliminado");
		}
		
		// Reseteamos el txtIDProducto
		txtIDProducto.setText("");
		txtIDProducto.requestFocus();
		
		llenarTabla();
	}

	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private int leerStock() {
		if (txtStock.getText().equals("")) {
			alerta("Ingrese Stock");
			return -1;
		}
		return Integer.parseInt(txtStock.getText());
	}

	private double leerPrecio() {
		if (txtPrecio.getText().isEmpty()) {
			alerta("Ingrese Precio");
			return -1;
		}
		
		if (!txtPrecio.getText().matches("[0-9]*[.]{0,1}[0-9]{0,2}")) {
			alerta("Precio no válido, debe tener hasta dos decimales");
			return -1;
			
		}
		
		try {
			return Double.parseDouble(txtPrecio.getText());
		} catch (NumberFormatException e) {
			alerta("Error en el valor de precio, solo acepta números");
			return -1;
		}
	}

	private int leerCategoria() {
		if (cboCategoria.getSelectedIndex() == 0) {
			alerta("Tipo no válido, debe seleccionar un tipo");
			return -1;
		}
		return cboCategoria.getSelectedIndex();
	}

	private String leerNombreProducto() {
		if (txtNomProducto.getText().equals("")) {
			alerta("Ingrese Nombre del Producto");
			return null;
		}
		return txtNomProducto.getText();
	}

	private String leerIdProducto() {
		if (txtIDProducto.getText().equals("")) {
			alerta("Ingrese ID del Producto");
			return null;
		}
		if (!txtIDProducto.getText().matches("[P][0123456789][0-9]{3}")) {
			alerta("ID del Producto nó valido, debe tener la forma: PXXXX");
			return null;
		}
		return txtIDProducto.getText();
	}
	
	// Eventos para obtener el valor de la tabla
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblSalida) {
			mouseClickedtblProductos(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedtblProductos(MouseEvent e) {
		mostrarDatos();
	}
	public void keyPressed(KeyEvent e) {
	}
	protected void keyPressedTblProductos(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == tblSalida) {
			keyReleasedtblProductos(e);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyReleasedtblProductos(KeyEvent e) {
		mostrarDatos();
	}
	void mostrarDatos() {
		// Variables
		int posFila;
		String IDProd, nombre, precio, stock;
		//obtener posición de la fila 
		posFila = tblSalida.getSelectedRow();
		
		//obtener valores de la fila actual	
		IDProd = tblSalida.getValueAt(posFila, 0).toString();
		nombre = tblSalida.getValueAt(posFila, 1).toString();
		precio = tblSalida.getValueAt(posFila, 3).toString();
		stock = tblSalida.getValueAt(posFila, 4).toString();
				
		//mostrar en la cajas el valor de los atributos del objeto "bean"
		txtIDProducto.setText(IDProd);
		txtNomProducto.setText(nombre);
		txtPrecio.setText(precio);
		txtStock.setText(stock);
	}


}
