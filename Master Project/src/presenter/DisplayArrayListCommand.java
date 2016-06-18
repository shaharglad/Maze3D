package presenter;

import cliDisplays.DisplaySolution;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class DisplayArrayListCommand extends CommonViewCommand {

	public DisplayArrayListCommand(View v, Model m) {
		super(v, m);
	}

	@Override
	public void doCommand(String[] args) {
		@SuppressWarnings("unchecked")
		Solution<Position> solution= (Solution<Position>) this.getM().getObj();
		if(solution != null){
			getV().display(solution, new DisplaySolution(getV()));
		}
		else{
			this.setMessage("Unable to get the solution");
			this.getM().displayMessage(this.getMessage());
			return;
		}
		
	}

}
