package Poderes;

import Juego.*;
import Tanques.*;

public abstract class PowerUp extends ObjetoConImagen {
	//Constructor
	public PowerUp(Celda celda){
		super(celda); 
		cambiarImagenActual(0);
	}
	
	//Comandos
	
	public void actuar(Jugador tanque){
		celda.setPower(null);
		ponerImagenVacia();
	}
	
	public void setCelda(Celda nueva){
		if(celda!=null){
			celda.setPower(null);
		}
		celda = nueva; 
		if (celda!=null){
			celda.setPower(this);  
		}
	}
}