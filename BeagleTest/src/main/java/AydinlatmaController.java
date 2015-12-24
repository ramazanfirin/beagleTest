import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Lamba;
import constants.Constants;
import constants.Util;

@ManagedBean
@RequestScoped
public class AydinlatmaController extends BaseController {
	
Lamba lamba1= new Lamba();	
Lamba lamba2= new Lamba();	
Lamba lamba3= new Lamba();	
Lamba lamba4= new Lamba();	

Lamba operasyonLamba1= new Lamba();	
Lamba operasyonLamba2= new Lamba();	
Lamba uvLamba= new Lamba();	
Lamba negLamba= new Lamba();	

@Override
void prepareData() {
	lamba1.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_1])));
	lamba2.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_2])));
	lamba3.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_3])));
	lamba4.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_4])));
	
	operasyonLamba1.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_5])));
	operasyonLamba2.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_6])));
	uvLamba.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_7])));
	negLamba.setStatus(translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_8])));
	
}

public Boolean translate(String a){
	if(a.equals("0"))
		return true;
	else
		return false;
}

public void changeLamba1Status() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_1, Util.translate(!lamba1.getStatus()));
	lamba1.setStatus(!lamba1.getStatus());
}

public void changeLamba2Status() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_2, Util.translate(!lamba2.getStatus()));
	lamba2.setStatus(!lamba2.getStatus());
}
public void changeLamba3Status() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_3, Util.translate(!lamba3.getStatus()));
	lamba3.setStatus(!lamba3.getStatus());
}
public void changeLamba4Status() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_4, Util.translate(!lamba4.getStatus()));
	lamba4.setStatus(!lamba4.getStatus());
}
public void changeOperasyonLamba1Status() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_5, Util.translate(!operasyonLamba1.getStatus()));
	operasyonLamba1.setStatus(!operasyonLamba1.getStatus());
}
public void changeOperasyonLamba2Status() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_6, Util.translate(!operasyonLamba2.getStatus()));
	operasyonLamba2.setStatus(!operasyonLamba2.getStatus());
}
public void changeUvLambaStatus() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_7, Util.translate(!uvLamba.getStatus()));
	uvLamba.setStatus(!uvLamba.getStatus());
}
public void changeNegLambaStatus() throws IOException{
	saveModbus(Constants.ANALOG_OUTPUT_8, Util.translate(!negLamba.getStatus()));
	negLamba.setStatus(!negLamba.getStatus());
}
public Lamba getLamba1() {
	return lamba1;
}

public void setLamba1(Lamba lamba1) {
	this.lamba1 = lamba1;
}

public Lamba getLamba2() {
	return lamba2;
}

public void setLamba2(Lamba lamba2) {
	this.lamba2 = lamba2;
}

public Lamba getLamba3() {
	return lamba3;
}

public void setLamba3(Lamba lamba3) {
	this.lamba3 = lamba3;
}

public Lamba getLamba4() {
	return lamba4;
}

public void setLamba4(Lamba lamba4) {
	this.lamba4 = lamba4;
}

public Lamba getOperasyonLamba1() {
	return operasyonLamba1;
}

public void setOperasyonLamba1(Lamba operasyonLamba1) {
	this.operasyonLamba1 = operasyonLamba1;
}

public Lamba getOperasyonLamba2() {
	return operasyonLamba2;
}

public void setOperasyonLamba2(Lamba operasyonLamba2) {
	this.operasyonLamba2 = operasyonLamba2;
}

public Lamba getUvLamba() {
	return uvLamba;
}

public void setUvLamba(Lamba uvLamba) {
	this.uvLamba = uvLamba;
}

public Lamba getNegLamba() {
	return negLamba;
}

public void setNegLamba(Lamba negLamba) {
	this.negLamba = negLamba;
}


}