import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Klima;


@ManagedBean
@ApplicationScoped
public class KlimaController {

	Klima klima= new Klima();
	
	public void setKlimaStatusTamDebi(){
		klima.setStatus(Klima.STATUS.TAM_DEBI);
	}
	
	public void setKlimaStatusYarimDebi(){
		klima.setStatus(Klima.STATUS.YARIM_DEBI);	
	}

	public void setKlimaStatusKapali(){
		klima.setStatus(Klima.STATUS.KAPALI);
	}
	
	public void changeKlimaStatusIsitici(){
		klima.getIsiticiStatus().setStatus(!klima.getIsiticiStatus().getStatus());
	}

	public Klima getKlima() {
		return klima;
	}

	public void setKlima(Klima klima) {
		this.klima = klima;
	}
	
}