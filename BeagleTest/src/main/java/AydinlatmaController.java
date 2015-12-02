import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Lamba;

@ManagedBean
@ApplicationScoped
public class AydinlatmaController {
	
Lamba lamba1= new Lamba();	
Lamba lamba2= new Lamba();	
Lamba lamba3= new Lamba();	
Lamba lamba4= new Lamba();	

Lamba operasyonLamba1= new Lamba();	
Lamba operasyonLamba2= new Lamba();	
Lamba uvLamba= new Lamba();	
Lamba negLamba= new Lamba();	

public void changeLamba1Status(){
	lamba1.setStatus(!lamba1.getStatus());
}

public void changeLamba2Status(){
	lamba2.setStatus(!lamba2.getStatus());
}
public void changeLamba3Status(){
	lamba3.setStatus(!lamba3.getStatus());
}
public void changeLamba4Status(){
	lamba4.setStatus(!lamba4.getStatus());
}
public void changeOperasyonLamba1Status(){
	operasyonLamba1.setStatus(!operasyonLamba1.getStatus());
}
public void changeOperasyonLamba2Status(){
	operasyonLamba2.setStatus(!operasyonLamba2.getStatus());
}
public void changeUvLambaStatus(){
	uvLamba.setStatus(!uvLamba.getStatus());
}
public void changeNegLambaStatus(){
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