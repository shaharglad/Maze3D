package presenter;


import model.Model;

/**
 * 
 * @author Shahar
 * @ version 1.0
 * @since 06.06.16
 *
 */

public class MazeSizeCommand extends CommonModelCommand {

	public MazeSizeCommand(Model model) {
		super(model);
	}

	/**
	 * This command will display the maze size in the memory of the maze specified in the argument.
	 * @paramsrgs - The argument for the method.
	 */
	
	@Override
	public void doCommand(String[] args) {
		if (args == null ||args.length < 1){
			this.setMessage("Incorrect number of args");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		String name = args[0];
			this.getModel().mazeSize(name);		
	}

}
