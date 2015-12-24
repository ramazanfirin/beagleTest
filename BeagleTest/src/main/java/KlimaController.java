import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Constants;
import constants.Util;
import model.Klima;


@ManagedBean
@RequestScoped
public class KlimaController extends BaseController{

	Klima klima= new Klima();
	

	@Override
	void prepareData() {
		if(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_9]==1)
			klima.setStatus(Klima.STATUS.TAM_DEBI);
		else if (dataController.getModbusValues()[Constants.ANALOG_OUTPUT_9]==2)
			klima.setStatus(Klima.STATUS.YARIM_DEBI);
		else if (dataController.getModbusValues()[Constants.ANALOG_OUTPUT_9]==3)
			klima.setStatus(Klima.STATUS.KAPALI);
	
	
		klima.getIsiticiStatus().setStatus((translate(String.valueOf(dataController.getModbusValues()[Constants.ANALOG_OUTPUT_10]))));
	}
	
	public Boolean translate(String a){
		if(a.equals("0"))
			return true;
		else
			return false;
	}
	
	public void setKlimaStatusTamDebi() throws IOException{
		saveModbus(Constants.ANALOG_OUTPUT_9,1);
		klima.setStatus(Klima.STATUS.TAM_DEBI);
	}
	
	public void setKlimaStatusYarimDebi() throws IOException{
		saveModbus(Constants.ANALOG_OUTPUT_9,2);
		klima.setStatus(Klima.STATUS.YARIM_DEBI);	
	}

	public void setKlimaStatusKapali() throws IOException{
		saveModbus(Constants.ANALOG_OUTPUT_9,3);
		klima.setStatus(Klima.STATUS.KAPALI);
	}
	
	public void changeKlimaStatusIsitici() throws IOException{
		saveModbus(Constants.ANALOG_OUTPUT_10,Util.translate(!klima.getIsiticiStatus().getStatus()));
		klima.getIsiticiStatus().setStatus(!klima.getIsiticiStatus().getStatus());
	}

	public Klima getKlima() {
		return klima;
	}

	public void setKlima(Klima klima) {
		this.klima = klima;
	}

	
}