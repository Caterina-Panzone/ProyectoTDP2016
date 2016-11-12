package JGeneradores;

import Juego.Logica;

public class GeneradorNivel1 extends Generador {

	public GeneradorNivel1(Logica logica){
		super(logica);
	}

	public boolean perteneceRapido(int n) {
		return (n>=14)&&(n<=16);
	}

	public boolean perteneceDePoder(int n) {
		return (n>=0)&&(n<=6);
	}

	public boolean perteneceBlindado(int n) {
		return (n>=17)&&(n<=19);
	}
	
	public Generador getSiguienteGenerador(){
		return new GeneradorNivel2(logica); 
	}
}
