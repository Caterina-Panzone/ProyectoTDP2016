package Poderes;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Logica;
import Juego.Tematica;
import Tanques.Jugador;

public class TematicaDexter extends CambioTematica{
	
	public TematicaDexter(Celda celda, Logica logica) {
		super(celda, logica);
	}

	public void setearImagenes(){
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/DexterTematica.gif"));
	}
	
	public void actuar(Jugador tanque) {
		Tematica.setTematica("Dexter");
		super.actuar(tanque); 
	}
}
