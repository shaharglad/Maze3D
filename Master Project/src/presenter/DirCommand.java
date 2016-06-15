package presenter;

import java.io.IOException;

import model.Model;

public class DirCommand extends CommonModelCommand {

	public DirCommand(Model model) {
		super(model);
	}

	/**
	 * This command will display the files and directories in the directory that specified in the argument.
	 * @paramsrgs - The argument for the method.
	 */
	
	@Override
	public void doCommand(String[] args) {
		if (args ==null || args.length < 1){
			setMessage("Incorrect number of args");
			getModel().displayMessage(getMessage());
			return;
		}
		String path = args[0];
		
		this.getModel().dir(path);
	}
}
