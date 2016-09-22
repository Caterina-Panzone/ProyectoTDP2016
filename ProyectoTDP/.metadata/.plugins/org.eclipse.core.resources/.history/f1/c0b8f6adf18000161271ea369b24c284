package Tanques;

import java.util.LinkedList;
import java.util.List;
import Juego.*;

public abstract class Tanque extends ObjetoConImagen {

    //Atributos
	
	protected int velocidadMovimiento;
	protected int velocidadDisparo;
	protected int resistencia;
	protected int golpesRecibidos; 
	protected List<Disparo> disparosRealizados;
	protected char direccion;
	
	//Constructor
	
	public Tanque(int vm, int vd, int resis, char direc, Celda celda){
		super(32,celda);
		vm = velocidadMovimiento;
		vd = velocidadDisparo;
		resistencia = resis;
		direccion = direc;
		golpesRecibidos=0;
		disparosRealizados = new LinkedList<Disparo>();
	}
	
	//Comandos
	
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
		if(golpesRecibidos == resistencia)
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
	
	public int getVelocidadMovimiento(){
		return velocidadMovimiento;
	}
	
	public int getVelocidadDisparo(){
		return velocidadDisparo;
	}
}
