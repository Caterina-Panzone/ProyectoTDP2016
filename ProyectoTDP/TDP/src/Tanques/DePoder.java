package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;
import TDisparo.Disparo;
import TDisparo.DisparoDePoder;

  public class DePoder extends Enemigo{
  
	//Constructor
	  
    public DePoder(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(4,4,1,celda, 300, inteligencia, enemigos);
    }
    
    public void setearImagenes(){
    	String tematica = Tematica.getTematica(); 
    	imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/PoderArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/PoderAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/PoderDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/PoderIzquierda.gif"));
    }

	public int getEsperaPersonal() {
		return 16;
	}
	
	protected Disparo dispararAux(Logica logica){
		return new DisparoDePoder(this, direccion, celda, logica);
	}
  }
