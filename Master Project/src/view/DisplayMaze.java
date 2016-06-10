package view;

import java.io.IOException;

import algorithms.search.SearchableMaze3dAdapter;

/**
 * 
 * @author Shahar & Bar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class DisplayMaze extends CommonDisplayType {

	public DisplayMaze(View v) {
		super(v);
	}

	/**
	 * This method will display rhe maze.
	 * @paramobj - the maze we want to display.
	 */
	
	@Override
	public void display(Object obj) {
		try {
			((MyView)this.getV()).getOut().write(((SearchableMaze3dAdapter)obj).getMaze().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			((MyView)this.getV()).getOut().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}
