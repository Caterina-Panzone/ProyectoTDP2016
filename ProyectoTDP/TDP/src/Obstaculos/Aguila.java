package Obstaculos;

import javax.swing.ImageIcon;
import Juego.*;

public class Aguila extends Obstaculo{
	//Constructor
	protected Logica logica; 
	
	public Aguila(Celda celda,Logica logica){
		super(false, false, celda); 
		this.logica = logica; 
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/F.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/AguilaMuerta.png"));
		cambiarImagenActual(0); 
	}
	
	//Comando
	
	public void recibirGolpe(){
		cambiarImagenActual(1);
		logica.finalizarJuego(); 
	}

}
