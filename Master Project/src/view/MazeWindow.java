package view;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Listener;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 11.06.16
 *
 */
public class MazeWindow extends BasicWindow {

	HashMap<String,Listener> buttons;
	Button generateButton;
	Button hintButton;
	Button solveButton;
	
	/**
	 * Ctor
	 * @param title
	 */
	public MazeWindow(String title) {
		super(title);
	}

	/**
	 * This method will set the buttons hashMap.
	 * @param buttons - another hashMap. 
	 */
	public void setButtons(HashMap<String, Listener> buttons) {
		this.buttons = buttons;
	}
	
	/**
	 * This method will load all widgets into the main window.
	 */
	@Override
	public void initWidgets() {
		Menu menuBar, fileMenu, helpMenu;
		MenuItem fileItem, helpItem;
		shell.setLayout(new GridLayout(2, false));
		
		//----------Menu--------------//
		menuBar = new Menu(shell, SWT.BAR);
		
		//-----File Menu--------//
		fileMenu = new Menu(menuBar);
		fileItem = new MenuItem(menuBar, SWT.CASCADE);
		fileItem.setText("File");
		fileItem.setMenu(fileMenu);
		MenuItem propertiesItem = new MenuItem(fileMenu, SWT.PUSH);
		propertiesItem.setText("Open Properties");
		propertiesItem.addListener(SWT.Selection, buttons.get("Properties"));
		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("Exit");
		exitItem.addListener(SWT.Selection, buttons.get("Exit"));
	    // the "X" button at the corner
	    shell.addListener(SWT.Close, buttons.get("Exit"));
		

		//-------Help Item----------//
		helpMenu = new Menu(menuBar);
		helpItem = new MenuItem(menuBar, SWT.CASCADE);
		helpItem.setText("Help");
		helpItem.setMenu(helpMenu);
		MenuItem about = new MenuItem(helpMenu, SWT.PUSH);
		about.setText("About");
		about.addListener(SWT.Selection, buttons.get("About"));
		
		//-------Buttons-------//
		//-------Generate Button------//
		generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 3, 1));
		generateButton.addListener(SWT.Selection, buttons.get("Generate"));
		
		//-------Hint Button------//
		hintButton=new Button(shell, SWT.PUSH);
		hintButton.setText("Hint");
		hintButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 3, 1));
		//hintButton.addListener(SWT.Selection, buttons.get("generate"));
		
		//-------Solve Button------//
		solveButton=new Button(shell, SWT.PUSH);
		solveButton.setText("Solve");
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 3, 1));
		//solveButton.addListener(SWT.Selection, buttons.get("generate"));
		
		shell.setMenuBar(menuBar);
		
		
	}

}