package client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Command;

public class GameClient{
	private String clientName; // user name of the client
	
	private Socket server; // connection to server
	private ObjectOutputStream out; // output stream
	private ObjectInputStream in; // input stream
	
	public void newMessage(String message) {
		// TODO Display a new message to our text/chat panel. May need to add a local list of messages	
	}

	/**
	 * This class reads and executes commands sent from the server
	 * 
	 * @author Gabriel Kishi
	 *
	 */
	private class ServerHandler implements Runnable{
		public void run() {
			try{
				while(true){
					// read a command from server and execute it
					Command<GameClient> c = (Command<GameClient>)in.readObject();
					c.execute(GameClient.this);
				}
			}
			catch(SocketException e){
				return; // "gracefully" terminate after disconnect
			}
			catch (EOFException e) {
				return; // "gracefully" terminate
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public GameClient(){
		// ask the user for a host, port, and user name
		String host = JOptionPane.showInputDialog("Host address:");
		String port = JOptionPane.showInputDialog("Host port:");
		clientName = JOptionPane.showInputDialog("User name:");
		
		if (host == null || port == null || clientName == null)
			return;
		
		try{
			// Open a connection to the server
			server = new Socket(host, Integer.parseInt(port));
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());
			
			// write out the name of this client
			out.writeObject(clientName);
			
			// add a listener that sends a disconnect command to when closing
//			this.addWindowListener(new WindowAdapter(){
//				public void windowClosing(WindowEvent arg0) {
//					try {
//						out.writeObject(null);// TODO send a disconnect command to the server to safely disconnect
//						out.close();
//						in.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			});
			
			// TODO set up the GUI
			
			// start a thread for handling server events
			new Thread(new ServerHandler()).start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		new GameClient();
	}

	/**
	 * Updates the ChatPanel with the updated message log
	 * 
	 * @param messages	the log of messages to display
	 */
	public void update(List<String> messages) {
		// TODO update the gui when called
		// GUI.update(messages);
	}
}
