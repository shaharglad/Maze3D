package presenter;

import algorithms.search.SearchableMaze3dAdapter;
import model.Model;
import view.DisplayCross;
import view.DisplayMaze;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class DisplayCrossCommand extends CommonViewCommand {

	public DisplayCrossCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) {
		int[][] maze2d= (int[][]) this.getM().getObj();
		if(maze2d != null){
			getV().display(maze2d, new DisplayCross(getV()));
		}
		else{
			this.setMessage("Unable to get maze 2d");
			this.getM().displayMessage(this.getMessage());
			return;
		}
		
		
	}

}
