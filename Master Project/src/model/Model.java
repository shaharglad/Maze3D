package model;

import presenter.Properties;
import algorithms.mazeGenerators.Position;
import algorithms.search.SearchableMaze3dAdapter;
import algorithms.search.Solution;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public interface Model {
	public void generate3dMaze(final String name, final int rows, final int cols, final int depth);
	public void saveMaze(String name, String fileName);
	public void displayMessage (String message);
	public Object getObj ();
	public void dir(String path);
	//public void displayMaze (String name);
	public SearchableMaze3dAdapter getMaze(String name);
	public void displayCrossSection(String section, int index, String name);
	public void mazeSize (String name);
	public void fileSize(String path);
	@SuppressWarnings("rawtypes")
	public Solution getSolution(String name);
	public void solve(String name, String searcherAlgoName, Position pos);
	public void DisplaySolution(String name);
	public void loadMaze(String path, String name);
	public void exit();
	public void closeThreads();
	public void loadAndDecompress();
	public void compressAndSave();
	public Properties getProperties();
	public void setProperties(String path);
}
