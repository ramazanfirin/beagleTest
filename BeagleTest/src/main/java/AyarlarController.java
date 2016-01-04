

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Constants;

@ManagedBean
@RequestScoped
public class AyarlarController extends BaseController{

	int odaBasincDownLimit;
	int odaBasincUpLimit;
	
	int odaSicaklikDownLimit;
	int odaSicaklikUpLimit;
	
	int nemDownLimit;
	int nemUpLimit;
	
	int filtreDownLimit;
	int filtreUpLimit;

	String etiket1;
	String etiket2;
	String etiket3;
	String etiket4;
	String etiket5;

@Override
void prepareData() {
	odaBasincDownLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_DOWN_LIMIT_1];
	odaBasincUpLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_UP_LIMIT_1];
	
	odaSicaklikDownLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_DOWN_LIMIT_2];
	odaSicaklikUpLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_UP_LIMIT_2];
	
	nemDownLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_DOWN_LIMIT_3];
	nemUpLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_UP_LIMIT_3];
	
	filtreDownLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_DOWN_LIMIT_4];
	filtreUpLimit = dataController.getModbusValues()[Constants.ANALOG_INPUT_DOWN_LIMIT_4];
	
	etiket1 = prepareEtiketData(Constants.GAS_ALARM_DISPLAY_LABEL_1_CHAR_1);
	etiket2 = prepareEtiketData(Constants.GAS_ALARM_DISPLAY_LABEL_2_CHAR_1);
	etiket3 = prepareEtiketData(Constants.GAS_ALARM_DISPLAY_LABEL_3_CHAR_1);
	etiket4 = prepareEtiketData(Constants.GAS_ALARM_DISPLAY_LABEL_4_CHAR_1);
	etiket5 = prepareEtiketData(Constants.GAS_ALARM_DISPLAY_LABEL_5_CHAR_1);
}

public String prepareEtiketData(int startAddress){
	String result="";
	for (int i = 0; i < 5; i++) {
		result = result+(char)dataController.getModbusValues()[startAddress+i];
	}
	return result;
}
public String manipulateLabel(String label){
	int labelSize = label.length();
	for (int i = 0; i < 5-labelSize; i++) {
		label=label+" ";
	}
	return label;
}

public void saveEtiket1() throws IOException{
	etiket1 = manipulateLabel(etiket1);
	 for (int i = 0; i < etiket1.length(); i++) {
		  dataController.saveModbus(Constants.GAS_ALARM_DISPLAY_LABEL_1_CHAR_1+i, etiket1.charAt(i));
	  }
}

public void saveEtiket2() throws IOException{
	if(etiket2.equals(""))
		etiket2="     ";
	for (int i = 0; i < etiket2.length(); i++) {
		  dataController.saveModbus(Constants.GAS_ALARM_DISPLAY_LABEL_2_CHAR_1+i, etiket2.charAt(i));
	  }
}

public void saveEtiket3() throws IOException{
	if(etiket3.equals(""))
		etiket3="     ";
	for (int i = 0; i < etiket3.length(); i++) {
		  dataController.saveModbus(Constants.GAS_ALARM_DISPLAY_LABEL_3_CHAR_1+i, etiket3.charAt(i));
	  }
}

public void saveEtiket4() throws IOException{
	if(etiket4.equals(""))
		etiket4="     ";
	for (int i = 0; i < etiket4.length(); i++) {
		  dataController.saveModbus(Constants.GAS_ALARM_DISPLAY_LABEL_4_CHAR_1+i, etiket4.charAt(i));
	  }
}

public void saveEtiket5() throws IOException{
	if(etiket5.equals(""))
		etiket5="     ";
	for (int i = 0; i < etiket5.length(); i++) {
		  dataController.saveModbus(Constants.GAS_ALARM_DISPLAY_LABEL_5_CHAR_1+i, etiket5.charAt(i));
	  }
}

public void saveOdaBasinci() throws IOException{
	saveModbus(Constants.ANALOG_INPUT_DOWN_LIMIT_1, odaBasincDownLimit);
	saveModbus(Constants.ANALOG_INPUT_UP_LIMIT_1, odaBasincUpLimit);
}

public void saveOdaSicakligi() throws IOException{
	saveModbus(Constants.ANALOG_INPUT_DOWN_LIMIT_2, odaSicaklikDownLimit);
	saveModbus(Constants.ANALOG_INPUT_UP_LIMIT_2, odaSicaklikUpLimit);
}

public void saveNem() throws IOException{
	saveModbus(Constants.ANALOG_INPUT_DOWN_LIMIT_3, nemDownLimit);
	saveModbus(Constants.ANALOG_INPUT_UP_LIMIT_3, nemUpLimit);
}

public void saveFiltre() throws IOException{
	saveModbus(Constants.ANALOG_INPUT_DOWN_LIMIT_4, filtreDownLimit);
	saveModbus(Constants.ANALOG_INPUT_UP_LIMIT_4, filtreUpLimit);
}

public int getOdaBasincDownLimit() {
	return odaBasincDownLimit;
}

public void setOdaBasincDownLimit(int odaBasincDownLimit) {
	this.odaBasincDownLimit = odaBasincDownLimit;
}

public int getOdaBasincUpLimit() {
	return odaBasincUpLimit;
}

public void setOdaBasincUpLimit(int odaBasincUpLimit) {
	this.odaBasincUpLimit = odaBasincUpLimit;
}

public int getOdaSicaklikDownLimit() {
	return odaSicaklikDownLimit;
}

public void setOdaSicaklikDownLimit(int odaSicaklikDownLimit) {
	this.odaSicaklikDownLimit = odaSicaklikDownLimit;
}

public int getOdaSicaklikUpLimit() {
	return odaSicaklikUpLimit;
}

public void setOdaSicaklikUpLimit(int odaSicaklikUpLimit) {
	this.odaSicaklikUpLimit = odaSicaklikUpLimit;
}

public int getNemDownLimit() {
	return nemDownLimit;
}

public void setNemDownLimit(int nemDownLimit) {
	this.nemDownLimit = nemDownLimit;
}

public int getNemUpLimit() {
	return nemUpLimit;
}

public void setNemUpLimit(int nemUpLimit) {
	this.nemUpLimit = nemUpLimit;
}

public int getFiltreDownLimit() {
	return filtreDownLimit;
}

public void setFiltreDownLimit(int filtreDownLimit) {
	this.filtreDownLimit = filtreDownLimit;
}

public int getFiltreUpLimit() {
	return filtreUpLimit;
}

public void setFiltreUpLimit(int filtreUpLimit) {
	this.filtreUpLimit = filtreUpLimit;
}

public String getEtiket1() {
	return etiket1;
}

public void setEtiket1(String etiket1) {
	this.etiket1 = etiket1;
}

public String getEtiket2() {
	return etiket2;
}

public void setEtiket2(String etiket2) {
	this.etiket2 = etiket2;
}

public String getEtiket3() {
	return etiket3;
}

public void setEtiket3(String etiket3) {
	this.etiket3 = etiket3;
}

public String getEtiket4() {
	return etiket4;
}

public void setEtiket4(String etiket4) {
	this.etiket4 = etiket4;
}

public String getEtiket5() {
	return etiket5;
}

public void setEtiket5(String etiket5) {
	this.etiket5 = etiket5;
}





}
