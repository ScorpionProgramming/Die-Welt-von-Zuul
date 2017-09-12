package Kampfsystem;
import Lebewesen.Lebewesen;

public interface IKampfsystem {
		void angreifen(Lebewesen lebewesen);
		void verteidigen(Lebewesen lebewesen); // TODO wird noch nicht verwendet ( loeschen? )
}
