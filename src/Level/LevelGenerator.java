package Level;

import Items.Gegenstand;
import Items.Karte;
import Items.Muffin;
import Lebewesen.Monster;
import Lebewesen.Spieler;

public class LevelGenerator {
	//private Raum[][] map; //fertige Map
	private Landkarte landkarte;
	
	//startwert fuer die Raumgeneration
	private int 	x,
					y,
					max; //width und height des arrays
	
	private int maxItems	= 6;	
	private int maxMonster	= 3;
	
	//hier alle Gegenstaende anlegen die auf der Map enthalten sein sollen
	//wenn eine ein Gegenstand mit einer höhren Wahrscheinlichkeit plaziert werden soll, muss dieser öfter in den Pool hinzugefuegt werden.
	private Gegenstand gegenstandsPool[] = {
			new Gegenstand("Gewehr","ein Maschinengewehr mit 100 Schuss", 9.5),
			new Gegenstand("Buch", "ein sehr altes Buch mit Ledereinband", 0.6),
			new Muffin("Muffin", "ein magischer Muffin", 0.1, 5.0),
			new Gegenstand("Nahrung", "ein Korb mit mehreren Fruchtsorten", 1.0)
	};
	
	//hier alle Monster hinzufuegen die auf der Map enthalten sein sollen
	//wenn eine ein Monster mit einer höhren Wahrscheinlichkeit plaziert werden soll, muss dieser öfter in den Pool hinzugefuegt werden.
	private Monster monsterPool[] = {
		new Monster("Skelett"),
		new Monster("Ratte"),
		new Monster("Geist"),
		new Monster("Spinne")
	};
	
	/**
	 * @author Pfaus
	 * Standart ctor
	 * TODO später startwerte fuer den Algorithmus angeben
	 */
	public LevelGenerator() {
		this.x = 0;
		this.y = 0;
	}
	
	public Landkarte generate(Spieler spieler, int max) {
		this.max = max;
		landkarte = new Landkarte(this.max);
		this.raeumeGenerieren();
		this.platziereItems(this.landkarte, maxItems);
		this.platziereMonster(this.landkarte, maxMonster);
		spieler.setAktuellerRaum(this.landkarte.getZufaelligenRaum());
		return this.landkarte;
	}
	
	/**
	 * @Autor Pfaus
	 * Generiert zufällig Raeume und Gaenge und fuegt sie der Karte hinzu
	 */
	private void raeumeGenerieren() {
		Raum 	aktuellerRaum = null;
		int 	i 	= 0;
		x 		= ( int ) ( Math.random() * max ); 
		y 		= ( int ) ( Math.random() * max );
		max 	= (int)(4 + (Math.random() * 4));
		
		// erstellt die groeße des Raumes anhand des zufaelligen Wertes von 'max'
		//this.map = new Raum[max][max]; TODO wird schon im ctor erledigt
		
		int maxRaeume = 8;//(int) ( Math.random() * 6 );
		while(i <= maxRaeume){

			if(i == 0) {
				aktuellerRaum = new Teleporter("Raum_"+i);
				//raeume.add(aktuellerRaum);
				//this.map[x][y] = aktuellerRaum;
				this.landkarte.setRaum(x, y, aktuellerRaum);
			}
			
			aktuellerRaum = addRoomToArray(aktuellerRaum, new Raum("Raum_"+i));
			i++;
		};
		//Am ende des Algorthmus einen Teleporter hinzufuegen
		//addRoomToArray(aktuellerRaum, new Teleporter("Raum_"+i));
		
		//this.displayMap();
	}
	
	/**
	 * @param aktuellerRaum der Raum in dem sich der Algorithmus gerade befindet
	 * @param nachfolgeRaum der Raum der mitgegegeben wird. Kann auch ein Teleporter sein.
	 * @return den neuen Raum der für den Algorithmus gebraucht wird.
	 */
	private Raum addRoomToArray(Raum aktuellerRaum, Raum nachfolgeRaum) {
		double randRichtung;
		
		boolean loop_nochmal;
		do {
			loop_nochmal = false;
			//In welche Richtung soll der neue Raum angehangen werden?
			//erst richtung herausfinden
			//dann schauen ob der Raum noch nicht existiert
			randRichtung = Math.random();
			if(randRichtung < 0.25d && x+1 < max) {				//ost
				//if(this.map[x+1][y] == null) {
				if(this.landkarte.getRaum(x+1, y) == null) {
					//this.map[x+1][y] = nachfolgeRaum;
					this.landkarte.setRaum(x+1, y, nachfolgeRaum);
					
					aktuellerRaum.setAusgang("east", nachfolgeRaum);
					nachfolgeRaum.setAusgang("west", aktuellerRaum);
				}else {
//					nachfolgeRaum = this.map[x+1][y];
					nachfolgeRaum = this.landkarte.getRaum(x+1, y);
					
					aktuellerRaum.setAusgang("east", nachfolgeRaum);
					nachfolgeRaum.setAusgang("west", aktuellerRaum);
				}
				x++;
			}else if(randRichtung < 0.5d && y+1 < max){ 						//sued
//				if(this.map[x][y+1] == null) {
				if(this.landkarte.getRaum(x, y+1)== null) {
					//this.map[x][y+1] = nachfolgeRaum;
					this.landkarte.setRaum(x, y+1, nachfolgeRaum);

					aktuellerRaum.setAusgang("south", nachfolgeRaum);
					nachfolgeRaum.setAusgang("north", aktuellerRaum);
				}else {
					//nachfolgeRaum = this.map[x][y+1];
					nachfolgeRaum = this.landkarte.getRaum(x, y+1);

					aktuellerRaum.setAusgang("south", nachfolgeRaum);
					nachfolgeRaum.setAusgang("north", aktuellerRaum);
				}
				y++;
			}else if(randRichtung < 0.75d && x-1 >= 0){ 						//west
				//if(this.map[x-1][y] == null) {
				if(this.landkarte.getRaum(x-1, y) == null) {
					//raeume.add(nachfolgeRaum);
					//this.map[x-1][y] = nachfolgeRaum;
					this.landkarte.setRaum(x-1, y, nachfolgeRaum);
					
					aktuellerRaum.setAusgang("west", nachfolgeRaum);
					nachfolgeRaum.setAusgang("east", aktuellerRaum);
				}else {
					//nachfolgeRaum = this.map[x-1][y];
					nachfolgeRaum = this.landkarte.getRaum(x-1, y);
					
					aktuellerRaum.setAusgang("west", nachfolgeRaum);
					nachfolgeRaum.setAusgang("east", aktuellerRaum);
				}
				x--;
			}else if(randRichtung < 1.0d && y-1 >= 0){ 						//norden
				//if(this.map[x][y-1] == null) {
				if(this.landkarte.getRaum(x, y-1) == null) {
					//raeume.add(nachfolgeRaum);
					//this.map[x][y-1] = nachfolgeRaum;
					this.landkarte.setRaum(x, y-1, nachfolgeRaum);
					
					aktuellerRaum.setAusgang("north", nachfolgeRaum);
					nachfolgeRaum.setAusgang("south", aktuellerRaum);
				}else {
					//nachfolgeRaum = this.map[x][y-1];
					nachfolgeRaum = this.landkarte.getRaum(x, y-1);
					
					aktuellerRaum.setAusgang("north", nachfolgeRaum);
					nachfolgeRaum.setAusgang("south", aktuellerRaum);
				}
				y--;
			}else if(aktuellerRaum.getAusgang("north") == null || aktuellerRaum.getAusgang("south") == null || aktuellerRaum.getAusgang("west") == null || aktuellerRaum.getAusgang("east") == null){ //wenn hier, dann muss nochmal neu nach einem weg gesucht werden
				loop_nochmal = true;
			}

		}while(loop_nochmal);

		aktuellerRaum = nachfolgeRaum;
		nachfolgeRaum = null;
		
		return aktuellerRaum;
	}
	
	/**
	 * @author Pfaus
	 * Setzt in die Map zufällig Items aus der Gegenstandsliste
	 * @param maxGegenstaende maximale Anzahl an Gegenstaende die auf der Map verteilt werden sollen
	 */
	private void platziereItems(Landkarte landkarte, int maxGegenstaende) {
		for(int i = 0; i < maxGegenstaende; i++) {
			Gegenstand g = gegenstandsPool[(int)(Math.random()*gegenstandsPool.length)];
			landkarte.getZufaelligenRaum().gegenstandAblegen(g);
		}
		//eine Karte Plazieren
		landkarte.getZufaelligenRaum().gegenstandAblegen(new Karte("Map", "eine Landkarte", 0.5d, landkarte));
	}
	
	/**
	 * @author Pfaus
	 * Setzt zufaellig auf die Karte Monster aus der Monster Liste
	 * @param maxMonster maximale Anzahl an Monster die auf der Map verteilt werden sollen
	 */
	private void platziereMonster(Landkarte landkarte, int maxMonster) {
		for(int i = 0; i < maxMonster; i++) {
			Monster m = monsterPool[(int)(Math.random()*monsterPool.length)];
			Raum r = landkarte.getZufaelligenRaum();
			m.setAktuellerRaum(r);
			r.setMonster(m);
		}
	}

}
