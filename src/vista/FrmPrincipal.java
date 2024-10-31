package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.HiloReloj;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnSistema;
	private JMenuItem mntmCerrar;
	private JMenu mnMantenimiento;
	private JMenu mnTransaccion;
	private JMenu mnReportes;
	private JMenuItem mntmManteCliente;
	private JMenuItem mntmConsuCliente;
	private JMenuItem mntmConsuProductos;
	private JMenuItem mntmManteProducto;
	private JMenuItem mntmTransaccion;
	private JMenuItem mntmMantePersonal;
	private JMenuItem mntmReportes;
	private JMenu mnConsultas;
	private JDesktopPane escritorio;
	public static JLabel lblReloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setTitle("Bienvenido: " + FrmLogin.p.getNombre() + " " + FrmLogin.p.getApellido());
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/img/352110_daydream_system_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 638);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSistema = new JMenu("Sistema");
		mnSistema.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/3668839_gear_maintenance_service_icon.png")));
		menuBar.add(mnSistema);
		
		mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmCerrar(e);
			}
		});
		mntmCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnSistema.add(mntmCerrar);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/299093_window_system_icon.png")));
		menuBar.add(mnMantenimiento);
		
		mntmManteCliente = new JMenuItem("Cliente\r\n");
		mntmManteCliente.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/6372982_account_client_costumers_customer_profile_icon.png")));
		mntmManteCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmManteCliente(e);
			}
		});
		mnMantenimiento.add(mntmManteCliente);
		
		mntmManteProducto = new JMenuItem("Producto");
		mntmManteProducto.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/605509_filter_goods_item_label_product_icon.png")));
		mntmManteProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmManteProducto(e);
			}
		});
		mnMantenimiento.add(mntmManteProducto);
		
		mntmMantePersonal = new JMenuItem("Personal");
		mntmMantePersonal.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/4137175_building_construction_industry_worker_icon.png")));
		mntmMantePersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmPersonal(e);
			}
		});
		mnMantenimiento.add(mntmMantePersonal);
		
		mnConsultas = new JMenu("Consultas");
		mnConsultas.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/3709749_advice_consult_mobile_service_system_icon.png")));
		menuBar.add(mnConsultas);
		
		mntmConsuCliente = new JMenuItem("Clientes");
		mntmConsuCliente.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/477135_support_client_communication_user_connect_icon.png")));
		mnConsultas.add(mntmConsuCliente);
		
		mntmConsuProductos = new JMenuItem("Productos");
		mntmConsuProductos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/49602_freight_packing_products_shipment_trucking_icon.png")));
		mnConsultas.add(mntmConsuProductos);
		mntmConsuProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmConsuProductos(e);
			}
		});
		mntmConsuCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmConsuCliente(e);
			}
		});
		
		mnTransaccion = new JMenu("Transacci\u00F3n");
		mnTransaccion.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/7007515_payment_money_finance_transaction_business_icon.png")));
		menuBar.add(mnTransaccion);
		
		mntmTransaccion = new JMenuItem("Venta");
		mntmTransaccion.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/1034365_sale_buy_price_tag_icon.png")));
		mntmTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmTransaccion(e);
			}
		});
		mnTransaccion.add(mntmTransaccion);
		
		mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/2303181_bar_chart_diagram_line_report_icon.png")));
		menuBar.add(mnReportes);
		
		mntmReportes = new JMenuItem("Ventas");
		mntmReportes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/49617_report_sales_icon.png")));
		mntmReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedMntmReportes(e);
			}
		});
		mnReportes.add(mntmReportes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(240, 255, 240));
		contentPane.add(escritorio, BorderLayout.CENTER);
		
		// Códgio del reloj
		JInternalFrame reloj = new JInternalFrame("Hora");
		reloj.setBounds(10, 11, 105, 55);
		escritorio.add(reloj);
				
		lblReloj = new JLabel("00:00:00");
		reloj.getContentPane().add(lblReloj, BorderLayout.CENTER);
		reloj.setVisible(true);
		
		reloj();
		//
		switch (FrmLogin.p.getTipoPersonal()) {
		case 2:		// Cajero
			mntmConsuProductos.setEnabled(false);
			mntmConsuCliente.setEnabled(false);
			mntmMantePersonal.setEnabled(false);
			mntmManteProducto.setEnabled(false);
			break;
		default:
			break;
		}
	}
	void reloj() {
		HiloReloj hr = new HiloReloj();
		hr.start();
	}

	// Boton Cerrar
	protected void actionPerformedMntmCerrar(ActionEvent e) {
		System.exit(0);
	}
	// Abrir ventana Mantenimiento - Clientes
	protected void actionPerformedMntmManteCliente(ActionEvent e) {
		FrmRegCliente rc = new FrmRegCliente();
		rc.setVisible(true);
		escritorio.add(rc);
		
	}
	// Abrir ventana Mantenimiento - Productos
	protected void actionPerformedMntmManteProducto(ActionEvent e) {
		FrmRegProducto rp = new FrmRegProducto();
		rp.setVisible(true);
		escritorio.add(rp);
	}
	// Abrir ventana Mantenimiento - Personal
	protected void actionPerformedMntmPersonal(ActionEvent e) {
		FrmRegPersonal frp = new FrmRegPersonal();
		frp.setVisible(true);
		escritorio.add(frp);
	}
	// Abrir ventana Consultas - Clientes
	protected void actionPerformedMntmConsuCliente(ActionEvent e) {
		FrmRepoCliente cc = new FrmRepoCliente();
		cc.setVisible(true);
		escritorio.add(cc);
	}
	// Abrir ventana Consultas - Productos
	protected void actionPerformedMntmConsuProductos(ActionEvent e) {
		FrmRepoProducto cp = new FrmRepoProducto();
		cp.setVisible(true);
		escritorio.add(cp);
	}
	// Abrir ventana Transaccion - Ventas
	protected void actionPerformedMntmTransaccion(ActionEvent e) {
		FrmTransVentas tv = new FrmTransVentas();
		tv.setVisible(true);
		escritorio.add(tv);
	}
	// Abrir ventana Reportes
	protected void actionPerformedMntmReportes(ActionEvent e) {
		FrmReportes r = new FrmReportes();
		r.setVisible(true);
		escritorio.add(r);
	}
}
