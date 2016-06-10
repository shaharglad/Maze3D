package presenter;

import model.Model;

public abstract class CommonModelCommand implements Command {

	private Model model;
	private String message;
	
	/**
	 * Ctor
	 * @param model
	 */
	
	public CommonModelCommand(Model model) {
		super();
		this.model = model;
	}

	/**
	 * Return the message
	 * @return String
	 */
	
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message
	 * @param message - The message we want to set.
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Return the model
	 * @return
	 */
	
	public Model getModel() {
		return model;
	}

	/**
	 * Set the specified model.
	 * @param model - The model we want to set.
	 */

	public void setModel(Model model) {
		this.model = model;
	}


	@Override
	public abstract void doCommand(String[] args); 

}
