package Grafica.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Grafica.controladora.ControladoraMenuAdmin;

import Logica.Fachada;
import Logica.IFachada;
import Persistencia.PersistenciaException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMenuAdmin extends JFrame{

	private JPanel contentPane;
	private IFachada fachada;
	
	private ControladoraMenuAdmin controladora;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmMenuAdmin().setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMenuAdmin() {
		controladora=new ControladoraMenuAdmin(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				int respuesta=JOptionPane.showConfirmDialog(null, "Desea salir del programa?","Sistema...",JOptionPane.YES_NO_OPTION);
				if(respuesta==JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null, "Gracias");
					System.exit(0);
				}
				
				
				
			}
		});
		setTitle("MENU ADMIN");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar Jugador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controladora.registrarJugador();
			}
		});
		btnNewButton.setBounds(106, 32, 206, 38);
		contentPane.add(btnNewButton);

		JButton btnListarJugadores = new JButton("Listar Jugadores");
		btnListarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladora.listarJugadores();
			}
		});
		btnListarJugadores.setBounds(106, 77, 206, 38);
		contentPane.add(btnListarJugadores);

		JButton btnListarPartidas = new JButton("Listar Partida");
		btnListarPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladora.listarPartida();
			}
		});
		btnListarPartidas.setBounds(106, 126, 206, 38);
		contentPane.add(btnListarPartidas);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladora.guardar();
			}
		});
		btnGuardar.setBounds(106, 175, 206, 38);
		contentPane.add(btnGuardar);
	}

	
	public void listarJugadores() {
		FrmListarJugadoresAdmin ventana2=new FrmListarJugadoresAdmin(this);
		ventana2.setVisible(true);
		this.setEnabled(false);
		
	}

	public void registrarJugador() {
		FrmRegistrarJugador registrarJugador = new FrmRegistrarJugador(this);
		this.setEnabled(false);
		registrarJugador.setVisible(true);
		
	}

	public void listarPartida() {
		FrmListarPartidaAdmin ventana3 = new FrmListarPartidaAdmin(this);
		ventana3.setVisible(true);
		this.setEnabled(false);
		
	}

	
	public void guardar() {
		JOptionPane.showMessageDialog(this, "Se guardo correctamente");
		
	}

	public void errorMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje);
		
	}
}
