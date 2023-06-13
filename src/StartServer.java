import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


public class StartServer extends JFrame {
	private JPanel contentPane;
	static String result;
	static int port1;
	static StartServer frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new StartServer();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartServer() {
		setTitle("Start Server");
		setBounds(100, 100, 414, 268);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Start Group Server");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(136, 21, 202, 35);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result=comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setBounds(199, 140, 121, 27);
		contentPane.add(comboBox);
		
		JButton btnFilterMyGroups = new JButton("Filter my groups");
		btnFilterMyGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root","");
				    Statement stmt= con.createStatement();
					String sql= "Select * from groups where CreatedBy= '"+login.usernamecall+"'";
					ResultSet rs= stmt.executeQuery(sql);
					
					String usernamecall;
					while(rs.next()) {
						usernamecall=rs.getString("GroupName");
						comboBox.addItem(usernamecall);
						}
					
			
				}catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFilterMyGroups.setBounds(160, 96, 129, 27);
		contentPane.add(btnFilterMyGroups);
		
		
		JLabel lblNewLabel_1 = new JLabel("Choose Group:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(95, 141, 112, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Start Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root","");
			    Statement stmt= con.createStatement();
				String sql1= "Select * from groups where  GroupName='" +result+"'";
				ResultSet rs1=stmt.executeQuery(sql1);
						while(rs1.next()) {
							String port=rs1.getString("Port");
							port1=Integer.parseInt(port);
							
						}
						try {
							new AdminServerChat().main(null);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				//frame.dispose();
					
				}catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
			
		});
		btnNewButton.setBounds(160, 177, 129, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("GOOD DAY! Prof."+login.usernamecall);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(156, 66, 182, 20);
		contentPane.add(lblNewLabel_3);
		
		
	}
}
