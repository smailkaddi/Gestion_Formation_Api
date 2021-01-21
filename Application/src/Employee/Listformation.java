package Employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Main.Signup;
import Main.Login;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Listformation extends JFrame {

	private JPanel contentPane;
	Connection conn;
	PreparedStatement stmt;
	PreparedStatement stmt2;
	JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	 public static Connection getConnection(){
	     
	        Connection con = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        return con;
	    }
	private boolean checkUsername(String code)
	{
	PreparedStatement ps;
	ResultSet rs;
	String query = "SELECT * FROM formation where code =?";
	boolean checkUser = true;

	try {
	 ps = Signup.getConnection().prepareStatement(query);
	 ps.setString(1, code);
	 rs = ps.executeQuery();	      
	 	 
	 if(rs.next())
	{
	  checkUser = false;
	}
	} catch (SQLException ex) {
	ex.printStackTrace();
	}
	return checkUser;
	}
	public Listformation(String login) {
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
		
		JLabel lblNewLabel = new JLabel("List Of Formations\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 43));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(283, 102, 517, 52);
		contentPane.add(lblNewLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(138, 198, 826, 197);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
				
				new Object[][] {
				},
				new String[] {
					"ID", "Code", "Libélle", "Description"
				}
	){

			public boolean isCellEditable(int rowIndex, int columnIndex) {
			    return false;
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(500);
		scrollPane.setViewportView(table);
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
		btnNewButton_1.setBounds(10, 675, 142, 43);
		contentPane.add(btnNewButton_1);
		JButton btnNewButton_1_1 = new JButton("ADD");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code_formation = textField.getText();
				String Request = "InComplate";
				try {

                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation",
                        "root", "");

                    PreparedStatement st = (PreparedStatement) con
                        .prepareStatement("UPDATE employé SET code_formation=?,Request=? WHERE login=?");

                    st.setString(1, code_formation);
                    st.setString(2, Request);
                    st.setString(3, login);
                    if(textField.getText().equals("")) {
                    	JOptionPane.showMessageDialog(btnNewButton_1_1, "Please add code formation");
                    }else if (checkUsername(code_formation)) {
                    	JOptionPane.showMessageDialog(btnNewButton_1_1, "This code not on the list");
					}else {
	                    st.executeUpdate();
	                    JOptionPane.showMessageDialog(btnNewButton_1_1, "formation has been successfully added please wait for responsable to accepte");
	                    textField.setText("");
					}

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            
			}
		});
		
		btnNewButton_1_1.setBounds(480, 432, 154, 43);
		contentPane.add(btnNewButton_1_1);
		
		textField = new JTextField();
		textField.setEditable(true);
		textField.setEnabled(true);
		textField.setBounds(195, 432, 245, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1_2 = new JButton("List Of Request");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		dispose();
        		ListofRequest lr = new ListofRequest(login);
        		lr.setTitle("REQUEST");
        		lr.setVisible(true);
			}
		});
		btnNewButton_1_2.setBounds(810, 432, 154, 43);
		contentPane.add(btnNewButton_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Join with code :\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(33, 430, 343, 43);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1_1_1 = new JButton("SEE MAP\r\n");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String url = "http://localhost:3000/map/";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		btnNewButton_1_1_1.setBounds(978, 675, 154, 43);
		contentPane.add(btnNewButton_1_1_1);
		
		
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
//				String typ = result.getString("Validation");
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
	private void Display1() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");					
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");					 										 
			 stmt = conn.prepareStatement("SELECT * FROM formation");						
			 ResultSet result = stmt.executeQuery();
			 DefaultTableModel df = (DefaultTableModel)table.getModel();					 
			 df.setRowCount(0);					 
			 while(result.next()) {
				String code = result.getString("code");
                df.addRow(new Object[] {code});                       
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
