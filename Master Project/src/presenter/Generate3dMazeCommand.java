package presenter;

import model.Model;

public class Generate3dMazeCommand extends CommonModelCommand {

	/**
	 * Ctor
	 * @param m
	 */
	
	public Generate3dMazeCommand(Model model) {
		super(model);
	}

	/**
	 * Command to ganerate maze.
	 * @param args -The arguments we want to use in order to generate 3D maze.
	 */
	
	@Override
	public void doCommand(String[] args) {

			if (args == null || args.length < 4){
				this.setMessage("Incorrect number of args");
				this.getModel().displayMessage(this.getMessage());
				return;
			}
			
			String name = args[0];
			
			if (args[1].matches("[^0-9]*") || args[2].matches("[^0-9]*") || args[3].matches("[^0-9]*")){
				this.setMessage("Rows Cols and Depth must be a number!");
				this.getModel().displayMessage(this.getMessage());
				return;
			}
			
			int rows = Integer.parseInt(args[1]);
			int cols = Integer.parseInt(args[2]);
			int depth = Integer.parseInt(args[3]);
					
			this.getModel().generate3dMaze(name, rows, cols, depth);
		}

		
}

