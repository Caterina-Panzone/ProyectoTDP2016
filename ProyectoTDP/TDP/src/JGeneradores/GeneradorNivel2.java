package JGeneradores;

import Juego.Logica;

public class GeneradorNivel2 extends Generador {

	public GeneradorNivel2(Logica logica){
		super(logica);
	}

	public boolean perteneceRapido(int n) {
		return (n>=12)&&(n<=15);
	}

	public boolean perteneceDePoder(int n) {
		return (n>=0)&&(n<=5);
	}

	public boolean perteneceBlindado(int n) {
		return (n>=16)&&(n<=19);
	}
	
	public Generador getSiguienteGenerador(){
		return new GeneradorNivel3(logica); 
	}
}
