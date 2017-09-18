package Lebewesen;
import Level.Raum;
import Zustaende.IZustand;
/**
 * Klasse Lebewesen hat die Attribute "aktuellerRaum" und "aktuellerZustand"
 *
 */
public class Lebewesen {

	Raum aktuellerRaum;
	IZustand aktuellerZustand;
/**
 * Getter
 * @return aktueller Raum des Lebewesens
 */
	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}
/**
 * Setter
 * @param aktuellerRaum Aktueller Raum des Lebewesens
 */
	public void setAktuellerRaum(Raum aktuellerRaum) {
		this.aktuellerRaum = aktuellerRaum;
	}
/**
 * Methode heilen um den Zustand des Lebewesens zu verbessern
 * 
 */
	public void heilen(){
		aktuellerZustand=aktuellerZustand.heilen();		
	}
/**
 * Methode leichtVerletzen
 * Verschlechtert den Zustand als Folge eines Kampfes
 */
	public void leichtVerletzen(){
		aktuellerZustand= aktuellerZustand.leichtVerletzen();
	}
	/**
	 * Methode starkVerletzen
	 * Verschlechtert den Zustand als Folge eines Kampfes
	 */	
	public void starkVerletzen(){
		aktuellerZustand = aktuellerZustand.starkVerletzen();
	}
	/**
	 * Methode toetlichVerletzen
	 * Verschlechtert den Zustand als Folge eines Kampfes -> tötet das Lebewesen auf jeden Fall
	 */	
	public void toetlichVerletzen(){
		aktuellerZustand = aktuellerZustand.toetlichVerletzen();
	}
	/**
	 * Methode getAktuellerZustand
	 * @return gibt den Aktuellen Zustand des Lebewesens zurück
	 */	
	public IZustand getAktuellerZustand() {
		return aktuellerZustand;
	}
	
//	public void angreifen(Lebewesen lebewesen){
//		double zahl = Math.random(); //generiert eine Zahl (double) zwischen 0 und 1
//		
//		//welche klasse greift welche
//		
//		if(zahl < 0.6d){ //leichtverletzen
//			getClass.leichtVerletzen();
//		}else if(zahl < 0.9d){ //schwer verletzen
//			this.spieler.aktuellerRaum.getMonster().starkVerletzen();
//		}else{ //direkt töten
//			this.spieler.aktuellerRaum.getMonster().toetlichVerletzen();
//		}
//	
//	}
	
	
	
}
