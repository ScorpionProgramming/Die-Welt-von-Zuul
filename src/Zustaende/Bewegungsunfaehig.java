package Zustaende;

public class Bewegungsunfaehig implements IZustand {
	
    private static Bewegungsunfaehig instance = new Bewegungsunfaehig();
	
	private Bewegungsunfaehig(){
		
	}
	
	public static IZustand getInstance(){
		return instance;
	}

	@Override
	public IZustand heilen() {
		return Verwundet.getInstance();
	}

	@Override
	public IZustand leichtVerletzen() {
		return Tod.getInstance();

	}

	@Override
	public IZustand starkVerletzen() {
		return Tod.getInstance();

	}
	
	public String toString(){
		return "Bewegungsunfähig";
	}

	@Override
	public IZustand toetlichVerletzen() {
		return Tod.getInstance();
	}

}
