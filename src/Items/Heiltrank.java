package Items;

import Lebewesen.Spieler;

public class Heiltrank extends Gegenstand {

	private Spieler spieler;
	private double erhoehungZustand;
	
	public Heiltrank(String name, String beschreibung, double gewicht, double ez)
	{
		super(name, beschreibung, gewicht);
		this.erhoehungZustand = ez;
	}
	
	public String toString()
	{
		return (super.toString()+ ", Gesundheit verbessert auf"+ this.spieler.getAktuellerZustand());
		
	}
}