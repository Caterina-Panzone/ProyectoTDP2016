package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;

  public class Rapido extends Enemigo{
  
	//Constructor 
	  
    public Rapido(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(3,2,1,celda, 200, inteligencia,enemigos);
    }
    
    public void setearImagenes(){
    	String tematica = Tematica.getTematica();
    	imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/RapidoArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/RapidoAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/RapidoDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/RapidoIzquierda.gif"));
    }

	public int getEsperaPersonal() {
		return 12;
	}
  }
