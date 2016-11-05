package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class Blindado extends Enemigo{
  
	//Constructor
	  
    public Blindado(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(1,2,4,celda, 400, inteligencia,enemigos);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BlindadoIzquierda.gif"));
		cambiarImagenActual(direccion);
    }

	public int getEsperaPersonal() {
		return 4;
	}
  }
