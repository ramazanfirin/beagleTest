import java.io.IOException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Constants;


@ManagedBean
@RequestScoped
public class PhoneController extends BaseController{
	Date date= new Date();
	
	Date time= new Date();

	
	int saat;
	int dakika;
	int saniye;
	String phoneNumber;
	
	public void save() throws IOException{ 

	  for (int i = 0; i < phoneNumber.length(); i++) {
		  dataController.saveModbus(Constants.TELEPHNUM_DIG1+i, phoneNumber.charAt(i));
	  }
	dataController.saveModbus(Constants.DIAL,1);
	  
	}
	
	public void answer() throws IOException{
		dataController.saveModbus(Constants.ANSWER_THE_PHONE,1);
	}
	
	public void close() throws IOException{
		dataController.saveModbus(Constants.CALL_END,1);
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

	@Override
	void prepareData() {
		// TODO Auto-generated method stub
		
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
