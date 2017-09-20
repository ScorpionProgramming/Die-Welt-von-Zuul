package Level;

import java.util.ArrayList;
import java.util.HashMap;

import Items.Gegenstand;
import Lebewesen.Monster;

/**
 * Diese Klasse modelliert R?ume in der Welt von Zuul.
 *
 * Ein "Raum" repr?sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen R?umen ?ber Ausg?nge verbunden.
 * M?gliche Ausg?nge liegen im Norden, Osten, S?den und Westen.
 * F?r jede Richtung h?lt ein Raum eine Referenz auf den 
 * benachbarten Raum.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Raum
{
    private String beschreibung;
    private ArrayList<Gegenstand> gegenstaende;
    private HashMap<String, Raum> ausgaenge;
    private Monster monster; //TODO: eventuell zu ArrayList umschreiben
    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausg?nge.
     * @param beschreibung enth?lt eine Beschreibung in der Form
     *        "in einer K?che" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<String, Raum>();
        gegenstaende = new ArrayList<Gegenstand>();
        monster = null;
    }

    /**
     * Definiere einen Ausgang aus diesem Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der ?ber diesen Ausgang erreicht wird
     */
    public void setAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung,  nachbar);
    }

    /**
     * @return Die Beschreibung dieses Raums.
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }
    
    /**
     * Liefere den Raum, den wir errichen, wenn wir aus diesem Raum
     * in die angegebene Richtung gehen. Liefere null, wenn
     * in dieser Richtung kein Ausgang ist.
     * @param richtung die Richtung, in die gegangen werden soll.
     * @return den Raum, der in der angegebenen Richtung liegt.
     */
    
    public Raum getAusgang(String richtung)
    {
    	return ausgaenge.get(richtung);
    }
    
    /**
     * Liefert alle Ausgaenge des Raumes.
     * @return alle Ausgaenge des Raumes
     */
    
    public String ausgaengeToString()
    {
        StringBuilder ergebnis = new StringBuilder("Ausg?nge: ");
        for(String key : ausgaenge.keySet())
    		ergebnis.append(key).append(" ");    	
    	return ergebnis.toString();
    }
    
    /**
     * Liefere eine lange Beschreibung dieses Raums, in der Form:
     *    Sie sind in der K?che.
     *    Ausg?nge: north, west
     * @return eine lange Beschreibung des Raums
     */
    public String getLangeBeschreibung()
    {	
    	String monstervar = "";
    	if(monster != null){
    		monstervar = "\nMonster: " + monster.getName();
    	}
    	return "Sie sind " + beschreibung + ".\n"+ ausgaengeToString()+
                "\nGegenst?nde: " + gegenstaendeToString() +
                monstervar;
    }
    
    public void gegenstandAblegen(Gegenstand g)
    {
    	gegenstaende.add(g);
    }
    
    public ArrayList<Gegenstand> getAlleGegenstaende()
    {
    	return gegenstaende;
    }
    
    public void entferneGegenstand(Gegenstand g)
    {
    	gegenstaende.remove(g);
    }
    
    public String gegenstaendeToString()
    {
    	StringBuilder ergebnis = new StringBuilder();
    	if(gegenstaende.isEmpty())
    	{
    		return "keine";
    	}
    	else
    	{
    		for(Gegenstand g: gegenstaende)
    			ergebnis.append(g.toString()+"; ");
    		return ergebnis.toString();    			
    	}
    }

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

}
