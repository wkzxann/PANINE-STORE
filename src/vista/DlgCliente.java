package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionUsuario;
import model.Cliente;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class DlgCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblSalida;
	
	DefaultTableModel modelo = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	private JLabel lblCodigo;
	private JTextField txtCodCliente;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCliente dialog = new DlgCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCliente() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 224));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(10, 36, 414, 181);
			contentPanel.add(scrollPane);
			{
				tblSalida = new JTable();
				tblSalida.setModel(modelo);
				modelo.addColumn("Codigo");
				modelo.addColumn("Nombre Completo");
				modelo.addColumn("Dirección");
				// Cambiamos el Ancho de la columna nombre completo
				tblSalida.getColumnModel().getColumn(0).setPreferredWidth(0);
				tblSalida.getColumnModel().getColumn(1).setPreferredWidth(130);
				scrollPane.setViewportView(tblSalida);
			}
		}
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(10, 11, 46, 14);
		contentPanel.add(lblCodigo);
		
		txtCodCliente = new JTextField();
		txtCodCliente.setBounds(55, 8, 86, 20);
		contentPanel.add(txtCodCliente);
		txtCodCliente.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscar(e);
			}
		});
		btnBuscar.setBounds(335, 7, 89, 23);
		contentPanel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 224));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actionPerformedOkButton(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actionPerformedCancelButton(e);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		llenarTabla();
	}

	private void llenarTabla() {
		// Obtener un listado de los usuarios
		// Debemos llamar a la Gestion
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Cliente> lista = gu.listado();
				
		for (Cliente c : lista) {
			Object datos [] = {c.getIdCliente(), c.getNombre() + " " + c.getApellido(), c.getDireccion()};
			modelo.addRow(datos);	
		}
	}

	protected void actionPerformedOkButton(ActionEvent e) {
		// Obtener la fila seleccionada
		int fila = tblSalida.getSelectedRow();
		if (fila == -1){
			JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
			return;
		}
		FrmTransVentas.txtCodigoCliente.setText(tblSalida.getValueAt(fila, 0).toString());
		FrmTransVentas.txtNomCliente.setText(tblSalida.getValueAt(fila, 1).toString());
		FrmTransVentas.txtDirecCliente.setText(tblSalida.getValueAt(fila, 2).toString());
		dispose();
		FrmTransVentas.btnBuscarCliente.setEnabled(true);
		
	}
	protected void actionPerformedCancelButton(ActionEvent e) {
		FrmTransVentas.btnBuscarCliente.setEnabled(true);
		dispose();	
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		// Entradas
		String IdCliente;
		
		// Lectura
		IdCliente = leerCodCliente();
		
		GestionUsuario gu = new GestionUsuario();
		ArrayList<Cliente> lista = gu.listado(IdCliente);
		
		modelo.setRowCount(0);
		for (Cliente c : lista) {
			Object datos [] = {c.getIdCliente(), c.getNombre() + " " + c.getApellido(), c.getDireccion()};
			modelo.addRow(datos);
		}
		
	}

	private String leerCodCliente() {
		return txtCodCliente.getText();
	}
}
