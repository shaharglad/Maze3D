package cliDisplays;

import java.io.IOException;

import view.MyView;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class DisplayCross extends CommonDisplayType {

	public DisplayCross(View v) {
		super(v);
	}

	/**
	 * This method will display the maze cross section by any coordinate.
	 * @param obj - The object we want to display
	 */
	
	@Override
	public void display(Object obj){
		for(int[] i : (int[][])obj){
			for(int j : i){
				try {
					((MyView)this.getV()).getOut().write(((Integer)j).toString());
					((MyView)this.getV()).getOut().write("");
					((MyView)this.getV()).getOut().flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
					
	}

}
