package Grafica.Jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.admin.FrmMenuAdmin;
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
	private IFachada fachada;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFachada fachada;

					try {
						
						fachada = (IFachada) Naming.lookup("//localhost:1099/fachada");
						FrmMenuJugadorLogin frame = new FrmMenuJugadorLogin(fachada);
						frame.setVisible(true);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMenuJugadorLogin(IFachada fachada) {
		this.fachada=fachada;
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
				String nombre=textUsuario.getText();
				String codigo=textCodigo.getText();
				VOJugadorLogin login=new VOJugadorLogin(nombre, codigo);
				try {
					if(fachada.logearseParaJugar(login)) {
						FrmMenuJugador menu=new FrmMenuJugador(fachada,login);
						menu.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "usuario y/o contrasenia equivocada");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JugadorException e1) {
					JOptionPane.showMessageDialog(null, e1.getMensaje());
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(386, 177, 89, 39);
		contentPane.add(btnLogin);
	}

}
