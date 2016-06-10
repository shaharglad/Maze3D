package presenter;

import model.Model;

public class DisplaySolutionCommand extends CommonModelCommand {

	public DisplaySolutionCommand(Model model) {
		super(model);
	}

	/**
	 * Command to display the solution
	 * @param -the arguments for that method.
	 */
	
	@Override
	public void doCommand(String[] args) {
		if (args == null || args.length < 1){
			this.setMessage("Incorrect number of args");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		String name = args[0];
		this.getModel().DisplaySolution(name);		
	}

}
