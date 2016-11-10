package TDisparo;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Logica;
import Juego.Tematica;
import Tanques.Tanque;

public class DisparoJugador extends Disparo{

	public DisparoJugador(Tanque emisor, int dir, Celda celda, Logica logica) {
		super(emisor, dir, celda, logica);
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaJugadorArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaJugadorAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaJugadorDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaJugadorIzquierda.gif"));
	}
}
