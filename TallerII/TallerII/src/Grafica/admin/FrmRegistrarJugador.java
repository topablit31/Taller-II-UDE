package Grafica.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.controladora.ControladoraRegistrarJugador;
import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorLogin;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmRegistrarJugador extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtCodigo;
	private JButton btnRegistrar;
	private FrmRegistrarJugador ventana;
	private ControladoraRegistrarJugador controladora;
	private JButton btnVolver;
	private FrmMenuAdmin menu;
	/**
	 * Create the frame.
	 */
	public FrmRegistrarJugador(FrmMenuAdmin menu) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				menu.setEnabled(true);
				dispose();
			}
		});
		setTitle("LOGIN");
		ventana=this;
		this.menu=menu;
		controladora=new ControladoraRegistrarJugador(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 414, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(152, 11, 206, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(152, 51, 206, 29);
		contentPane.add(txtCodigo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(30, 18, 84, 22);
		contentPane.add(lblUsuario);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(30, 58, 84, 22);
		contentPane.add(lblCodigo);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registar();
				
				
			}
		});
		btnRegistrar.setBounds(282, 130, 89, 23);
		contentPane.add(btnRegistrar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setEnabled(true);
				dispose();
			}
		});
		btnVolver.setBounds(30, 130, 89, 23);
		contentPane.add(btnVolver);
	}
	
	public void registar() {
		
			String nombre=txtUsuario.getText().trim();
			String codigo=txtCodigo.getText();
			
			if(nombre.equals(""))
			{
				JOptionPane.showMessageDialog(ventana,"Ingrese el nombre, por favor");
			}
			else {
				if(codigo.equals(""))
				{
					JOptionPane.showMessageDialog(ventana,"Ingrese el codigo, por favor");
				}
				else {
					VOJugadorLogin registrar=new VOJugadorLogin(nombre, codigo);
					controladora.registrarNuevoJugador(registrar);
					
					
					
				}
			}
			
			
		
		
		
		
	}

	public void errorMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
		
	}

	public void ok() {
		JOptionPane.showMessageDialog(this, "Se registro correctamente");
		txtCodigo.setText("");
		txtUsuario.setText("");
		
	}
}
