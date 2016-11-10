package TDisparo;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Logica;
import Juego.Tematica;
import Tanques.Tanque;

public class DisparoRapido extends Disparo{
	public DisparoRapido(Tanque emisor, int dir, Celda celda, Logica logica) {
		super(emisor, dir, celda, logica);
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaRapidoArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaRapidoAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaRapidoDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaRapidoIzquierda.gif"));
	}
}
