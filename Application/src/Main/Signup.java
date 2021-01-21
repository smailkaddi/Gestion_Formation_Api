package Main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import Main.*;
import Admin.*;
import Employee.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;

/**
 * User Registration using Swing
 * @author javaguides.net
 *
 */
public class Signup extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField fname;
    private JTextField lname;
    private JTextField matricu;
    private JTextField log;
    private JTextField vill;
    private JPasswordField pass;
    private JButton btnNewButton;
    private JButton btnLogin;
	Connection conn;
	PreparedStatement stmt;
	private JPasswordField confirmpass;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Signup frame = new Signup();
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
private boolean checkUsername(String login)
{
PreparedStatement ps;
ResultSet rs;
String query = "SELECT * FROM employé where login =?";
boolean checkUser = false;

try {
 ps = Signup.getConnection().prepareStatement(query);
 ps.setString(1, login);				
 rs = ps.executeQuery();	      
 	 
 if(rs.next())
{
  checkUser = true;
}
} catch (SQLException ex) {
ex.printStackTrace();
}
return checkUser;
}
    public Signup() {
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1156, 765);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("CheckBox.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Register\r\n");
        lblNewUserRegister.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(341, 44, 436, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lablefname = new JLabel("Nom");
        lablefname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lablefname.setBounds(58, 152, 99, 43);
        contentPane.add(lablefname);

        JLabel lablelname = new JLabel("Prenom\r\n");
        lablelname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lablelname.setBounds(58, 243, 110, 29);
        contentPane.add(lablelname);

        JLabel labelmatricule = new JLabel("Matricule");
        labelmatricule.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelmatricule.setBounds(58, 324, 124, 36);
        contentPane.add(labelmatricule);

        fname = new JTextField();
        fname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        fname.setBounds(214, 151, 228, 50);
        contentPane.add(fname);
        fname.setColumns(10);

        lname = new JTextField();
        lname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lname.setBounds(214, 235, 228, 50);
        contentPane.add(lname);
        lname.setColumns(10);

        matricu = new JTextField();

        matricu.setFont(new Font("Tahoma", Font.PLAIN, 32));
        matricu.setBounds(214, 320, 228, 50);
        contentPane.add(matricu);
        matricu.setColumns(10);

        log = new JTextField();
        log.setFont(new Font("Tahoma", Font.PLAIN, 32));
        log.setBounds(214, 398, 228, 50);
        contentPane.add(log);
        log.setColumns(10);

        JLabel usernamlbl = new JLabel("Username");
        usernamlbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
        usernamlbl.setBounds(58, 413, 99, 29);
        contentPane.add(usernamlbl);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(531, 162, 99, 24);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Ville");
        lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMobileNumber.setBounds(531, 333, 139, 26);
        contentPane.add(lblMobileNumber);

        vill = new JTextField();
        vill.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vill.setBounds(707, 324, 228, 50);
        contentPane.add(vill);
        vill.setColumns(10);

        pass = new JPasswordField();
        pass.setFont(new Font("Tahoma", Font.PLAIN, 32));
        pass.setBounds(707, 152, 228, 50);
        contentPane.add(pass);
    
      	
        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nom = fname.getText();
				String prénom = lname.getText();
				String matricule = matricu.getText();
				String ville = vill.getText();
				String login = log.getText();
				String mot_de_passe = pass.getText();
				String confimPass = confirmpass.getText();
				String type = "Employee";
				String Request = "NotYet";
				String code_formation = "";
                String msg = "" + matricule;
                msg += " \n";
                if(fname.getText().equals("")) {
                	JOptionPane.showMessageDialog(btnNewButton, "Please add First Name");
                }else if(lname.getText().equals("")) {
                	JOptionPane.showMessageDialog(btnNewButton, "Please add Last Name");
                }else if(matricu.getText().equals("")) {
                	JOptionPane.showMessageDialog(btnNewButton, "Please add Matricule");
                }else if(checkUsername(login)) {
                	JOptionPane.showMessageDialog(btnNewButton, "This Username Already Exist");
                }else if(pass.getText().equals("")) {
                	JOptionPane.showMessageDialog(btnNewButton, "Please add Password");
                }else if(!(pass.getText().equals(confirmpass.getText()))) {
                	JOptionPane.showMessageDialog(btnNewButton, "Your passwords do not match");
                }
                else if(log.getText().equals("")) {
                	JOptionPane.showMessageDialog(btnNewButton, "Please add login");
//                }else if() {
//                	JOptionPane.showMessageDialog(btnNewButton, "Please add login");
//                }else if(vill.getText().equals("")) {
                	JOptionPane.showMessageDialog(btnNewButton, "Please add Ville");
                }                 
                else {
              	
				

                try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation", "root", "");

					 stmt = conn.prepareStatement("insert into employé(nom,prénom,matricule,ville,login,mot_de_passe,type,Request,code_formation)values(?,?,?,?,?,?,?,?,?)");
					 stmt.setString(1,nom);
					 stmt.setString(2,prénom);
					 stmt.setString(3,matricule);
					 stmt.setString(4,ville);
					 stmt.setString(5,login);
					 stmt.setString(6,mot_de_passe);
					 stmt.setString(7,type);
					 stmt.setString(8,Request);
					 stmt.setString(9,code_formation);
		             stmt.execute();
		                 fname.setText("");
	       				 lname.setText("");
	       				 matricu.setText("");
	       				 vill.setText("");
	       				 log.setText("");
	       				 pass.setText("");
	       				 dispose();
	       				Login ul = new Login();
	       				ul.setTitle("Welcome");
	       				ul.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "Account ADDED Seccusfuly");

					 

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
                }
                
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(592, 523, 259, 74);
        contentPane.add(btnNewButton);
        
        btnLogin = new JButton("LogIn");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				 dispose();
				 Login us = new Login();
				 us.setTitle("Login");
				 us.setVisible(true);
        	}
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnLogin.setBounds(289, 523, 259, 74);
        contentPane.add(btnLogin);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password\r\n");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblConfirmPassword.setBounds(531, 253, 160, 24);
        contentPane.add(lblConfirmPassword);
        
        confirmpass = new JPasswordField();
        confirmpass.setFont(new Font("Tahoma", Font.PLAIN, 32));
        confirmpass.setBounds(707, 243, 228, 50);
        contentPane.add(confirmpass);
        

    }
}