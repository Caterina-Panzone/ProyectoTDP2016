package Tanques;

import java.util.List;

import javax.swing.ImageIcon;

import Juego.*;
import TDisparo.Disparo;
import TDisparo.DisparoBasico;

  public class Basico extends Enemigo{
  
	//Constructor
    public Basico(Celda celda, InteligenciaEnemigo inteligencia, List<Enemigo> enemigos){
	    super(1,1,1,celda, 100, inteligencia,enemigos);
    }
    
    public void setearImagenes(){
    	String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BasicoArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BasicoAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BasicoDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BasicoIzquierda.gif"));
    }

	public int getEsperaPersonal() {
		return 2;
	}
	
	protected Disparo dispararAux(Logica logica){
		if(Tematica.getTematica()=="Dexter"){
			return new DisparoBasico(this, direccion, celda, logica);
		} else{
			return super.dispararAux(logica); 
		}
	}
  }
