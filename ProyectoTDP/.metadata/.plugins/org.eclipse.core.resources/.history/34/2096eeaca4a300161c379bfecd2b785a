package Poderes;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.*; 

public class Casco extends PowerUp{
	
	//Constructor
	
	public Casco(Celda celda){
		super(celda); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Casco.gif"));
		cambiarImagenActual(0);
	}

	public void actuar(Jugador tanque) {
		super.actuar(tanque); 
		tanque.setInvulnerabildiad(true);
		//Tiene que tener un contador
	}
	
}
