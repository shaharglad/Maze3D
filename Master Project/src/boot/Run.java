package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import presenter.Presenter;
import view.Gui;
import view.MyView;
import model.MyModel;

public class Run {

	public static void main(String[] args) {
		
		MyModel model = new MyModel();
		if(model.getProperties().getView().equals("Gui")){
			Gui gui = new Gui("Shahar & Bar Maze");
			
			Presenter presenter = new Presenter(model, gui);
			gui.addObserver(presenter);
			model.addObserver(presenter);
			
			gui.start();
		}
		else if(model.getProperties().getView().equals("cli")){
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(System.out);
			MyView view = new MyView(reader, writer);
			
			Presenter presenter = new Presenter(model, view);
			view.addObserver(presenter);
			model.addObserver(presenter);
			
			view.start();
		}
	}
}
