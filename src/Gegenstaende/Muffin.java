package Gegenstaende;
public class Muffin extends Gegenstand {
	
	private double erhoehungTragkraft;
	
	public Muffin(String name, String beschreibung, double gewicht, double et)
	{
		super(name, beschreibung, gewicht);
		this.erhoehungTragkraft = et;
	}

	public double getErhoehungTragkraft() {
		return erhoehungTragkraft;
	}
	
	public String toString()
	{
		return (super.toString()+ ", Erhöhung der Tragkraft um "+ erhoehungTragkraft);
		
	}

}
