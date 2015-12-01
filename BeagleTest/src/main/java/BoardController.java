import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class BoardController {
	private String sicaklik="34";
	private String nem="53";
	private String odaBasinci="65";
	private String filtreBasinci="115";
	
	private String sicaklikColor="bg-aqua";
	
	public String getSicaklikColor() {
		return sicaklikColor;
	}
	public void setSicaklikColor(String sicaklikColor) {
		this.sicaklikColor = sicaklikColor;
	}
	public String getSicaklik() {
		return sicaklik;
	}
	public void setSicaklik(String sicaklik) {
		this.sicaklik = sicaklik;
	}
	public String getNem() {
		return nem;
	}
	public void setNem(String nem) {
		this.nem = nem;
	}
	public String getOdaBasinci() {
		return odaBasinci;
	}
	public void setOdaBasinci(String odaBasinci) {
		this.odaBasinci = odaBasinci;
	}
	public String getFiltreBasinci() {
		return filtreBasinci;
	}
	public void setFiltreBasinci(String filtreBasinci) {
		this.filtreBasinci = filtreBasinci;
	}
}
