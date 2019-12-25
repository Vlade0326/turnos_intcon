package Turnos;

import java.awt.BorderLayout;
import java.awt.EventQueuet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextFiled;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.awt.event.ActionEvent;

public class Inicio extends JFrame {

private JPanel contentPane;
private JTextField textFieldusuario;
private JTextField textFieldpass;

/**
*Lanzar aplicacion Launch the application.
*/
public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
               try {
                   Inicio frame = new Inicio();
                   frame.setVisible(true);
               } catch (Exception e) {
               } e.printStackTrace();
               }
           }
           });
       }
       
       /**
       *Crear Frame
       */
       public Inicio() {
       setDefaultCloseOpertation(JFrame.EXIT_ON_CLOSE);
       setBounds(100, 100, 366, 217);
       contentPane = new JPanel();
       setContentPane(contentPane);
       contentPane.setLayout(null);
       
       
