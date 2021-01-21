package Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Main.*;


import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

public class ListeRequest extends JFrame {

	private JPanel contentPane;
	JTable table;
	Connection conn;
	PreparedStatement stmt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeRequest frame = new ListeRequest();
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
	public ListeRequest() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Display();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1156, 765);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManagmentEmployee = new JLabel("Request\r\n");
		lblManagmentEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagmentEmployee.setFont(new Font("Rockwell", Font.BOLD, 31));
		lblManagmentEmployee.setBounds(342, 32, 459, 69);
		contentPane.add(lblManagmentEmployee);
		
		JButton btnNewButton_1 = new JButton("LOGOUT\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnNewButton_1, "Are you sure?");
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
		btnNewButton_1.setBounds(10, 675, 154, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnOrder = new JButton("DISPLAY\r\n");
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOrder.setBackground(Color.BLACK);
		btnOrder.setBounds(838, 507, 159, 38);
		contentPane.add(btnOrder);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBackground(Color.BLACK);
		btnDelete.setBounds(838, 459, 159, 38);
		contentPane.add(btnDelete);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Status :", "InComplate", "Complate"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(174, 211, 823, 40);
		contentPane.add(comboBox);
		
		
		JButton btnUpdate = new JButton("Join");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();							     
					try {												
						int id_employee = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());		
						String Request = comboBox.getSelectedItem().toString();
						Class.forName("com.mysql.cj.jdbc.Driver");						
						 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");						 		 						 												 														
							 stmt = conn.prepareStatement("UPDATE employé SET " + "Request = '" + Request + "' where id_employee = '" + id_employee + "'");
							 stmt.executeUpdate();							  
								 JOptionPane.showMessageDialog(btnDelete, "Statu edited Successfully");	
								 comboBox.setSelectedIndex(0);
								 Display();																 	 
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setBounds(838, 411, 159, 38);
		contentPane.add(btnUpdate);
		

		JLabel lblLogin_1_1 = new JLabel("Request\r\n");
		lblLogin_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLogin_1_1.setBounds(51, 209, 113, 50);
		contentPane.add(lblLogin_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 375, 750, 197);
		contentPane.add(scrollPane);
		//table(((((((((((((((((((((((((((((((((((((((((((((((((
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						

						 DefaultTableModel df = (DefaultTableModel)table.getModel();	
					     int selectedIndex = table.getSelectedRow();
					     comboBox.setSelectedItem(df.getValueAt(selectedIndex, 1).toString());
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setFont(new Font("Tahoma", Font.BOLD, 12));
				table.setModel(new DefaultTableModel(
						
					new Object[][] {
					},
					new String[] {
						"ID", "FirstName", "LastName", "matricule", "Ville", "Code de formation","Statu"
					}
				) {

					public boolean isCellEditable(int rowIndex, int columnIndex) {
					    return false;
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(55);
				table.getColumnModel().getColumn(3).setPreferredWidth(50);
				table.getColumnModel().getColumn(4).setPreferredWidth(81);
				scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_1 = new JButton("Dashboard\r\n");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                Main main = new Main();
                main.setTitle("HOME");
                main.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(978, 10, 154, 43);
		contentPane.add(btnNewButton_1_1);
	}
	private void Display() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");					
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");					 										 
			 stmt = conn.prepareStatement("SELECT * FROM employé where Request ='InComplate' OR Request ='Complate'");						
			 ResultSet result = stmt.executeQuery();
			 DefaultTableModel df = (DefaultTableModel)table.getModel();					 
			 df.setRowCount(0);					 
			 while(result.next()) {
				int id_employee = result.getInt("id_employee");
				String nom = result.getString("nom");
				String prénom = result.getString("prénom");
				String matricule = result.getString("matricule");
				String ville = result.getString("ville");
				String code_formation = result.getString("code_formation");
				String Request = result.getString("Request");
                df.addRow(new Object[] {id_employee,nom,prénom,matricule,ville,code_formation,Request});                       
             }						 
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
	}
}
