package cliDisplays;

import java.io.IOException;

import view.MyView;
import view.View;

public class DisplayMessage extends CommonDisplayType {

	public DisplayMessage(View v) {
		super(v);
	}

	@Override
	public void display(Object obj) {
		try {
			((MyView)this.getV()).getOut().write((String)obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			((MyView)this.getV()).getOut().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
