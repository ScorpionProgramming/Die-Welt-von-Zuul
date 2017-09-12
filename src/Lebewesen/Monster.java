package Lebewesen;
import Kampfsystem.IKampfsystem;
import Zustaende.Gesund;

public class Monster extends Lebewesen implements IKampfsystem{

	private String name;
	
	public Monster()
	{
		this.name = "Monster";
		aktuellerZustand = Gesund.getInstance();
	}
	
	public Monster(String name){
		this.name = name;
		aktuellerZustand = Gesund.getInstance();
	}

	public String getName() {
		return name;
	}
	
	public void angreifen(Lebewesen lebewesen) {
		// TODO Auto-generated method stub
		double zahl = Math.random(); //generiert eine Zahl (double) zwischen 0 und 1
		if(zahl < 0.9d){ //leichtverletzen
			lebewesen.leichtVerletzen();
			//System.out.println("Monster: "+ this.aktuellerZustand);
		}else if(zahl < 0.98d){ //schwer verletzen
			lebewesen.starkVerletzen();
			//System.out.println("Monster: "+ this.aktuellerZustand);
		}else{ //direkt töten
			lebewesen.toetlichVerletzen();
			//System.out.println("Monster: "+ this.aktuellerZustand);
		}
	}

	@Override
	public void verteidigen(Lebewesen lebewesen) {
		// TODO verteidigen macht momentan noch nichts
	}
}
