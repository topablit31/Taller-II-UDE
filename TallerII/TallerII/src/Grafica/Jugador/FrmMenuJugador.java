package Grafica.Jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.IFachada;
import ValueObjects.VOJugadorLogin;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMenuJugador extends JFrame {

	private JPanel contentPane;
	private IFachada fachada;
	private VOJugadorLogin credencial;

	/**
	 * Create the frame.
	 */
	public FrmMenuJugador(IFachada fachada, VOJugadorLogin credencial) {
		this.credencial=credencial;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.fachada=fachada;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPartidaJugador nuevaPartida=new FrmPartidaJugador(fachada,credencial);
				nuevaPartida.setVisible(true);
			}
		});
		btnNuevaPartida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevaPartida.setBounds(88, 59, 265, 32);
		contentPane.add(btnNuevaPartida);
		
		JButton btnVisualizar = new JButton("Visualizar Partida");
		btnVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVisualizar.setBounds(88, 131, 265, 32);
		contentPane.add(btnVisualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVolver.setBounds(335, 218, 89, 32);
		contentPane.add(btnVolver);
	}

}
