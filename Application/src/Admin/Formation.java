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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

public class Formation extends JFrame {

	private JPanel contentPane;
	private JTextField cod;
	private JTextField libl;
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
					Formation frame = new Formation();
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
	public Formation() {
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
		
		JLabel lblManagmentFormation = new JLabel("Formation\r\n");
		lblManagmentFormation.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagmentFormation.setFont(new Font("Rockwell", Font.BOLD, 31));
		lblManagmentFormation.setBounds(359, 28, 459, 69);
		contentPane.add(lblManagmentFormation);
		
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
		
		JLabel lblCode = new JLabel("Code\r\n");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCode.setBounds(87, 166, 153, 40);
		contentPane.add(lblCode);
		
		cod = new JTextField();
		cod.setFont(new Font("Tahoma", Font.BOLD, 13));
		cod.setColumns(10);
		cod.setBounds(238, 166, 251, 40);
		contentPane.add(cod);
		
		JLabel lblLibell = new JLabel("libell\u00E9");
		lblLibell.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLibell.setBounds(598, 166, 153, 40);
		contentPane.add(lblLibell);
		
		JLabel lblLogin = new JLabel("Discription\r\n");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLogin.setBounds(87, 241, 132, 50);
		contentPane.add(lblLogin);
		
		libl = new JTextField();
		libl.setFont(new Font("Tahoma", Font.BOLD, 13));
		libl.setColumns(10);
		libl.setBounds(687, 166, 251, 40);
		contentPane.add(libl);
		
		JTextArea disc = new JTextArea();
		disc.setBounds(238, 241, 700, 69);
		contentPane.add(disc);
		
		JScrollPane formationlist = new JScrollPane();
		formationlist.setBounds(87, 382, 725, 182);
		contentPane.add(formationlist);
		//table(((((((((((((((((((((((((((((((((((((((((((((((((
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						

						 DefaultTableModel df = (DefaultTableModel)table.getModel();	
					     int selectedIndex = table.getSelectedRow();
						 cod.setText(df.getValueAt(selectedIndex, 1).toString());
						 libl.setText(df.getValueAt(selectedIndex, 2).toString());
						 disc.setText(df.getValueAt(selectedIndex, 3).toString());

					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setFont(new Font("Tahoma", Font.BOLD, 12));
				table.setModel(new DefaultTableModel(
						
					new Object[][] {
					},
					new String[] {
						"ID", "Code", "libellé", "description"
					}
				) {

					public boolean isCellEditable(int rowIndex, int columnIndex) {
					    return false;
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(55);
				table.getColumnModel().getColumn(3).setPreferredWidth(700);
				formationlist.setViewportView(table);
		
		JButton btnNewButton_1_1 = new JButton("DASHBOARD\r\n");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                Main main = new Main();
                main.setTitle("HOME");
                main.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(962, 28, 154, 43);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = cod.getText();
				String libellé = libl.getText();
				String description = disc.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");
					 stmt = conn.prepareStatement("insert into formation(code,libellé,description)values(?,?,?)");
					 stmt.setString(1,code);
					 stmt.setString(2,libellé);
					 stmt.setString(3,description);
					 stmt.execute();
							 JOptionPane.showMessageDialog(btnAdd, "Formation Added Successfully");
							 cod.setText("");
							 libl.setText("");
							 disc.setText("");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				Display();
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBackground(Color.BLACK);
		btnAdd.setBounds(840, 382, 159, 38);
		contentPane.add(btnAdd);
		
		JButton btnOrder = new JButton("DISPLAY\r\n");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Display();
			}
		});
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOrder.setBackground(Color.BLACK);
		btnOrder.setBounds(840, 430, 159, 38);
		contentPane.add(btnOrder);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int msg = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ??!!", "Delete", JOptionPane.YES_NO_OPTION);
				if(msg==0) {									
				 DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();				
			     try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
						 Class.forName("com.mysql.cj.jdbc.Driver");	
						 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");
					     stmt = conn.prepareStatement("DELETE from formation  where id_formation = '" + id + "'");
						 stmt.executeUpdate();	  
						 JOptionPane.showMessageDialog(btnAdd, "Formation Deleted Successfully");	 
						 cod.setText("");
						 libl.setText("");
						 disc.setText("");
						 Display();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBackground(Color.BLACK);
		btnDelete.setBounds(840, 478, 159, 38);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();							     
					try {												
						int id_formation = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());	
						String code = cod.getText();
						String libellé = libl.getText();
						String description = disc.getText();	
						Class.forName("com.mysql.cj.jdbc.Driver");						
						 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");						 		 						 												 														
							 stmt = conn.prepareStatement("UPDATE formation SET " + "code = '" + code + "', libellé = '" + libellé + "', description = '" + description + "' where id_formation = '" + id_formation + "'");
							 stmt.executeUpdate();							  
								 JOptionPane.showMessageDialog(btnAdd, "Formation edited Successfully");
								 cod.setText("");
								 libl.setText("");
								 disc.setText("");
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
		btnUpdate.setBounds(840, 526, 159, 38);
		contentPane.add(btnUpdate);
	}
	private void Display() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");					
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");					 										 
			 stmt = conn.prepareStatement("SELECT * FROM formation");						
			 ResultSet result = stmt.executeQuery();
			 DefaultTableModel df = (DefaultTableModel)table.getModel();					 
			 df.setRowCount(0);					 
			 while(result.next()) {
				int id_formation = result.getInt("id_formation");
				String code = result.getString("code");
				String libellé = result.getString("libellé");
				String description = result.getString("description");
                df.addRow(new Object[] {id_formation,code,libellé,description});                       
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
