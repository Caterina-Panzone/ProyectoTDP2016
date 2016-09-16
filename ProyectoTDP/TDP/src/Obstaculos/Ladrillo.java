package Obstaculos;

import javax.swing.ImageIcon;
import Juego.*; 

public class Ladrillo extends Obstaculo {
	//Atributos
	
	protected int golpesRecibidos;
	
	//Constructor
	
	public Ladrillo(Celda celda){
		super(false, false, celda); 
		golpesRecibidos=0;
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/L1.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/L2.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/L3.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/L4.png"));
		cambiarImagenActual(0); 
	}
	
	//Comandos
	
	public boolean recibirGolpe(){
		golpesRecibidos++; 
		if(golpesRecibidos>=4){
			celda.piso();
			celda = null; 
		}else
			cambiarImagenActual(golpesRecibidos);

		return false; 
	}
}