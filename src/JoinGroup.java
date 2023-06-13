import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JoinGroup extends JFrame {
	static int port1;
	private JPanel contentPane;
	private JTextField txtfld_GrpName;
	private JPasswordField txtfld_GrpPsw;
	static String GroupName;
	static JoinGroup frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JoinGroup();
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
	public JoinGroup() {
		
		
		setTitle("Join Group");
		Image img =(new ImageIcon(("developers_logo.png")).getImage());
		setIconImage(img);
		setBounds(100, 100, 444, 279);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		ImageIcon developers_logo= new ImageIcon("developers_logo.png");
		JLabel lbl_developerslogo = new JLabel("");
		lbl_developerslogo.setBounds(40, 45, 168, 125);
		lbl_developerslogo.setIcon(developers_logo);
		contentPane.add(lbl_developerslogo);
		
		JLabel lbl_GrpName = new JLabel("Group Name");
		lbl_GrpName.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lbl_GrpName.setBounds(272, 45, 98, 27);
		contentPane.add(lbl_GrpName);
		
		txtfld_GrpName = new JTextField();
		txtfld_GrpName.setBounds(239, 82, 163, 27);
		contentPane.add(txtfld_GrpName);
		txtfld_GrpName.setColumns(10);
		
		JLabel lbl_GrpPsw = new JLabel("Password");
		lbl_GrpPsw.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lbl_GrpPsw.setBounds(282, 132, 98, 27);
		contentPane.add(lbl_GrpPsw);
		
		txtfld_GrpPsw = new JPasswordField();
		txtfld_GrpPsw.setBounds(239, 169, 163, 27);
		contentPane.add(txtfld_GrpPsw);
		
		JButton Btn_CreateGrp = new JButton("Join");
		Btn_CreateGrp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root","");
				Statement stmt= con.createStatement();
				String sql= "Select * from groups where GroupName= '"+txtfld_GrpName.getText()+"'and Password='" +txtfld_GrpPsw.getText().toString()+"'";
				ResultSet rs= stmt.executeQuery(sql);
				
				while(rs.next()) {
					JOptionPane.showMessageDialog(null, "Successfully join Group Chat");
					GroupName=txtfld_GrpName.getText();
					String port=rs.getString("Port");
					port1=Integer.parseInt(port);					
				}
				
				new userinterface().setVisible(true);
				
				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root","");
					Statement stmt1= con1.createStatement();
					String sql1="Select * from chathistory where GroupName= '"+txtfld_GrpName.getText()+"'";
					ResultSet rs1=stmt1.executeQuery(sql1);
					
					while(rs1.next()) {
							String sender=rs1.getString("Sender");
							String chat=rs1.getString("Chat");
							userinterface.typeMessage.append("["+rs1.getDate("Time")+" "+ rs1.getTime("Time")+"]"+" "+sender+" :"+chat+"\n");
					}
					con1.close();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					
				}
				
				con.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			}
			
			
		});

		Btn_CreateGrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_CreateGrp.setBounds(272, 206, 98, 27);
		contentPane.add(Btn_CreateGrp);
	}

}
