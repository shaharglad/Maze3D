package presenter;

import model.Model;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 12.06.16
 *
 */
public class SetPropertiesCommand extends CommonModelCommand {

	/**
	 * Ctor
	 * @param model
	 */
	public SetPropertiesCommand(Model model) {
		super(model);
	}

	/**
	 * This method will call another method that set the properties file.
	 */
	@Override
	public void doCommand(String[] args) {
		if (args ==null || args.length < 1){
			setMessage("Incorrect number of args");
			getModel().displayMessage(getMessage());
			return;
		}
		String path = args[0];
		
			this.getModel().setProperties(path);
		
	}

}
