package groupchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnection implements Runnable {
	private Socket server;
	private BufferedReader input;
	
	public ServerConnection(Socket s) throws IOException {
		server=s;
		input= new BufferedReader(new InputStreamReader(server.getInputStream()));
	}
	
	public void run() {
		
			String message = null;
			try {
				while(true) {
					message = input.readLine();
					if (message==null) break;
					System.out.println("admin"+message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
	
	
	public static void main(String args[]) {
		
	}
}
