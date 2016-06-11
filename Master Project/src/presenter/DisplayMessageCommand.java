package presenter;

import cliDisplays.DisplayMessage;
import model.Model;
import view.View;

public class DisplayMessageCommand extends CommonViewCommand {
			
	

		public DisplayMessageCommand(View v, Model m) {
		super(v, m);
	}

		@Override
		public void doCommand(String[] args) {
			String msg = (String) this.getM().getObj();
			getV().display(msg, new DisplayMessage(getV()));
		}
		
}
