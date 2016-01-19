package model;

import constants.Constants;

public class BaseModel {
	protected String selectedColor = Constants.COLOR_AQUA;
	protected String unselectedColor =Constants.COLOR_RED;
	
	protected String defaultColor=Constants.COLOR_GREEN;;
	protected String errorColor=Constants.COLOR_RED;;
	
	protected Boolean errorStatus=false;
	public String getColor(){
		if(errorStatus)
			return errorColor;
		else
			return defaultColor;
	}
	
	public BaseModel(String label) {
		super();
		this.label = label;
	}
	
	public BaseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected String label;
	protected Double value;
	protected Boolean render = true;
	
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	public Boolean getRender() {
		return render;
	}

	public void setRender(Boolean render) {
		this.render = render;
	}

	public String getErrorColor() {
		return errorColor;
	}

	public void setErrorColor(String errorColor) {
		this.errorColor = errorColor;
	}

	public Boolean getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(Boolean errorStatus) {
		this.errorStatus = errorStatus;
	}
}
