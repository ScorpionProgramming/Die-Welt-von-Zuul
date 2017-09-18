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
	 * @param et Methode zur erh�hung der Tragkraft
	 */
	public Muffin(String name, String beschreibung, double gewicht, double et)
	{
		super(name, beschreibung, gewicht);
		this.erhoehungTragkraft = et;
	}
/**
 * Methode
 * @return erh�hte Tragkraft
 */
	public double getErhoehungTragkraft() {
		return erhoehungTragkraft;
	}
	/**
	 * Liefert einen String damit der Spieler wei� wozu der Muffin dient
	 * @return beschreibung des Gegenstandes und sein Gewicht
	 */
	public String toString()
	{
		return (super.toString()+ ", Erh�hung der Tragkraft um "+ erhoehungTragkraft);
		
	}
}
