package model;

public class Muzik extends BaseModel{

	public enum CHANNEL
	{
	  KANAL_1, KANAL_2, KANAL_3, KANAL_4
	}
	
	private CHANNEL channel;
	private int volume=0;
	public CHANNEL getChannel() {
		return channel;
	}
	public void setChannel(CHANNEL channel) {
		this.channel = channel;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
}
