package Tanques;

import javax.swing.ImageIcon;

import Juego.*;

  public class DePoder extends Enemigo{
  
	//Constructor
	  
    public DePoder(Celda celda){
	    super(2,3,1,'I',celda, 300);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderIzquierda.png"));
		cambiarImagenActual(3);
    }
  }