package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.toedter.calendar.JDateChooser;

import mantenimiento.GestionReportes;
import model.Reportes;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class FrmReportes extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblFecha;
	private JLabel lblIDCliente;
	private JTextField txtIDCliente;
	private JComboBox cboTipoReporte;
	private JButton btnReportFecha;
	private JTable tblSalida;
	
	
	private JDateChooser txtFecha;
	private JButton btnReportCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReportes frame = new FrmReportes();
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
	public FrmReportes() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reportes\r\n");
		setBounds(100, 100, 469, 108);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 43, 42, 14);
		contentPane.add(lblFecha);
		
		lblIDCliente = new JLabel("IDCliente");
		lblIDCliente.setBounds(10, 14, 62, 14);
		contentPane.add(lblIDCliente);
		
		txtIDCliente = new JTextField();
		txtIDCliente.setColumns(10);
		txtIDCliente.setBounds(72, 11, 110, 20);
		contentPane.add(txtIDCliente);
		
		cboTipoReporte = new JComboBox();
		cboTipoReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedCboTipoReporte(e);
			}
		});
		cboTipoReporte.setModel(new DefaultComboBoxModel(new String[] {"ID Cliente", "Fecha"}));
		cboTipoReporte.setToolTipText("");
		cboTipoReporte.setBounds(209, 11, 100, 23);
		contentPane.add(cboTipoReporte);
		
		btnReportFecha = new JButton("Reporte Fecha");
		btnReportFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnReportFecha(e);
			}
		});
		btnReportFecha.setBounds(321, 36, 122, 23);
		contentPane.add(btnReportFecha);
		
		txtFecha = new JDateChooser();
		txtFecha.setBounds(72, 39, 110, 20);
		contentPane.add(txtFecha);
		
		btnReportCliente = new JButton("Reporte Cliente");
		btnReportCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnReportCliente(e);
			}
		});
		btnReportCliente.setBounds(321, 11, 122, 23);
		contentPane.add(btnReportCliente);
		
		HabilitarEntradas();
	}
	private void HabilitarEntradas() {
		txtIDCliente.setEnabled(false);
		txtFecha.setEnabled(false);
		btnReportCliente.setEnabled(false);
		btnReportFecha.setEnabled(false);
	}

	protected void actionPerformedCboTipoReporte(ActionEvent e) {
		int opcion = cboTipoReporte.getSelectedIndex();
		switch (opcion) {
		case 0:
			txtIDCliente.setEnabled(true);
			txtIDCliente.setText("");
			txtIDCliente.requestFocus();
			btnReportCliente.setEnabled(true);
			txtFecha.setDate(null);
			txtFecha.setEnabled(false);
			btnReportFecha.setEnabled(false);
			break;
		default:
			txtIDCliente.setEnabled(false);
			btnReportCliente.setEnabled(false);
			txtIDCliente.setText("");
			txtFecha.setEnabled(true);
			btnReportFecha.setEnabled(true);
			txtFecha.setDate(null);
			break;
		}
	}
	protected void actionPerformedBtnReportCliente(ActionEvent e) {
		String codCliente = leerCodCliente();
		
		GestionReportes gr = new GestionReportes();
		ArrayList<Reportes> lista = gr.listado(codCliente);
		
		if (lista.size() == 0) {
			JOptionPane.showMessageDialog(this, "Listado Vacio");
			return;
		}
		
		String nomarchivo = "reportes/" + "Reporte de Ventas por CodCliente" + ".pdf";
		try {
			Document doc = new Document();
			FileOutputStream fos = new FileOutputStream(nomarchivo);
			PdfWriter pdfw = PdfWriter.getInstance(doc, fos);
			
			doc.open();
			
			
			Paragraph titulo = new Paragraph("REPORTE DE VENTAS DE " + leerCodCliente());
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			for (Reportes r : lista) {
				doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
				doc.add(new Paragraph("Código de boleta:               " + r.getCodbol()));
				doc.add(new Paragraph("Fecha de la Venta:             " + r.getFecha()));
				doc.add(new Paragraph("Nombre del Cliente:           " + r.getNomCliente()));
				doc.add(new Paragraph("Nombre del Vendedor:       " + r.getNomVendedor()));
				doc.add(new Paragraph("Monto Pagado:                   " + String.format("S/%.2f", r.getImporte())));
			}
			doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
			
			doc.close();
			
			Desktop.getDesktop().open(new File(nomarchivo));
			
		} catch (Exception e1) {
			alerta("Error al crear PDF: " + e1.getMessage());
		}
	}

	private void alerta(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void actionPerformedBtnReportFecha(ActionEvent e) {
		String fecha = leerFecha();
		
		GestionReportes gr = new GestionReportes();
		ArrayList<Reportes> lista = gr.listadoXFecha(fecha);
		
		if (lista.size() == 0) {
			JOptionPane.showMessageDialog(this, "Listado Vacio");
			return;
		}
		
		String nomarchivo = "reportes/" + "Reporte de Ventas por Fecha" + ".pdf";
		try {
			Document doc = new Document();
			FileOutputStream fos = new FileOutputStream(nomarchivo);
			PdfWriter pdfw = PdfWriter.getInstance(doc, fos);
			
			doc.open();
			
			
			Paragraph titulo = new Paragraph("REPORTE DE VENTAS " + leerFecha());
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			for (Reportes r : lista) {
				doc.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------------"));
				doc.add(new Paragraph("C�digo de boleta:               " + r.getCodbol()));
				doc.add(new Paragraph("Fecha de la Venta:             " + r.getFecha()));
				doc.add(new Paragraph("Nombre del Cliente:           " + r.getNomCliente()));
				doc.add(new Paragraph("Nombre del Vendedor:       " + r.getNomVendedor()));
				doc.add(new Paragraph("Monto Pagado:                   " + String.format("S/%.2f", r.getImporte())));
			}
			doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
			
			doc.close();
			
			Desktop.getDesktop().open(new File(nomarchivo));
			
		} catch (Exception e1) {
			alerta("Error al crear PDF: " + e1.getMessage());
		}
	}
	
	private String leerFecha() {
		if (txtFecha.getDate() == null) {
			alerta("Debe ingresar una fecha");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(txtFecha.getDate());
	}

	private String leerCodCliente() {
		if (txtIDCliente.getText().isEmpty()) {
			alerta("Debe ingresar un c�digo de cliente");
		}
		return txtIDCliente.getText();
	}
}
