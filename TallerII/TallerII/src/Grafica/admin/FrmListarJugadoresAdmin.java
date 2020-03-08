package Grafica.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.IFachada;
import Logica.JugadorException;
import ValueObjects.VOJugadorDespliegue;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmListarJugadoresAdmin extends JFrame {

	private JPanel contentPane;
	
	private FrmMenuAdmin menu;
	/**
	 * Create the frame.
	 */
	public FrmListarJugadoresAdmin(FrmMenuAdmin menu) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menu.setEnabled(true);
				dispose();
			}
		});
		setTitle("LISTAR JUGADORES");
		this.menu=menu;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 452);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LISTA DE JUGADORES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(62, 28, 308, 40);
		contentPane.add(lblNewLabel);
		
		JList lstJugadores = new JList();
		lstJugadores.setBounds(98, 79, 244, 247);
		contentPane.add(lstJugadores);
		
		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setEnabled(true);
				dispose();
			}
		});
		btnNewButton.setBounds(345, 369, 89, 33);
		contentPane.add(btnNewButton);
		
		/*
		try {
			//ArrayList<VOJugadorDespliegue> jugadores=fachada.listarJugadores();
			//lstJugadores.setListData(jugadores.toArray());
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JugadorException e1) {
			JOptionPane.showMessageDialog(this, e1.getMensaje());
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}
}
