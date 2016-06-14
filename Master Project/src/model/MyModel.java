package model;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import presenter.Properties;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DFS;
import algorithms.search.SearchableMaze3dAdapter;
import algorithms.search.Solution;
/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 */

public class MyModel extends Observable implements Model {
	
	@SuppressWarnings("unused")
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private HashMap<String, SearchableMaze3dAdapter> mazes = new HashMap<String, SearchableMaze3dAdapter>();
	private HashMap<String, Solution<Position>> solutions = new HashMap<String,Solution<Position>>();
	private HashMap<Maze3d,Solution<Position>> mazeToSol = new HashMap<Maze3d, Solution<Position>>(); 
	private Object obj;
	private ExecutorService executor;
	private Properties properties;
	//private static final int threadsNum = 20;
	//private static final String cachingPath = "solutions.txt";
	
	
	/**
	 * Ctor
	 */
	
	public MyModel() {
		this.properties=new Properties("lib/Properties.xml");
		executor = Executors.newFixedThreadPool(Integer.parseInt(this.properties.getMaxThreads()));
	}

	/**
	 * Return the Object
	 * @return
	 */
	
	@Override
	public Object getObj() {
		return obj;
	}
	
	/**
	 * This method will return the properties.
	 * @return Properties
	 */
	@Override
	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * This method will set the properties file.
	 * @param path - The path of the properties file.
	 */
	@Override
	public void setProperties(String path) {
		this.properties = new Properties(path);
		obj = (String) "\nProperties Sucsessfuly Loaded\n" ;
		setChanged();
		notifyObservers("display_message");
	}

	/**
	 * This method will generate searchableMaze3DAdapter using the following parameters.
	 * @param name - The name of the maze.
	 * @param rows - amount of maze's rows (x).
	 * @param cols - amount of maze's columns (y).
	 * @param depth - amount of maze's depth (z).
	 * @return void
	 * 
	 */
	
	@Override
	public void generate3dMaze(final String name, final int rows, final int cols, final int depth) {
		
		executor.submit(new Callable<SearchableMaze3dAdapter>() {
			@Override
			public SearchableMaze3dAdapter call() throws Exception {
				SearchableMaze3dAdapter maze = null;
				
				if(properties.getGenerateAlgorithm().contains("myMaze")){
					maze = new SearchableMaze3dAdapter((new MyMaze3dGenerator()).generate(rows, cols, depth));
				}
				else if(properties.getGenerateAlgorithm().contains("simpleMaze")){
					maze = new SearchableMaze3dAdapter((new SimpleMaze3dGenerator()).generate(rows, cols, depth));
				}
				else{
					obj = (String) "\nThere is no maze generator algorithm with name" + properties.getGenerateAlgorithm() + "\n" ;
					setChanged();
					notifyObservers("display_message");
				}
				
				if(maze != null){
					mazes.put(name, maze);
				}
				
				obj = (String) "\nMaze " + name + " is ready!!!\n";
				setChanged();
				notifyObservers("display_message");
				return maze;				
			}	
			
		});
		
		
		/*---------Runnable---------------------*/
		/*Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {				
				SearchableMaze3dAdapter myMaze = new SearchableMaze3dAdapter((new MyMaze3dGenerator()).generate(rows, cols, depth));
				mazes.put(name, myMaze);
				obj = (String) "\nMaze " + name + " is ready\n";
				setChanged();
				notifyObservers("display_message");				
			}				
		});
		thread.start();	
		threads.add(thread);*/
	}
	
	/**
	 * This function will get the maze from the Maze searchable adapter
	 * @param name - The maze's name of the maze that we want to get. 
	 */
	
	@Override
	public SearchableMaze3dAdapter getMaze(String name) {
		return mazes.get(name);			
	}

	/**
	 * Thie method will save the maze into a file.
	 * @param name - The name of the maze we want to save.
	 * @param fileName - The name of the file where we want to save the maze. 
	 */
	
	@Override
	public void saveMaze(String name, String fileName) {
		if (!mazes.containsKey(name)) {
			obj = (String) "\nMaze " + name + " does not exist\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		SearchableMaze3dAdapter maze = mazes.get(name);
		try {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			byte[] bytes = maze.getMaze().toByteArray();
			out.write(bytes.length);
			out.write(bytes);
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			obj = (String) e.getMessage();
			setChanged();
			notifyObservers("display_message");
			return;
		} catch (IOException e) {
			obj = (String) e.getMessage();
			setChanged();
			notifyObservers("display_message");
			return;
		}
	}

	/**
	 * This method will notify the observer about the messege.
	 * @param message - The message we want to display.  
	 */
	
	@Override
	public void displayMessage(String message) {
		obj = (String) message;
		setChanged();
		notifyObservers("display_message");
		return;
		
	}
	
	/**
	 * This command return the files and directories in the directory that specified in the path.
	 * @param path - The path we want to explore.
	 */
	
	@Override
	public void dir(String path)  {
		File file = new File(path);
		if(file.exists()){
			obj = (String[]) file.list();
			setChanged();
			notifyObservers("display_String_Array");
			return;
		}
		 obj = (String) "\nNot a Valid Path\n";
		 setChanged();
		 notifyObservers("display_message");
		 return;
	}

	/**
	 * This method will notify observer about display maze.
	 * @param name - The maze we want to display
	 */
	//--------NotInUsed--------------//
	/*@Override
	public void displayMaze(String name) {
		if (!mazes.containsKey(name)) {
			obj = (String) "\nMaze " + name + " does not exist\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		else{
			obj = (SearchableMaze3dAdapter) mazes.get(name);
			setChanged();
			notifyObservers("display_3d_maze");
			return;
		}
		
	}*/

	/**
	 * This method will create the maze cross section according to the specified section.
	 * @param section - The coordinate.
	 * @param index - 
	 * @param name - The naze's name.
	 */
	
	@Override
	public void displayCrossSection(String section, int index, String name) {
		SearchableMaze3dAdapter maze = mazes.get(name);
		
		switch (section) {
		case "x":
			if(index < maze.getMaze().getMaze3d().length){
				obj = (int[][]) maze.getMaze().getCrossSectionByX(index);
				setChanged();
				notifyObservers("display_2d_maze");
			}
			else{
				obj = (String) "\nThe index you entered is bigger than the index of x\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
			break;
		case "y":
			if(index < maze.getMaze().getMaze3d()[1].length){
				obj = (int[][]) maze.getMaze().getCrossSectionByY(index);
				setChanged();
				notifyObservers("display_2d_maze");
			}
			else{
				obj = (String) "\nThe index you entered is bigger than the index of y\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
			break;
		case "z":
			if(index < maze.getMaze().getMaze3d()[0][1].length){
				obj = (int[][]) maze.getMaze().getCrossSectionByZ(index);
				setChanged();
				notifyObservers("display_2d_maze");
			}
			else{
				obj = (String) "\nThe index you entered is bigger than the index of z\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
			break;
		}	
		
	}

	/**
	 * This method check the maze's size in memory.
	 * @param name - the name of the maze that we want to exam.
	 */
	
	@Override
	public void mazeSize(String name) {
		SearchableMaze3dAdapter maze = mazes.get(name);
		if(maze == null){
			obj = (String) "\nMaze" +name+ " Doesn't Exists!\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		
		obj = (long) maze.getMaze().toByteArray().length;
		setChanged();
		notifyObservers("display_long");
		return;
	}

	/**
	 * This method return the file's size in memory.
	 * @param name - The file we want to measure.
	 * @return long
	 * @throws IOException 
	 */
	
	@Override
	public void fileSize(String path){
		File file = new File(path);
		if(file.exists()){
			obj = (long) file.length();
			setChanged();
			notifyObservers("display_long");
			return;
		}
		else{
		obj = (String) "\nFile not exists!\n";
		setChanged();
		notifyObservers("display_message");
		return;
		}
	}

	/**
	 * This method will return the specified solution from the hashMap.
	 * @param name - The name of the solution we want to get.
	 * @return Solution.
	 */
	
	@SuppressWarnings("rawtypes")
	@Override
	public Solution getSolution(String name) {
		return this.solutions.get(name);
	}

	/**
	 * This function will solve the maze using the algorithm we get as patameter.
	 * @param name - The maze's name of the maze that we want to solve.
	 * @param searcherAlgoName - The algorithm's name that we want to solve with. 
	 */
	
	@Override
	public void solve(final String name, final String searcherAlgoName) {
		SearchableMaze3dAdapter maze = mazes.get(name);
		if(mazeToSol.containsKey(maze.getMaze())){
			obj = (String) "\nSolution for " + name + " is ready\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		else{
			executor.submit(new Callable<Solution<Position>>() {
				public Solution<Position> call() throws Exception {
					SearchableMaze3dAdapter maze = mazes.get(name);
					Solution<Position> solution = new Solution<Position>();
					switch (searcherAlgoName) {
					case "dfs":
						DFS<Position> dfs = new DFS<Position>();
						solution = dfs.search(maze);
						break;
					case "bfs":
						BFS<Position> bfs = new BFS<Position>();
						solution = bfs.search(maze);
						break;
					case "breadthfirstsearch":
						BreadthFirstSearch<Position> breadthFirstSearch = new BreadthFirstSearch<Position>();
						solution = breadthFirstSearch.search(maze);
						break;
					}
					solutions.put(name, solution);
					mazeToSol.put(maze.getMaze(), solution);
					obj = (String) "\nSolution for " + name + " is ready\n";
					setChanged();
					notifyObservers("display_message");
					return solution;
				}
		});
	}
		/*Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				SearchableMaze3dAdapter maze = mazes.get(name);
				if(maze == null){
					obj = (String) "\nMaze" +name+ " Doesn't Exists!\n";
					setChanged();
					notifyObservers("display_message");
					return;
				}
				if(!(searcherAlgoName.equals("dfs")) && !(searcherAlgoName.equals("bfs")) && !(searcherAlgoName.equals("breadthfirstsearch"))){	
					obj = (String) "\nSearcher Algorithm name must be: DFS,BFS or BreadthFirstSearch\n";
					setChanged();
					notifyObservers("display_message");
					return;
				}
				switch (searcherAlgoName) {
				case "dfs":
					DFS<Position> dfs = new DFS<Position>();
					Solution<Position> dfsSolution = dfs.search(maze);
					solutions.put(name, dfsSolution);
					obj = (String) "\nSolution for " + name + " is ready\n";
					setChanged();
					notifyObservers("display_message");
					break;
				case "bfs":
					BFS<Position> bfs = new BFS<Position>();
					Solution<Position> bfsSolution = bfs.search(maze);
					solutions.put(name, bfsSolution);
					obj = (String) "\nSolution for " + name + " is ready\n";
					setChanged();
					notifyObservers("display_message");
					break;
				case "breadthfirstsearch":
					BreadthFirstSearch<Position> breadthFirstSearch = new BreadthFirstSearch<Position>();
					Solution<Position> breadthfsSolution = breadthFirstSearch.search(maze);
					solutions.put(name, breadthfsSolution);
					obj = (String) "\nSolution for " + name + " is ready\n";
					setChanged();
					notifyObservers("display_message");
					break;
				}
				
			}
		});
		thread.start();	
		threads.add(thread);*/
	}

	/**
	 * This method will notify the observer about display a solution
	 * @param name - The solution we want to display.
	 */
	
	@Override
	public void DisplaySolution(String name) {
		if (!solutions.containsKey(name)) {
			obj = (String) "\nSolution " + name + " does not exist\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		else{
			obj = (Solution<Position>) solutions.get(name);
			setChanged();
			notifyObservers("display_array_list");
			return;
		}
		
	}

	/**
	 * This method will load the maze from a specified file and save it into the HashMap
	 * @param path - the file's path we want to load from.
	 * @param name - The name of the naze we want to load.
	 */
	
	@Override
	public void loadMaze(String path, String name) {
		InputStream in = null;
		try {
			File file = new File(path);

			if (!file.exists()) {
				obj = (String) "\nFile " + path + " not exists!\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
			in = new FileInputStream(path);
			//InputStream in = new MyDecompressorInputStream(new FileInputStream(file))
			int length = in.read();
			System.out.println(length);
			//int length = ((int)file.getTotalSpace())*8;
			byte[] bytes = new byte[length];
			in = new MyDecompressorInputStream(new FileInputStream(file));
			byte[] b = new byte[4];
			in.read(b, 0, 3);
			in.read(bytes);
			SearchableMaze3dAdapter mazeFromFile = new SearchableMaze3dAdapter(new Maze3d(bytes));
			in.close();
			if (mazes.containsKey(name)) {
				mazes.remove(name);
			}
			mazes.put(name, mazeFromFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * This method will close all open threads.
	 */
	
	@Override
	public void exit() {
		compressAndSave();
		setChanged();
		notifyObservers("close_files");
	}

	/**
	 * This method will return mazeToSol hashMap
	 * @return HashMap
	 */
	
	public HashMap<Maze3d, Solution<Position>> getMazeToSol() {
		return mazeToSol;
	}

	/**
	 * This method will close all open threads
	 */
	
	@Override
	public void closeThreads() {
		executor.shutdown();
		@SuppressWarnings("unused")
		boolean allTasksCompleted=false;
		try {
			while(!(allTasksCompleted=executor.awaitTermination(10, TimeUnit.SECONDS))){
				obj = (String) "\nwaiting for all threads to finish...\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
		} catch (InterruptedException e) {
			obj = (String) e.getMessage();
			setChanged();
			notifyObservers("display_message");
			return;
		}
		
		//----------MVC exit-----------//
		/*for (Thread thread : threads) {
			thread.interrupt();
		}*/
	}
	
	/**
	 * This method will compress the mazes To Solutions hashMap and write to a file.
	 */
	@Override
	public void compressAndSave(){
		if(mazeToSol.isEmpty()){
			obj = (String) "\nHash map is empty, there is no solutions to save!\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		else{
			try {
				GZIPOutputStream zip = new GZIPOutputStream(new FileOutputStream(this.properties.getCachingPath()));
				ObjectOutputStream out = new ObjectOutputStream(zip);
				out.writeObject(mazeToSol);
				out.close();
			} catch (IOException e) {
				obj = (String) "\nError while saving the hashMap.\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
		}
	}
	
	
	/**
	 * This method will load the cache file and decompress it into a hashMap.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void loadAndDecompress(){
		File file = new File(this.properties.getCachingPath());
		if (!file.exists()) {
			obj = (String) "\nFile " + this.properties.getCachingPath() + " not exists!\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		else{
			try {
				GZIPInputStream unzip = new GZIPInputStream(new FileInputStream(this.properties.getCachingPath()));
				ObjectInputStream in;
				in = new ObjectInputStream(unzip);
				mazeToSol = (HashMap<Maze3d, Solution<Position>>) in.readObject();
				in.close();
				unzip.close();
			} catch (IOException | ClassNotFoundException e) {
				obj = (String) e.getMessage();
				setChanged();
				notifyObservers("display_message");
				return;
			}
		}
	}
}
