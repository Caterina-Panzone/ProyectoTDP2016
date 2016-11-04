package Tanques;

import java.util.Random;

import Juego.*;
import Poderes.PowerUp;


public abstract class Tanque extends ObjetoDesplazable {

    //Atributos
	
	protected int golpesRecibidos; 
	protected int disparosRealizados;
	protected int direccion;
	
	//Constructor
	
	public Tanque(Celda celda){
		super(celda,1);
		Random rnd = new Random();
		direccion = rnd.nextInt(4);
		golpesRecibidos=0;
		disparosRealizados = 0;
	}
	
	//Comandos
	
	public abstract void actuar(PowerUp poder);
	
	public Disparo disparar(Logica logica){
		Disparo disparo = null; 
		if(disparosRealizados<cantMaximaDisparos()){
			disparosRealizados++;
			disparo = new Disparo(this, direccion, celda, logica);
		}
		return disparo; 
	}
	
	public void eliminarDisparo(){
		if(disparosRealizados>0){
			disparosRealizados--;
		}
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
