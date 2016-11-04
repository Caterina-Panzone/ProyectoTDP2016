package Juego;


import Poderes.PowerUp;

public class GeneradorNivel1 extends Generador {

	public GeneradorNivel1(Logica logica){
		super(logica);
	}

	public boolean perteneceRapido(int n) {
		return (n>=12)&&(n<=15);
	}

	public boolean perteneceDePoder(int n) {
		return (n>=0)&&(n<=5);
	}

	public boolean perteneceBlindado(int n) {
		return (n>=16)&&(n<=20);
	}

	
	
}
