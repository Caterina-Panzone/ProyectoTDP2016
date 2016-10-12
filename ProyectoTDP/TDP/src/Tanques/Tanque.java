package Tanques;

import java.util.LinkedList;
import java.util.List;
import Juego.*;
import Poderes.PowerUp;

public abstract class Tanque extends ObjetoConImagen {

    //Atributos
	
	protected int cantMaxima; 
	protected int golpesRecibidos; 
	protected int disparosRealizados;
	protected char direccion;
	
	//Constructor
	
	public Tanque(char direc, Celda celda){
		super(32,celda);
		direccion = direc;
		golpesRecibidos=0;
		disparosRealizados = 0;
	}
	
	//Comandos
	
	public abstract void actuar(PowerUp poder);
	
	public Disparo disparar(){
		Disparo disparo= null;
		if(disparosRealizados<cantMaxima){
			disparosRealizados++;
			disparo = new Disparo(this, direccion, celda);
		}
		return disparo;
	}
	
	public void eliminarDisparo(){
		disparosRealizados--;
	}
	
	//hay que cambiar el boolean a void. 
	
	public abstract boolean recibirGolpe(Jugador tanque);
	
	public abstract boolean recibirGolpe(Enemigo tanque);
	
	public void setDireccion(char dir){
		direccion=dir;
	}
	
	public abstract boolean dispareTanque(Tanque tanque);
	
	//Consultas
	
	public char getDireccion(){
		return direccion;
	}
	
	public abstract int getResistencia(); 
	
	public abstract int getVelocidadMovimiento();
	
	public abstract int getVelocidadDisparo();
}
