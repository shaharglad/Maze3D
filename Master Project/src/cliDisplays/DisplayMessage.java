package cliDisplays;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import view.MyView;
import view.Gui;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 13.06.16
 *
 */
public class DisplayMessage extends CommonDisplayType {

	/**
	 * Ctor
	 * @param v
	 */
	public DisplayMessage(View v) {
		super(v);
	}

	/**
	 * This method will display messages using the appropriate view: CLI or GUI.
	 */
	@SuppressWarnings("static-access")
	@Override
	public void display(final Object obj) {
		if(getV().getClass().getCanonicalName().contains("MyView")){
			try {
				((MyView)this.getV()).getOut().write((String)obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				((MyView)this.getV()).getOut().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(getV().getClass().getCanonicalName().contains("Gui")){
			final Shell sh = ((Gui) this.getV()).getMazeWindow().getShell();
			Display dis =((Gui) this.getV()).getMazeWindow().getDisplay();
			if(((String)obj).contains("Properties")){
				dis.getDefault().asyncExec(new Runnable() {
					public void run() {
						MessageBox messageBox = new MessageBox(sh,  SWT.ICON_INFORMATION | SWT.OK);
						messageBox.setMessage(((String)obj));
						messageBox.setText("Load completed Succesfully!");	
						messageBox.open();	
					}
				});
			}
			else{
				dis.getDefault().asyncExec(new Runnable() {
					public void run() {
						MessageBox messageBox = new MessageBox(sh,  SWT.ICON_WARNING | SWT.OK);
						messageBox.setMessage(((String)obj));
						messageBox.setText("Error");	
						messageBox.open();	
					}
				});
			}
		}
	}
		

}
