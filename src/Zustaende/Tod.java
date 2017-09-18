package Zustaende;

public class Tod implements IZustand{
	
	private static Tod instance = null;
	public Tod(){
		
	}
	
	public static IZustand getInstance(){
		if(instance == null){
			instance = new Tod();
		}
		return instance;
	}
	
	@Override
	public IZustand heilen() {
		return instance;
	}

	@Override
	public IZustand leichtVerletzen() {
		return instance;
	}

	@Override
	public IZustand starkVerletzen() {
		return instance;
	}

	@Override
	public IZustand toetlichVerletzen() {
		return instance;
	}
	
	public String toString(){
		return "Tod";
	}

}
