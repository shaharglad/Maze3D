package presenter;

import model.Model;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class ExitCommand extends CommonModelCommand {

	public ExitCommand(Model model) {
		super(model);
	}

	/**
	 * This method will start an organized exit procedure.
	 */
	
	@Override
	public void doCommand(String[] args) {
		this.getModel().exit();
	}

}
