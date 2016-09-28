package Tanques;

import javax.swing.ImageIcon;

import Juego.*;

  public class Basico extends Enemigo{
  
	//Constructor
    public Basico(Celda celda){
	    super(1,1,1,'I',celda, 100);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoIzquierda.png"));
		cambiarImagenActual(3);
    }
  }