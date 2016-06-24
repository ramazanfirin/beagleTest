

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Constants;

@ManagedBean
@RequestScoped
public class TimerController extends BaseController{

public void startStop() throws IOException{
	dataController.saveModbus(Constants.CHRONOMETER_START_STOP,1);
}

public void reset() throws IOException{
	dataController.saveModbus(Constants.CHRONOMETER_RESET,1);
}

@Override
void prepareData() {
	// TODO Auto-generated method stub
	
}
}
