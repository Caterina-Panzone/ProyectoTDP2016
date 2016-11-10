package Obstaculos;

import javax.swing.ImageIcon;

import Juego.Celda;
import Juego.Tematica;
import Tanques.*; 

public class Acero extends Obstaculo{
	//Atributos
	
	protected int golpesRecibidos;
	
	//Constructor
	
	public Acero(Celda celda){
		super(false, false, celda); 
		golpesRecibidos=0;
	}
	
	//Comandos
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/A1.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/A2.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/A3.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/A4.png"));
	}
	
	public void recibirGolpe(Tanque tanque){
		tanque.romperAcero(this); 
	}
	
	public void recibirGolpe(){
		golpesRecibidos++; 
		if(golpesRecibidos>=4){
			ponerImagenVacia();
			celda.setObstaculo(null);
			celda = null; 
		}else
			cambiarImagenActual(golpesRecibidos);
	}
	
	public int indiceActual(){
		return golpesRecibidos; 
	}
}

