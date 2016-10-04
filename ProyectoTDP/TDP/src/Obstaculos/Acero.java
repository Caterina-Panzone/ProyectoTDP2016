package Obstaculos;

import javax.swing.ImageIcon;

import Juego.Celda;

public class Acero extends Obstaculo{
	//Atributos
	
	protected int golpesRecibidos;
	
	//Constructor
	
	public Acero(Celda celda){
		super(false, false, celda); 
		golpesRecibidos=0;
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/A.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/A2.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/A3.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/A4.png"));
		cambiarImagenActual(0); 
	}
	
	//Comandos
	
	public void recibirGolpe(){
		golpesRecibidos++; 
		if(golpesRecibidos>=4){
			ponerImagenVacia();
			celda.setObstaculo(null);
			celda = null; 
		}else
			cambiarImagenActual(golpesRecibidos);
	}
}

