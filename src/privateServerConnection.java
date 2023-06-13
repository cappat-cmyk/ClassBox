


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class privateServerConnection implements Runnable {
	private Socket server;
	private BufferedReader input;
	
	public privateServerConnection(Socket s) throws IOException {
		server=s;
		input= new BufferedReader(new InputStreamReader(server.getInputStream()));
	}
	
	public void run() {
		
			
			try {

				while(true) {
					String message = input.readLine();
					privateuserinterface.typeMessage.append(message+"\n");
				}
					//System.out.println("admin"+message); eto yung narereceive ng mga clients
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
