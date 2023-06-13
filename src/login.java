
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JFrame frame;
	static String usernamecall;
	private JTextField textField_username;
	private JPasswordField passwordField;
	static String role;
	


	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lbl_companylogo = new JLabel("");
		lbl_companylogo.setBounds(27, 10, 149, 114);
		ImageIcon company_logo= new ImageIcon("company_logo.png");
		lbl_companylogo.setIcon(company_logo);
		contentPane.add(lbl_companylogo);
		
		ImageIcon developers_logo= new ImageIcon("developers_logo.png");
		JLabel lbl_developerslogo = new JLabel("");
		lbl_developerslogo.setBounds(190, 10, 149, 114);
		lbl_developerslogo.setIcon(developers_logo);
		contentPane.add(lbl_developerslogo);
		
		
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel.setBounds(28, 134, 90, 28);
		contentPane.add(lblNewLabel);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(28, 172, 311, 28);
		contentPane.add(textField_username);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblPassword.setBounds(28, 201, 90, 28);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(28, 239, 311, 28);
		contentPane.add(passwordField);
		
		JButton btn_signin = new JButton("Sign in");
		btn_signin.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
				
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root","");
					Statement stmt= con.createStatement();
					String sql= "Select * from login where studentID= '"+textField_username.getText()+"'and Password='" +passwordField.getText().toString()+"'";
					ResultSet rs= stmt.executeQuery(sql);
					
					
					 while(rs.next()) {
						
						usernamecall=rs.getString("FirstName")+" "+rs.getString("LastName");
						JOptionPane.showMessageDialog(frame, "Welcome to ClassBox \n"+usernamecall,"Successfully login", getDefaultCloseOperation(), new ImageIcon("developers_logo.png"));
						 role= rs.getString("Role").toString();
					}
					 if(role.equals("Admin")) {
						 new adminView().setVisible(true);
					 }else {
						 new clientlistdashboard().setVisible(true);
						 Main.frame.dispose();
						
					 }
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				 
			}
			
			
		});
		
		
		btn_signin.setFont(new Font("MS PGothic", Font.BOLD, 12));
		btn_signin.setBackground(new Color(220, 220, 220));
		btn_signin.setBounds(234, 288, 105, 28);
		contentPane.add(btn_signin);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
