package Main;
import java.util.ArrayList;
import java.util.LinkedList;

import Items.Gegenstand;
import Items.Karte;
import Items.Muffin;
import Lebewesen.Spieler;
import Level.Landkarte;
import Level.LevelGenerator;
import Level.Raum;
import Utils.Befehl;
import Utils.Parser;
import Zustaende.Gesund;

/**
 *  Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul".
 *  "Die Welt von Zuul" ist ein sehr einfaches, textbasiertes
 *  Adventure-Game. Ein Spieler kann sich in einer Umgebung bewegen,
 *  mehr nicht. Das Spiel sollte auf jeden Fall ausgebaut werden,
 *  damit es interessanter wird!
 *
 *  Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und
 *  an ihr die Methode "spielen" aufgerufen werden.
 *
 *  Diese Instanz erzeugt und initialisiert alle anderen Objekte
 *  der Anwendung: Sie legt alle R�ume und einen Parser an und
 *  startet das Spiel. Sie wertet auch die Befehle aus, die der
 *  Parser liefert, und sorgt f�r ihre Ausf�hrung.
 *
 * @author  Michael K�lling und David J. Barnes
 * @version 2008.03.30
 */

public class Spiel
{
    private Spieler spieler;
	private Parser parser;
	private Landkarte landkarte;
	private LevelGenerator levelGen = new LevelGenerator();

    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel()
    {
    	spieler = new Spieler();
    	//landkarte = new Landkarte(5);
        //landkarte.raeumeAnlegen(spieler);

    	landkarte = levelGen.generate(spieler, 5, 6, 4, 10);

        parser = new Parser();
    }

    /**
     * Erzeugt ein Spielobjekt und f�hrt an ihm die Methode spielen aus.
     *
	 * @param args .
	 */
	public static void main(String[] args) {
		Spiel meinSpiel = new Spiel();
		meinSpiel.spielen();
	}

    /**
     * Die Hauptmethode zum Spielen. L�uft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen()
    {
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und f�hren sie aus, bis das Spiel beendet wird.

        boolean beendet = false;
        while (! beendet && !spieler.getAktuellerZustand().toString().equals("Tod") && !(landkarte.getAnzahlMonster() <= 0)) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }

        if(spieler.getAktuellerZustand().toString().equals("Tod")) {
        	System.out.println("Sie sind leider gestorben.");
        }else if(landkarte.getAnzahlMonster() <= 0) {
        	System.out.println("--- Herzlichen Glueckwunsch ---");
        	System.out.println("Sie haben das Spiel gewonnen, weil sie Zuul von allen Monstern befreit haben");
        }


        System.out.println("Danke f�r dieses Spiel. Auf Wiedersehen.");
    }

    /**
     * Einen Begr��ungstext f�r den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu Zuul!");
        System.out.println("Zuul ist ein neues, nicht mehr so langweiliges Spiel.");
        System.out.println("Ziel ist es, alle " + landkarte.getAnzahlMonster() + " Monster in der Welt von Zuul zu bekaempfen.");
        System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
        System.out.println();
        rauminfoAusgeben();

    }

    /**
     * Verarbeite einen gegebenen Befehl (f�hre ihn aus).
     * @param befehl Der zu verarbeitende Befehl.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean verarbeiteBefehl(Befehl befehl)
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("Ich wei� nicht, was Sie meinen...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help"))
            hilfstextAusgeben();
        else if (befehlswort.equals("go"))
            wechsleRaum(befehl);
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        else if(befehlswort.equals("look")){
        	umsehen();
        }
        else if(befehlswort.equals("take"))
        {
        	nimmGegenstand(befehl);
        	System.out.println(spieler.zeigeStatus());
        	System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
        }
        else if(befehlswort.equals("drop"))
        {
        	legeGegenstandAb(befehl);
        	System.out.println(spieler.zeigeStatus());
        	System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
        }
        else if(befehlswort.equals("eat"))
        {
        	issMuffin(befehl);
        	System.out.println(spieler.zeigeStatus());
        	System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
        }
        else if(befehlswort.equals("attack"))
        {
        	// wenn ein Monster im Raum dann angreifen sonst nicht
        	if(this.spieler.getAktuellerRaum().getMonster() != null){
        		angreifen();
        	}else{
        		System.out.println("Kein Monster hier!");
        	}
        } else if (befehlswort.equals("use")) {
            showmap(befehl);
        } else if (befehlswort.equalsIgnoreCase("heal")) {
            if (zaehltGegenstand("heiltrank") > 0 && !(spieler.getAktuellerZustand() == Gesund.getInstance())){
                heilenTrankBenutzen();
                System.out.println("Du wurdest geheilt." + spieler.zeigeZustand());;
            }else if (spieler.getAktuellerZustand() == Gesund.getInstance()){
                System.out.println("Du bist schon gesund!");
            }else if (zaehltGegenstand("heiltrank") == 0){
                System.out.println("Du hast gerade keinen Heiltrank!");
            }else{
                System.out.println("Du bist schon gesund und hast keinen Heiltrank mehr");
            }
        }

        return moechteBeenden;
    }

	/**
     * Methode zum Befehl attack
     * Greift ein Monster an und das Monster greift direkt zur�ck an
     *
     */
    private void angreifen() {
        System.out.print("Du greifst das Monster an");
        makePause();
        this.spieler.angreifen(this.spieler.getAktuellerRaum().getMonster());
        System.out.print(this.spieler.getAktuellerRaum().getMonster().getName());
        System.out.println(": " + this.spieler.getAktuellerRaum().getMonster().getAktuellerZustand().toString());
        System.out.println();
        if (this.spieler.getAktuellerRaum().getMonster().getAktuellerZustand().toString().equals("Tod")) {
            this.spieler.getAktuellerRaum().setMonster(null);
    	}else{
    		System.out.print("Das Monster greift dich an");
            makePause();
        	this.spieler.getAktuellerRaum().getMonster().angreifen(this.spieler);
    	}
    	System.out.println("Spielerstatus: " + this.spieler.getAktuellerZustand().toString());
	}

	// Implementierung der Benutzerbefehle:

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlsw�rter.
     */
    private void hilfstextAusgeben()
    {
        System.out.println("Sie haben sich verlaufen. Sie sind allein.");
        System.out.println("Sie irren auf dem Unigel�nde herum.");
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verf�gung:");
        System.out.println(parser.getAlleBefehle());
    }

    /**
     * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    private void wechsleRaum(Befehl befehl)
    {
        if(!befehl.hatZweitesWort()) {
        	// Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin m�chten Sie gehen?");
            return;
        }else if(spieler.getAktuellerZustand().toString().equals("Bewegungsunf�hig")) {
        	System.out.println("Sie sind bewegungsunf�hig und m�ssen sich heilen bevor sie weiter k�nnen");
        	return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen den Raum zu verlassen.
        Raum naechsterRaum = spieler.getAktuellerRaum().getAusgang(richtung);

        if (naechsterRaum == null) {
            System.out.println("Dort ist keine T�r!");
        }
        else {
        	if(naechsterRaum.getClass().getName().equals("Level.Teleporter")) {
        		spieler.setAktuellerRaum(landkarte.getZufaelligenRaum());
        		System.out.println("Du wurdest Teleportiert");
        	}else {
        		 spieler.setAktuellerRaum(naechsterRaum);
        	}

        	rauminfoAusgeben();
        }
    }

    /**
     * "quit" wurde eingegeben. �berpr�fe den Rest des Befehls,
     * ob das Spiel wirklich beendet werden soll.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean beenden(Befehl befehl)
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else {
            return true;  // Das Spiel soll beendet werden.
        }
    }

    private void rauminfoAusgeben()
    {
    	System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
        System.out.println();
    }

    private void umsehen()
    {
    	System.out.println(spieler.getAktuellerRaum().getLangeBeschreibung());
    }
    
    private void nimmGegenstand(Befehl befehl)
    {
    	if(!befehl.hatZweitesWort()) {
        	// Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Welchen Gegenstand m�chten Sie aufnehmen?");
            return;
        }

        String name = befehl.gibZweitesWort();
       // Wir versuchen den Gegenstand aufzunehmen.
        ArrayList<Gegenstand> gegenstaende = spieler.getAktuellerRaum().getAlleGegenstaende();
        for(Gegenstand g: gegenstaende)
        {
        	if(g.getName().equals(name))
        	{
        		if(spieler.gegenstandAufnehmen(g)){
        			spieler.getAktuellerRaum().entferneGegenstand(g);
        			return;
        		}
        		else
        		{
        			System.out.println("Der Gegenstand ist zu schwer!");
        			return;
        		}
        	}
        }
        System.out.println("Den Gegenstand gibt es hier nicht");
    }

    private void legeGegenstandAb(Befehl befehl)
    {
    	if(!befehl.hatZweitesWort()) {
        	// Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Welchen Gegenstand m�chten Sie ablegen?");
            return;
        }

        String name = befehl.gibZweitesWort();
        spieler.getAktuellerRaum().gegenstandAblegen(spieler.legeGegenstandAb(name));
    }

    private void issMuffin(Befehl befehl)
    {
    	if(!befehl.hatZweitesWort()) {
        	// Gibt es kein zweites Wort, wissen wir nicht, welcher Gegenstand gegessen werden soll..
            System.out.println("Welchen Gegenstand m�chten Sie essen?");
            return;
        }

        if(befehl.gibZweitesWort().equals("Muffin"))
        {
        	// Wir versuchen den Muffin zu essen.
        	LinkedList<Gegenstand> gegenstaende = spieler.getAlleGegenstaende();
        	for(Gegenstand g: gegenstaende)
        	{
        		if(g.getName().equals("Muffin"))
        		{
        			Muffin m = (Muffin) g;
        			spieler.setTragkraft(spieler.getTragkraft()+ m.getErhoehungTragkraft());
        			gegenstaende.remove(g);
        			return;
        		}
        	}
        }
        System.out.println("Du hast keinen Muffin");

    }

    /**
     * @author Pfaus
     * @param befehl
     * Zeigt eine Ascii Karte der Raeume in der Console.
     */
    private void showmap(Befehl befehl) {
        if (!befehl.hatZweitesWort()) {
            System.out.println("Welchen Gegenstand wollen Sie benutzen?");
        }
        if (befehl.gibZweitesWort().equalsIgnoreCase("Map")) {
            LinkedList<Gegenstand> gegenstaende = spieler.getAlleGegenstaende();
            for (Gegenstand g : gegenstaende) {
                if (g.getName().equals("Map")) {
                    Karte k = (Karte) g;
                    k.displayMap(); //ruft die Methode der Klasse Karte auf
                    return;
                }
            }
            System.out.println("Du hast noch keine Karte gefunden");
        }
    }

    /**
     * Der Heiltrank wird benutz
     */
    private void heilenTrankBenutzen() {
        LinkedList<Gegenstand> gegenstaende = spieler.getAlleGegenstaende();
        for (Gegenstand g : gegenstaende) {
            if (g.getName().equalsIgnoreCase("heiltrank")) {
                spieler.heilen();
                gegenstaende.remove(g);
                System.out.print("Heiltrank wird benutzt");
                makePause();
                System.out.println("Du hast noch " + zaehltGegenstand("heiltrank") + " Heiltranke!");
                return;
            }
        }
        System.out.println("Du hast gerade keinen Heiltrank");
    }

    /**
     * @param gegenstandName
     * @return die Zahl von einem bestimmten Gegenstand, der der Spieler hat
     */
    private int zaehltGegenstand(String gegenstandName){
        LinkedList<Gegenstand> gegenstaende = spieler.getAlleGegenstaende();
        int zahl = 0;
        for (Gegenstand g : gegenstaende){
            if (g.getName().equalsIgnoreCase(gegenstandName)){
                zahl++;
            }
        }
        return zahl;
    }

    /**
     * Der Fluss von die Konsoleausgabe wird kurz gehalten zu halten
     *
     */
    private void makePause() {
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(500); //Ausgabe wird 0.5 gehalten
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
