package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 11.06.16
 *
 */

public abstract class BasicWindow extends Observable implements Runnable {

	protected Display display;
	protected Shell shell;
	
	/**
	 * Ctor
	 * @param title - The main window's title.
	 */
	public BasicWindow(String title) {
		super();
		display = new Display();
		shell =new Shell(display);
		shell.setText(title);
	}

	/**
	 * This method will load all widgets into the main window.
	 */
	public abstract void initWidgets();

	/**
	 * This method will return the shell.
	 * @return Shell
	 */
	public Shell getShell() {
		return shell;
	}
	
	
	/**
	 * return the display
	 * @return Display
	 */
	public Display getDisplay() {
		return display;
	}

	/**
	 * This method will run the main load the main window.
	 */
	
	@Override
	public void run() {
		initWidgets();
		shell.open();

		// main event loop
		 while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ 	// if the queue is empty
		       display.sleep(); 			// sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components
		
	}

}
