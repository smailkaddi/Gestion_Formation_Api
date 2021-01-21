package Admin;
import Main.*;
import Admin.*;
import Employee.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1156, 765);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Rockwell", Font.BOLD, 31));
		lblNewLabel.setBounds(347, 71, 459, 69);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("employ\u00E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                Employé emp = new Employé();
                emp.setTitle("Employee");
                emp.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 34));
		btnNewButton.setBounds(448, 211, 257, 57);
		contentPane.add(btnNewButton);
		
		JButton btnSession = new JButton("Session\r\n");
		btnSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Session sess = new Session();
				sess.setTitle("Session");
				sess.setVisible(true);
			}
		});
		btnSession.setFont(new Font("Rockwell", Font.PLAIN, 34));
		btnSession.setBounds(448, 339, 257, 57);
		contentPane.add(btnSession);
		
		JButton btnFormation = new JButton("Formation ");
		btnFormation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Formation form = new Formation();
				form.setTitle("Formations");
				form.setVisible(true);
			}
		});
		btnFormation.setFont(new Font("Rockwell", Font.PLAIN, 34));
		btnFormation.setBounds(448, 467, 257, 57);
		contentPane.add(btnFormation);
		
		JButton btnNewButton_1 = new JButton("LOGOUT\r\n");
		btnNewButton_1.setBackground(UIManager.getColor("Button.focus"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login obj = new Login();
                    obj.setTitle("Login");
                    obj.setVisible(true);
                }
                dispose();
                Login obj = new Login();
                obj.setTitle("Login");
                obj.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 663, 154, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnListOfRequest = new JButton("List of Request");
		btnListOfRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ListeRequest lr = new ListeRequest();
				lr.setTitle("Liste Request");
				lr.setVisible(true);
			}
		});
		btnListOfRequest.setFont(new Font("Rockwell", Font.PLAIN, 34));
		btnListOfRequest.setBounds(389, 595, 374, 57);
		contentPane.add(btnListOfRequest);
	}
}
