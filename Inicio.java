package Turnos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 217);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 32, 116, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 67, 82, 14);
		contentPane.add(lblPassword);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setText("admin");
		textFieldUsuario.setBounds(90, 29, 121, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldPass = new JTextField();
		textFieldPass.setText("admin");
		textFieldPass.setBounds(90, 57, 121, 20);
		contentPane.add(textFieldPass);
		textFieldPass.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(233, 58, 89, 23);
		contentPane.add(btnNewButton);				

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldUsuario.getText().equals("admin") ){
					if (textFieldPass.getText().equals("admin")){
						Menu jfMenu = new Menu(); 
						jfMenu.main(null);						
					}else{	
						JOptionPane.showMessageDialog(null,  "Datos incorrectos");	
					}
				}else{	
					JOptionPane.showMessageDialog(null,  "Datos incorrectos");	
				}
			}
		});
	}
}
