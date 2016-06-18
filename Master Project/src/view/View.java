package view;


import cliDisplays.DisplayType;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 06.06.16
 *
 */

public interface View {
	public void start();
	public void display (Object obj,DisplayType d);
	//public Writer getOut();
	public void exit();
}
