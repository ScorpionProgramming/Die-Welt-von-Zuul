package Zustaende;
public interface IZustand {
	IZustand heilen();
	IZustand leichtVerletzen();
	IZustand starkVerletzen();
	IZustand toetlichVerletzen();
}
