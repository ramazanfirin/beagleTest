

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import constants.Constants;

@ManagedBean
@ApplicationScoped
public class AyarlarController extends BaseController{

	int odaBasincDownLimit;
	int odaBasincUpLimit;
	
	int odaSicaklikDownLimit;
	int odaSicaklikUpLimit;
	
	int nemDownLimit;
	int nemUpLimit;
	
	int filtreDownLimit;
	int filtreUpLimit;


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





}
