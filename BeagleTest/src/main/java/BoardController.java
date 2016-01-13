import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import model.AmeliyatButton;
import constants.Constants;
import constants.Util;

@ManagedBean
@RequestScoped
public class BoardController extends BaseController{
	private String sicaklik="34";
	private String nem="53";
	private String odaBasinci="65";
	private String filtreBasinci="115";
	private String n20="115";
	private String air5="115";
	private String o2="115";
	private String vac="115";
	private String co2="115";
	private String abc="115";
	
	private String sicaklikColor="bg-aqua";
	
	private AmeliyatButton ameliyatButton = new AmeliyatButton();
	private AmeliyatButton alarmButton = new AmeliyatButton();
	
	@PostConstruct
	public void init(){
		prepareData();
		
	}
	
	public void prepareData(){
		sicaklik = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_1]);
		nem = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_2]);
		odaBasinci = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_3]);
		filtreBasinci = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_4]);
		
		n20 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_5]);
		air5 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_6]);
		o2 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_7]);
		vac = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_8]);
		co2 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_9]);
		abc = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_10]);
		
		ameliyatButton.setStatus(Util.translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_11])));
		alarmButton.setStatus(Util.translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_12])));
	}
	
	public void changeStatusAmeliyatButton() throws Exception{
		saveModbus(Constants.ANALOG_INPUT_11, Util.translate(!ameliyatButton.getStatus()));
		ameliyatButton.setStatus(!ameliyatButton.getStatus());

	}
	
	public void changeStatusAlarmButton() throws Exception{
		saveModbus(Constants.ANALOG_INPUT_12, Util.translate(!alarmButton.getStatus()));
		alarmButton.setStatus(!alarmButton.getStatus());

	}
	
	public String getSicaklikColor() {
		return sicaklikColor;
	}
	public void setSicaklikColor(String sicaklikColor) {
		this.sicaklikColor = sicaklikColor;
	}
	public String getSicaklik() {
		return sicaklik;
	}
	public void setSicaklik(String sicaklik) {
		this.sicaklik = sicaklik;
	}
	public String getNem() {
		return nem;
	}
	public void setNem(String nem) {
		this.nem = nem;
	}
	public String getOdaBasinci() {
		return odaBasinci;
	}
	public void setOdaBasinci(String odaBasinci) {
		this.odaBasinci = odaBasinci;
	}
	public String getFiltreBasinci() {
		return filtreBasinci;
	}
	public void setFiltreBasinci(String filtreBasinci) {
		this.filtreBasinci = filtreBasinci;
	}

	public String getN20() {
		return n20;
	}

	public void setN20(String n20) {
		this.n20 = n20;
	}

	public String getAir5() {
		return air5;
	}

	public void setAir5(String air5) {
		this.air5 = air5;
	}

	public String getO2() {
		return o2;
	}

	public void setO2(String o2) {
		this.o2 = o2;
	}

	public String getVac() {
		return vac;
	}

	public void setVac(String vac) {
		this.vac = vac;
	}

	public String getCo2() {
		return co2;
	}

	public void setCo2(String co2) {
		this.co2 = co2;
	}

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	public AmeliyatButton getAmeliyatButton() {
		return ameliyatButton;
	}

	public void setAmeliyatButton(AmeliyatButton ameliyatButton) {
		this.ameliyatButton = ameliyatButton;
	}

	public AmeliyatButton getAlarmButton() {
		return alarmButton;
	}

	public void setAlarmButton(AmeliyatButton alarmButton) {
		this.alarmButton = alarmButton;
	}
}
