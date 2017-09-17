package Items;

import Level.Landkarte;

public class Karte extends Gegenstand{
	
	Landkarte landkarte;

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
