package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionPersonal;
import mantenimiento.GestionTipo;
import model.Personal;
import model.TipoPersonal;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmRegPersonal extends JInternalFrame implements ActionListener, MouseListener, KeyListener{

	private JPanel contentPane;
	private JLabel lblIdpersonal;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblTipo;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JTextField txtApePersonal;
	private JTextField txtNomPersonal;
	private JTextField txtIDPersonal;
	private JComboBox cboRegAcEli;
	private JButton btnRegistrar;
	private JButton btnNuevo;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JLabel lblClave;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JComboBox cboTipoPersonal;
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
					FrmRegPersonal frame = new FrmRegPersonal();
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
	public FrmRegPersonal() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Registrar - Actualizar - Eliminar Personal\r\n");
		setBounds(100, 100, 596, 439);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIdpersonal = new JLabel("IDPersonal");
		lblIdpersonal.setBounds(10, 14, 73, 14);
		contentPane.add(lblIdpersonal);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 39, 62, 14);
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 64, 62, 14);
		contentPane.add(lblApellido);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 89, 62, 14);
		contentPane.add(lblTipo);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setBounds(10, 114, 62, 14);
		contentPane.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setEnabled(false);
		txtDNI.setColumns(10);
		txtDNI.setBounds(82, 111, 100, 20);
		contentPane.add(txtDNI);
		
		txtApePersonal = new JTextField();
		txtApePersonal.addKeyListener(new KeyAdapter() {
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
		txtApePersonal.setEnabled(false);
		txtApePersonal.setColumns(10);
		txtApePersonal.setBounds(82, 61, 100, 20);
		contentPane.add(txtApePersonal);
		
		txtNomPersonal = new JTextField();
		txtNomPersonal.addKeyListener(new KeyAdapter() {
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
		txtNomPersonal.setEnabled(false);
		txtNomPersonal.setColumns(10);
		txtNomPersonal.setBounds(82, 36, 100, 20);
		contentPane.add(txtNomPersonal);
		
		txtIDPersonal = new JTextField();
		txtIDPersonal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				boolean numeros = tecla >= 48 && tecla <=57;
				
				if (!(numeros)) {
					e.consume();
				}
			}
		});
		txtIDPersonal.setEnabled(false);
		txtIDPersonal.setColumns(10);
		txtIDPersonal.setBounds(82, 11, 100, 20);
		contentPane.add(txtIDPersonal);
		
		cboRegAcEli = new JComboBox();
		cboRegAcEli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedCboRegAcEli(e);
			}
		});
		cboRegAcEli.setModel(new DefaultComboBoxModel(new String[] {"Registrar", "Actualizar", "Eliminar"}));
		cboRegAcEli.setToolTipText("");
		cboRegAcEli.setBounds(335, 10, 100, 23);
		contentPane.add(cboRegAcEli);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(FrmRegPersonal.class.getResource("/img/2931176_diskette_guardar_save_disk_drive_icon.png")));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnRegistrar(e);
			}
		});
		btnRegistrar.setBounds(455, 10, 115, 23);
		contentPane.add(btnRegistrar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(FrmRegPersonal.class.getResource("/img/134224_add_plus_new_icon.png")));
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNuevo(e);
			}
		});
		btnNuevo.setBounds(455, 85, 115, 23);
		contentPane.add(btnNuevo);
		
		lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(10, 139, 62, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(82, 136, 100, 20);
		contentPane.add(txtTelefono);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 164, 62, 14);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(82, 161, 100, 20);
		contentPane.add(txtUsuario);
		
		txtClave = new JTextField();
		txtClave.setEnabled(false);
		txtClave.setColumns(10);
		txtClave.setBounds(82, 186, 100, 20);
		contentPane.add(txtClave);
		
		lblClave = new JLabel("Clave");
		lblClave.setBounds(10, 189, 62, 14);
		contentPane.add(lblClave);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(FrmRegPersonal.class.getResource("/img/172618_update_icon.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(455, 35, 115, 23);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FrmRegPersonal.class.getResource("/img/2931168_bin_delete_remove_trash_garbage_icon.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(455, 60, 115, 23);
		contentPane.add(btnEliminar);
		
		cboTipoPersonal = new JComboBox();
		cboTipoPersonal.setBounds(82, 85, 100, 22);
		contentPane.add(cboTipoPersonal);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 214, 560, 183);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.addKeyListener(this);
		tblSalida.addMouseListener(this);
		tblSalida.setModel(modelo);
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Tipo");
		modelo.addColumn("DNI");
		modelo.addColumn("Teléfono");
		modelo.addColumn("Usuario");
		modelo.addColumn("Clave");
		tblSalida.getColumnModel().getColumn(0).setPreferredWidth(35);
		tblSalida.getColumnModel().getColumn(1).setPreferredWidth(105);
		scrollPane.setViewportView(tblSalida);
		
		HabilitarEntradas();
		llenaCombo();
		llenarTabla();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	private void llenarTabla() {
		GestionPersonal gu = new GestionPersonal();
		ArrayList<Personal> lista = gu.listado();
		
		// Pasamos el listado a una tabla
		modelo.setRowCount(0);
		for (Personal p : lista) {
			Object datos [] = {p.getIdPersonal(), p.getNombre(), p.getApellido(), p.getTipoPersonal(), p.getDni(), p.getTelefono(), p.getUsuario(), p.getClave()};
			modelo.addRow(datos);
		}
	}

	private void HabilitarEntradas() {
		txtIDPersonal.setEnabled(false);
		txtIDPersonal.setText("Autogenerado");
		txtNomPersonal.setEnabled(false);
		txtApePersonal.setEnabled(false);
		cboTipoPersonal.setEnabled(false);
		txtDNI.setEnabled(false);
		txtTelefono.setEnabled(false);
		txtUsuario.setEnabled(false);
		txtClave.setEnabled(false);
		btnRegistrar.setEnabled(false);
		btnActualizar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnNuevo.setEnabled(false);
	}

	protected void actionPerformedCboRegAcEli(ActionEvent e) {
		int opcion = cboRegAcEli.getSelectedIndex();
		switch (opcion) {
		case 0: 		// Opcion Registrar
			txtIDPersonal.setEnabled(false);
			txtIDPersonal.setText("Autogenerado");
			txtNomPersonal.setText("");
			txtApePersonal.setText("");
			cboTipoPersonal.setSelectedIndex(0);
			txtDNI.setText("");
			txtTelefono.setText("");
			txtUsuario.setText("");
			txtClave.setText("");
			// -------
			txtNomPersonal.setEnabled(true);
			txtNomPersonal.requestFocus();
			txtApePersonal.setEnabled(true);
			cboTipoPersonal.setEnabled(true);
			txtDNI.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtUsuario.setEnabled(true);
			txtClave.setEnabled(true);
			btnRegistrar.setEnabled(true);
			btnActualizar.setEnabled(false);
			btnEliminar.setEnabled(false);
			btnNuevo.setEnabled(true);
			break;
		case 1:			// Opcion Actualizar
			txtIDPersonal.setText("");
			txtIDPersonal.setEnabled(true);
			txtIDPersonal.requestFocus();
			txtNomPersonal.setText("");
			txtApePersonal.setText("");
			cboTipoPersonal.setSelectedIndex(0);
			txtDNI.setText("");
			txtTelefono.setText("");
			txtUsuario.setText("");
			txtClave.setText("");
			// -------
			txtNomPersonal.setEnabled(true);
			txtApePersonal.setEnabled(true);
			cboTipoPersonal.setEnabled(true);
			txtDNI.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtUsuario.setEnabled(true);
			txtClave.setEnabled(true);
			btnRegistrar.setEnabled(false);
			btnActualizar.setEnabled(true);
			btnEliminar.setEnabled(false);
			btnNuevo.setEnabled(true);
			break;
		default:		// Opcion Eliminar
			txtIDPersonal.setText("");
			txtIDPersonal.setEnabled(true);
			txtIDPersonal.setEnabled(true);
			txtNomPersonal.setText("");
			txtApePersonal.setText("");
			cboTipoPersonal.setSelectedIndex(0);
			txtDNI.setText("");
			txtTelefono.setText("");
			txtUsuario.setText("");
			txtClave.setText("");
			// -------
			txtNomPersonal.setEnabled(false);
			txtApePersonal.setEnabled(false);
			cboTipoPersonal.setEnabled(false);
			txtDNI.setEnabled(false);
			txtTelefono.setEnabled(false);
			txtUsuario.setEnabled(false);
			txtClave.setEnabled(false);
			btnRegistrar.setEnabled(false);
			btnActualizar.setEnabled(false);
			btnEliminar.setEnabled(true);
			btnNuevo.setEnabled(true);
			break;
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		// Varaibles
		int tipoPersonal, dni, telefono;
		String nombre, apellido, usuario, clave;
		
		// Entradas
		nombre = leerNombre();
		apellido = leerApellido();
		tipoPersonal = leerTipoPersonal();
		dni = leerDNI();
		telefono = leerTelefono();
		usuario = leerUsuario();
		clave = leerClave();
		
		if ( nombre == null|| apellido == null || tipoPersonal == -1 || dni == -1 || telefono == -1 || usuario == null || clave == null)
			return;
		
		Personal p = new Personal();
		p.setNombre(nombre);
		p.setApellido(apellido);
		p.setTipoPersonal(tipoPersonal);
		p.setDni(dni);
		p.setTelefono(telefono);
		p.setUsuario(usuario);
		p.setClave(clave);
		
		// Procesos
		GestionPersonal gp = new GestionPersonal();
		int ok = gp.registrar(p);
		// salidas
		if (ok == 0) {
			alerta("Error al registrar Personal");
		}else {
			alerta("Personal Registrado");
		}
		
		llenarTabla();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		// Variables
		int codigo, telefono, tipoPersonal;
		String nombre, apellido, clave;
				
		// Entradas
		codigo = leerCodigo();
		nombre = leerNombre();
		apellido = leerApellido();
		tipoPersonal = leerTipoPersonal();
		telefono = leerTelefono();
		clave = leerClave();
				
		Personal p = new Personal();
		p.setIdPersonal(codigo);
		p.setNombre(nombre);
		p.setApellido(apellido);
		p.setTipoPersonal(tipoPersonal);
		p.setTelefono(telefono);
		p.setClave(clave);
				
		// Proceso de Actualizar
		GestionPersonal gu = new GestionPersonal();
		int ok = gu.actualizarPersonal(p);
				
		// Salida
		if (ok == 0) {
			alerta("Error al Actualizar Personal >> Código no existente");
		} else {
			alerta("Personal Actualizado");
		}
		
		llenarTabla();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		// Variable
		int codigo;
		
		// Entradas
		codigo = leerCodigo();
		
		Personal p = new Personal();
		p.setIdPersonal(codigo);
		
		// Proceso de Eliminar
		GestionPersonal gp = new GestionPersonal();
		int ok = gp.eliminarPersonal(p);
		
		// Salida
		if (ok == 0) {
			alerta("Error al Eliminar Personal >> Código no existente");
		} else {
			alerta("Personal Eliminado");
		}
		
		// Reseteamos el txtIDPersonal
		txtIDPersonal.setText("");
		txtIDPersonal.requestFocus();
		
		llenarTabla();	
	}

	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	void llenaCombo() {
		// Obtener un listado de las categorias, usando metodos gestion
		GestionTipo gt = new GestionTipo();
		ArrayList<TipoPersonal> lista = gt.listado();
		// Pasar el listado al combo
		cboTipoPersonal.addItem("Seleccione...");
		for (TipoPersonal tp : lista) {
			cboTipoPersonal.addItem(tp.getDescripcion());
		}
	}
	
	protected void actionPerformedBtnNuevo(ActionEvent e) {
		txtIDPersonal.setText("Autogenerado");
		txtNomPersonal.setText("");
		txtApePersonal.setText("");
		txtDNI.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
		txtClave.setText("");
		txtIDPersonal.requestFocus();
	}
	
	private String leerClave() {
		if (txtClave.getText().equals("")) {
			alerta("Debe ingresar una Clave");
			return null;
		}
		return txtClave.getText();
	}

	private String leerUsuario() {
		if (txtUsuario.getText().equals("")) {
			alerta("Debe ingresar un Usuario");
			return null;
		}
		return txtUsuario.getText();
	}

	private String leerApellido() {
		if (txtApePersonal.getText().equals("")) {
			alerta("Ingresar Apellido del Cliente");
			return null;
		}
		return txtApePersonal.getText();
	}

	private String leerNombre() {
		if (txtNomPersonal.getText().equals("")) {
			alerta("Ingrese Nombre del Cliente");
			return null;
		}
		return txtNomPersonal.getText();
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

	private int leerDNI() {
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

	private int leerTipoPersonal() {
		if (cboTipoPersonal.getSelectedIndex() == 0) {
			alerta("Tipo no válido, debe seleccionar un tipo");
			return -1;
		}
		return cboTipoPersonal.getSelectedIndex();
	}
	private int leerCodigo() {
		if (txtIDPersonal.getText().equals("")) {
			alerta("Debe ingresar un código, para actualizar y/o eliminar");
			return -1;
		}
		return Integer.parseInt(txtIDPersonal.getText());
	}

	// Eventos para obtener el valor de la tabla
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tblSalida) {
				mouseClickedtblPostulante(e);
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
		protected void mouseClickedtblPostulante(MouseEvent e) {
			mostrarDatos();
		}
		public void keyPressed(KeyEvent e) {
		}
		protected void keyPressedTblPostulante(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == tblSalida) {
				keyReleasedtblPostulante(e);
			}
		}
		public void keyTyped(KeyEvent e) {
		}
		protected void keyReleasedtblPostulante(KeyEvent e) {
			mostrarDatos();
		}
		void mostrarDatos() {
			
			int posFila;
			String nombre, apellido, tipo, dni, telefono, usuario, clave;
			//obtener posición de la fila 
			posFila = tblSalida.getSelectedRow();
			
			//obtener valores de la fila actual
			nombre = tblSalida.getValueAt(posFila, 1).toString();
			apellido = tblSalida.getValueAt(posFila, 2).toString();
			dni = tblSalida.getValueAt(posFila, 4).toString();
			telefono = tblSalida.getValueAt(posFila, 5).toString();
			usuario = tblSalida.getValueAt(posFila, 6).toString();
			clave = tblSalida.getValueAt(posFila, 7).toString();
			
			//mostrar en la cajas el valor de los atributos del objeto "bean"
			txtNomPersonal.setText(nombre);
			txtApePersonal.setText(apellido);
			txtDNI.setText(dni);
			txtTelefono.setText(telefono);
			txtUsuario.setText(usuario);
			txtClave.setText(clave);
		}
}
