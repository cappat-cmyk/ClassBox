import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class CreateGroup extends JFrame {

	private JPanel contentPane;
	private JTextField txtfld_GrpName;
	private JTextField txtfld_GrpPsw;
	static CreateGroup frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame= new CreateGroup();
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public CreateGroup() {
		setTitle("Create Group");
		Image img =(new ImageIcon(("developers_logo.png")).getImage());
		setIconImage(img);
		setBounds(100, 100, 483, 313);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		ImageIcon developers_logo= new ImageIcon("developers_logo.png");
		JLabel lbl_developerslogo = new JLabel("");
		lbl_developerslogo.setBounds(40, 45, 211, 141);
		lbl_developerslogo.setIcon(developers_logo);
		contentPane.add(lbl_developerslogo);
		
		JLabel lbl_GrpName = new JLabel("Group Name");
		lbl_GrpName.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lbl_GrpName.setBounds(311, 45, 98, 27);
		contentPane.add(lbl_GrpName);
		
		txtfld_GrpName = new JTextField();
		txtfld_GrpName.setBounds(278, 82, 163, 27);
		contentPane.add(txtfld_GrpName);
		txtfld_GrpName.setColumns(10);
		
		JLabel lbl_GrpPsw = new JLabel("Password");
		lbl_GrpPsw.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lbl_GrpPsw.setBounds(321, 132, 98, 27);
		contentPane.add(lbl_GrpPsw);
		
		txtfld_GrpPsw = new JTextField();
		txtfld_GrpPsw.setBounds(278, 169, 163, 27);
		contentPane.add(txtfld_GrpPsw);
		
		JButton Btn_CreateGrp = new JButton("Create");
		Btn_CreateGrp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            // TODO add your handling code here:
			            insert();
			        } catch (ClassNotFoundException ex) {
			            Logger.getLogger(CreateGroup.class.getName()).log(Level.SEVERE, null, ex);
			        } catch (InstantiationException ex) {
			            Logger.getLogger(CreateGroup.class.getName()).log(Level.SEVERE, null, ex);
			        } catch (IllegalAccessException ex) {
			            Logger.getLogger(CreateGroup.class.getName()).log(Level.SEVERE, null, ex);
			        }
			}
		});
		Btn_CreateGrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_CreateGrp.setBounds(23, 216, 98, 27);
		contentPane.add(Btn_CreateGrp);
		
		JButton Btn_CreateGrp_1 = new JButton("Delete");
		Btn_CreateGrp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn= null;
				Statement stmt= null;
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
				conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/classbox", "root", "");
				stmt=(Statement) conn.createStatement();
				
				String query1= "DELETE from groups WHERE GroupName= '"+txtfld_GrpName.getText()+"'";
				stmt.executeUpdate(query1);
				JOptionPane.showMessageDialog(null, "Successfully Deleted from the Database");
			}catch (SQLException excep) {
		         excep.printStackTrace();
		    } catch (Exception excep) {
		         excep.printStackTrace();
		    } finally {
		    	
		    	try {
		    		if (stmt != null)
	                conn.close();
	          } catch (SQLException se) {}
		    	
		    	try {
	             if (conn != null)
	                conn.close();
	          } catch (SQLException se) {
	             se.printStackTrace();
	          }  
	       }
		}
	});
		Btn_CreateGrp_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_CreateGrp_1.setBounds(139, 216, 98, 27);
		contentPane.add(Btn_CreateGrp_1);
		
		JButton Btn_CreateGrp_1_1 = new JButton("Search");
		Btn_CreateGrp_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				Statement stmt=null;
				
				
				try {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/classbox", "root", "");
					stmt=(Statement) conn.createStatement();
					
					String query1= "SELECT * FROM groups WHERE GroupName= '"+txtfld_GrpName.getText()+"' and CreatedBy= '"+login.usernamecall+"'"; 
					ResultSet rs=stmt.executeQuery(query1);
					
				while(rs.next()) {
					txtfld_GrpName.setText(rs.getString("GroupName"));
					txtfld_GrpPsw.setText(rs.getString("Password"));
					
				}
				
				}catch (SQLException excep) {
			         excep.printStackTrace();
			    } catch (Exception excep) {
			         excep.printStackTrace();
			    } finally {
			    	
			    	try {
			    		if (stmt != null)
		                conn.close();
		          } catch (SQLException se) {}
			    	
			    	try {
		             if (conn != null)
		                conn.close();
		          } catch (SQLException se) {
		             se.printStackTrace();
		          }  
		       }
			}
			
		});
		Btn_CreateGrp_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_CreateGrp_1_1.setBounds(247, 216, 98, 27);
		contentPane.add(Btn_CreateGrp_1_1);
		
		JButton Btn_CreateGrp_1_1_1 = new JButton("Update");
		Btn_CreateGrp_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn= null;
				PreparedStatement stmt= null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/classbox", "root", "");
				stmt=(PreparedStatement) conn.prepareStatement("UPDATE groups"+" set Password= '"+txtfld_GrpPsw.getText()+"' where GroupName='"+txtfld_GrpName.getText()+"' and CreatedBy='"+login.usernamecall+"'");
				stmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Successfully update account");
			}catch (SQLException excep) {
		         excep.printStackTrace();
		    } catch (Exception excep) {
		         excep.printStackTrace();
		    } finally {
		    	
		    	try {
		    		if (stmt != null)
	                conn.close();
	          } catch (SQLException se) {}
		    	
		    	try {
	             if (conn != null)
	                conn.close();
	          } catch (SQLException se) {
	             se.printStackTrace();
	          }  
		}
			}
		});
		Btn_CreateGrp_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		Btn_CreateGrp_1_1_1.setBounds(355, 216, 98, 27);
		contentPane.add(Btn_CreateGrp_1_1_1);
}

	@SuppressWarnings("deprecation")
	public void insert() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {   
    Connection conn = null;
         Statement stmt = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/classbox","root",""); 
        stmt = conn.createStatement();  
        String pass = txtfld_GrpPsw.getText(); 
        String sql="Select * from groups";
        ResultSet rs=stmt.executeQuery(sql);
        rs.last();
        int updatedport=rs.getInt("Port");
        updatedport++;
 
        String name=login.usernamecall;
		String sql1= "INSERT INTO groups (GroupName, Password, Port, CreatedBy)"+ "VALUES ('"+txtfld_GrpName.getText()+"','"+pass+"','"+updatedport+"','"+login.usernamecall+"')";
        stmt.executeUpdate(sql1);
        
        
        JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted",
                "Result", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);	
        /*try {
        	//insert here first if the it exists in the database.
			new AdminServerChat().main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        conn.close();
        stmt.close();
        //frame.dispose();
    }

    catch (SQLException e1)
    {
        System.out.println("Exception:" + e1);
    }

}
}
