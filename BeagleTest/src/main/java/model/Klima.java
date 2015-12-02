package model;

import constants.Constants;

public class Klima {

	public enum STATUS
	{
	  TAM_DEBI, YARIM_DEBI, KAPALI 
	}
	
	private STATUS status;
	private BaseToggleButton isiticiStatus = new BaseToggleButton();
	
	private String selectedColor = Constants.COLOR_AQUA;
	private String unselectedColor =Constants.COLOR_RED;
	
	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public BaseToggleButton getIsiticiStatus() {
		return isiticiStatus;
	}

	public void setIsiticiStatus(BaseToggleButton isiticiStatus) {
		this.isiticiStatus = isiticiStatus;
	}

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
