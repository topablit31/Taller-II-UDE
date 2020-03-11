package Grafica.Jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.admin.FrmMenuAdmin;
import Grafica.controladora.ControladoraMenuAdmin;
import Grafica.controladora.ControladoraMenuJugadorLogin;
import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class FrmMenuJugadorLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textCodigo;
	private ControladoraMenuJugadorLogin controladora;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmMenuJugadorLogin().setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMenuJugadorLogin() {
		
		controladora=new ControladoraMenuJugadorLogin(this);
	
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(28, 47, 68, 30);
		contentPane.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(112, 49, 256, 28);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textCodigo = new JTextField();
		textCodigo.setColumns(10);
		textCodigo.setBounds(112, 98, 256, 30);
		contentPane.add(textCodigo);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCodigo.setBounds(28, 98, 68, 30);
		contentPane.add(lblCodigo);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
				
			}

			private void login() {
				String nombre=textUsuario.getText();
				String codigo=textCodigo.getText();
				VOJugadorLogin login=new VOJugadorLogin(nombre, codigo);
				
				
					if(controladora.logueoOK(login)) {
						FrmMenuJugador menu=new FrmMenuJugador(login);
						menu.setVisible(true);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "usuario y/o contraseña equivocada");
						textUsuario.setText("");
						textCodigo.setText("");
					}
				} 
		});
		btnLogin.setBounds(386, 177, 89, 39);
		contentPane.add(btnLogin);
	}



}
