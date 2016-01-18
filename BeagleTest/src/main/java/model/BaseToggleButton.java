package model;

import constants.Constants;

public class BaseToggleButton extends BaseModel{
	public BaseToggleButton() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseToggleButton(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	Boolean status=false;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getColor() {
		if(status)
			return selectedColor;
		else
			return unselectedColor;
	}
	
	public String getMessage() {
		if(status)
			return Constants.MESSAGE_OPEN;
		else
			return Constants.MESSAGE_CLOSE;
	}
}
