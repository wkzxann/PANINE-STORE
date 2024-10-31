package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionProducto;
import mantenimiento.GestionTipo;
import mantenimiento.GestionUsuario;
import model.Cliente;
import model.Producto;
import model.TipoPersonal;
import model.TipoProducto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
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

public class FrmRepoProducto extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblIDProducto;
	private JScrollPane scrollPane;
	private JButton btnConsultar;
	private JTable tblSalida;
	
	DefaultTableModel model = new DefaultTableModel();
	private JButton btnListAll;
	private JComboBox cboTipoConsulta;
	private JComboBox cboTipoProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRepoProducto frame = new FrmRepoProducto();
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
	public FrmRepoProducto() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consultar Productos Registrados\r\n");
		setBounds(100, 100, 508, 274);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIDProducto = new JLabel("Tipo de Producto:");
		lblIDProducto.setBounds(10, 14, 105, 14);
		contentPane.add(lblIDProducto);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 97, 472, 136);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		scrollPane.setViewportView(tblSalida);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(new ImageIcon(FrmRepoProducto.class.getResource("/img/9042816_page_search_icon.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnConsultar(e);
			}
		});
		btnConsultar.setBounds(366, 11, 116, 23);
		contentPane.add(btnConsultar);
		
		tblSalida = new JTable();
		tblSalida.setModel(model);
		model.addColumn("ID Producto");
		model.addColumn("Nombre");
		model.addColumn("Categoría");
		model.addColumn("Precio");
		model.addColumn("Stock");
		tblSalida.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblSalida.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblSalida.getColumnModel().getColumn(2).setPreferredWidth(60);
		tblSalida.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblSalida.getColumnModel().getColumn(4).setPreferredWidth(40);
		scrollPane.setViewportView(tblSalida);
		
		btnListAll = new JButton("List All");
		btnListAll.setIcon(new ImageIcon(FrmRepoProducto.class.getResource("/img/9042816_page_search_icon.png")));
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnListAll(e);
			}
		});
		btnListAll.setBounds(366, 37, 116, 23);
		contentPane.add(btnListAll);
		
		cboTipoConsulta = new JComboBox();
		cboTipoConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedCboTipoConsulta(e);
			}
		});
		cboTipoConsulta.setModel(new DefaultComboBoxModel(new String[] {"Tipo Producto", "Listar All"}));
		cboTipoConsulta.setBounds(240, 11, 116, 23);
		contentPane.add(cboTipoConsulta);
		
		cboTipoProducto = new JComboBox();
		cboTipoProducto.setBounds(113, 10, 105, 22);
		contentPane.add(cboTipoProducto);
		
		habilitarEntradas();
		llenaCombo();
	}
	void llenaCombo() {
		// Obtener un listado de las categorias, usando metodos gestion
		GestionTipo gt = new GestionTipo();
		ArrayList<TipoProducto> lista = gt.listadoTipoProducto();
		// Pasar el listado al combo
		cboTipoProducto.addItem("Seleccione...");
		for (TipoProducto tp : lista) {
			cboTipoProducto.addItem(tp.getDescripcion());
		}
	}

	private void habilitarEntradas() {
		cboTipoProducto.setEnabled(false);
		btnConsultar.setEnabled(false);
		btnListAll.setEnabled(false);	
	}
	protected void actionPerformedCboTipoConsulta(ActionEvent e) {
		int opcion = cboTipoConsulta.getSelectedIndex();
		switch (opcion) {
		case 0:      // Consulta por ID del Cliente
			cboTipoProducto.setEnabled(true);
			cboTipoProducto.setSelectedIndex(0);
			btnConsultar.setEnabled(true);
			btnListAll.setEnabled(false);
			break;
		default:		 // Consultar todos los productos
			cboTipoProducto.setEnabled(false);
			cboTipoProducto.setSelectedIndex(0);
			btnConsultar.setEnabled(false);
			btnListAll.setEnabled(true);
			break;
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		// Entradas
		int tipo;
				
		// Leer la entrada
		tipo = leerTipo();
				
		// Gestion
		GestionProducto gp = new GestionProducto();
		ArrayList<Producto> lista = gp.listado(tipo);
		
		
		// Pasamos el listado a una tabla
		model.setRowCount(0);
		for (Producto p : lista) {
			Object datos [] = {p.getIdProducto(), p.getNombre(), p.getCategoria(), "S/. " + p.getPrecio(), p.getStock()};
			model.addRow(datos);
		}
	}

	protected void actionPerformedBtnListAll(ActionEvent e) {
		GestionProducto gp = new GestionProducto();
		ArrayList<Producto> lista = gp.listado();
		
		// Pasamos el listado a una tabla
		model.setRowCount(0);
		for (Producto p : lista) {
			Object datos [] = {p.getIdProducto(), p.getNombre(), p.getCategoria(), "S/. " + p.getPrecio(), p.getStock()};
			model.addRow(datos);
		}
	}
	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private int leerTipo() {
		if (cboTipoProducto.getSelectedIndex() == 0) {
			alerta("Tipo no válido, debe seleccionar un tipo");
			return -1;
		}
		return cboTipoProducto.getSelectedIndex();
	}

}
