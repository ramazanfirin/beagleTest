import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Constants;
import model.Klima;
import model.Muzik;


@ManagedBean
@RequestScoped
public class MuzikController extends BaseController{

	Muzik muzik= new Muzik();
	
	@Override
	void prepareData() {
		
	int auidoChannel= dataController.getModbusValues()[Constants.AUDIO_CHANNEL_SELECT];
	if(auidoChannel==1)
		muzik.setChannel(Muzik.CHANNEL.KANAL_1);
	else if (auidoChannel==2)
		muzik.setChannel(Muzik.CHANNEL.KANAL_2);
	else if (auidoChannel==3)
		muzik.setChannel(Muzik.CHANNEL.KANAL_3);
	else if (auidoChannel==4)
		muzik.setChannel(Muzik.CHANNEL.KANAL_4);

		
	}
	
	public void setChannel1(){
		muzik.setChannel(Muzik.CHANNEL.KANAL_1);
	}
	
	public void setChannel2(){
		muzik.setChannel(Muzik.CHANNEL.KANAL_2);	
	}

	public void setChannel3(){
		muzik.setChannel(Muzik.CHANNEL.KANAL_3);
	}
	
	public void setChannel4(){
		muzik.setChannel(Muzik.CHANNEL.KANAL_4);
	}
	
	public void changeVolume(){
		muzik.setVolume(0);
	}

	public Muzik getMuzik() {
		return muzik;
	}

	public void setMuzik(Muzik muzik) {
		this.muzik = muzik;
	}

	

	
	
}