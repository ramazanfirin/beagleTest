import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Constants;
import constants.Util;
import model.Klima;
import model.Lamba;


@ManagedBean
@RequestScoped
public class KlimaController extends BaseController{

	Klima klima= new Klima();
	
	Lamba tamDebi= new Lamba();
	Lamba yarimDebi= new Lamba();
	Lamba kapali= new Lamba();
	Lamba isitici= new Lamba();
	
	int digitalPutputs;
	String value;

	@Override
	void prepareData() {
		digitalPutputs = dataController.getModbusValues()[Constants.DIGITAL_OUTPUTS__BITS];
		if(digitalPutputs<0)
			digitalPutputs=digitalPutputs*-1;
		value = Util.convertToBinary(digitalPutputs);
		
//		tamDebi.setStatus(translate(Util.getBit(value, 8)));
//		yarimDebi.setStatus(translate(Util.getBit(value, 9)));
//		yarimDebi.setStatus(translate(Util.getBit(value, 10)));
//		
		
		if(!translate(Util.getBit(value, 8)))
			klima.setStatus(Klima.STATUS.TAM_DEBI);
		
		if (!translate(Util.getBit(value, 9)))
			klima.setStatus(Klima.STATUS.YARIM_DEBI);
		
		if ( (!translate(Util.getBit(value, 8)) && (!translate(Util.getBit(value, 9)))))
			klima.setStatus(Klima.STATUS.KAPALI);
	
//	
		klima.getIsiticiStatus().setStatus(translate(Util.getBit(value, 10)));
	}
	
	public Boolean translate(String a){
		if(a.equals("0"))
			return true;
		else
			return false;
	}
	
	public void setKlimaStatusTamDebi() throws IOException{
		digitalPutputs = Util.updateBit(value, 8, 1);
		value = Util.convertToBinary(digitalPutputs);
		
		digitalPutputs = Util.updateBit(value, 9, 0);
		saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);

		klima.setStatus(Klima.STATUS.TAM_DEBI);
	}
	
	public void setKlimaStatusYarimDebi() throws IOException{
		digitalPutputs = Util.updateBit(value, 9, 1);
		value = Util.convertToBinary(digitalPutputs);
		
		digitalPutputs = Util.updateBit(value, 8, 0);
		saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);

		klima.setStatus(Klima.STATUS.YARIM_DEBI);	
	}

	public void setKlimaStatusKapali() throws IOException{
		digitalPutputs = Util.updateBit(value, 9, 0);
		value = Util.convertToBinary(digitalPutputs);
		
		digitalPutputs = Util.updateBit(value, 8, 0);
		saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
		klima.setStatus(Klima.STATUS.KAPALI);
	}
	
	public void changeKlimaStatusIsitici() throws IOException{
		digitalPutputs = Util.updateBit(value, 10, Util.translate(!klima.getIsiticiStatus().getStatus()));
		saveModbus(Constants.DIGITAL_OUTPUTS__BITS, digitalPutputs);
		klima.getIsiticiStatus().setStatus(!klima.getIsiticiStatus().getStatus());
	}

	public Klima getKlima() {
		return klima;
	}

	public void setKlima(Klima klima) {
		this.klima = klima;
	}

	
}