package view;


import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 13.06.16
 *
 */
public class Maze3D extends MazeDisplay {
	
	private Maze3d currentMaze;
	public Position character = new Position(0,2,0); // The default character position according to default maze data.
	Image myImage = new Image( getDisplay(), "images/Minion.png" ); //character image
	public Position goal;
	Image goalImage = new Image( getDisplay(), "images/banna.png" ); //character image
	private Timer timer;
	private TimerTask task;
	
	
	/**
	 * Ctor
	 * @param parent
	 * @param style
	 */
	public Maze3D(Composite parent, int style) {
		super(parent, style);

		final Color white = new Color(null, 255, 255, 255);
		setBackground(white);
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null, 0, 0, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));
				
				int width = getSize().x;
				int height = getSize().y;
				
				int mx = width / 2;
				
				double w = (double) width / mazeData[0].length;
				double h = (double) height / mazeData.length;
				for(int  i = 0; i < mazeData.length ; i++){
				   double w0 = 0.7 * w + 0.3 * w * i /mazeData.length;
				   double w1 = 0.7 * w + 0.3 * w * (i+1) /mazeData.length;
				   double start = mx-w0 * mazeData[i].length / 2;
				   double start1 = mx-w1 * mazeData[i].length / 2;
				   for(int j=0; j<mazeData[i].length; j++){
					   double[] dpoints = {start+j*w0,i*h,start+j*w0+w0,i*h,start1+j*w1+w1,i*h+h,start1+j*w1,i*h+h};
					   double cheight = h / 2;
					   if(mazeData[i][j] != 0){
						   paintCube(dpoints, cheight, e);
					   }
					   
					 //draw the character image when he moving
				          if(i==character.getZ() && j==character.getX()){
				        	  e.gc.drawImage(myImage, 0, 0, 220, 220, (int)Math.round(dpoints[0]+2), (int)Math.round(dpoints[1]-cheight/2+2), (int)Math.round((w0+w1)/2/1.5), (int)Math.round(h/1.5));			        	  
				          }
				   }
				}
			}
		});
	}
	
	
	/**
	 * painting single cube on the canvas
	 */
	private void paintCube(double[] p,double h, PaintEvent e){
		
        int[] f=new int[p.length];
        for(int k=0;k<f.length;f[k]=(int)Math.round(p[k]),k++);
        
        e.gc.drawPolygon(f);
        
        int[] r = f.clone();
        for(int k = 1; k < r.length; r[k]=f[k]-(int)(h), k+=2);
        

        int[] b={r[0],r[1],r[2],r[3],f[2],f[3],f[0],f[1]};
        e.gc.drawPolygon(b);
        int[] fr={r[6],r[7],r[4],r[5],f[4],f[5],f[6],f[7]};
        e.gc.drawPolygon(fr);
        
        e.gc.fillPolygon(r);
	}


	@Override
	public void setCurrentMaze(Maze3d m) {
		currentMaze = m;
		goal = m.getGoalPosition();
		mazeData = currentMaze.getCrossSectionByY(currentMaze.getStartPosition().getY());
		setCharacterPosition(currentMaze.getStartPosition().getX(), currentMaze.getStartPosition().getY(), currentMaze.getStartPosition().getZ());
	}


	@Override
	public Maze3d getCurrentMaze() {
		return currentMaze;
	}


	@Override
	public void setCharacterPosition(int x, int y, int z) {
		mazeData = currentMaze.getCrossSectionByY(y);
		moveCharacter(x, y, z);
		
	}


	@Override
	public Position getCharacter() {
		return character;
	}


	@Override
	public void moveUp() {
		Position pos = getCharacter();
		if((pos.getY() - 1 >= 0 && pos.getY() - 1 < currentMaze.getMaze3d()[1].length)){
			mazeData = currentMaze.getCrossSectionByY(pos.getY() - 1);
			moveCharacter(pos.getX(), pos.getY() - 1, pos.getZ());
			redraw();
			update();
			layout();
		}
	}


	@Override
	public void moveDown() {
		Position pos = getCharacter();
		if((pos.getY() + 1 >= 0 && pos.getY() + 1 < currentMaze.getMaze3d()[1].length)){
			mazeData = currentMaze.getCrossSectionByY(pos.getY() + 1);
			moveCharacter(pos.getX(), pos.getY() + 1, pos.getZ());
			redraw();
			update();
			layout();
		}
		
	}


	@Override
	public void moveForward() {
		Position pos = getCharacter();
		moveCharacter(pos.getX(), pos.getY(), pos.getZ() - 1);
	}


	@Override
	public void moveBackward() {
		Position pos = getCharacter();
		moveCharacter(pos.getX(), pos.getY(), pos.getZ() + 1);
	}


	@Override
	public void moveLeft() {
		Position pos = getCharacter();
		moveCharacter(pos.getX() - 1, pos.getY(), pos.getZ());
	}


	@Override
	public void moveRight() {
		Position pos = getCharacter();
		moveCharacter(pos.getX() + 1, pos.getY(), pos.getZ());
	}

	/**
	 * This method will take the character to the goal position step by step.
	 */
	@Override
	public void WalkToExit(final Solution<Position> solution) {
		/*final int size = solution.getPath().size();
		int i = 0;
		Position currentPos = getCharacter();
		for (i = 0; i < size; i++) {
			if(currentPos.equals(solution.getPath().get(i).getState()))
				break;
		}
		i += 1;
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				
				if(i >= 0){
					moveCharacter(solution.getPath().get(i).getState().getX(), solution.getPath().get(i).getState().getY(),
							solution.getPath().get(i).getState().getZ());
					i++;
				}
				else{
					timer.cancel();
					timer.purge();
				}
			}
		};
*/		
		
	}


	@Override
	public void WalkByHint(Solution<Position> solution) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean moveCharacter(int x, int y, int z){
		Position pos = new Position(x, y, z);
		if((x >= 0 && x < currentMaze.getMaze3d().length) && (y >= 0 && y < currentMaze.getMaze3d()[1].length) && (z >= 0 && z < currentMaze.getMaze3d()[0][1].length)){
			if  (currentMaze.getValue(pos) == 0){
				character.setX(x);
				character.setY(y);
				character.setZ(z);
				getDisplay().syncExec(new Runnable() {	
					@Override
					public void run() {
						redraw();
						getShell().update();
						getDisplay().update();
						
					}
				});
				return true;
			}	
		}
		return false;
	}
	
}
