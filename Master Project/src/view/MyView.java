package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Observable;
import java.util.Observer;

import cliDisplays.DisplayType;

/**
 * 
 * @author Shahar & Bar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class MyView extends Observable implements View,Observer {

	@SuppressWarnings("unused")
	private BufferedReader in;
	private Writer out;
	private CLI cli;	
		
	/**
	 * Ctor
	 * @param in - The input.
	 * @param out - The Output.
	 */
	
	public MyView(BufferedReader in, Writer out)
	{		
		this.in = in;
		this.out = out;		
		cli = new CLI(in, out);
		cli.addObserver(this);
	}
	
	/**
	 * This method notify the observer about changes.
	 * @param o - The observable that updated.
	 * @param arg - The arguments.
	 */
	
	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
		this.setChanged();
		this.notifyObservers(arg);			
		}		
	}

	/**
	 * This method will start the CLI's start method.
	 */
	
	@Override
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {				
				cli.start();
			}
			
		});	
		thread.start();
	}

	/**
	 * Return the output
	 * @return Writer
	 */	
	public Writer getOut() {
		return out;
	}

	/**
	 * Set the output
	 * @param out - The output we want to set.
	 */
	
	public void setOut(Writer out) {
		this.out = out;
	}

	/**
	 * implements display method of view interface <br>
	 * @param obj - The object to Display.
	 * @param d - The suitable display type.
	 * 
	 */
	@Override
	public void display (Object obj,DisplayType d) {
			d.display(obj);
	}
	
	/**
	 * This method will call another method that exit all open files.
	 */
	@Override
	public void exit(){
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//exit.exit();
		setChanged();
		notifyObservers("close_threads");
	}

}
