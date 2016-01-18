package model;

import constants.Constants;

public class AmeliyatButton extends BaseToggleButton{
	
	
	public AmeliyatButton(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public AmeliyatButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		if(status)
			return Constants.MESSAGE_PRESENT;
		else
			return Constants.MESSAGE_APSENT;
	}
}
