package Items;

public class Gegenstand {
	private String name;
	private String beschreibung;
	private double gewicht;
	
	public Gegenstand(String name, String beschreibung, double gewicht)
	{
		this.name = name;
		this.beschreibung = beschreibung;
		this.gewicht = gewicht;
	}

	public String getName() {
		return name;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}

	public double getGewicht() {
		return gewicht;
	}

	/**
	 * Liefere eine lange Beschreibung des Gegenstandes im Format
	 *    ein Schatz, 2.5 kg
	 * @return beschreibung des Gegenstandes und sein Gewicht
	 */
	public String toString() {
		return beschreibung + ", "+ gewicht + "kg";
	}
	
	
	
	

}
