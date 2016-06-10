package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Observable;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class CLI extends Observable {
	
	private BufferedReader in;
	private Writer out;	
	
	/**
	 * Ctor
	 * @param in - The input.
	 * @param out - The Output.
	 */
	
	public CLI(BufferedReader in, Writer out) {
		this.in = in;
		this.out = out;		
	}
	
	/**
	 * This method will read the commands from the client. 
	 */
	
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				 
				try {					
					String line = null;
					do {
						out.write("");
						out.write("Enter command: ");
						out.write("");
						out.flush();
						line = in.readLine().toLowerCase();
						setChanged();
						notifyObservers(line);						
					} while (!(line.equals("exit")));					 
					
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}						
			}
			
		});
		thread.start();
	}
}
