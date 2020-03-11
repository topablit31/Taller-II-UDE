package Grafica.Jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.controladora.ControladoraPartidaJugador;
import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorLogin;
import ValueObjects.VOPartida;
import ValueObjects.VOPartidaEsMayorMenor;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmPartidaJugador extends JFrame {

	private JPanel contentPane;
	private JTextField textNumeroIngresado;
	private VOJugadorLogin credencial;

	private ControladoraPartidaJugador controladora;
	private JLabel lblNumero;
	/**
	 * Create the frame.
	 */
	public FrmPartidaJugador(VOJugadorLogin credencial) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int resultado=JOptionPane.showConfirmDialog(null, "Desea abandonar la partida?","Sistema...",JOptionPane.YES_NO_OPTION);
				if(resultado==JOptionPane.YES_OPTION)
				{
						controladora.abandonarPartida(credencial);
						dispose();
					
				}
				
			}
		});
	
		controladora=new ControladoraPartidaJugador(this);
		controladora.iniciarNuevaPartida(credencial);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 636, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNumero = new JLabel("?");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumero.setBounds(141, 32, 58, 35);
		contentPane.add(lblNumero);
		
		JLabel lblNumeroSecreto = new JLabel("Numero Secreto");
		lblNumeroSecreto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumeroSecreto.setBounds(10, 32, 121, 35);
		contentPane.add(lblNumeroSecreto);
		
		JLabel lblJuego = new JLabel("JUEGO");
		lblJuego.setForeground(Color.GREEN);
		lblJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblJuego.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJuego.setBounds(159, 109, 297, 35);
		contentPane.add(lblJuego);
		
		textNumeroIngresado = new JTextField();
		textNumeroIngresado.setBounds(261, 176, 156, 20);
		contentPane.add(textNumeroIngresado);
		textNumeroIngresado.setColumns(10);
		
		JLabel lblIngresarNumero = new JLabel("Ingresar Numero");
		lblIngresarNumero.setBounds(137, 178, 114, 17);
		contentPane.add(lblIngresarNumero);
		
		JButton btnIntentar = new JButton("Intentar");
		btnIntentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!textNumeroIngresado.getText().trim().equals(""))
				{
					int numero=Integer.parseInt(textNumeroIngresado.getText());
					
					controladora.realizarUnIntento(credencial, numero);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Se debe ingresar un numero");
				}
				
			} 
		});
		btnIntentar.setForeground(new Color(0, 100, 0));
		btnIntentar.setBounds(461, 175, 89, 23);
		contentPane.add(btnIntentar);
		
		JButton btnAbandonar = new JButton("Abandonar");
		btnAbandonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					controladora.abandonarPartida(credencial);
					
			
				
			} 
			
		});
		btnAbandonar.setForeground(Color.RED);
		btnAbandonar.setBounds(461, 230, 89, 23);
		contentPane.add(btnAbandonar); 
	}
	public void mostrarResultado(VOPartidaEsMayorMenor partida) {
		JOptionPane.showMessageDialog(null, partida.getMensaje());
		
		
		
	} 
	
	
	public void gano()
	{
		
			JOptionPane.showMessageDialog(null, "Felicitaciones");
			lblNumero.setText(textNumeroIngresado.getText());
			dispose();
		
	}
	public void abandonar()
	{
		JOptionPane.showMessageDialog(null, "GAME OVER");
		dispose();
	}
	public void errorMensaje(String message) {
		JOptionPane.showMessageDialog(null, message);
		
	}

}
