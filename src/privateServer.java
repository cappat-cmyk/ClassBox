

import java.awt.BorderLayout;
import java.net.*;
import java.io.*;
import java.awt.EventQueue;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class privateServer implements Runnable {
	public DataInputStream dis;
	private static final int PORT=3000;
	private static ArrayList<privateClientHandler> clients= new ArrayList<>();
	
	private static ExecutorService pool = Executors.newFixedThreadPool(2); 	
	
	public static void main(String[] args) throws IOException   {
		 Runnable runnable=new privateServer();
	      Thread thread=new Thread(runnable);
	      thread.start();

	}
	

	@Override
	public void run() {
		try {
			AdminServerChat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AdminServerChat() throws IOException {
		JOptionPane.showMessageDialog(null, "Server Started");
		ServerSocket listener=new ServerSocket(PORT);
		
		while(true) {
		Socket client=listener.accept();
		System.out.println("Start"); //it print that the server is starting
		
		privateClientHandler clientThread= new privateClientHandler(client,clients);
		clients.add(clientThread);
		pool.execute(clientThread);		
		}
	}
}
	
