package cliDisplays;

import java.io.IOException;

import view.MyView;
import view.View;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

/**
 * 
 * @author Shahar & Bar
 * @version 1.0 
 * @since 06.06.16
 *
 */

public class DisplaySolution extends CommonDisplayType {

	/**
	 * Ctor.
	 * @param v
	 */
	
	public DisplaySolution(View v) {
		super(v);
	}

	/**
	 * This method will display the solution
	 * @param obj - The object we want to display.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void display(Object obj) {
		for(State<Position> position : ((Solution<Position>)obj).getPath()){
			try {
				((MyView)this.getV()).getOut().write(position.toString());
				((MyView)this.getV()).getOut().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

}
