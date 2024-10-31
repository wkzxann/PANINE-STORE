package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionUsuario;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class FrmRegCliente extends JInternalFrame implements ActionListener, MouseListener, KeyListener{

	private JPanel contentPane;
	private JLabel lblIDCliente;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDNI;
	private JComboBox cboRegAcEli;
	private JButton btnNuevo;
	private JTextField txtIDCliente;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtTelefono;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JLabel lblDireccion;
	private JLabel lblClave;
	private JTextField txtDireccion;
	private JButton btnEliminar;
	private JDateChooser txtFecha;
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
					FrmRegCliente frame = new FrmRegCliente();
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
	public FrmRegCliente() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Registrar - Actualizar - Eliminar Clientes");
		setBounds(100, 100, 625, 405);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 165, 32));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIDCliente = new JLabel("IDCliente");
		lblIDCliente.setBounds(10, 14, 62, 14);
		contentPane.add(lblIDCliente);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 39, 62, 14);
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 64, 62, 14);
		contentPane.add(lblApellido);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(10, 89, 62, 14);
		contentPane.add(lblDNI);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(10, 114, 62, 14);
		contentPane.add(lblTelefono);
		
		cboRegAcEli = new JComboBox();
		cboRegAcEli.setToolTipText("");
		cboRegAcEli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedCboRegAcEli(e);
			}
		});
		cboRegAcEli.setModel(new DefaultComboBoxModel(new String[] {"Registrar", "Actualizar", "Eliminar"}));
		cboRegAcEli.setBounds(365, 14, 100, 23);
		contentPane.add(cboRegAcEli);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(FrmRegCliente.class.getResource("/img/134224_add_plus_new_icon.png")));
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNuevo(e);
			}
		});
		btnNuevo.setBounds(485, 89, 114, 23);
		contentPane.add(btnNuevo);
		
		txtIDCliente = new JTextField();
		txtIDCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean mayuscula = tecla >= 65 && tecla <= 90 || tecla == 'Ñ';
				
				if (!(mayuscula)) {
					e.consume();
				}
				
				if (txtIDCliente.getText().length() >= 5) {
					e.consume();
				}
				
			}
		});
		txtIDCliente.setBounds(76, 11, 131, 20);
		contentPane.add(txtIDCliente);
		txtIDCliente.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean mayuscula = tecla >= 65 && tecla <= 90 || tecla == 'Ñ';
				boolean minuscula = tecla >= 'a' && tecla <= 'z' || tecla == 'ñ';
				boolean tildes = tecla >= 'á' && tecla <= 'ú';
				boolean espacio = tecla == 32;
				
				if (!(mayuscula || minuscula || tildes || espacio)) {
					e.consume();
				}
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(76, 36, 131, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean mayuscula = tecla >= 65 && tecla <= 90 || tecla == 'Ñ';
				boolean minuscula = tecla >= 'a' && tecla <= 'z' || tecla == 'ñ';
				boolean tildes = tecla >= 'á' && tecla <= 'ú';
				boolean espacio = tecla == 32;
				
				if (!(mayuscula || minuscula || tildes || espacio)) {
					e.consume();
				}
			}
		});
		txtApellido.setColumns(10);
		txtApellido.setBounds(76, 61, 131, 20);
		contentPane.add(txtApellido);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean numeros = tecla >= 48 && tecla <= 57;
				
				if (!(numeros)) {
					e.consume();
				}
				
				// Para que no se ingrese más de 8 caracteres
				if (txtDNI.getText().length() >= 8) {
					e.consume();
				}
			}
		});
		txtDNI.setColumns(10);
		txtDNI.setBounds(76, 86, 131, 20);
		contentPane.add(txtDNI);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean numeros = tecla >= 48 && tecla <= 57;
				
				if (!(numeros)) {
					e.consume();
				}
				
				// Para que no se ingrese más de 8 caracteres
				if (txtTelefono.getText().length() >= 9) {
					e.consume();
				}
			}
		});
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(76, 111, 131, 20);
		contentPane.add(txtTelefono);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(FrmRegCliente.class.getResource("/img/2931176_diskette_guardar_save_disk_drive_icon.png")));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnRegistrar(e);
			}
		});
		btnRegistrar.setBounds(485, 14, 114, 23);
		contentPane.add(btnRegistrar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(FrmRegCliente.class.getResource("/img/172618_update_icon.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(485, 39, 114, 23);
		contentPane.add(btnActualizar);
		
		lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(10, 139, 62, 14);
		contentPane.add(lblDireccion);
		
		lblClave = new JLabel("Fec Nacimiento");
		lblClave.setBounds(10, 164, 100, 14);
		contentPane.add(lblClave);
		
		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean mayuscula = tecla >= 65 && tecla <= 90 || tecla == 'Ñ';
				boolean minuscula = tecla >= 'a' && tecla <= 'z' || tecla == 'ñ';
				boolean numeros = tecla >= 48 && tecla <= 57;
				boolean tildes = tecla >= 'á' && tecla <= 'ú';
				boolean espacio = tecla == 32;
				
				if (!(mayuscula || minuscula || tildes || espacio || numeros)) {
					e.consume();
				}
			}
		});
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(76, 136, 131, 20);
		contentPane.add(txtDireccion);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FrmRegCliente.class.getResource("/img/2931168_bin_delete_remove_trash_garbage_icon.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(485, 64, 114, 23);
		contentPane.add(btnEliminar);
		
		txtFecha = new JDateChooser();
		txtFecha.setBounds(107, 161, 100, 20);
		contentPane.add(txtFecha);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 189, 589, 175);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.addKeyListener(this);
		tblSalida.addMouseListener(this);
		tblSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("DNI");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Dirección");
		modelo.addColumn("Nacimiento");
		tblSalida.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblSalida.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblSalida.getColumnModel().getColumn(2).setPreferredWidth(60);
		tblSalida.getColumnModel().getColumn(3).setPreferredWidth(55);
		tblSalida.getColumnModel().getColumn(4).setPreferredWidth(60);
		scrollPane.setViewportView(tblSalida);
		
		HabilitarEntradas();
		llenarTabla();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	private void llenarTabla() {
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Cliente> lista = gu.listado();
		
		// Pasamos el listado a una tabla
		modelo.setRowCount(0);
		for (Cliente c : lista) {
			Object datos [] = {c.getIdCliente(), c.getNombre(), c.getApellido(), c.getDni(), c.getTelefono(), c.getDireccion(), c.getFechanac()};
			modelo.addRow(datos);
		}
	}
	private void HabilitarEntradas() {
		txtIDCliente.setEnabled(false);
		txtNombre.setEnabled(false);
		txtApellido.setEnabled(false);
		txtDNI.setEnabled(false);
		txtTelefono.setEnabled(false);
		txtDireccion.setEnabled(false);
		txtFecha.setEnabled(false);
		btnRegistrar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnNuevo.setEnabled(false);
	}

	protected void actionPerformedBtnNuevo(ActionEvent e) {
		txtIDCliente.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtDNI.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtFecha.setCalendar(null);
		txtIDCliente.requestFocus();
	}
	protected void actionPerformedCboRegAcEli(ActionEvent e) {
		int opcion = cboRegAcEli.getSelectedIndex();
		switch (opcion) {
		case 0: 		// Opcion Registrar
			txtIDCliente.setEnabled(true);
			txtIDCliente.setText("");
			txtIDCliente.requestFocus();
			txtNombre.setText("");
			txtApellido.setText("");
			txtDNI.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
			txtFecha.setDate(null);
			btnNuevo.setEnabled(true);
			// -------
			btnRegistrar.setEnabled(true);
			txtNombre.setEnabled(true);
			txtApellido.setEnabled(true);
			txtDNI.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtDireccion.setEnabled(true);
			txtFecha.setEnabled(true);
			btnActualizar.setEnabled(false);
			btnEliminar.setEnabled(false);
			break;
		case 1:			// Opcion Actualizar
			txtIDCliente.setText("");
			txtIDCliente.setEnabled(true);
			txtIDCliente.requestFocus();
			txtNombre.setText("");
			txtApellido.setText("");
			txtDNI.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
			txtFecha.setDate(null);
			btnActualizar.setEnabled(true);
			// -------
			txtNombre.setEnabled(true);
			txtApellido.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtFecha.setEnabled(true);
			txtDireccion.setEnabled(true);
			btnRegistrar.setEnabled(false);
			btnEliminar.setEnabled(false);
			txtDNI.setEnabled(true);
			break;
		default:		// Opcion Eliminar
			txtIDCliente.setText("");
			txtIDCliente.setEnabled(true);
			txtIDCliente.setEnabled(true);
			txtNombre.setText("");
			txtApellido.setText("");
			txtDNI.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
			txtFecha.setDate(null);
			btnEliminar.setEnabled(true);
			// -------
			txtNombre.setEnabled(false);
			txtApellido.setEnabled(false);
			txtDNI.setEnabled(false);
			txtTelefono.setEnabled(false);
			txtDireccion.setEnabled(false);
			txtFecha.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnRegistrar.setEnabled(false);
			break;
		}
	}
	
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		// Variables
		int dni, telefono;
		String codigo, nombre, apellido, direccion, fechanac;
		
		// Entradas
		codigo = leerCodigo();
		nombre = leerNombre();
		apellido = leerApellido();
		dni = leerDni();
		telefono = leerTelefono();
		direccion = leerDireccion();
		fechanac = leerFecha();
		
		if (codigo == null || nombre == null || apellido == null || dni == -1 || telefono == -1 || direccion == null || fechanac == null)
			return;
		
		Cliente u = new Cliente();
		u.setIdCliente(codigo);
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setDni(dni);
		u.setTelefono(telefono);
		u.setDireccion(direccion);
		u.setFechanac(fechanac);
		
		// Procesos
		GestionUsuario gu = new GestionUsuario();
		int ok = gu.registrar(u);
		// salidas
		if (ok == 0) {
			alerta("Error al registrar Cliente");
			return;
		}else {
			alerta("Cliente Registrado");
		}
		
		txtNombre.setText("");
		txtApellido.setText("");
		txtDNI.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtFecha.setCalendar(null);	
		llenarTabla();
	}

	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		String codigo;
		// Variables
		int telefono;
		String nombre, apellido, fechanac;
		
		// Entradas
		codigo = leerCodigo();
		nombre = leerNombre();
		apellido = leerApellido();
		telefono = leerTelefono();
		fechanac = leerFecha();
		
		Cliente u = new Cliente();
		u.setIdCliente(codigo);
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setTelefono(telefono);
		u.setFechanac(fechanac);
		
		// Proceso de Actualizar
		GestionUsuario gu = new GestionUsuario();
		int ok = gu.actualizarUsuario(u);
		
		// Salida
		if (ok == 0) {
			alerta("Error al Actualizar Cliente, Código no existente");
		} else {
			alerta("Cliente Actualizado");
		}
		
		llenarTabla();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		String codigo;
		
		// Entradas
		codigo = leerCodigo();
		
		Cliente u = new Cliente();
		u.setIdCliente(codigo);
		
		// Proceso de Eliminar
		GestionUsuario gu = new GestionUsuario();
		int ok = gu.eliminarUsuario(u);
		
		// Salida
		if (ok == 0) {
			alerta("Error al Eliminar Cliente, Código no existente");
		} else {
			alerta("Cliente Eliminado");
		}
		
		// Reseteamos el txtIDCliente
		txtIDCliente.setText("");
		txtIDCliente.requestFocus();
		
		llenarTabla();
	}
	
	// Ingreso
	private String leerFecha() {
		if(txtFecha.getDate() == null) {
			alerta("Debe ingresar Fecha de Nacimiento");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(txtFecha.getDate());
	}

	private String leerDireccion() {
		if (txtDireccion.getText().isEmpty()) {
			alerta("Debe ingresar una Dirección");
			return null;
		}
		return txtDireccion.getText();
	}

	private int leerTelefono() {
		if (txtTelefono.getText().equals("")) {
			alerta("Debe ingresar un número telefónico");
			return -1;
		}
		if (!txtTelefono.getText().matches("[9][0-9]{8}")) {
			alerta("El teléfono solo puede tener 9 dígitos");
			return -1;
		}
		return Integer.parseInt(txtTelefono.getText());
	}

	private int leerDni() {
		if (txtDNI.getText().equals("")) {
			alerta("Debe ingresar DNI");
			return -1;
		}
		if (!txtDNI.getText().matches("[0-9]{8}")) {
			alerta("El DNI solo puede tener 8 dígitos");
			return -1;
		}
		return Integer.parseInt(txtDNI.getText());
	}

	private String leerApellido() {
		if (txtApellido.getText().equals("")) {
			alerta("Ingresar Apellido del Cliente");
			return null;
		}
		return txtApellido.getText();
	}

	private String leerNombre() {
		if (txtNombre.getText().equals("")) {
			alerta("Ingrese Nombre del Cliente");
			return null;
		}
		return txtNombre.getText();
	}
	
	private String leerCodigo() {
		if (txtIDCliente.getText().equals("")) {
			alerta("Debe ingresar un código, para actualizar y/o eliminar");
			return null;
		}
		return txtIDCliente.getText();
	}
	
	
	// Eventos para obtener el valor de la tabla
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblSalida) {
			mouseClickedtblClientes(e);
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
	protected void mouseClickedtblClientes(MouseEvent e) {
		mostrarDatos();
	}
	public void keyPressed(KeyEvent e) {
	}
	protected void keyPressedTblClientes(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == tblSalida) {
			keyReleasedtblClientes(e);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyReleasedtblClientes(KeyEvent e) {
		mostrarDatos();
	}
	void mostrarDatos() {
		
		int posFila;
		String cod, nombre, apellido, dni, telefono, direccion;
		//obtener posición de la fila 
		posFila = tblSalida.getSelectedRow();
		
		//obtener valores de la fila actual
		cod = tblSalida.getValueAt(posFila, 0).toString();
		nombre = tblSalida.getValueAt(posFila, 1).toString();
		apellido = tblSalida.getValueAt(posFila, 2).toString();
		dni = tblSalida.getValueAt(posFila, 3).toString();
		telefono = tblSalida.getValueAt(posFila, 4).toString();
		direccion = tblSalida.getValueAt(posFila, 5).toString();
		//mostrar en la cajas el valor de los atributos del objeto "bean"
		txtIDCliente.setText(cod);
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtDNI.setText(dni);
		txtTelefono.setText(telefono);
		txtDireccion.setText(direccion);
	}
}
