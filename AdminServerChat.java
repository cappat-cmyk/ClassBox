package groupchat;
import java.awt.BorderLayout;
import java.net.*;
import java.io.*;
import java.awt.EventQueue;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminServerChat {
	public DataInputStream dis;
	private static final int PORT=1235;
	private static ArrayList<ClientHandler> clients= new ArrayList<>();
	
	private static ExecutorService pool = Executors.newFixedThreadPool(10);  

	public static void main(String[] args) throws IOException {
					
	ServerSocket listener=new ServerSocket(1235);
	
	while(true) {
	Socket client=listener.accept();
	System.out.println("Start");
	
	ClientHandler clientThread= new ClientHandler(client,clients);
	clients.add(clientThread);
	pool.execute(clientThread);
	}

	}

	/**
	 * Create the frame.
	 */
	public AdminServerChat() {
		
	}

}
