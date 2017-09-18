package Items;
/**
 * Klasse Muffin erweitert Gegenstand 
 * erbt die attribute "name" "beschreibung" und "gewicht"
 * 
 *
 */
public class Muffin extends Gegenstand {
	
	private double erhoehungTragkraft;
	/**
	 *  Konstruktor
	 * @param name Name des Muffins
	 * @param beschreibung Beschreibung des Muffins
	 * @param gewicht Gewicht des Muffins
	 * @param et Methode zur erhöhung der Tragkraft
	 */
	public Muffin(String name, String beschreibung, double gewicht, double et)
	{
		super(name, beschreibung, gewicht);
		this.erhoehungTragkraft = et;
	}
/**
 * Methode
 * @return erhöhte Tragkraft
 */
	public double getErhoehungTragkraft() {
		return erhoehungTragkraft;
	}
	/**
	 * Liefert einen String damit der Spieler weiß wozu der Muffin dient
	 * @return beschreibung des Gegenstandes und sein Gewicht
	 */
	public String toString()
	{
		return (super.toString()+ ", Erhöhung der Tragkraft um "+ erhoehungTragkraft);
		
	}
}
