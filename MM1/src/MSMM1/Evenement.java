package MSMM1;

public class Evenement {
	private int t;
	private String type_event;
	public Evenement(int t, String type_event) {
		super();
		this.t = t;
		this.type_event = type_event;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public String getType_event() {
		return type_event;
	}
	public void setType_event(String type_event) {
		this.type_event = type_event;
	}
	@Override
	public String toString() {
		return "Evenement [t=" + t + ", type_event=" + type_event + "]";
	}
	
}
