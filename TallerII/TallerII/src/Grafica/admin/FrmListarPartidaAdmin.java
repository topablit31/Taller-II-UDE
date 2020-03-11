package Grafica.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.controladora.ControladoraListarPartidaAdmin;
import Logica.IFachada;
import Logica.JugadorException;
import Logica.PartidaException;
import ValueObjects.VOPartida;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmListarPartidaAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreJugador;
	private FrmMenuAdmin menu;
	private JList listPartidas;
	private ControladoraListarPartidaAdmin controladora;

	/**
	 * Create the frame.
	 */
	public FrmListarPartidaAdmin(FrmMenuAdmin menu) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menu.setEnabled(true);
				dispose();
			}
		});
		controladora=new ControladoraListarPartidaAdmin(this);
		this.menu=menu;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombreJugador = new JTextField();
		txtNombreJugador.setBounds(28, 40, 256, 33);
		contentPane.add(txtNombreJugador);
		txtNombreJugador.setColumns(10);
		
		JLabel lblJugador = new JLabel("JUGADOR");
		lblJugador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJugador.setBounds(28, 15, 151, 14);
		contentPane.add(lblJugador);
		
		listPartidas = new JList();
		listPartidas.setBounds(28, 103, 256, 272);
		contentPane.add(listPartidas);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombreJugador.getText().trim();
				controladora.buscar(nombre);
			
				
			}
		});
		btnBuscar.setBounds(329, 40, 110, 33);
		contentPane.add(btnBuscar);
		
		
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setEnabled(true);
				dispose();
			}
		});
		btnVolver.setBounds(435, 342, 110, 33);
		contentPane.add(btnVolver);
	}

	public void listarPartida(Object[] partidas) {
		listPartidas.setListData(partidas);
		
	}

	public void errorMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
		
	}

}
