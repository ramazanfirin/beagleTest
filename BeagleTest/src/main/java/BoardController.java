import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.AmeliyatButton;
import model.BaseModel;
import model.BaseToggleButton;
import constants.Constants;
import constants.Util;

@ManagedBean
@SessionScoped
public class BoardController extends BaseController{
	private String sicaklik="34";
	private String nem="53";
	private String odaBasinci="65";
	private String filtreBasinci="115";
	private String temp = "0";
	
	private String n20="115";
	private String air5="115";
	private String o2="115";
	private String vac="115";
	private String co2="115";
	private String abc="115";
	
	private List<BaseModel> valueList=new ArrayList<BaseModel>();
	
	private BaseModel value1=new BaseModel("Sıcaklık");
	private BaseModel value2=new BaseModel("Nem");
	private BaseModel value3=new BaseModel("O.Basıncı");
	private BaseModel value4=new BaseModel("F.Basıncı");
	private BaseModel value5=new BaseModel("Temp");
	
	private BaseModel value6=new BaseModel("n20");
	private BaseModel value7=new BaseModel("air5");
	private BaseModel value8=new BaseModel("02");
	private BaseModel value9=new BaseModel("vac");
	private BaseModel value10=new BaseModel("co2");
	
	private BaseModel value11=new BaseModel("");
	private BaseModel value12=new BaseModel("");
	private BaseModel value13=new BaseModel("");
	private BaseModel value14=new BaseModel("");
	private BaseModel value15=new BaseModel("");
	
	
	
	
	private String sicaklikColor="bg-aqua";
	
	private AmeliyatButton ameliyatButton = new AmeliyatButton("Ameliyat");
	private BaseToggleButton alarmButton = new BaseToggleButton("Alarm");
	
	
	private String alarmIconColor="none";
	@PostConstruct
	public void init(){
		
		valueList.add(value1);
		valueList.add(value2);
		valueList.add(value3);
		valueList.add(value4);
		valueList.add(value5);
		valueList.add(value6);
		valueList.add(value7);
		valueList.add(value8);
		valueList.add(value9);
		valueList.add(value10);
		valueList.add(value11);
		valueList.add(value12);
		valueList.add(value13);
		valueList.add(value14);
		valueList.add(value15);
		prepareData();

		
	}
	
	public void prepareData(){
//		sicaklik = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_1]);
//		nem = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_2]);
//		odaBasinci = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_3]);
//		filtreBasinci = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_4]);
//		temp="0";
//		n20 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_5]);
//		air5 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_6]);
//		o2 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_7]);
//		vac = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_8]);
//		co2 = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_9]);
//		abc = String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_10]);
		disableAllAlarms();
		checkAlarms((dataController.getModbusValues()[Constants.ANALOG_INPUTS_ALARM_STATUS_BITS_HIGH_ALARM]));
		checkAlarms((dataController.getModbusValues()[Constants.ANALOG_INPUTS_ALARM_STATUS_BITS_HIGH_ALARM]));
		
		value1.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_1]));
		value2.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_2]));
		value3.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_3]));
		value4.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_4]));
		value5.setValue(new Double(0));
		
		value6.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_6]));
		value7.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_7]));
		value8.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_8]));
		value9.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_9]));
		value10.setValue(new Double(dataController.getModbusValues()[Constants.ANALOG_INPUT_10]));
		
		prepareValuesByName(value11,Constants.GAS_ALARM_DISPLAY_LABEL_1_CHAR_1,Constants.ANALOG_INPUT_11);
		prepareValuesByName(value12,Constants.GAS_ALARM_DISPLAY_LABEL_2_CHAR_1,Constants.ANALOG_INPUT_12);
		prepareValuesByName(value13,Constants.GAS_ALARM_DISPLAY_LABEL_3_CHAR_1,Constants.ANALOG_INPUT_13);
		prepareValuesByName(value14,Constants.GAS_ALARM_DISPLAY_LABEL_4_CHAR_1,Constants.ANALOG_INPUT_14);
		prepareValuesByName(value15,Constants.GAS_ALARM_DISPLAY_LABEL_5_CHAR_1,Constants.ANALOG_INPUT_15);
		
		
		ameliyatButton.setStatus(Util.translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_11])));
		alarmButton.setStatus(Util.translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_INPUT_12])));
		try {
			checkAlarm();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void disableAllAlarms(){
		for (Iterator iterator = valueList.iterator(); iterator.hasNext();) {
			BaseModel baseModel = (BaseModel) iterator.next();
			baseModel.setErrorStatus(false);
		}
	}
	
	public void checkAlarms(int alarms){
		if(alarms==0)
			return;
		String alarmBits = Integer.toBinaryString(alarms);
		for (int i = 0; i < alarmBits.length(); i++) {
			String temp=alarmBits.substring(i, i+1);
			if(temp.equals("1")){
				//int tempint= Integer.parseInt(i);
				valueList.get(i).setErrorStatus(true);
			}
		}
	}
	
	
	public void prepareValuesByName(BaseModel value,int labelStartAddress,int valueAddress){
		String label = prepareEtiketData(labelStartAddress);
		//System.out.println(label);label.length();
		String a = new String(label.getBytes());
		if(!a.replaceAll(" ", "").equals("")){
			value.setValue(new Double(dataController.getModbusValues()[valueAddress]));
			value.setLabel(label);
			value.setRender(true);
		}else
			value.setRender(false);
		
	}
	
	public void changeStatusAmeliyatButton() throws Exception{
		saveModbus(Constants.ANALOG_INPUT_11, Util.translate(!ameliyatButton.getStatus()));
		ameliyatButton.setStatus(!ameliyatButton.getStatus());

	}
	
	public void changeStatusAlarmButton() throws Exception{
		if(!getAlarmStatus())
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alarm aktif degildir."));
		else{
			saveModbus(Constants.ANALOG_INPUT_12, Util.translate(!alarmButton.getStatus()));
			alarmButton.setStatus(!alarmButton.getStatus());
		}
	}
	
	public void changeAlarmIconBackground() throws IOException{
		checkAlarm();
		if(super.getAlarmStatus()){
			setAlarmIconChanged(!getAlarmIconChanged());
			if(getAlarmIconChanged()){
				alarmIconColor = Constants.ALAR_ICON_BACKGORUND;
			}else
				alarmIconColor= 	"none";
		
		}else
			alarmIconColor= "none";
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

	public BaseToggleButton getAlarmButton() {
		return alarmButton;
	}

	public void setAlarmButton(BaseToggleButton alarmButton) {
		this.alarmButton = alarmButton;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public BaseModel getValue1() {
		return value1;
	}

	public void setValue1(BaseModel value1) {
		this.value1 = value1;
	}

	public BaseModel getValue2() {
		return value2;
	}

	public void setValue2(BaseModel value2) {
		this.value2 = value2;
	}

	public BaseModel getValue3() {
		return value3;
	}

	public void setValue3(BaseModel value3) {
		this.value3 = value3;
	}

	public BaseModel getValue4() {
		return value4;
	}

	public void setValue4(BaseModel value4) {
		this.value4 = value4;
	}

	public BaseModel getValue5() {
		return value5;
	}

	public void setValue5(BaseModel value5) {
		this.value5 = value5;
	}

	public BaseModel getValue6() {
		return value6;
	}

	public void setValue6(BaseModel value6) {
		this.value6 = value6;
	}

	public BaseModel getValue7() {
		return value7;
	}

	public void setValue7(BaseModel value7) {
		this.value7 = value7;
	}

	public BaseModel getValue8() {
		return value8;
	}

	public void setValue8(BaseModel value8) {
		this.value8 = value8;
	}

	public BaseModel getValue9() {
		return value9;
	}

	public void setValue9(BaseModel value9) {
		this.value9 = value9;
	}

	public BaseModel getValue10() {
		return value10;
	}

	public void setValue10(BaseModel value10) {
		this.value10 = value10;
	}

	public BaseModel getValue11() {
		return value11;
	}

	public void setValue11(BaseModel value11) {
		this.value11 = value11;
	}

	public BaseModel getValue12() {
		return value12;
	}

	public void setValue12(BaseModel value12) {
		this.value12 = value12;
	}

	public BaseModel getValue13() {
		return value13;
	}

	public void setValue13(BaseModel value13) {
		this.value13 = value13;
	}

	public BaseModel getValue14() {
		return value14;
	}

	public void setValue14(BaseModel value14) {
		this.value14 = value14;
	}

	public BaseModel getValue15() {
		return value15;
	}

	public void setValue15(BaseModel value15) {
		this.value15 = value15;
	}

	public String getAlarmIconColor() {
		return alarmIconColor;
	}

	public void setAlarmIconColor(String alarmIconColor) {
		this.alarmIconColor = alarmIconColor;
	}

	
}
