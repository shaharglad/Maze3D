package cliDisplays;

import view.View;


/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 09.06.16
 *
 */

public abstract class CommonDisplayType implements DisplayType {

private View v;
	
	/**
	 * Ctor
	 * @param v
	 */
 
	public CommonDisplayType(View v) {
		super();
		this.v = v;
	}

	/**
	 * thie method will return the view.
	 * @return
	 */

	public View getV() {
		return v;
	}

	/**
	 * This method will set the view.
	 * @param v - The view we want to set.
	 */

	public void setV(View v) {
		this.v = v;
	}



	@Override
	public abstract void display(Object obj);

}
