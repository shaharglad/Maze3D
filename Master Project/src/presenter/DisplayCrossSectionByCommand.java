package presenter;

import algorithms.search.SearchableMaze3dAdapter;
import model.Model;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class DisplayCrossSectionByCommand extends CommonModelCommand {

	public DisplayCrossSectionByCommand(Model model) {
		super(model);
	}

	@Override
	public void doCommand(String[] args) {
		if (args == null ||args.length < 3){
			this.setMessage("Incorrect number of args");
			this.getModel().displayMessage(this.getMessage());
			return;
		}

		String section = args[0];
		int index = Integer.parseInt(args[1]);
		String name = args[2];
		SearchableMaze3dAdapter maze = this.getModel().getMaze(name);
		
		if(!(section.equals("x")) && !(section.equals("y")) && !(section.equals("z"))){
			this.setMessage("Incorrect coordinate. coordinate must be: x,y or z");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		
		if (maze == null){
			this.setMessage("Maze " + name + " not found");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		
		this.getModel().displayCrossSection(section, index, name);
			
	}

}
