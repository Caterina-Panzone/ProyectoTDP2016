package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class DePoder extends Enemigo{
  
	//Constructor
	  
    public DePoder(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(2,3,1,3,celda, 300, inteligencia, enemigos);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/DePoderIzquierda.png"));
		cambiarImagenActual(3);
    }

	public int getEsperaPersonal() {
		return 2;
	}
  }
