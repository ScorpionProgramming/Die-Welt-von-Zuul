package Lebewesen;
import java.util.ArrayList;
import java.util.LinkedList;

import Items.Gegenstand;
import Kampfsystem.IKampfsystem;
import Zustaende.Gesund;

public class Spieler extends Lebewesen implements IKampfsystem{
	private double tragkraft;
	private LinkedList<Gegenstand> gegenstaende;
	
	public Spieler()
	{
		tragkraft = 10.0;
		gegenstaende = new LinkedList<Gegenstand>();
		aktuellerZustand = Gesund.getInstance();
	}

	public double getTragkraft() {
		return tragkraft;
	}

	public void setTragkraft(double tragkraft) {
		this.tragkraft = tragkraft;
	}
	
	public boolean gegenstandAufnehmen(Gegenstand g)
	{
		if(ermittleAktuellesGewicht()+g.getGewicht()<= this.tragkraft)
		{
			gegenstaende.add(g);
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	private double ermittleAktuellesGewicht()
	{
		double gewicht = 0;
		for(Gegenstand g: gegenstaende)
			gewicht = gewicht + g.getGewicht();
		return gewicht;
	}
	
	public String zeigeStatus()
    {
    	StringBuilder ergebnis = new StringBuilder("Tragkraft:"+ this.tragkraft+"\nAufgenommene Gegenstände: ");
    	if(gegenstaende.isEmpty())
    	{
    		ergebnis.append("keine");
    	}
    	else
    	{
    		for(Gegenstand g: gegenstaende)
    			ergebnis.append(g.toString()+"; ");
    		ergebnis.append("\naktuelles Gewicht: "+ermittleAktuellesGewicht());
    		ergebnis.toString();    			
    	}
    	
    	//Zustand
    	ergebnis.append("\nZustand: "+aktuellerZustand.toString());
    	return ergebnis.toString();
    }
	
	public Gegenstand legeGegenstandAb(String name)
	{
		for(Gegenstand g: gegenstaende)
		{
			if(g.getName().equals(name)){
				gegenstaende.remove(g);
				return g;
			}
		}
		return null;
	}

	@Override
	public void angreifen(Lebewesen lebewesen) {
		// TODO Auto-generated method stub
		double zahl = Math.random(); //generiert eine Zahl (double) zwischen 0 und 1
		if(zahl < 0.6d){ //leichtverletzen
			lebewesen.leichtVerletzen();
			//System.out.println(lebewesen);
			//System.out.println("Der Gegner wurde leicht verletzt!");
		}else if(zahl < 0.9d){ //schwer verletzen
			lebewesen.starkVerletzen();
			//System.out.println("Der Gegner wurde schwer verletzt!");
		}else{ //direkt töten
			lebewesen.toetlichVerletzen();
			//System.out.println("Der Gegner wurde tötlich verletzt!");
		}
	}

	@Override
	public void verteidigen(Lebewesen lebewesen) {
		// TODO verteidigen methode macht momentan nichts
	}
	
	
	/**
	 * @author Pfaus
	 * @return ArrayList mit Gegenstaenden
	 * Gibt alle Gegenstaende in einer Linked List zurueck, die der Spieler bei sich trägt
	 */
	public LinkedList<Gegenstand> getAlleGegenstaende()
    {
    	return gegenstaende;
    }
}
