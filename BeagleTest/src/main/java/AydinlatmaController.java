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

public void changeLamba1Status(){
	lamba1.setStatus(!lamba1.getStatus());
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
}