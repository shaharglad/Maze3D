package presenter;

import model.Model;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class FileSizeCommand extends CommonModelCommand {

	/**
	 * Ctor
	 * @param model
	 */
	
	public FileSizeCommand(Model model) {
		super(model);
	}

	/**
	 * This method will call to a method that check a file size.
	 * @param args - The arguments for the method.
	 */
	
	@Override
	public void doCommand(String[] args) {
		if (args == null ||args.length < 1){
			this.setMessage("Incorrect number of args");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		String path = args[0];
		this.getModel().fileSize(path);		
	}
}
