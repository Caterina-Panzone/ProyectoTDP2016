package Juego;

import java.util.List;

import Poderes.PowerUp;
import Tanques.Enemigo;

public class GeneradorNivel1 extends Generador {

	public GeneradorNivel1(Logica logica){
		super(logica);
	}

	public PowerUp generarPowerUp() {
		//HAY QUE HACERLOOOOOOOOOOOO
		return null;
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