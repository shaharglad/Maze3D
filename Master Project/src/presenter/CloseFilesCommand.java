package presenter;

import model.Model;
import view.Exit;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 09.06.16
 *
 */

public class CloseFilesCommand extends CommonViewCommand {

	public CloseFilesCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) {
		Exit exit = new Exit(getV());
		getV().exit(exit);
		
	}

}
