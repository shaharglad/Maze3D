package presenter;

import model.Model;

public class SaveMazeCommand extends CommonModelCommand {

	/**
	 * Ctor
	 * @param model
	 */
	
	public SaveMazeCommand(Model model) {
		super(model);
	}

	/**
	 * Command to save the maze
	 * @param args - the arguments for that method.
	 */
	
	@Override
	public void doCommand(String[] args) {
		if (args ==null || args.length < 2){
			setMessage("Incorrect number of args");
			this.getModel().displayMessage(getMessage());
		}
		
		String name = args[0];
		String fileName = args[1];
		
		this.getModel().saveMaze(name, fileName);
		
	}

}
