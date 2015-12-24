import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import constants.Constants;


public abstract class BaseController {

	
	@ManagedProperty(value="#{dataController}")
	public DataController dataController;
	
	abstract void prepareData();
	@PostConstruct
	void init(){
		prepareData();
	}
	
	public void update() throws IOException{
		prepareData();
		checkAlarm();
	}
	
	
	public void checkAlarm() throws IOException{
		if(dataController.getModbusValues()[Constants.ANALOG_INPUT_1]>10){
			redirect("klima.xhtml");
		}
	}
	
	public void redirect(String url) throws IOException{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
		String uri = ((HttpServletRequest) context.getRequest()).getRequestURI();
		if(uri.contains(url))
			return;
		else 
			context.redirect(url);
	}
	
	public void saveModbus(int address,int value) throws IOException{
		dataController.saveModbus(address, value);
	}
	
	public void readModbus(int address) throws IOException{
		//dataController.saveModbus(address, value);
	}
	
	
	

	public DataController getDataController() {
		return dataController;
	}

	public void setDataController(DataController dataController) {
		this.dataController = dataController;
	}
}
