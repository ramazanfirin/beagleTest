import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Muzik;


@ManagedBean
@ApplicationScoped
public class MuzikController {

	Muzik muzik= new Muzik();
	
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