package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class Basico extends Enemigo{
  
	//Constructor
    public Basico(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(1,1,1,3,celda, 100, inteligencia,enemigos);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BasicoIzquierda.png"));
		cambiarImagenActual(3);
    }

	public int getEsperaPersonal() {
		return 2;
	}
  }
