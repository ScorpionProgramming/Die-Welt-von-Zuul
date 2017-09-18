package Items;
/**
 * Klasse Gegenstand hat die attribute "name" "beschreibung" und "gewicht"
 *  
 */
public class Gegenstand {

	private String name;
	private String beschreibung;
	private double gewicht;
/**
 * Konstruktor	
 * @param name Name des Gegestandes
 * @param beschreibung Beschreibung des Gegenstandes
 * @param gewicht Gewicht des Gegenstandes
 */
	public Gegenstand(String name, String beschreibung, double gewicht)
	{
		this.name = name;
		this.beschreibung = beschreibung;
		this.gewicht = gewicht;
	}
/**
 * Getter
 * @return Namen des Gegenstandes
 */
	public String getName() {
		return name;
	}
/**
 * Getter	
 * @return Beschreibung des Gegenstandes
 */
	public String getBeschreibung() {
		return beschreibung;
	}
/**
 * Getter
 * @return Gewicht des Gegenstandes
 */
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
