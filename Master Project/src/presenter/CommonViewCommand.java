package presenter;

import model.Model;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public abstract class CommonViewCommand implements Command {

	private View v;
	private Model m;
	private String message;
	
	/**
	 * Ctor
	 * @param v
	 * @param m
	 */
	
	public CommonViewCommand(View v, Model m) {
		super();
		this.v = v;
		this.m = m;
	}

	/**
	 * Return the Model
	 * @return
	 */
	
	public Model getM() {
		return m;
	}

	/**
	 * Sets the Model.
	 * @param v - The Model we want to set.
	 */
	
	public void setM(Model m) {
		this.m = m;
	}

	/**
	 * Return the View
	 * @return
	 */
	
	public View getV() {
		return v;
	}

	/**
	 * Sets the view.
	 * @param v - The view we want to set.
	 */
	
	public void setV(View v) {
		this.v = v;
	}
	
	/**
	 * Retuern the message
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



	@Override
	public abstract void doCommand(String[] args) ;

}
