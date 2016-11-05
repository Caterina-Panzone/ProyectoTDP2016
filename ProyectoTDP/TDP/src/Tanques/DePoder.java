package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class DePoder extends Enemigo{
  
	//Constructor
	  
    public DePoder(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(2,3,1,celda, 300, inteligencia, enemigos);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/PoderArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/PoderAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/PoderDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/PoderIzquierda.gif"));
		cambiarImagenActual(direccion);
    }

	public int getEsperaPersonal() {
		return 16;
	}
  }
