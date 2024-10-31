package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;

import hilo.HiloBarra;

import javax.swing.event.ChangeEvent;
import java.awt.Toolkit;

public class FrmPreLoader extends JFrame {

	private JPanel contentPane;
	private JLabel lblMensajes;
	//private JProgressBar prbCarga;
	public static JProgressBar prbCarga;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPreLoader frame = new FrmPreLoader();
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
	public FrmPreLoader() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPreLoader.class.getResource("/img/172581_battery_charge_icon.png")));
		setResizable(false);
		setTitle("Cargando...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 84);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMensajes = new JLabel("El sistema est\u00E1 cargando, espere unos segundos");
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setForeground(Color.DARK_GRAY);
		lblMensajes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMensajes.setBounds(0, 5, 313, 14);
		contentPane.add(lblMensajes);
		
		prbCarga = new JProgressBar();
		prbCarga.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// Forma
				if(prbCarga.getValue() == 100) {
					FrmPrincipal v = new FrmPrincipal();
					v.setVisible(true);
					v.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		prbCarga.setFont(new Font("Tahoma", Font.PLAIN, 10));
		prbCarga.setStringPainted(true);
		prbCarga.setBounds(10, 23, 292, 17);
		contentPane.add(prbCarga);
		
		barraLlena();
	}

	private void barraLlena() {
		HiloBarra hb = new HiloBarra();
		hb.start();	
	}

}
