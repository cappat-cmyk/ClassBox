package groupchat;
import java.awt.BorderLayout;
import java.util.*;
import java.awt.EventQueue;
import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientHandler implements Runnable  {

	private Socket client;
	private BufferedReader input;
	private PrintWriter output;
	private ArrayList<ClientHandler> clients;
	
	public static void main(String[] args) {
				
	}


	public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
		this.client=clientSocket;
		this.clients=clients;
		input= new BufferedReader(new InputStreamReader(client.getInputStream()));
		output= new PrintWriter(client.getOutputStream(),true);
	}
	
	public void run() {
		try {
			Scanner keyboard= new Scanner(System.in);
			while(true) {
			String msgfromclient=input.readLine();
			if(msgfromclient!=null) {
				outToAll(msgfromclient);
			}
			System.out.println("client: "+msgfromclient);
			
			
			String msg=keyboard.nextLine();
			output.println(msg);
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
		for (ClientHandler aClient: clients) {
			aClient.output.println(msgfromclient);
		}
		
	}

}
