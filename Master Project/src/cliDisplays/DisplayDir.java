package cliDisplays;

import java.io.IOException;

import view.MyView;
import view.View;

/**
 * 
 * @author Shahar & Bar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class DisplayDir extends CommonDisplayType {

	/**
	 * Ctor
	 * @param v
	 */
	
	public DisplayDir(View v) {
		super(v);
	}

	/**
	 * This method will display array of strings.
	 * @param obj - The object we want to display.
	 */
	
	@Override
	public void display(Object obj) {
		for(String file : (String[])obj){
			try {
				((MyView)this.getV()).getOut().write(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			((MyView)this.getV()).getOut().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}
