package Tanques;

import Juego.*;
import Poderes.PowerUp;

public abstract class Tanque extends ObjetoConImagen {

    //Atributos
	
	protected int golpesRecibidos; 
	protected int disparosRealizados;
	protected int direccion;
	protected int x;
	protected int y; 
	
	//Constructor
	
	public Tanque(int direc, Celda celda){
		super(32,celda);
		direccion = direc;
		golpesRecibidos=0;
		disparosRealizados = 0;
		x = celda.getColumna()*tama�o;
		y = celda.getFila()*tama�o; 
	}
	
	//Comandos
	
	protected void moverseGraficamente(){
		switch(direccion){
			case 0:{//Arriba
				y-=(getVelocidadMovimiento()*2); 
				break; 
			}
			case 1:{//Abajo
				y+=(getVelocidadMovimiento()*2); 
				break; 
			}
			case 2:{//Derecha
				x+=(getVelocidadMovimiento()*2); 
				break; 
			}
			case 3:{//Izquierda
				x-=(getVelocidadMovimiento()*2); 
				break; 
			}
		}
		imagenActual.setIcon(imagenes[direccion]);
		imagenActual.setBounds(x,y,tama�o, tama�o);	
	}
	
	public abstract void actuar(PowerUp poder);
	
	public Disparo disparar(){
		Disparo disparo= null;
		if(disparosRealizados<cantMaximaDisparos()){
			disparosRealizados++;
			disparo = new Disparo(this, direccion, celda);
		}
		return disparo;
	}
	
	public void eliminarDisparo(){
		if(disparosRealizados>0){
			disparosRealizados--;
		}
		//System.out.println("Disparos Tanque:: "+disparosRealizados);
	}
	
	//hay que cambiar el boolean a void. 
	
	public abstract boolean recibirGolpe(Jugador tanque);
	
	public abstract boolean recibirGolpe(Enemigo tanque);
	
	public void setDireccion(int dir){
		direccion=dir;
	}
	
	public abstract boolean dispareTanque(Tanque tanque);
	
	//Consultas
	
	public int getDireccion(){
		return direccion;
	}
	
	public int cantMaximaDisparos(){
		return 1;
	}
	
	public abstract int getResistencia(); 
	
	public abstract int getVelocidadMovimiento();
	
	public abstract int getVelocidadDisparo();
}
