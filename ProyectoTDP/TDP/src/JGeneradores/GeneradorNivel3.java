package JGeneradores;
import Juego.Logica;

public class GeneradorNivel3 extends Generador {

	public GeneradorNivel3(Logica logica){
		super(logica);
	}

	public boolean perteneceRapido(int n) {
		return (n>=6)&&(n<=12);
	}

	public boolean perteneceDePoder(int n) {
		return (n>=3)&&(n<=5);
	}

	public boolean perteneceBlindado(int n) {
		return (n>=13)&&(n<=19);
	}
	
	public Generador getSiguienteGenerador(){
		return null; 
	}
}
