package Grafica.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.Jugador.FrmPartidaJugador;
import Grafica.controladora.ControladoraPartidaJugador;
import ValueObjects.VOJugadorLogin;
import ValueObjects.VOPartida;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class FrmVisualizarPartida extends JFrame {
	
	private VOJugadorLogin credencial;
	private ControladoraPartidaJugador controladora;
	private JPanel contentPane;
	private JLabel lblCantidadIntentos;

	
	
	public FrmVisualizarPartida(VOJugadorLogin credencial) {
		
		this.credencial=credencial;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCantidadIntentos = new JLabel("New label");
		lblCantidadIntentos.setBounds(212, 60, 255, 31);
		
		contentPane.add(lblCantidadIntentos);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de Intentos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(29, 60, 130, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblFin = new JLabel("Finalizada?");
		lblFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFin.setBounds(29, 133, 130, 31);
		contentPane.add(lblFin);
		
		JLabel lblFinalizada = new JLabel("New label");
		lblFinalizada.setBounds(212, 133, 255, 31);
		contentPane.add(lblFinalizada);
		
		JLabel lblpunt = new JLabel("Puntaje Obtenido");
		lblpunt.setHorizontalAlignment(SwingConstants.CENTER);
		lblpunt.setBounds(29, 207, 130, 31);
		contentPane.add(lblpunt);
		
		JLabel lblPuntajeObtenido = new JLabel("New label");
		lblPuntajeObtenido.setBounds(212, 207, 255, 31);
		contentPane.add(lblPuntajeObtenido);
		
		
	}
	

	public void mostrarPartida(VOPartida partida) {
		lblCantidadIntentos.setText(partida.getVOCantIntentos()+"");
		
	}

	public void mensajeError(String message) {
		JOptionPane.showMessageDialog(this, message);
		
	}


}
