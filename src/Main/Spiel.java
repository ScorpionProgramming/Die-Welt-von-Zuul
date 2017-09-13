package Main;
import java.util.ArrayList;
import java.util.LinkedList;

import Items.Gegenstand;
import Items.Karte;
import Items.Muffin;
import Lebewesen.Spieler;
import Level.Landkarte;
import Level.Raum;
import Utils.Befehl;
import Utils.Parser;

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
        
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
    	spieler = new Spieler();
    	landkarte = new Landkarte();
        landkarte.raeumeAnlegen(spieler);
        parser = new Parser();
    }

    /**
     * Erzeugt ein Spielobjekt und f�hrt an ihm die Methode spielen aus.
	 * @param args
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
        while (! beendet && !spieler.getAktuellerZustand().toString().equals("Tod")) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        
        if(spieler.getAktuellerZustand().toString().equals("Tod"))
        	System.out.println("Sie sind leider gestorben.");
        
        System.out.println("Danke f�r dieses Spiel. Auf Wiedersehen.");
    }

    /**
     * Einen Begr��ungstext f�r den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu Zuul!");
        System.out.println("Zuul ist ein neues, unglaublich langweiliges Spiel.");
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
        }else if(befehlswort.equals("map")) {
        	showmap();
        }
        	
        return moechteBeenden;
    }

	/**
     * Methode zum Befehl attack
     * Greift ein Monster an und das Monster greift direkt zur�ck an
     *
     */
    private void angreifen() {
    	this.spieler.angreifen(this.spieler.getAktuellerRaum().getMonster());
    	
    	System.out.print(this.spieler.getAktuellerRaum().getMonster().getName());
    	System.out.println(": " + this.spieler.getAktuellerRaum().getMonster().getAktuellerZustand().toString());
    	
    	if(this.spieler.getAktuellerRaum().getMonster().getAktuellerZustand().toString().equals("Tod")){
    		this.spieler.getAktuellerRaum().setMonster(null);
    	}else{
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
        	ArrayList<Gegenstand> gegenstaende = spieler.getAktuellerRaum().getAlleGegenstaende();
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
        System.out.println("Dieser Raum hat keinen Muffin");
    	
    }
    
    /**
     * @author Pfaus
     * @param befehl
     * Zeigt eine Ascii Karte der Raeume in der Console.
     */
    private void showmap() {
  		LinkedList<Gegenstand> gegenstaende = spieler.getAlleGegenstaende();
  		for(Gegenstand g: gegenstaende) {
  			if(g.getName().equals("Map")) {
  				Karte k = (Karte) g;
  				k.displayMap(); //ruft die Methode der Klasse Karte auf
  				return;
  			}
  		}
  		System.out.println("Du hast noch keine Karte gefunden");
  	}
}
