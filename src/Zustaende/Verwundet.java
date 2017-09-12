package Zustaende;

public class Verwundet implements IZustand {
	
	private static Verwundet instance = null;
	
	private Verwundet(){
		
	}
	
	public static IZustand getInstance(){
		if(instance == null){
			instance = new Verwundet();
		}
		return instance;
	}

	@Override
	public IZustand heilen() {
		return Gesund.getInstance();
	}

	@Override
	public IZustand leichtVerletzen() {
		return  Bewegungsunfaehig.getInstance();

	}

	@Override
	public IZustand starkVerletzen() {
		return Tod.getInstance();
	}
	
	public String toString(){
		return "Verwundet";
	}

	@Override
	public IZustand toetlichVerletzen() {
		return Tod.getInstance();
	}

}
