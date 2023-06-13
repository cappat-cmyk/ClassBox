import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;


public class adminView extends JFrame {

	private JPanel contentPane;
	private JTextField txtfld_firstname;
	private JTextField textfld_lastname;
	private JTextField txtfld_studentid;
	private JTextField txtfld_password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminView frame = new adminView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public adminView() {
		Image img =(new ImageIcon(("developers_logo.png")).getImage());
		setIconImage(img);
		setBounds(100, 100, 487, 384);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		ImageIcon company_logo= new ImageIcon("company_logo.png");
		
		ImageIcon developers_logo= new ImageIcon("developers_logo.png");
		
		JLabel lblNewLabel = new JLabel("Welcome Admin!");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBounds(194, 16, 169, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_firstname = new JLabel("First Name:");
		lbl_firstname.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_firstname.setBounds(30, 65, 132, 27);
		contentPane.add(lbl_firstname);
		
		JLabel lbl_lastname = new JLabel("Last Name:");
		lbl_lastname.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_lastname.setBounds(30, 102, 132, 27);
		contentPane.add(lbl_lastname);
		
		JLabel lbl_stuID = new JLabel("Student ID:");
		lbl_stuID.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_stuID.setBounds(30, 139, 132, 27);
		contentPane.add(lbl_stuID);
		
		JRadioButton btn_role_teacher = new JRadioButton("Teacher");
		btn_role_teacher.setBounds(132, 224, 103, 21);
		contentPane.add(btn_role_teacher);
		
		JRadioButton btn_role_student = new JRadioButton("Student");
		btn_role_student.setBounds(132, 248, 103, 21);
		contentPane.add(btn_role_student);
		
		JRadioButton btn_role_admin = new JRadioButton("Admin");
		btn_role_admin.setBounds(219, 235, 103, 21);
		contentPane.add(btn_role_admin);
		
		ButtonGroup role= new ButtonGroup();
		role.add(btn_role_teacher);
		role.add(btn_role_student);
		role.add(btn_role_admin);
		
		JLabel lbl_chooserole = new JLabel("Choose role:");
		lbl_chooserole.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_chooserole.setBounds(30, 219, 132, 27);
		contentPane.add(lbl_chooserole);
		
		txtfld_firstname = new JTextField();
		txtfld_firstname.setBounds(119, 65, 264, 25);
		contentPane.add(txtfld_firstname);
		txtfld_firstname.setColumns(10);
		
		textfld_lastname = new JTextField();
		textfld_lastname.setColumns(10);
		textfld_lastname.setBounds(119, 102, 264, 25);
		contentPane.add(textfld_lastname);
		
		txtfld_studentid = new JTextField();
		txtfld_studentid.setColumns(10);
		txtfld_studentid.setBounds(119, 142, 264, 25);
		contentPane.add(txtfld_studentid);
		
		JLabel passwordbtn = new JLabel("Password:");
		passwordbtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		passwordbtn.setBounds(30, 176, 132, 27);
		contentPane.add(passwordbtn);
		
		txtfld_password = new JTextField();
		txtfld_password.setColumns(10);
		txtfld_password.setBounds(119, 176, 264, 25);
		contentPane.add(txtfld_password);
		
		JButton Addbtn = new JButton("ADD ");
		Addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String roles = null;
				
				if(btn_role_teacher.isSelected()) {
					roles=btn_role_teacher.getText();
				}
				else if(btn_role_student.isSelected()) {
					roles=btn_role_student.getText();
				}
				else if(btn_role_admin.isSelected()) {
					roles=btn_role_admin.getText();
				}

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
				
				String query1= "INSERT INTO login (FirstName, LastName, StudentID, Role, Password)"+ "VALUES ('"+txtfld_firstname.getText()+"','"+textfld_lastname.getText()+"','"+txtfld_studentid.getText()+"','"+roles+"','"+txtfld_password.getText()+"')";
				stmt.executeUpdate(query1);
				JOptionPane.showMessageDialog(null, "Successfully Added to the Database");
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
		Addbtn.setBounds(10, 283, 85, 21);
		contentPane.add(Addbtn);
		
		JButton deletebtn = new JButton("DELETE ");
		deletebtn.addActionListener(new ActionListener() {
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
				
				String query1= "DELETE from login WHERE StudentID= '"+txtfld_studentid.getText()+"'";
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
			
		deletebtn.setBounds(105, 283, 115, 21);
		contentPane.add(deletebtn);
		
		JButton searchbtn = new JButton("SEARCH ");
		searchbtn.addActionListener(new ActionListener() {
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
					
					String query1= "SELECT * FROM login WHERE StudentID= '"+txtfld_studentid.getText()+"'"; 
					ResultSet rs=stmt.executeQuery(query1);
					
				while(rs.next()) {
					txtfld_firstname.setText(rs.getString("FirstName"));
					textfld_lastname.setText(rs.getString("LastName"));
					txtfld_password.setText(rs.getString("Password"));
					//role.clearSelection();
					String role1=rs.getString("Role").toString().trim();
					if(role1.equals("Student"))
						btn_role_student.setSelected(true);
					else if(role1.equals("Teacher"))
						btn_role_teacher.setSelected(true);
					else if(role1.equals("Admin"))
						btn_role_admin.setSelected(true);


					
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
		
		searchbtn.setBounds(230, 283, 115, 21);
		contentPane.add(searchbtn);
		
		JButton updatebtn = new JButton("UPDATE ");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roles=null;
				if(btn_role_teacher.isSelected()) {
					roles=btn_role_teacher.getText();
				}
				else if(btn_role_student.isSelected()) {
					roles=btn_role_student.getText();
				}
				else {
					roles=btn_role_admin.getText();
				}
				
				Connection conn= null;
				PreparedStatement stmt= null;
			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
				conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/classbox", "root", "");
				stmt=(PreparedStatement) conn.prepareStatement("UPDATE login"+" set FirstName= '"+txtfld_firstname.getText()+"',LastName='"+textfld_lastname.getText()+"',Role='"+roles+"',Password='"+txtfld_password.getText()+"' where StudentID='"+txtfld_studentid.getText()+"'");
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
				
				/*try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/classbox", "root", "");
				         Statement stmt = conn.createStatement();
				      ) {		      
				         String sql = "UPDATE groups " +
				            "SET age = 30 WHERE id in (100, 101)";
				         stmt.executeUpdate(sql);
				         ResultSet rs = stmt.executeQuery(QUERY);
				         while(rs.next()){
				            //Display values
				            System.out.print("ID: " + rs.getInt("id"));
				            System.out.print(", Age: " + rs.getInt("age"));
				            System.out.print(", First: " + rs.getString("first"));
				            System.out.println(", Last: " + rs.getString("last"));
				         }
				         rs.close();
				      } catch (SQLException e) {
				         e.printStackTrace();
				      } 
				   }*/
	});

		updatebtn.setBounds(350, 283, 115, 21);
		contentPane.add(updatebtn);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtfld_firstname.setText("");
				textfld_lastname.setText("");
				txtfld_studentid.setText("");
				txtfld_password.setText("");
				role.clearSelection();
				
				
			}
		});
		btnClear.setBounds(182, 314, 115, 21);
		contentPane.add(btnClear);
		
		
		
		
	}
}
