package Juego;
import javax.swing.ImageIcon;

import Tanques.Tanque;

public class Disparo extends ObjetoConImagen{

	//Atributos
	
	protected Tanque emisor;
	protected char direccion;
	
	//Constructor
	
	public Disparo(Tanque emisor, char dir, Celda celda){
		super(8,celda);
		this.emisor=emisor;
		direccion=dir;
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaA.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaB.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaD.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaI.png"));
		
		switch (direccion){
			case 'A': {
				cambiarImagenActual(0);
				break;
			}
			case 'B': {
				cambiarImagenActual(1);
				break;
			}
			case 'D': {
				cambiarImagenActual(2);
				break;
			}
			case 'I': {
				cambiarImagenActual(3);
				break;
			}
		}
	}
	
	//Comandos 
	
	public void destruirse(){
		emisor.eliminarDisparo(this);
		emisor=null;
	}
	
}