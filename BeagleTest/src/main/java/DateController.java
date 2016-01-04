import java.io.IOException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Constants;


@ManagedBean
@RequestScoped
public class DateController extends BaseController{
	Date date= new Date();
	
	Date time= new Date();

	
	int saat;
	int dakika;
	int saniye;
	
	@Override
	void prepareData() {
		saat = dataController.getModbusValues()[Constants.HOUR_WATCH];
		dakika = dataController.getModbusValues()[Constants.MINUTE_WATCH];
		saniye = dataController.getModbusValues()[Constants.SECOND_WATCH];
	}
	
	public void save() throws IOException{
	  dataController.saveModbus(Constants.HOUR_WATCH, saat);
	  dataController.saveModbus(Constants.MINUTE_WATCH, dakika);
	  dataController.saveModbus(Constants.SECOND_WATCH, saniye);
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getSaat() {
		return saat;
	}

	public void setSaat(int saat) {
		this.saat = saat;
	}

	public int getDakika() {
		return dakika;
	}

	public void setDakika(int dakika) {
		this.dakika = dakika;
	}

	public int getSaniye() {
		return saniye;
	}

	public void setSaniye(int saniye) {
		this.saniye = saniye;
	}

	
}
