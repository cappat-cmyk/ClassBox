
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class clientlistdashboard extends JFrame {

	private JPanel contentPane;
	static String Add;
	static String usernameupper;
	static JComboBox<String> comboBox;
	static login login;
	static String receivername;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientlistdashboard frame = new clientlistdashboard();
					frame.setVisible(true);
					frame.setResizable(false);
					login = new login();
					login.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
					
	}

	/**
	 * Create the frame.
	 */
	public clientlistdashboard() {
		setTitle("Dashboard");
		setBounds(100, 100, 394, 551);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
        DefaultListModel<String> l1 = new DefaultListModel<>();  

		JList<String> list = new JList<String>(l1);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList theList = (JList) e.getSource();
		        if (e.getClickCount() == 2) {
		        	int index = theList.locationToIndex(e.getPoint());
		        	if (index >= 0) {
		                Object o = theList.getModel().getElementAt(index);
		                	receivername= o.toString();
		                	try {
								new privateuserinterface().setVisible(true);
		                	Class.forName("com.mysql.jdbc.Driver");
							Connection con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root","");
							Statement stmt1= con1.createStatement();
							String sql1="Select * from privatehistory where Sender= '"+login.usernamecall+"' and Receiver='" +receivername+"' or Sender= '"+receivername+"' and Receiver='"+login.usernamecall+"'";
							ResultSet rs1=stmt1.executeQuery(sql1);

							while(rs1.next()) {
									String sender=rs1.getString("Sender");
									String chat=rs1.getString("Chat");
									privateuserinterface.typeMessage.append("["+rs1.getDate("Time")+" "+ rs1.getTime("Time")+"]"+" "+sender+" :"+chat+"\n");
							}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                	
		              }
		        	
		        }
			}
		});
		list.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		list.setBounds(10, 89, 361, 415);
		contentPane.add(list);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root","");
			Statement stmt= con.createStatement();
			String sql= "Select * from login";
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				Add = rs.getString("FirstName")+" "+rs.getString("LastName");
				l1.addElement(Add);
				}
			}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		
		JLabel lbl_username = new JLabel("");
		lbl_username.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_username.setBackground(Color.BLUE);
		lbl_username.setBounds(20, 32, 231, 34);
		contentPane.add(lbl_username);
		usernameupper= login.usernamecall;
		lbl_username.setText(usernameupper);
		
		if (login.role.equals("Teacher")) {
		String [] options= {"Join Group","Manage my Groups","Start Group Server","Start Private Server","Log out"};
		comboBox = new JComboBox<String>(options);
		}
		else {
		String [] options1= {"Join Group","Start Private Server","Log out"};
		comboBox = new JComboBox<String>(options1);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String chooseoptions = (String)comboBox.getSelectedItem();
		        if(chooseoptions=="Join Group") {
		        	new JoinGroup().setVisible(true);
		        }
		        else if(chooseoptions=="Manage my Groups") {
			        	new CreateGroup().setVisible(true);
		        }
		        else if(chooseoptions=="Start Group Server") {
			        	new StartServer().setVisible(true);
		        }
		        else if(chooseoptions=="Start Private Server") {
		        	new StartprivateServer().setVisible(true);
		        }
		        else if(chooseoptions=="Log out") {
		        	System.exit(ABORT);
		        }
			}
		});
		comboBox.setBounds(230, 32, 130, 26);
		contentPane.add(comboBox);
		

		
	}
}
