package presenter;

import model.Model;

/**
 * 
 * @author Shahar & Bar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class LoadMazeCommand extends CommonModelCommand {

	public LoadMazeCommand(Model model) {
		super(model);
	}

	@Override
	public void doCommand(String[] args) {
		if (args == null ||args.length < 2){
			this.setMessage("Incorrect number of args");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		String path = args[0];
		String name = args[1];
		
		this.getModel().loadMaze(path, name);		
	}

}
