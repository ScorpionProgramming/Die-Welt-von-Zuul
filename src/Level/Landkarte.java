package Level;

import java.util.ArrayList;

import Items.Gegenstand;
import Items.Muffin;
import Lebewesen.Monster;
import Lebewesen.Spieler;

public class Landkarte {

	ArrayList<Raum> raeume= new ArrayList<Raum>();
	Raum[][] raum_begangen; // wird vom Algorithmus verwendet

	//hier alle Gegenstaende anlegen die auf der Map enthalten sein sollen
	Gegenstand gegenstandsPool[] = {
			new Gegenstand("Gewehr","ein Maschinengewehr mit 100 Schuss", 9.5),
			new Gegenstand("Buch", "ein sehr altes Buch mit Ledereinband", 0.6),
			new Muffin("Muffin", "ein magischer Muffin", 0.1, 5.0),
			new Gegenstand("Nahrung", "ein Korb mit mehreren Fruchtsorten", 1.0)
	};

	//hier alle Monster hinzufuegen die auf der Map enthalten sein sollen
	Monster monsterPool[] = {
		new Monster("Skelett"),
		new Monster("Ratte"),
		new Monster("Geist"),
		new Monster("Spinne")
	};

	public Landkarte(){
		//ctor
		raeumeGenerieren();
		platziereItems(6);
		platziereMonster(3);
	}
	/**
	 * Erzeuge alle Räume und verbinde ihre Ausgänge miteinander.
	 */
	public void raeumeAnlegen(Spieler spieler)
	{
		//        Raum draussen, hoersaal, cafeteria, labor, buero;
		//      
		//        // die Räume erzeugen
		//        draussen = new Raum("vor dem Haupteingang der Universität");
		//        hoersaal = new Raum("in einem Vorlesungssaal");
		//        cafeteria = new Raum("in der Cafeteria der Uni");
		//        labor = new Raum("in einem Rechnerraum");
		//        buero = new Raum("im Verwaltungsbüro der Informatik");
		//        
		//        // die Ausgänge initialisieren
		//        draussen.setAusgang("east", hoersaal);
		//        draussen.setAusgang("south", labor);
		//        draussen.setAusgang("west",cafeteria);
		//        hoersaal.setAusgang("west", draussen);
		//        cafeteria.setAusgang("east", draussen);
		//        labor.setAusgang("north",draussen);
		//        labor.setAusgang("east", buero);
		//        buero.setAusgang("west", labor);        
		//        
		//        //Räume mit Gegenstände versehen
		//        draussen.gegenstandAblegen(new Gegenstand("Gewehr","ein Maschinengewehr mit 100 Schuss", 9.5));
		//        draussen.gegenstandAblegen(new Gegenstand("Buch", "ein sehr altes Buch mit Ledereinband", 0.6));
		//        draussen.gegenstandAblegen(new Muffin("Muffin", "ein magischer Muffin", 0.1, 5.0));
		//        hoersaal.gegenstandAblegen(new Gegenstand("Schatz", "eine kleine Schatztruhe mit Münzen", 2.5));
		//        cafeteria.gegenstandAblegen(new Gegenstand("Pistole", "eine Pistole mit sechs Schuss", 0.5));
		//        labor.gegenstandAblegen(new Gegenstand("Nahrung", "ein Korb mit mehreren Fruchtsorten", 1.0));
		//        buero.gegenstandAblegen(new Gegenstand("Seil", "ein Seil der Länge 3m", 0.3));
		//        
		//        //Räume mit Monstern versehen
		//        cafeteria.setMonster(new Monster("Hugo"));
		//        labor.setMonster(new Monster());
		//
		//        //Initialisierung des Spielers
		//        //spieler = new Spieler();
		//        
		//     // das Spiel startet draussen
		//        spieler.setAktuellerRaum(draussen);  

		//raeumeGenerieren();
		spieler.setAktuellerRaum(raeume.get(0));
	}

	/**
	 * @Autor Pfaus
	 * Generiert zufällig Raeume und Gaenge
	 */
	private void raeumeGenerieren() {
		Raum 	aktuellerRaum = null,
				nachfolgeRaum = null;
		int 	i 	= 0,
				max = (int)(4 + (Math.random() * 4)),
				//startwert fuer die Raumgeneration
				x 	= ( int ) ( Math.random() * max ), 
				y 	= ( int ) ( Math.random() * max )
				;

		double randRichtung;

		// erstellt die groeße des Raumes anhand des zufaelligen Wertes von 'max'
		this.raum_begangen = new Raum[max][max];
		

		int maxRaeume = 8;//(int) ( Math.random() * 6 );
		while(i <= maxRaeume){
			// TODO 
			if(i == 0) {
				aktuellerRaum = new Raum("Raum_"+i);
				raeume.add(aktuellerRaum);
				this.raum_begangen[x][y] = aktuellerRaum;
			}

			boolean loop_nochmal;
			do {
				loop_nochmal = false;
				//In welche Richtung soll der neue Raum angehangen werden?
				//erst richtung herausfinden
				//dann schauen ob der Raum noch nicht existiert
				randRichtung = Math.random();
				if(randRichtung < 0.25d && x+1 < max) {				//ost
					if(this.raum_begangen[x+1][y] == null) {
						nachfolgeRaum = new Raum("Raum_"+(i+1));
						raeume.add(nachfolgeRaum);
						this.raum_begangen[x+1][y] = nachfolgeRaum;

						aktuellerRaum.setAusgang("east", nachfolgeRaum);
						nachfolgeRaum.setAusgang("west", aktuellerRaum);
					}else {
						nachfolgeRaum = this.raum_begangen[x+1][y];

						aktuellerRaum.setAusgang("east", nachfolgeRaum);
						nachfolgeRaum.setAusgang("west", aktuellerRaum);
					}
					x++;
				}else if(randRichtung < 0.5d && y+1 < max){ 						//sued
					if(this.raum_begangen[x][y+1] == null) {
						nachfolgeRaum = new Raum("Raum_"+(i+1));
						raeume.add(nachfolgeRaum);
						this.raum_begangen[x][y+1] = nachfolgeRaum;

						aktuellerRaum.setAusgang("south", nachfolgeRaum);
						nachfolgeRaum.setAusgang("north", aktuellerRaum);
					}else {
						nachfolgeRaum = this.raum_begangen[x][y+1];

						aktuellerRaum.setAusgang("south", nachfolgeRaum);
						nachfolgeRaum.setAusgang("north", aktuellerRaum);
					}
					y++;
				}else if(randRichtung < 0.75d && x-1 > 0){ 						//west
					if(this.raum_begangen[x-1][y] == null) {
						nachfolgeRaum = new Raum("Raum_"+(i+1));
						raeume.add(nachfolgeRaum);
						this.raum_begangen[x-1][y] = nachfolgeRaum;

						aktuellerRaum.setAusgang("west", nachfolgeRaum);
						nachfolgeRaum.setAusgang("east", aktuellerRaum);
					}else {
						nachfolgeRaum = this.raum_begangen[x-1][y];

						aktuellerRaum.setAusgang("west", nachfolgeRaum);
						nachfolgeRaum.setAusgang("east", aktuellerRaum);
					}
					x--;
				}else if(randRichtung < 1.0d && y-1 > 0){ 						//norden
					if(this.raum_begangen[x][y-1] == null) {
						nachfolgeRaum = new Raum("Raum_"+(i+1));
						raeume.add(nachfolgeRaum);
						this.raum_begangen[x][y-1] = nachfolgeRaum;

						aktuellerRaum.setAusgang("north", nachfolgeRaum);
						nachfolgeRaum.setAusgang("south", aktuellerRaum);
					}else {
						nachfolgeRaum = this.raum_begangen[x][y-1];

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

			i++;
		};
		this.displayMap();
	}
	
	/**
	 * @author Pfaus
	 * Waehlt zufaellig einen Raum aus der Map aus
	 */
	public Raum getZufaelligenRaum() {
		boolean raumOk = false;
		int x, y;
		do {
			x = (int) (Math.random()*raum_begangen.length);
			y = (int) (Math.random()*raum_begangen[0].length);
			if(raum_begangen[x][y] != null) {
				raumOk = true;
				return raum_begangen[x][y];
			}
		}while(raumOk != true);
		return null;
	}
	
	/**
	 * @author Pfaus
	 * Setzt in die Map zufällig Items aus der Gegenstandsliste
	 * @param maxGegenstaende maximale Anzahl an Gegenstaende die auf der Map verteilt werden sollen
	 */
	private void platziereItems(int maxGegenstaende) {
		for(int i = 0; i < maxGegenstaende; i++) {
			Gegenstand g = gegenstandsPool[(int)(Math.random()*gegenstandsPool.length)];
			getZufaelligenRaum().gegenstandAblegen(g);
		}
	}
	
	/**
	 * @author Pfaus
	 * Setzt zufaellig auf die Karte Monster aus der Monster Liste
	 * @param maxMonster maximale Anzahl an Monster die auf der Map verteilt werden sollen
	 */
	private void platziereMonster(int maxMonster) {
		for(int i = 0; i < maxMonster; i++) {
			Monster m = monsterPool[(int)(Math.random()*monsterPool.length)];
			Raum r = getZufaelligenRaum();
			m.setAktuellerRaum(r);
			r.setMonster(m);
		}
	}

	/**
	 * @Autor Pfaus
	 * @param min
	 * @param max
	 * Erstellt einen Initialen Raum mit der angegeben groeße im Bereich des Minimums und Maximums
	 */
	private void levelSizeGenerator(int min, int max) {
		int size = (int) ( max-min + (Math.random()*max) ) ; // Level größe zufällig zwischen min und max
		this.raum_begangen = new Raum[size][size];
	}

	/**
	 * @Autor Pfaus
	 * Gibt die Anzahl der begehbaren Raume zurueck
	 */
	public int getAnzahlRaeume() {
		return 1;
	}

	/**
	 * @Autor Pfaus
	 * Ausgabe der Karte in der Konsole
	 */
	public void displayMap() {
		for(int y = 0; y < this.raum_begangen.length * 3;y++) {
			for(int x = 0; x < this.raum_begangen[y/3].length * 3; x++) {
				if(raum_begangen[x/3][y/3] != null) { //es ist ein Raum da
					if(x%3 == 0 || x %3 == 2) { //senkrechte und eckpunkte also + für die Ecken und | für die Senkrechten
						//wenns kein Linkes oder Rechtes Mittelstück ist ist es eine Ecke
						if(y %3 == 1 ) {
							if(this.raum_begangen[x/3][y/3].getAusgang("west") != null && x%3 == 0) { //pruefen ob der Raum einen Ausgang nach Westen oder Osten hat um dementsprechend die Zeichen anzupassen
								System.out.print(" ");
							}else if(this.raum_begangen[x/3][y/3].getAusgang("east") != null && x%3 == 2) {
								System.out.print(" ");
							}else {
								System.out.print("|");
							}
						}else {
							System.out.print("+");
						}
					}else if(x %3 == 1) { //wenns aber ein Mittelstück ist dann soll geguckt werden ob es die Mitte ist oder ein Randstück
						if(y%3 ==1 && x %3 == 1) //ist ein Randstück wenn X und Y Modulo 3 -> 1 ergeben 
							System.out.print(" ");
						else {
							if(this.raum_begangen[x/3][y/3].getAusgang("north") != null && y%3 == 0) { //pruefen ob der Raum einen Ausgang nach norden oder sueden hat um dementsprechend die Zeichen anzupassen
								System.out.print(" ");
							}else if(this.raum_begangen[x/3][y/3].getAusgang("south") != null && y%3 == 2) {
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

		System.out.println("Raeume : "	+ raeume.size());
		System.out.println("SizeX : " 	+ this.raum_begangen[0].length);
		System.out.println("SizeY : " 	+ this.raum_begangen.length);
	}

}
