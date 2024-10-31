package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopIconUI;

import hilo.HiloConteo;
import mantenimiento.GestionPersonal;
import mantenimiento.GestionUsuario;
import model.Personal;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Color;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JLabel lblMensaje;
	private JLabel lblIcon;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JTextField txtUsuario;
	private JButton btnIngresar;
	private JButton btnSalir;
	//private JLabel lblTiempo;
	public static JLabel lblTiempo;
	int errores;
	private JPasswordField txtClave;
	
	void conteo() {		// M�todo que va realizar la cuenta de 60 a 0
		HiloConteo h = new HiloConteo(this);
		h.start();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/img/309058_key_login_private_protect_protection_icon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				conteo();
			}
		});
		setTitle("PanineStore - Acceso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 199);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setLocationRelativeTo(null);
		
		lblMensaje = new JLabel("La ventana se cerrar\u00E1 en:\r\n");
		lblMensaje.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		lblMensaje.setBounds(130, 11, 156, 14);
		contentPane.add(lblMensaje);
		
		lblTiempo = new JLabel("40s");
		lblTiempo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		lblTiempo.setBounds(281, 11, 46, 14);
		contentPane.add(lblTiempo);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/5402435_account_profile_user_avatar_man_icon.png")));
		lblIcon.setBounds(10, 34, 125, 115);
		contentPane.add(lblIcon);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(145, 61, 81, 14);
		contentPane.add(lblUsuario);
		
		lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(145, 88, 81, 14);
		contentPane.add(lblContrasena);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(222, 58, 100, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnIngresar(e);
			}
		});
		btnIngresar.setBounds(145, 126, 89, 23);
		contentPane.add(btnIngresar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnSalir(e);
			}
		});
		btnSalir.setBounds(238, 126, 89, 23);
		contentPane.add(btnSalir);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(222, 81, 100, 20);
		contentPane.add(txtClave);
		
	} // Fin del constructor de la clase
	
	public static Personal p = new Personal();
	
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		String usuario, clave;
		
		usuario = leerUsuario();
		clave = leerClave();
		

		GestionPersonal gp = new GestionPersonal();
		p = gp.validarAcceso(usuario, clave);;
		
		if (p == null) {
			aviso("Usuario o clave incorrectos");
		} else {
			aviso("Bienvenido " + p.getNombre());
			FrmPreLoader fp = new FrmPreLoader();
			fp.setVisible(true);
			dispose();
		}
	}

	private void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
	}

	private String leerClave() {
		return txtClave.getText();
	}

	private String leerUsuario() {
		return txtUsuario.getText();
	}

	protected void actionPerformedBtnSalir(ActionEvent e) {
		System.exit(0);
	}
}
