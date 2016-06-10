package presenter;

import algorithms.search.SearchableMaze3dAdapter;
import model.Model;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class DisplayMazeCommand extends CommonModelCommand {

	/**
	 * Ctor
	 * @param v
	 * @param m
	 */
	
	public DisplayMazeCommand(Model m) {
		super(m);
	}

	/**
	 * Thie method will call to the appropriate display command
	 * @param args - the argument for the command.
	 */
	
	@Override
	public void doCommand(String[] args) {
		if (args == null || args.length < 1){
			this.setMessage("Incorrect number of args");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		
		String name = args[0];
		this.getModel().displayMaze(name);
	}

}
