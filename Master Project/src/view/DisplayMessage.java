package view;

import java.io.IOException;

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
