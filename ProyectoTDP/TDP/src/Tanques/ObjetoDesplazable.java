package Tanques;

import Juego.*; 

public abstract class ObjetoDesplazable extends ObjetoConImagen{
	protected int x;
	protected int y; 
	protected int lock; 
	protected int aumento; 

	public ObjetoDesplazable(int tamaño, Celda celda, int aumento) {
		super(tamaño, celda);
		this.aumento = aumento; 
		x = celda.getColumna()*tamaño;
		y = celda.getFila()*tamaño; 
		lock = 0; 
	}
	
	//Comandos
	
	protected void moverseGraficamente(){
		switch(getDireccion()){
			case 0:{//Arriba
				y-=(getVelocidadMovimiento()*aumento); 
				break; 
			}
			case 1:{//Abajo
				y+=(getVelocidadMovimiento()*aumento); 
				break; 
			}
			case 2:{//Derecha
				x+=(getVelocidadMovimiento()*aumento); 
				break; 
			}
			case 3:{//Izquierda
				x-=(getVelocidadMovimiento()*aumento); 
				break; 
			}
		}
		imagenActual.setIcon(imagenes[getDireccion()]);
		imagenActual.setBounds(x,y,tamaño, tamaño);	
	}
	
	public abstract int getVelocidadMovimiento(); 
	
	public abstract int getDireccion();
	
	public abstract void moverse(); 
}
