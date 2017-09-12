package Zustaende;

public class Gesund implements IZustand {

	private static Gesund instance=null;
	
	private Gesund(){
		
	}
	
	public static IZustand getInstance(){
		if(instance == null){
			instance = new Gesund();
		}
		return instance;
	}
	
	@Override
	public IZustand heilen() {
		return instance;

	}

	@Override
	public IZustand leichtVerletzen() {
		return Verwundet.getInstance();
	}

	@Override
	public IZustand starkVerletzen() {
		return Bewegungsunfaehig.getInstance();
	}
	
	
	
	public String toString(){
		return "Gesund";
	}

	@Override
	public IZustand toetlichVerletzen() {
		return Tod.getInstance();
	}

}
