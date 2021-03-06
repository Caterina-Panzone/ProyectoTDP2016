package Poderes;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Logica;
import Juego.Tematica;
import Tanques.Jugador;

public class TematicaCoraje extends CambioTematica{
	
	public TematicaCoraje(Celda celda, Logica logica) {
		super(celda, logica);
	}

	public void setearImagenes(){
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/CorajeTematica.gif"));
	}
	
	public void actuar(Jugador tanque) {
		Tematica.setTematica("Coraje");
		super.actuar(tanque); 
	}
}
