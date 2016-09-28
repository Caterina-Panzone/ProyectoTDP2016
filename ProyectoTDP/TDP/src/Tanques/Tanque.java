package Tanques;

import java.util.LinkedList;
import java.util.List;
import Juego.*;
import Poderes.PowerUp;

public abstract class Tanque extends ObjetoConImagen {

    //Atributos
	
	protected int golpesRecibidos; 
	protected List<Disparo> disparosRealizados;
	protected char direccion;
	
	//Constructor
	
	public Tanque(char direc, Celda celda){
		super(32,celda);
		direccion = direc;
		golpesRecibidos=0;
		disparosRealizados = new LinkedList<Disparo>();
	}
	
	//Comandos
	
	public abstract boolean actuar(PowerUp poder);
	
	public Disparo disparar(){
		Disparo disparo = new Disparo(this, direccion, celda);
		disparosRealizados.add(disparo);
		return disparo;
	}
	
	public void eliminarDisparo(Disparo disparo){
		disparosRealizados.remove(disparo);
	}
	
	/**
	 * 
	 * @return Falso si el tanque fue destruido, sino retorna verdadero. 
	 */
	
	public boolean recibirGolpe(){
		golpesRecibidos++;
		if(golpesRecibidos == getResistencia())
			return false;
		else 
			return true; 
	}
	
	public void setDireccion(char dir){
		direccion=dir;
	}
	
	//Consultas
	
	public char getDireccion(){
		return direccion;
	}
	
	public abstract int getResistencia(); 
	
	public abstract int getVelocidadMovimiento();
	
	public abstract int getVelocidadDisparo();
}
