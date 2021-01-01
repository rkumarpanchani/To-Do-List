package todolist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class todo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					todo frame = new todo();
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
	Connection con = null; //For connection
	public todo() {
		con = (Connection) DB.dbconnect();//For connection
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My To-Do List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(60, 10, 268, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Main Tasks");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(60, 115, 85, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Other Tasks");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(60, 153, 103, 13);
		contentPane.add(lblNewLabel_2);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(212, 167, 190, 184);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// I wrote the code here in try and catch block
				try {
					String imp = textField.getText();
					String other = textArea.getText();
					
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into todo(important,other)values(?,?)");
					
					pst.setString(1, imp);
					pst.setString(2, other);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "task added");
					
					textField.setText("");
					textArea.setText("");
					
					int a; //for numbers of columns
					PreparedStatement pst1 = (PreparedStatement) con.prepareStatement("select * from todo"); // select data from todo list
					ResultSet rs = pst1.executeQuery(); //store in rs object
					
					ResultSetMetaData rd = (ResultSetMetaData) rs.getMetaData(); //getMetaData() will retreive the number and types and properties of rs object.
					a = rd.getColumnCount(); //to count column

					DefaultTableModel df = (DefaultTableModel) table.getModel();
					df.setRowCount(0);
					
					while(rs.next()) {
						Vector v2 = new Vector();
						for(int  i = 1; i<=a; i++) {
							//in database we to print info for id, important and other
							v2.add(rs.getString("id"));
							v2.add(rs.getString("important"));
							v2.add(rs.getString("other"));
						}
						df.addRow(v2); //will save in row format
					}
				}
				catch(Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(60, 389, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			private java.sql.PreparedStatement pst;

			public void actionPerformed(ActionEvent e) {
				// I wrote the code here in try and catch block
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				int id = Integer.parseInt(df.getValueAt(s, 0).toString());
				try {
					String imp = textField.getText();
					String other = textField.getText();
					
					pst = con.prepareStatement("update todo set important=?,other=? where id=?");
					pst.setString(1, imp);
					pst.setString(2, other);
					pst.setInt(3, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "list Updated");
					textField.setText("");
					textArea.setText("");
				
				int a; //for numbers of columns
				//Query fired
				pst = (PreparedStatement) con.prepareStatement("select * from todo"); // select data from todo list
				ResultSet rs = pst.executeQuery(); //store in rs object
				
				ResultSetMetaData rd = (ResultSetMetaData) rs.getMetaData(); //getMetaData() will retreive the number and types and properties of rs object.
				a = rd.getColumnCount(); //to get all column

				DefaultTableModel df1 = (DefaultTableModel) table.getModel();
				df1.setRowCount(0);
				
				while(rs.next()) {
					Vector v2 = new Vector();
					for(int  i = 1; i<=a; i++) {
						//in database we to print info for id, important and other
						v2.add(rs.getString("id"));
						v2.add(rs.getString("important"));
						v2.add(rs.getString("other"));
					}
					df.addRow(v2); //will save in row format
				}
			}
			catch(Exception e2) {
				System.out.println(e2);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(184, 389, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Done");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(317, 389, 85, 21);
		contentPane.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(212, 114, 190, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(444, 116, 363, 246);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			//Mouse event handling for login button in signup form
			DefaultTableModel df = (DefaultTableModel) table.getModel(); //create df object
			int selected = table.getSelectedRow(); //store row index into selected variable
			int id = Integer.parseInt(df.getValueAt(selected, 0).toString());
			textField.setText(df.getValueAt(selected, 1).toString()); //3rd row's 1st column, is set to textField
			textArea.setText(df.getValueAt(selected, 2).toString());//3rd row's 2nd column, is set to textArea
			btnNewButton.setEnabled(false);
			}
			
		});
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Important", "Other"
			}
		));
		

	}
}