package Items;

import Level.Landkarte;
/**
 * Klasse Karte erweitert Gegenstand und erbtdie attribute "name" "beschreibung" und "gewicht"
 * @Autor Pfaus
 */
public class Karte extends Gegenstand{
	
	Landkarte landkarte;
/**
 * 
 * @param name Name der Karte
 * @param beschreibung Beschreibung  der Karte
 * @param gewicht Gewicht der  der Karte
 * @param landkarte Objekt erstellen
 */
	public Karte(String name, String beschreibung, double gewicht, Landkarte landkarte) {
		super(name, beschreibung, gewicht);
		// TODO Auto-generated constructor stub
		this.landkarte = landkarte;
	}
	
	/**
	 * @Autor Pfaus
	 * Ausgabe der Karte in der Konsole
	 */
	public void displayMap() {
		landkarte.displayMap();
	}
	
}
