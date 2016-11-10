package TDisparo;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Logica;
import Juego.Tematica;
import Tanques.Tanque;

public class DisparoBasico extends Disparo{

	public DisparoBasico(Tanque emisor, int dir, Celda celda, Logica logica) {
		super(emisor, dir, celda, logica);
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaBasicoArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaBasicoAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaBasicoDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaBasicoIzquierda.gif"));
	}

}
