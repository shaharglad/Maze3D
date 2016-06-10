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

public class SolveCommand extends CommonModelCommand {

	/**
	 * Ctor
	 * @param model
	 */
	
	public SolveCommand(Model model) {
		super(model);
	}

	/**
	 * Command to solve the maze
	 * @param -the arguments for that method..
	 */
	
	@Override
	public void doCommand(String[] args) {
		if (args==null || args.length < 1){
			setMessage("Incorrect number of args");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		
		String name = args[0];
		String algo = getModel().getProperties().getSolvingAlgorithm().toLowerCase();
		
		SearchableMaze3dAdapter maze = getModel().getMaze(name);
		if(maze == null){
			setMessage("\nMaze" +name+ " Doesn't Exists!\n");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		if(!(algo.equals("dfs")) && !(algo.equals("bfs")) && !(algo.equals("breadthfirstsearch"))){	
			setMessage("\nSearcher Algorithm name must be: DFS,BFS or BreadthFirstSearch\n");
			this.getModel().displayMessage(this.getMessage());
			return;
		}
		this.getModel().solve(name, algo);
	}

}
