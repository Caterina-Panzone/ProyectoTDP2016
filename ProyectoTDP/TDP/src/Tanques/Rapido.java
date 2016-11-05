package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class Rapido extends Enemigo{
  
	//Constructor 
	  
    public Rapido(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(3,2,1,celda, 200, inteligencia,enemigos);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/RapidoIzquierda.gif"));
		cambiarImagenActual(direccion);
    }

	public int getEsperaPersonal() {
		return 12;
	}
  }
