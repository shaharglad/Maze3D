package cliDisplays;

import java.io.IOException;

import view.MyView;
import view.View;

public class DisplayLong extends CommonDisplayType {

	public DisplayLong(View v) {
		super(v);
	}

	@Override
	public void display(Object obj) {
		try {
			((MyView)this.getV()).getOut().write(((Long)obj).toString());
			((MyView)this.getV()).getOut().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
