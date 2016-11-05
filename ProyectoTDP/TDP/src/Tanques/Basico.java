package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class Basico extends Enemigo{
  
	//Constructor
    public Basico(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(1,1,1,celda, 100, inteligencia,enemigos);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoIzquierda.gif"));
		cambiarImagenActual(direccion);
    }

	public int getEsperaPersonal() {
		return 2;
	}
  }
