package todolist;

//When we use jdbc, then to connect mysql, we will need the mysql connector to connect with it.  
import com.mysql.jdbc.ResultSetMetaData;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	Connection con = null; //For Connection
	public signup(){
		con = DB.dbconnect(); //we just called DB class's function dbconnect and assigned to variable con.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SignUp Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(55, 58, 306, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(55, 169, 96, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(55, 227, 96, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("User ID");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(55, 297, 96, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(55, 361, 96, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email ID");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(55, 439, 90, 13);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(184, 168, 124, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 226, 124, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(184, 294, 124, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(184, 360, 124, 19);
		contentPane.add(passwordField);
		
		textField_3 = new JTextField();
		textField_3.setBounds(184, 438, 124, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("SignUp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//In try and catch I wrote my code
				try {
					// I assigned all the buttons to the variables. 
					String first = textField.getText();
					String last = textField_1.getText();
					String user = textField_2.getText();
					String password = passwordField.getText();
					String email = textField_3.getText();
					
					//PreparedStatement means we will write some query.
					PreparedStatement pst=(PreparedStatement) con.prepareStatement("insert into signup(firstname, lastname, userid, password, email) values(?,?,?,?,?)");
					pst.setString(1, first); //first goes in column 1
					pst.setString(2, last);  //last goes in column 2, so on.
					pst.setString(3, user);
					pst.setString(4, password);
					pst.setString(5, email);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "data added");
					//To empty the text field after submitting the form
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					passwordField.setText("");
					textField_3.setText("");
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(48, 491, 124, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//I just made l object here, if the signup is already done, then he can just login that page. 
				login l = new login();
				l.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(211, 491, 112, 40);
		contentPane.add(btnNewButton_1);
	}
}
