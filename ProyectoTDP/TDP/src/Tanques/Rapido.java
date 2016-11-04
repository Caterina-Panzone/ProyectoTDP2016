package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class Rapido extends Enemigo{
  
	//Constructor 
	  
    public Rapido(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(3,2,1,celda, 200, inteligencia,enemigos);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoIzquierda.png"));
		cambiarImagenActual(direccion);
    }

	public int getEsperaPersonal() {
		return 8;
	}
  }
