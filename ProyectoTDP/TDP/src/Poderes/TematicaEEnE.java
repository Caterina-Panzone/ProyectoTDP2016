package Poderes;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Logica;
import Juego.Tematica;
import Tanques.Jugador;

public class TematicaEEnE extends CambioTematica{
	
	public TematicaEEnE(Celda celda, Logica logica) {
		super(celda, logica);
	}

	public void setearImagenes(){
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/EdEdd&EddyTematica.gif"));
	}
	
	public void actuar(Jugador tanque) {
		Tematica.setTematica("EdEdd&Eddy");
		super.actuar(tanque); 
	}
}
