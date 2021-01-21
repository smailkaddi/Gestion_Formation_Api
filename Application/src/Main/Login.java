package Main;
import Main.*;
import Admin.*;
import Employee.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1156, 765);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setForeground(Color.BLACK);
        contentPane.setBackground(SystemColor.textHighlight);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setBounds(444, 87, 273, 93);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Vladimir Script", Font.PLAIN, 46));
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(297, 281, 546, 68);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(297, 414, 546, 68);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(91, 289, 193, 52);
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(91, 422, 193, 52);
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        contentPane.add(lblPassword);
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 27));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"TYPE :", "Admin", "Employee"}));
        comboBox.setBounds(295, 519, 548, 67);
        contentPane.add(comboBox);
        
        JLabel lblType = new JLabel("Type");
        lblType.setForeground(Color.BLACK);
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblType.setBackground(Color.CYAN);
        lblType.setBounds(91, 525, 144, 52);
        contentPane.add(lblType);

        btnNewButton = new JButton("Login");
        btnNewButton.setBounds(297, 641, 236, 52);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
                String type = (String) comboBox.getSelectedItem();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation","root", "");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select login, mot_de_passe,type from employé where login=? and mot_de_passe=? and type=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    st.setString(3, type);
                    
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {

                        if (type.equals("Admin")) {
                            dispose();
                            Main ah = new Main();
                            ah.setTitle("Welcome");
                            ah.setVisible(true);
                            JLabel lblll = new JLabel("Welcome back Admin :"+userName);
                            lblll.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                            lblll.setBounds(254, 21, 191, 52);
                            ah.getContentPane().add(lblll);
                        } else if (type.equals("Employee")) {
                    		dispose();
                    		Listformation lf = new Listformation(userName);
                            lf.setTitle("Employee");
                            lf.setVisible(true);
                            JLabel lblll = new JLabel("Welcome :"+userName);
                            lblll.setFont(new Font("Times New Roman", Font.PLAIN, 22));
                            lblll.setBounds(254, 21, 191, 52);
                            lf.getContentPane().add(lblll);
                        } else {
                        	JOptionPane.showMessageDialog(btnNewButton, "Wrong TYPE");
                        }

                    } else {
                    	JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password & TYPE");
                    }
  
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);
        
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                dispose();
                Signup ah = new Signup();
                ah.setTitle("Register");
                ah.setVisible(true);
        	}
        });
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnRegister.setBounds(609, 641, 236, 52);
        contentPane.add(btnRegister);
        
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.desktop);
        panel.setForeground(SystemColor.desktop);
        panel.setBounds(0, 0, 10, 10);
        contentPane.add(panel);
        

    }
}