package presenter;

import cliDisplays.DisplayMessage;
import model.Model;
import view.View;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 16.06.16
 *
 */
public class WinCommand extends CommonViewCommand {

	public WinCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) {
		String msg = "Congratulations you win!!!";
		getV().display(msg, new DisplayMessage(getV()));
		
	}

}
