package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 13.06.16
 *
 */
public abstract class MazeDisplay extends Canvas {

	//Default maze for testing
	/*int[][] mazeData={
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,0,0,1,1,1,1,0,0,0,1},
			{1,0,0,0,1,0,0,1,0,0,1,0,0,0,1},
			{1,0,1,1,1,0,0,1,1,1,1,0,0,0,1},
			{1,0,1,0,0,0,0,0,0,0,1,0,0,0,1},
			{1,0,1,1,1,0,0,0,0,0,1,0,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,1,1,0,0,1,1,1,1,0,0,0,1},
			{1,0,0,0,1,0,0,1,0,0,1,0,0,0,1},
			{1,0,0,0,1,0,0,1,0,0,1,0,0,0,1},
			{1,0,0,0,1,0,0,1,1,1,1,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		};*/
	
	int[][] mazeData={
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
			{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
			{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
			{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
			{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
			{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
			{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
		};

	
	/**
	 * Ctor
	 * @param parent
	 * @param style
	 */
	MazeDisplay(Composite parent, int style) {
		super(parent, style);
	}
	
	/**
	 * sets the current maze
	 */
	public abstract void setCurrentMaze(Maze3d m);
	
	/**
	 * get the current maze
	 */
	public abstract Maze3d getCurrentMaze();
	
	/**
	 * sets the character position parameters
	 */
	public abstract void setCharacterPosition(int x, int y, int z);
	
	/**
	 * Return the position of the character.
	 * @return
	 */
	public abstract Position getCharacter();
	
	/**
	 * moving the character to upper dimension
	 */
	public abstract boolean moveUp();
	
	/**
	 * moving the character to lower dimension
	 */
	public abstract boolean moveDown();
	
	/**
	 * moving the character up
	 */
	public abstract boolean moveForward();
	
	/**
	 * moving the character down
	 */
	public abstract boolean moveBackward();
	
	/**
	 * moving the character left
	 */
	public abstract boolean moveLeft();
	
	/**
	 * moving the character right
	 */
	public abstract boolean moveRight();

	/**
	 * taking the character step by step to the exit by timer task
	 */
	public abstract void WalkToExit(Solution<Position> solution);
	
	/**
	 * moving the character one step to help him. 
	 */
	public abstract void WalkByHint(Solution<Position> solution);

}
