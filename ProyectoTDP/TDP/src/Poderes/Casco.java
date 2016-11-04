package Poderes;

import Juego.*;
import Tanques.*; 

public class Casco extends PowerUp{
	
	//Constructor
	
	public Casco(Celda celda){
		super(celda); 
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		tanque.setInvulnerabildiad(true);
		//Tiene que tener un contador
	}
	
}
