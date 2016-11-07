package Obstaculos;

import javax.swing.ImageIcon;
import Juego.*;
import Tanques.Tanque;

public class Aguila extends Obstaculo{
	//Constructor
	protected Logica logica; 
	
	public Aguila(Celda celda,Logica logica){
		super(false, false, celda); 
		this.logica = logica; 
	}
	
	//Comando
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/F.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Fdead.png"));
	}
	
	public void recibirGolpe(Tanque tanque){
		cambiarImagenActual(1);
		logica.finalizarJuego(); 
	}

}
