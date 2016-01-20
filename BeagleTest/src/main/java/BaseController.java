import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import constants.Constants;


public abstract class BaseController {

	
	@ManagedProperty(value="#{dataController}")
	public DataController dataController;
	
	protected Boolean alarmStatus = false;
	private Boolean alarmIconChanged = new Boolean(false);
	
	abstract void prepareData();
	@PostConstruct
	void init(){
		prepareData();
	}
	
	public void update() throws IOException{
		prepareData();
		checkAlarm();
		chechPhoneRinging();
	}
	
	
	public void checkAlarm() throws IOException{
		if(dataController.getModbusValues()[Constants.ANALOG_INPUTS_ALARM_STATUS_BITS_HIGH_ALARM]>0){
			alarmStatus=true;
		}else if(dataController.getModbusValues()[Constants.ANALOG_INPUTS_ALARM_STATUS_BITS_LOW_ALARM]>0){
			alarmStatus=true;
		}else 
			alarmStatus=false;
	}
	
	public void chechPhoneRinging() throws IOException{
		if(dataController.getModbusValues()[Constants.PHONE_RING]>0){
			redirect("phone3.xhtml");
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
	public void saveModbusMultiple(int address,int[] value) throws IOException{
		dataController.saveModbusMultiple(address, value);
	}
	
	public void readModbus(int address) throws IOException{
		//dataController.saveModbus(address, value);
	}
	
	public String prepareEtiketData(int startAddress){
		String result="";
		for (int i = 0; i < 5; i++) {
			if(dataController.getModbusValues()[startAddress+i]==0)
				result = result+"";
			else
				result = result+(char)dataController.getModbusValues()[startAddress+i];
		}
		return result;
	}
	
	public String getAlarmIconBackground() throws IOException{
		checkAlarm();
		if(getAlarmStatus()){
			setAlarmIconChanged(!getAlarmIconChanged());
			if(getAlarmIconChanged()){
				return Constants.ALAR_ICON_BACKGORUND;
			}else
				return 	"none";
		
		}return "none";
	}

	public DataController getDataController() {
		return dataController;
	}

	public void setDataController(DataController dataController) {
		this.dataController = dataController;
	}
	public Boolean getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(Boolean alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public Boolean getAlarmIconChanged() {
		return alarmIconChanged;
	}
	public void setAlarmIconChanged(Boolean alarmIconChanged) {
		this.alarmIconChanged = alarmIconChanged;
	}
}
