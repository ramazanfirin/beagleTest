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
	String phoneNumber="";
	
	public void save() throws IOException{ 

	  int y=0;	
	  String a="";
	  for (int i = 0; i < phoneNumber.length(); i=i+2) {
		  if(i+2>phoneNumber.length())
			  a = phoneNumber.substring(i, i+1);
		  else
			  a = phoneNumber.substring(i, i+2);
		 
		  dataController.saveModbus(Constants.TELEPHNUM_DIG1+y, Integer.valueOf(a));
		  y++;
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
		if(dataController.getModbusValues()[Constants.PHONE_RING]==0)
			return;
		
		int phone1 = dataController.getModbusValues()[Constants.TELEPHNUM_DIG1];
		addNumber(phone1);
		
		int phone2 = dataController.getModbusValues()[Constants.TELEPHNUM_DIG2];
		addNumber(phone2);
		
		int phone3 = dataController.getModbusValues()[Constants.TELEPHNUM_DIG3];
		addNumber(phone3);
		
		int phone4 = dataController.getModbusValues()[Constants.TELEPHNUM_DIG4];
		addNumber(phone4);
		
		int phone5 = dataController.getModbusValues()[Constants.TELEPHNUM_DIG5];
		addNumber(phone5);
		
		int phone6 = dataController.getModbusValues()[Constants.TELEPHNUM_DIG6];
		addNumber(phone6);
	}
	
	public void addNumber(int phone){
		if(phone<10){
			String a = String.valueOf(phone);
			phoneNumber = phoneNumber+"0"+a;
		}else{
			String a = String.valueOf(phone);
			phoneNumber = phoneNumber+a.charAt(0)+a.charAt(1);
		}
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
