package view;

import java.util.HashMap;
import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.FileDialog;

import cliDisplays.DisplayType;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 12.06.16
 *
 */

public class Gui extends Observable implements View {

	MazeWindow mazeWindow;
	Shell shell;
	HashMap<String, Listener> buttons;
	String currentMazeName;

	/**
	 * Ctor
	 * @param title - The main window title.
	 */
	public Gui(String title) {
		super();
		buttons = new HashMap<String, Listener>();
		this.mazeWindow = new MazeWindow(title);
		this.shell=mazeWindow.getShell();
		initButtons();
		this.mazeWindow.setButtons(buttons);
	}
	
	
	/**
	 * Return the maze window
	 * @return MazeWindow
	 */
	public MazeWindow getMazeWindow() {
		return mazeWindow;
	}



	/**
	 * This method will insert the buttons and map them to the appropriate action in buttons hashMap. 
	 */
	public void initButtons(){
		
		//generate button
		buttons.put("Generate", new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				final Shell sh = new Shell(shell, SWT.TITLE | SWT.SYSTEM_MODAL | SWT.CLOSE | SWT.MAX);
				sh.setLayout(new GridLayout(2,false));
				sh.setSize(300, 300);
				sh.setText("Generate Maze");
				
				Label nameL= new Label(sh, SWT.NONE);
				nameL.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1));
				nameL.setText("Maze Name :");
				final Text nameT = new Text(sh, SWT.BORDER);
				nameT.setLayoutData(new GridData(SWT.NONE, SWT.TOP, true, true, 1, 1));
				
				Label rowL =new Label(sh, SWT.NONE);
				rowL.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
				rowL.setText("Number Of Rows :");
				final Text rowT=new Text(sh, SWT.BORDER);
				rowT.setLayoutData(new GridData(SWT.NONE, SWT.TOP, true, true, 1, 1));

				Label colsL =new Label(sh, SWT.NONE);
				colsL.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
				colsL.setText("Number Of Columns :");
				final Text colsT=new Text(sh, SWT.BORDER);
				colsT.setLayoutData(new GridData(SWT.NONE, SWT.TOP, true, true, 1, 1));
				
				Label depthL =new Label(sh, SWT.NONE);
				depthL.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
				depthL.setText("Depth :");
				final Text depthT=new Text(sh, SWT.BORDER);
				depthT.setLayoutData(new GridData(SWT.NONE, SWT.TOP, true, true, 1, 1));
				
				Button generateB=new Button(sh, SWT.PUSH);
				generateB.setText("Generate Maze");
				generateB.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, true, true, 2, 1));
				generateB.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						currentMazeName = nameT.getText();
						String line="generate_3d_maze " + currentMazeName + " " +  rowT.getText() + " " + colsT.getText() + " " +depthT.getText();
						setChanged();
						notifyObservers(line);
						sh.close();
						
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});				
				sh.open();
			}
			
		});
		
		//Properties Button
		buttons.put("Properties", new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Open");
				fd.setFilterPath("");
				String[] extFilter = {"*.xml"};
				fd.setFilterExtensions(extFilter);
				String fileName = fd.open();
				if(fileName != null){
					String line = "set_properties"+ " " + fileName;
					setChanged();
					notifyObservers(line);
				}
			}
		});
		
		//About Button
		buttons.put("About", new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				Shell aboutSh = new Shell(shell, SWT.TITLE | SWT.SYSTEM_MODAL | SWT.CLOSE);
				aboutSh.setLayout(new GridLayout(2, false));
				aboutSh.setSize(500, 75);
				aboutSh.setText("About");
				
				Label aboutL = new Label(aboutSh, SWT.NONE);
				aboutL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1));
				aboutL.setText("All the rigths to Shahar Gladshtein and Bar Yassure");
				aboutSh.open();
			}
		});
		
		//Exit Button
		buttons.put("Exit", new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				exit();
				shell.dispose();
				System.exit(0);
				
			}
		});
	}
	
	
	@Override
	public void start() {
		mazeWindow.run();
		
	}


	@Override
	public void display(Object obj, DisplayType d) {
		if(d.getClass().getCanonicalName().equals("cliDisplays.DisplayMessage.DisplayMessage")){
			//Maze is ready!!!
			if (((String)obj).contains("is ready!!!")) {
				String line = "display_maze"+ " " + currentMazeName;
				setChanged();
				notifyObservers(line);	
				return;
			}
			// Solution is ready
			else if (((String)obj).contains("is ready")){
				String line = "display_solution"+ " " + currentMazeName;
				setChanged();
				notifyObservers(line);	
				return;
			}
		}
		d.display(obj);
		
	}

	/**
	 * This method will notify the observer to close all threads.
	 */
	@Override
	public void exit() {
		setChanged();
		notifyObservers("close_threads");
		
	}

}
