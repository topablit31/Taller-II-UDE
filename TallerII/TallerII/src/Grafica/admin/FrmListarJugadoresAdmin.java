package Grafica.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Grafica.controladora.ControladoraListarJugadoresAdmin;

public class FrmListarJugadoresAdmin extends JFrame {

	private JPanel contentPane;
	
	private FrmMenuAdmin menu;
	private FrmListarJugadoresAdmin ventana;
	private ControladoraListarJugadoresAdmin controladora;
	private JList list;
	
	/**
	 * Create the frame.
	 */
	public FrmListarJugadoresAdmin(FrmMenuAdmin menu) {
		ventana=this;
		controladora = new ControladoraListarJugadoresAdmin(ventana);
		
		this.menu=menu;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menu.setEnabled(true);
				dispose();
			}
		});
		setTitle("LISTAR JUGADORES");
		
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
		
		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setEnabled(true);
				dispose();
			}
		});
		btnNewButton.setBounds(345, 369, 89, 33);
		contentPane.add(btnNewButton);
		
		list = new JList();
		list.setBounds(80, 127, 316, 192);
		contentPane.add(list);
		
		controladora.listarJugadores();
	}
	public void listarJugadores(Object[] jugadores) {
		
		
		
		list.setListData(jugadores);
		
		
	}
}
