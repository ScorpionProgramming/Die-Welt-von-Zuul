package Items;

import Level.Raum;

public class Karte extends Gegenstand{
	
	Raum[][] map;

	public Karte(String name, String beschreibung, double gewicht, Raum[][] map) {
		super(name, beschreibung, gewicht);
		// TODO Auto-generated constructor stub
		this.map = map;
	}
	
	/**
	 * @Autor Pfaus
	 * Ausgabe der Karte in der Konsole
	 */
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

		System.out.println("Raeume : -"	/* + raeume.size()*/);
		System.out.println("SizeX : " 	+ this.map[0].length);
		System.out.println("SizeY : " 	+ this.map.length);
	}
	
}
