
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;

public class privateuserinterface extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	static JTextArea typeMessage;
	private static final int PORT=3000;
	
	public static void main(String[] args) throws IOException {
					privateuserinterface frame = new privateuserinterface();
					frame.setVisible(true);
					frame.setResizable(false);
	}

	public privateuserinterface() throws UnknownHostException, IOException{
		//InetAddress ip=InetAddress.getLocalHost();
		Socket socket= new Socket("127.0.0.1",PORT);
		PrintWriter output= new PrintWriter(socket.getOutputStream(),true);
		privateServerConnection serverConn= new privateServerConnection(socket);
		new Thread(serverConn).start();
		System.out.println("connected.....");	
		
		setBounds(100, 100, 493, 636);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		//contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		typeMessage = new JTextArea();
		typeMessage.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		//typeMessage.setText("messagehere");
		typeMessage.setBounds(20, 59, 437, 445);
		typeMessage.setLineWrap(true);
		typeMessage.setWrapStyleWord(true);
		//contentPane.add(typeMessage);
		
		JScrollPane scroll= new JScrollPane(typeMessage);
		scroll.setBounds(20, 59, 437, 445);
		contentPane.add(scroll);
		
		JLabel lbl_username = new JLabel("");
		lbl_username.setText(clientlistdashboard.receivername.toUpperCase());
		lbl_username.setForeground(new Color(248, 248, 255));
		lbl_username.setBackground(new Color(30, 144, 255));
		lbl_username.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_username.setFont(new Font("Verdana", Font.BOLD, 20));
		lbl_username.setBounds(79, 10, 324, 39);
		contentPane.add(lbl_username);
		
		textField = new JTextField();
		textField.setBounds(20, 523, 331, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton send = new JButton("");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date dt = new java.util.Date();
				java.text.SimpleDateFormat sdf = 
				     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				
				String msgfromclient=" ";
				msgfromclient=textField.getText();
				output.println("["+currentTime+"]"+login.usernamecall+": "+msgfromclient);
				
				try {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/classbox", "root", "");
					Statement stmt=(Statement) conn.createStatement();
					
					String query1= "INSERT INTO privatehistory (Sender, Receiver, Chat, Time)"+ "VALUES ('"+login.usernamecall+"','"+clientlistdashboard.receivername+"','"+msgfromclient+"','"+currentTime+"')";
					stmt.executeUpdate(query1);
				}catch (SQLException excep) {
			         excep.printStackTrace();
			    }
				
				}
			
		});
		send.setBounds(361, 523, 90, 45);
		ImageIcon sendICon= new ImageIcon("sendimage.png");
		Image icon= sendICon.getImage().getScaledInstance(send.getWidth(), send.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon sendICon1= new ImageIcon(icon);
		send.setIcon(sendICon1);
		contentPane.add(send);
		
		
	}
}
