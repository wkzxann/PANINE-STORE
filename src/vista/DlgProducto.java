package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionProducto;
import model.Producto;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class DlgProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblSalida;
	
	DefaultTableModel modelo = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	private JLabel lblNombreProd;
	private JTextField txtNomProd;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgProducto dialog = new DlgProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProducto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.CYAN);
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
				modelo.addColumn("Código");
				modelo.addColumn("Nombre");
				modelo.addColumn("Precio");
				modelo.addColumn("Stock");
				// Cambiamos el Ancho de la columna nombre completo
				tblSalida.getColumnModel().getColumn(0).setPreferredWidth(35);
				tblSalida.getColumnModel().getColumn(1).setPreferredWidth(130);
				tblSalida.getColumnModel().getColumn(2).setPreferredWidth(40);
				tblSalida.getColumnModel().getColumn(3).setPreferredWidth(20);
				
				scrollPane.setViewportView(tblSalida);
			}
		}
		
		lblNombreProd = new JLabel("Nombre:");
		lblNombreProd.setBounds(10, 11, 55, 14);
		contentPanel.add(lblNombreProd);
		
		txtNomProd = new JTextField();
		txtNomProd.setBounds(72, 8, 86, 20);
		contentPanel.add(txtNomProd);
		txtNomProd.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBuscar(e);
			}
		});
		btnBuscar.setBounds(335, 8, 89, 23);
		contentPanel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 255, 255));
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
		GestionProducto gp = new GestionProducto();
		ArrayList<Producto> lista = gp.listado();
						
		for (Producto p : lista) {
			Object datos [] = {p.getIdProducto(), p.getNombre(), p.getPrecio(), p.getStock()};
			modelo.addRow(datos);
		}
		
	}

	protected void actionPerformedCancelButton(ActionEvent e) {
		FrmTransVentas.btnBuscarProducto.setEnabled(true);
		dispose();
	}
	protected void actionPerformedOkButton(ActionEvent e) {
		// Obtener la fila seleccionada
		int fila = tblSalida.getSelectedRow();
		if (fila == -1){
			JOptionPane.showMessageDialog(this, "Debe seleccionar un Producto");
			return;
		}
		FrmTransVentas.txtCodProd.setText(tblSalida.getValueAt(fila, 0).toString());
		FrmTransVentas.txtNombreProd.setText(tblSalida.getValueAt(fila, 1).toString());
		FrmTransVentas.txtPrecioProd.setText(tblSalida.getValueAt(fila, 2).toString());
		FrmTransVentas.txtStockProd.setText(tblSalida.getValueAt(fila, 3).toString());
		dispose();
		FrmTransVentas.btnBuscarProducto.setEnabled(true);
				
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		// Variable
		String nomprod;
		
		// Lectura
		nomprod = leerNomProd();
		
		GestionProducto gu = new GestionProducto();
		ArrayList<Producto> lista = gu.listadoXNombre(nomprod);
		
		modelo.setRowCount(0);
		for (Producto p : lista) {
			Object datos [] = {p.getIdProducto(), p.getNombre(), p.getPrecio(), p.getStock()};
			modelo.addRow(datos);
		}	
	}

	private String leerNomProd() {
		if (txtNomProd.getText().isEmpty()) {
			alerta("Ingrese Nombre del Producto");
		}
		return txtNomProd.getText();
	}

	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
