package presenter;

import model.Model;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class CloseThreadsCommand extends CommonModelCommand {

	public CloseThreadsCommand(Model model) {
		super(model);
	}

	/**
	 * This method will call another method that close all threads.
	 */
	
	@Override
	public void doCommand(String[] args) {
		this.getModel().closeThreads();
		
	}

}
