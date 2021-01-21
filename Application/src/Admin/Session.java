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
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.im.InputContext;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

public class Session extends JFrame {

	private JPanel contentPane;
	private JTextField cod;
	private JTextField libl;
	JTable table;
	Connection conn;
	PreparedStatement stmt;
	private JTextField anne;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session frame = new Session();
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
private boolean checkUsername(int id_formation)
{
PreparedStatement ps;
ResultSet rs;
String query = "SELECT * FROM formation where id_formation =?";
boolean checkUser = true;

try {
 ps = Signup.getConnection().prepareStatement(query);
 ps.setInt(1, id_formation);
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
	public Session() {
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
		
		JLabel lblManagmentSession = new JLabel("Session\r\n\r\n");
		lblManagmentSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagmentSession.setFont(new Font("Rockwell", Font.BOLD, 31));
		lblManagmentSession.setBounds(317, 10, 459, 69);
		contentPane.add(lblManagmentSession);
		
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
		
		cod = new JTextField();
		cod.setFont(new Font("Tahoma", Font.BOLD, 13));
		cod.setColumns(10);
		cod.setBounds(263, 154, 251, 40);
		contentPane.add(cod);
		
		JLabel lblLibell = new JLabel("libell\u00E9");
		lblLibell.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLibell.setBounds(623, 154, 153, 40);
		contentPane.add(lblLibell);
		
		libl = new JTextField();
		libl.setFont(new Font("Tahoma", Font.BOLD, 13));
		libl.setColumns(10);
		libl.setBounds(712, 154, 251, 40);
		contentPane.add(libl);
		
		JLabel lblCode = new JLabel("Code\r\n");
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCode.setBounds(112, 154, 153, 40);
		contentPane.add(lblCode);
		JComboBox moisbox = new JComboBox();
		moisbox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		moisbox.setBounds(262, 298, 252, 43);
		contentPane.add(moisbox);
		JComboBox combo = new JComboBox();
		combo.setBounds(262, 228, 252, 43);
		contentPane.add(combo);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root","");
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery("Select libellé from formation");
			                    while (rs.next()) {
					String libellé = rs.getString("libellé");
					combo.addItem(rs.getString("libellé"));
					combo.setVisible(true);
			                    }
				}
				
			 catch (Exception ex) {
	         		}
				
				JComboBox combo_1 = new JComboBox();
				combo_1.setBounds(711, 298, 252, 43);
				contentPane.add(combo_1);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root","");
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery("Select id_employee,login from employé");
			                    while (rs.next()) {
					String id_employee = rs.getString("id_employee");
					String login = rs.getString("login");
					combo_1.addItem(rs.getString("id_employee"));
					combo_1.setVisible(true);
			                    }
				}
				
			 catch (Exception ex) {
		     		}
	
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_session = (String) combo_1.getSelectedItem();
				String code = cod.getText();
				String libellé = libl.getText();
				String formation = (String)combo.getSelectedItem();
				String Mois = (String) moisbox.getSelectedItem();
				String annee = anne.getText();
				String id_employee = (String) combo_1.getSelectedItem();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");
					 stmt = conn.prepareStatement("insert into session(id_session ,id_employee,code,libellé,formation,Mois,annee)values(?,?,?,?,?,?,?)");
					 stmt.setString(1,id_session );
					 stmt.setString(2,id_employee);
					 stmt.setString(3,code);
					 stmt.setString(4,libellé);
					 stmt.setString(5,formation);
					 stmt.setString(6,Mois);
					 stmt.setString(7,annee);
					 stmt.execute();
							 JOptionPane.showMessageDialog(btnAdd, "Session Added Successfully");
							 cod.setText("");
							 libl.setText("");
							 moisbox.setSelectedIndex(0);
							 anne.setText("");
							 combo.setSelectedIndex(0);
							 combo_1.setSelectedIndex(0);
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
		btnAdd.setBounds(829, 395, 170, 38);
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
		btnOrder.setBounds(829, 443, 169, 38);
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
					     stmt = conn.prepareStatement("DELETE from session  where id_session = '" + id + "'");
						 stmt.executeUpdate();	  
						 JOptionPane.showMessageDialog(btnAdd, "Session Deleted Successfully");	 
						 cod.setText("");
						 libl.setText("");
						 combo.setSelectedItem(0);
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
		btnDelete.setBounds(829, 488, 170, 38);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();							     
					try {												
						int id_session = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());	
						String code = cod.getText();
						String libellé = libl.getText();
						String formation = (String)combo.getSelectedItem();
						Class.forName("com.mysql.cj.jdbc.Driver");						
						 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");						 		 						 												 														
							 stmt = conn.prepareStatement("UPDATE session SET " + "code = '" + code + "', libellé = '" + libellé + "', formation = '" + formation + "' where id_session = '" + id_session + "'");
							 stmt.executeUpdate();							  
								 JOptionPane.showMessageDialog(btnAdd, "Session edited Successfully");
								 cod.setText("");
								 libl.setText("");
								 combo.setSelectedItem(0);
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
		btnUpdate.setBounds(829, 536, 170, 38);
		contentPane.add(btnUpdate);
		
		JScrollPane sessionlist = new JScrollPane();
		sessionlist.setBounds(145, 395, 654, 197);
		contentPane.add(sessionlist);
		//table(((((((((((((((((((((((((((((((((((((((((((((((((
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				

				 DefaultTableModel df = (DefaultTableModel)table.getModel();	
			     int selectedIndex = table.getSelectedRow();
				 cod.setText(df.getValueAt(selectedIndex, 1).toString());
				 libl.setText(df.getValueAt(selectedIndex, 2).toString());
				 combo.setSelectedItem(df.getValueAt(selectedIndex, 3).toString());

			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
				
			new Object[][] {
			},
			new String[] {
				"ID", "Code", "libellé", "Formations", "id employee", "Mois","Annee",
			}
		) {

			public boolean isCellEditable(int rowIndex, int columnIndex) {
			    return false;
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		sessionlist.setViewportView(table);
		
		JButton btnNewButton_1_1 = new JButton("Dashboard\r\n");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                Main main = new Main();
                main.setTitle("HOME");
                main.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(978, 30, 154, 43);
		contentPane.add(btnNewButton_1_1);
		
		JLabel id_form = new JLabel("Formation\r\n");
		id_form.setFont(new Font("Tahoma", Font.BOLD, 12));
		id_form.setBounds(112, 228, 153, 40);
		contentPane.add(id_form);
		
		JLabel lblMois = new JLabel("Mois");
		lblMois.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMois.setBounds(112, 298, 132, 50);
		contentPane.add(lblMois);
		
		JLabel id_form_1 = new JLabel("Annee");
		id_form_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		id_form_1.setBounds(623, 228, 153, 40);
		contentPane.add(id_form_1);
		
		anne = new JTextField();
		anne.setFont(new Font("Tahoma", Font.BOLD, 13));
		anne.setColumns(10);
		anne.setBounds(712, 228, 251, 40);
		contentPane.add(anne);
		
		JLabel id_form_1_1 = new JLabel("Employee");
		id_form_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		id_form_1_1.setBounds(623, 301, 153, 40);
		contentPane.add(id_form_1_1);
		

		

	}
	private void Display() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");					
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");					 										 
			 stmt = conn.prepareStatement("SELECT * FROM session");						
			 ResultSet result = stmt.executeQuery();
			 DefaultTableModel df = (DefaultTableModel)table.getModel();					 
			 df.setRowCount(0);					 
			 while(result.next()) {
				int id_session = result.getInt("id_session");
				String code = result.getString("code");
				String libellé = result.getString("libellé");
				String formation = result.getString("formation");
				String Mois = result.getString("Mois");
				String annee = result.getString("annee");
				String id_employee = result.getString("id_employee");
                df.addRow(new Object[] {id_session,code,libellé,formation,id_employee,Mois,annee});                       
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
