package model;

import constants.Constants;

public class Klima extends BaseModel{

	public enum STATUS
	{
	  TAM_DEBI, YARIM_DEBI, KAPALI 
	}
	
	private STATUS status;
	private BaseToggleButton isiticiStatus = new BaseToggleButton();
	
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


}
