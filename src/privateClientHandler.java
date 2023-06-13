

import java.awt.BorderLayout;
import java.util.*;
import java.awt.EventQueue;
import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class privateClientHandler implements Runnable  {

	private Socket client;
	private BufferedReader input;
	private PrintWriter output;
	private ArrayList<privateClientHandler> clients;
	
	public static void main(String[] args) {
				
	}


	public privateClientHandler(Socket clientSocket, ArrayList<privateClientHandler> clients) throws IOException {
		this.client=clientSocket;
		this.clients=clients;
		input= new BufferedReader(new InputStreamReader(client.getInputStream()));
		output= new PrintWriter(client.getOutputStream(),true);
	}
	
	public void run() {
		try {
			while(true) {
			String msgfromclient=input.readLine();
				outToAll(msgfromclient);
			
			System.out.println("client: "+msgfromclient);// eto marereceive ng server
			

			//output.println(msgfromclient);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			output.close();
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}


	private void outToAll(String msgfromclient) {
		for (privateClientHandler aClient: clients) {
			aClient.output.println(msgfromclient);
		}
		
	}

}
