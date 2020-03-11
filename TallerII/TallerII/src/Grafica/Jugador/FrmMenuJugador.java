package Grafica.Jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.admin.FrmListarJugadoresAdmin;
import Grafica.admin.FrmVisualizarPartida;
import Grafica.controladora.ControladoraMenuJugador;
import Logica.IFachada;
import ValueObjects.VOJugadorLogin;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMenuJugador extends JFrame {

	private JPanel contentPane;
	private VOJugadorLogin credencial;
	private ControladoraMenuJugador controladora;
	private FrmVisualizarPartida ventana;
	

	/**
	 * Create the frame.
	 */
	public FrmMenuJugador( VOJugadorLogin credencial) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.credencial=credencial;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPartidaJugador nuevaPartida=new FrmPartidaJugador(credencial);
				nuevaPartida.setVisible(true);
			}
		});
		btnNuevaPartida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevaPartida.setBounds(88, 59, 265, 32);
		contentPane.add(btnNuevaPartida);
		
		JButton btnVisualizar = new JButton("Visualizar Partida");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana = new FrmVisualizarPartida (credencial);
				ventana.setVisible(true);
				
			}
		});
		btnVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVisualizar.setBounds(88, 131, 265, 32);
		contentPane.add(btnVisualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVolver.setBounds(335, 218, 89, 32);
		contentPane.add(btnVolver);
	}


	public void iniciarPartida() {
		FrmPartidaJugador ventana=new FrmPartidaJugador(credencial);
		ventana.setVisible(true);
		this.setEnabled(false);
		
	}


	public void visualizarPartida() {
		FrmVisualizarPartida ventana=new FrmVisualizarPartida(credencial);
		ventana.setVisible(true);
		this.setEnabled(false);
		
	}

}
