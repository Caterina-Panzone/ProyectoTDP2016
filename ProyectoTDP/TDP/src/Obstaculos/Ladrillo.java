package Obstaculos;

import javax.swing.ImageIcon;

import Juego.*;
import Tanques.Tanque; 

public class Ladrillo extends Obstaculo {
	//Atributos
	
	protected int golpesRecibidos;
	
	//Constructor
	
	public Ladrillo(Celda celda){
		super(false, false, celda); 
		golpesRecibidos=0;
	}
	
	//Comandos
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/L1.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/L2.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/L3.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/L4.png"));
	}
	
	public void recibirGolpe(Tanque tanque){
		golpesRecibidos++; 
		if(golpesRecibidos>=4){
			ponerImagenVacia();
			celda.setObstaculo(null);
			celda = null; 
		}else
			cambiarImagenActual(golpesRecibidos);
	}
}