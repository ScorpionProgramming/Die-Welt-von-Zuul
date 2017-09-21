package Level;

import Items.Gegenstand;
import Items.Karte;
import Items.Muffin;
import Lebewesen.Monster;
import Lebewesen.Spieler;

public class Landkarte {

	private Raum[][] map; // wird vom Algorithmus verwendet
	
//	//startwert fuer die Raumgeneration
//	int 	x,
//			y,
//			max;
//
//	//hier alle Gegenstaende anlegen die auf der Map enthalten sein sollen
//	//wenn eine ein Gegenstand mit einer höhren Wahrscheinlichkeit plaziert werden soll, muss dieser öfter in den Pool hinzugefuegt werden.
//	Gegenstand gegenstandsPool[] = {
//			new Gegenstand("Gewehr","ein Maschinengewehr mit 100 Schuss", 9.5),
//			new Gegenstand("Buch", "ein sehr altes Buch mit Ledereinband", 0.6),
//			new Muffin("Muffin", "ein magischer Muffin", 0.1, 5.0),
//			new Gegenstand("Nahrung", "ein Korb mit mehreren Fruchtsorten", 1.0)
//	};
//
//	//hier alle Monster hinzufuegen die auf der Map enthalten sein sollen
//	//wenn eine ein Monster mit einer höhren Wahrscheinlichkeit plaziert werden soll, muss dieser öfter in den Pool hinzugefuegt werden.
//	Monster monsterPool[] = {
//		new Monster("Skelett"),
//		new Monster("Ratte"),
//		new Monster("Geist"),
//		new Monster("Spinne")
//	};
	/**
	 * @author Pfaus
	 * Standart ctor
	 */
	public Landkarte(){
		//ctor
//		raeumeGenerieren();
//		platziereItems(6);
//		platziereMonster(3);
		map = new Raum[3][3];
	}
	
	public Landkarte(int size) {
		map = new Raum[size][size];
	}
	
	public Landkarte(int width, int height) {
		map = new Raum[width][height];
	}
	
	/**
	 * @param x Coordinate im Array
	 * @param y Coordinate im Array
	 * @return den Raum an der Stelle X-Y
	 */
	public Raum getRaum(int x, int y) {
		return map[x][y];
	}
	/**
	 * 
	 * @param x Coordinate im Array
	 * @param y Coordinate im Array
	 * @param raum der dem Array hinzugefuegt werden soll.
	 */
	public void setRaum(int x, int y, Raum raum) {
		this.map[x][y] = raum;
	}
	
	/**
	 * @author Pfaus
	 * Waehlt zufaellig einen Raum aus der Map aus
	 */
	public Raum getZufaelligenRaum() {
		boolean raumOk = false;
		int x, y;
		do {
			x = (int) (Math.random()*map.length);
			y = (int) (Math.random()*map[0].length);
			if(map[x][y] != null) {
				raumOk = true;
				return map[x][y];
			}
		}while(raumOk != true);
		return null;
	}
	
	
	/**
	 * @Autor Pfaus
	 * Gibt die Anzahl der begehbaren Raume zurueck
	 */
	public int getAnzahlRaeume() {
		int anzahl = 0;
		for(int x = 0; x < this.map.length; x++) {
			for(int y = 0; y < this.map[x].length; y++) {
				if(map[x][y] != null) {
					anzahl++;
				}
			}
		}
		return anzahl;
	}
	
	/**
	 * @author Pfaus
	 * @return
	 */
	public int getAnzahlMonster() {
		int anzahl = 0;
		for(int x = 0; x < this.map.length; x++) {
			for(int y = 0; y < this.map[x].length; y++) {
				if(map[x][y] != null && map[x][y].getMonster() != null) {
					anzahl++;
				}
			}
		}
		return anzahl;
	}

	/**
	 * @Autor Pfaus
	 * Ausgabe der Karte in der Konsole
	 */
	// TODO muss muss noch zueiner TOString Methode umgewandelt werden
	public void displayMap() {
		for(int y = 0; y < this.map.length * 3;y++) {
			for(int x = 0; x < this.map[y/3].length * 3; x++) {
				if(map[x/3][y/3] != null) { //es ist ein Raum da
					if(x%3 == 0 || x %3 == 2) { //senkrechte und eckpunkte also + für die Ecken und | für die Senkrechten
						//wenns kein Linkes oder Rechtes Mittelstück ist ist es eine Ecke
						if(y %3 == 1 ) {
							if(this.map[x/3][y/3].getAusgang("west") != null && x%3 == 0) { //pruefen ob der Raum einen Ausgang nach Westen oder Osten hat um dementsprechend die Zeichen anzupassen
								System.out.print(" ");
							}else if(this.map[x/3][y/3].getAusgang("east") != null && x%3 == 2) {
								System.out.print(" ");
							}else {
								System.out.print("|");
							}
						}else {
							System.out.print("+");
						}
					}else if(x %3 == 1) { //wenns aber ein Mittelstück ist dann soll geguckt werden ob es die Mitte ist oder ein Randstück
						if(y%3 ==1 && x %3 == 1) //ist die Mitte wenn X und Y Modulo 3 -> 1 ergeben 
							//pruefen ob der Typ des Raumes ein Teleporter ist, dann auf der Karte mit einem 'T' kennzeichnen
							if(this.map[x/3][y/3].getClass().getName().equals("Level.Teleporter")) {
								System.out.print("@");
							}else {
								System.out.print(" ");
							}
						else {
							if(this.map[x/3][y/3].getAusgang("north") != null && y%3 == 0) { //pruefen ob der Raum einen Ausgang nach norden oder sueden hat um dementsprechend die Zeichen anzupassen
								System.out.print(" ");
							}else if(this.map[x/3][y/3].getAusgang("south") != null && y%3 == 2) {
								System.out.print(" ");
							}else {
								System.out.print("-");
							}
						}
					}
				}else { //es ist kein Raum da
					System.out.print(" ");
				}
			}
			System.out.println(); //naechste Zeile.
		}

		System.out.println("Raeume : " 	+ this.getAnzahlRaeume());
		System.out.println("SizeX : " 	+ this.map[0].length);
		System.out.println("SizeY : " 	+ this.map.length);
	}
	
//	/**
//	 * @author Pfaus
//	 * Setzt in die Map zufällig Items aus der Gegenstandsliste
//	 * @param maxGegenstaende maximale Anzahl an Gegenstaende die auf der Map verteilt werden sollen
//	 */
//	private void platziereItems(int maxGegenstaende) {
//		for(int i = 0; i < maxGegenstaende; i++) {
//			Gegenstand g = gegenstandsPool[(int)(Math.random()*gegenstandsPool.length)];
//			getZufaelligenRaum().gegenstandAblegen(g);
//		}
//		//eine Karte Plazieren
//		getZufaelligenRaum().gegenstandAblegen(new Karte("Map", "eine Landkarte", 0.5d, map));
//	}
	
//	/**
//	 * @author Pfaus
//	 * Setzt zufaellig auf die Karte Monster aus der Monster Liste
//	 * @param maxMonster maximale Anzahl an Monster die auf der Map verteilt werden sollen
//	 */
//	private void platziereMonster(int maxMonster) {
//		for(int i = 0; i < maxMonster; i++) {
//			Monster m = monsterPool[(int)(Math.random()*monsterPool.length)];
//			Raum r = getZufaelligenRaum();
//			m.setAktuellerRaum(r);
//			r.setMonster(m);
//		}
//	}

}
