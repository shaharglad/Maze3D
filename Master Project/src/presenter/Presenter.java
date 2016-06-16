package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import presenter.Command;
import view.View;
import model.Model;

/**
 * 
 * @author Shahar & Bar
 * @version 1.0
 * @since 06.06.16
 *
 */

public class Presenter implements Observer {

	private Model model;
	private View view;
	private HashMap<String, Command> viewCommands;
	private HashMap<String, Command> modelCommands;
	
	/**
	 * Ctor
	 * @param model
	 * @param view
	 */
	
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
		
		this.model.loadAndDecompress();
		buildCommands();
	}
	
	/**
	 * This method will build the hashMaps, one with model commands and one with view commands.
	 */
	
	private void buildCommands() {
		viewCommands = new HashMap<String, Command>();
		viewCommands.put("generate_3d_maze", new Generate3dMazeCommand(model));
		viewCommands.put("save_maze", new SaveMazeCommand(model));
		viewCommands.put("dir", new DirCommand(model));
		viewCommands.put("display_maze", new DisplayMazeCommand(view, model));
		viewCommands.put("display_cross_section_by", new DisplayCrossSectionByCommand(model));
		viewCommands.put("maze_size", new MazeSizeCommand(model));
		viewCommands.put("file_size", new FileSizeCommand(model));
		viewCommands.put("solve", new SolveCommand(model));
		viewCommands.put("display_solution", new DisplaySolutionCommand(model));
		viewCommands.put("load_maze", new LoadMazeCommand(model));
		viewCommands.put("exit", new ExitCommand(model));
		viewCommands.put("close_threads", new CloseThreadsCommand(model));
		viewCommands.put("set_properties", new SetPropertiesCommand(model));
		viewCommands.put("win", new WinCommand(view, model));
		
		modelCommands = new HashMap<String, Command>();
		modelCommands.put("display_message", new DisplayMessageCommand(view, model));
		modelCommands.put("display_string_array", new DisplayStringArrayCommand(view, model));
		modelCommands.put("display_3d_maze", new Display3dMazeCommand(view, model));
		modelCommands.put("display_2d_maze", new DisplayCrossCommand(view, model));
		modelCommands.put("display_long", new DisplayLongCommand(view, model));
		modelCommands.put("display_array_list", new DisplayArrayListCommand(view, model));
		modelCommands.put("close_files", new CloseFilesCommand(view, model));
		
	}

	/**
	 * This method notified by observable about changes.
	 * @param o - The observable that updated.
	 * @param arg - The arguments.
	 */
	
	@Override
	public void update(Observable o, Object arg) {
		if (o == model) {
			String commandName = (String)arg;
			Command command = modelCommands.get(commandName);
			command.doCommand(null);
		}
		else if (o == view) {
			String commandLine = (String)arg;
			String[] arr = commandLine.split(" ");
			String commandName = arr[0];
			String[] args = new String[arr.length - 1];
			System.arraycopy(arr, 1, args, 0, arr.length - 1);
			
			Command command = viewCommands.get(commandName);
			command.doCommand(args);
		}
	}

}
