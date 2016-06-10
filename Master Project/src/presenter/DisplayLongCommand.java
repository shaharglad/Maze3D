package presenter;

import model.Model;
import view.DisplayCross;
import view.DisplayLong;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class DisplayLongCommand extends CommonViewCommand {

	public DisplayLongCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) {
		long size= (long) this.getM().getObj();
		if((Long) size != null){
			getV().display(size, new DisplayLong(getV()));
		}
		else{
			this.setMessage("Unable to get maze's size");
			this.getM().displayMessage(this.getMessage());
			return;
		}
		
	}

}
