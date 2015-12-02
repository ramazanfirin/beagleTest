package model;

import constants.Constants;

public class BaseToggleButton {
	Boolean status=false;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getColor() {
		if(status)
			return Constants.COLOR_AQUA;
		else
			return Constants.COLOR_RED;
	}
	
	public String getMessage() {
		if(status)
			return Constants.MESSAGE_OPEN;
		else
			return Constants.MESSAGE_CLOSE;
	}
}
