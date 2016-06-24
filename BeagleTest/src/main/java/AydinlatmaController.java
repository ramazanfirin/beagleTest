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

int digitalPutputs;
String value;

@Override
void prepareData() {
	digitalPutputs = dataController.getModbusValues()[Constants.DIGITAL_OUTPUTS__BITS];
	if(digitalPutputs<0)
		digitalPutputs=digitalPutputs*-1;
	value = Util.convertToBinary(digitalPutputs);
	
	lamba1.setStatus(translate(Util.getBit(value, 0)));
	lamba2.setStatus(translate(Util.getBit(value, 1)));
	lamba3.setStatus(translate(Util.getBit(value, 2)));
	lamba4.setStatus(translate(Util.getBit(value, 3)));
	
	operasyonLamba1.setStatus(translate(Util.getBit(value, 4)));
	operasyonLamba2.setStatus(translate(Util.getBit(value, 5)));
	negLamba.setStatus(translate(Util.getBit(value, 6)));
	uvLamba.setStatus(translate(Util.getBit(value, 7)));
	
	
}

public Boolean translate(String a){
	if(a.equals("0"))
		return true;
	else
		return false;
}

public void changeLamba1Status() throws IOException{
	digitalPutputs = Util.updateBit(value, 0, Util.translate(!lamba1.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	lamba1.setStatus(!lamba1.getStatus());
}

public void changeLamba2Status() throws IOException{
	digitalPutputs = Util.updateBit(value, 1, Util.translate(!lamba2.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	lamba2.setStatus(!lamba2.getStatus());
}
public void changeLamba3Status() throws IOException{
	digitalPutputs = Util.updateBit(value, 2, Util.translate(!lamba3.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	lamba3.setStatus(!lamba3.getStatus());
}
public void changeLamba4Status() throws IOException{
	digitalPutputs = Util.updateBit(value, 3, Util.translate(!lamba4.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	lamba4.setStatus(!lamba4.getStatus());
}
public void changeOperasyonLamba1Status() throws IOException{
	digitalPutputs = Util.updateBit(value, 4, Util.translate(!operasyonLamba1.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	operasyonLamba1.setStatus(!operasyonLamba1.getStatus());
}
public void changeOperasyonLamba2Status() throws IOException{
	digitalPutputs = Util.updateBit(value, 5, Util.translate(!operasyonLamba2.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	operasyonLamba2.setStatus(!operasyonLamba2.getStatus());
}
public void changeNegLambaStatus() throws IOException{
	digitalPutputs = Util.updateBit(value, 6, Util.translate(!negLamba.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	negLamba.setStatus(!negLamba.getStatus());
}
public void changeUvLambaStatus() throws IOException{
	digitalPutputs = Util.updateBit(value, 7, Util.translate(!uvLamba.getStatus()));
	saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
	uvLamba.setStatus(!uvLamba.getStatus());
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