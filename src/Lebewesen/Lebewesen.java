package Lebewesen;
import Level.Raum;
import Zustaende.IZustand;

public class Lebewesen {

	Raum aktuellerRaum;
	IZustand aktuellerZustand;
	
	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

	public void setAktuellerRaum(Raum aktuellerRaum) {
		this.aktuellerRaum = aktuellerRaum;
	}

	public void heilen(){
		aktuellerZustand=aktuellerZustand.heilen();		
	}
	
	public void leichtVerletzen(){
		aktuellerZustand= aktuellerZustand.leichtVerletzen();
	}
	
	public void starkVerletzen(){
		aktuellerZustand = aktuellerZustand.starkVerletzen();
	}
	
	public void toetlichVerletzen(){
		aktuellerZustand = aktuellerZustand.toetlichVerletzen();
	}
	
	public IZustand getAktuellerZustand() {
		return aktuellerZustand;
	}
	
	/**
	 * Methode soll angreifen - leicht oder schwer verletzen
	 * @param lebewesen
	 */
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
