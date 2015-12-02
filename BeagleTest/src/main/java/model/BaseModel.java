package model;

import constants.Constants;

public class BaseModel {
	protected String selectedColor = Constants.COLOR_AQUA;
	protected String unselectedColor =Constants.COLOR_RED;
	
	public String getSelectedColor() {
		return selectedColor;
	}
	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}
	public String getUnselectedColor() {
		return unselectedColor;
	}
	public void setUnselectedColor(String unselectedColor) {
		this.unselectedColor = unselectedColor;
	}
}
