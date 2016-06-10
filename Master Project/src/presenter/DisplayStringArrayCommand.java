package presenter;

import model.Model;
import view.DisplayDir;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 */

public class DisplayStringArrayCommand extends CommonViewCommand {

	/**
	 * Ctor
	 * @param v
	 * @param m
	 */
	
	public DisplayStringArrayCommand(View v, Model m) {
		super(v, m);
	}

	/**
	 * This method will call to the appropriate display method.
	 */
	
	@Override
	public void doCommand(String[] args) {

		String[] paths = (String[]) this.getM().getObj();
		getV().display(paths, new DisplayDir(getV()));
		
	}

}
