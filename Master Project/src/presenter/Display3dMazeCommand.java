package presenter;

import cliDisplays.DisplayMaze;
import cliDisplays.DisplayMessage;
import algorithms.search.SearchableMaze3dAdapter;
import model.Model;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 09.06.16
 *
 */

public class Display3dMazeCommand extends CommonViewCommand {

	public Display3dMazeCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) {
		SearchableMaze3dAdapter maze3d = (SearchableMaze3dAdapter) this.getM().getObj();
		if(maze3d != null){
			getV().display(maze3d, new DisplayMaze(getV()));
		}
		else{
			this.setMessage("Unable to get maze 3d");
			this.getM().displayMessage(this.getMessage());
			return;
		}
		
	}

}
