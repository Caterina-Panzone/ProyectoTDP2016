package Tanques;

import javax.swing.ImageIcon;

import Juego.*;

  public class Blindado extends Enemigo{
  
	//Constructor
	  
    public Blindado(Celda celda){
	    super(1,2,4,'I',celda, 400);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoIzquierda.png"));
		cambiarImagenActual(3);
    }
  }