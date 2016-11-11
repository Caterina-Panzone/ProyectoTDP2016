package Tanques;

import Juego.*;
import Poderes.PowerUp;
import TDisparo.Disparo;
import Obstaculos.Acero;


public abstract class Tanque extends ObjetoDesplazable {

    //Atributos
	
	protected int golpesRecibidos; 
	protected int disparosRealizados;
	protected int direccion;
	
	//Constructor
	
	public Tanque(Celda celda){
		super(celda,1);
		golpesRecibidos=0;
		disparosRealizados = 0;
	}
	
	//Comandos
	
	public abstract void actuar(PowerUp poder);
	
	public abstract Disparo disparar(Logica logica); 
	
	public void eliminarDisparo(){
		if(disparosRealizados>0){
			disparosRealizados--;
		}
	}
	
	//hay que cambiar el boolean a void. 
	
	public abstract boolean recibirGolpe(Jugador tanque);
	
	public abstract boolean recibirGolpe(Enemigo tanque);
	
	public abstract boolean dispareTanque(Tanque tanque);
	
	public void setDireccion(int dir){
		direccion=dir;
	}
	
	//Unicamente el jugador con nivel 4 rompe el acero. 
	public void romperAcero(Acero acero){ }
	
	public void destroy(){}
	
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
