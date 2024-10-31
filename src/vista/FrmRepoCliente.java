package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import mantenimiento.GestionUsuario;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

public class FrmRepoCliente extends JInternalFrame {
	

	private JPanel contentPane;
	private JLabel lblIDCliente;
	private JTextField txtIDCliente;
	private JButton btnConsultar;
	private JScrollPane scrollPane;
	private JTable tblSalida;
	private JButton btnListarAll;
	private JComboBox cboTipoConsulta;
	
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
					FrmRepoCliente frame = new FrmRepoCliente();
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
	public FrmRepoCliente() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consultar Clientes Registrados\r\n");
		setBounds(100, 100, 465, 253);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(189, 183, 107));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIDCliente = new JLabel("IDCliente");
		lblIDCliente.setBounds(10, 14, 63, 14);
		contentPane.add(lblIDCliente);
		
		txtIDCliente = new JTextField();
		txtIDCliente.setBounds(73, 11, 109, 20);
		contentPane.add(txtIDCliente);
		txtIDCliente.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(new ImageIcon(FrmRepoCliente.class.getResource("/img/9042816_page_search_icon.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnConsultar(e);
			}
		});
		btnConsultar.setBounds(329, 11, 110, 23);
		contentPane.add(btnConsultar);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 95, 429, 117);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		scrollPane.setViewportView(tblSalida);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre Completo");
		modelo.addColumn("DNI");
		modelo.addColumn("Teléfono");
		tblSalida.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblSalida.getColumnModel().getColumn(1).setPreferredWidth(150);
		scrollPane.setViewportView(tblSalida);
		
		btnListarAll = new JButton("Listar All");
		btnListarAll.setIcon(new ImageIcon(FrmRepoCliente.class.getResource("/img/9042816_page_search_icon.png")));
		btnListarAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnListarAll(e);
			}
		});
		btnListarAll.setBounds(329, 36, 110, 23);
		contentPane.add(btnListarAll);
		
		cboTipoConsulta = new JComboBox();
		cboTipoConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedComboBox(e);
			}
		});
		cboTipoConsulta.setModel(new DefaultComboBoxModel(new String[] {"Por C\u00F3digo", "Listar All"}));
		cboTipoConsulta.setBounds(210, 10, 109, 23);
		contentPane.add(cboTipoConsulta);
		
		habilitarEntradas();
	}
	private void habilitarEntradas() {
		txtIDCliente.setEnabled(false);
		btnConsultar.setEnabled(false);
		btnListarAll.setEnabled(false);
	}

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		// Entradas
		String IdCliente;
		
		// Leer
		IdCliente = leerIDCliente();
		
		// Gestion
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Cliente> lista = gu.listado(IdCliente);
		
		// Pasamos el listado auna tabla
		modelo.setRowCount(0);
		for (Cliente c : lista) {
			Object datos [] = {c.getIdCliente(), c.getNombre() + " " + c.getApellido(), c.getDni(), c.getTelefono()};
			modelo.addRow(datos);
		}
		
	}

	protected void actionPerformedBtnListarAll(ActionEvent e) {
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Cliente> lista = gu.listado();
		
		// Pasamos el listado a una tabla
		modelo.setRowCount(0);
		for (Cliente c : lista) {
			Object datos [] = {c.getIdCliente(), c.getNombre() + " " + c.getApellido(), c.getDni(), c.getTelefono()};
			modelo.addRow(datos);
		}
	}

	protected void actionPerformedComboBox(ActionEvent e) {
		int opcion = cboTipoConsulta.getSelectedIndex();
		switch (opcion) {
		case 0:      // Consulta por ID del Cliente
			txtIDCliente.setEnabled(true);
			txtIDCliente.setText("");
			txtIDCliente.requestFocus();
			btnConsultar.setEnabled(true);
			btnListarAll.setEnabled(false);
			break;
		default:		 // Consultar todos los productos
			txtIDCliente.setEnabled(false);
			txtIDCliente.setText("");
			btnConsultar.setEnabled(false);
			btnListarAll.setEnabled(true);
			break;
		}
	}
	private String leerIDCliente() {
		if (txtIDCliente.getText().isEmpty()) {
			alerta("Ingrese el ID del Cliente");
			return null;
		}
		return txtIDCliente.getText();
	}

	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
