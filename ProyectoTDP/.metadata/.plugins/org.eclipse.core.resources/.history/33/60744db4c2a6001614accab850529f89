package Juego;

import Tanques.*;
import Obstaculos.*;
import Poderes.*; 

public class Celda {
	protected int fila;
	protected int columna; 
	protected boolean bloqueada; 
	protected Obstaculo obstaculo;
	protected Disparo bala; 
	protected PowerUp powerUp; 
	protected Tanque tanque; 

	/**
	 * Constructor de la clase.
	 * Crea una celda en una posición indicada.
	 * @param fila Posicion horizontal de la celda.
	 * @param columna Posicion vertical de la celda.
	 */

	public Celda (int fila, int columna){
		this.fila = fila;
		this.columna = columna;
		bloqueada = false; 
	}

	/**
	 * Establece el tanque en la celda, reemplazando el tanque que se encontraba anteriormente. 
	 * @param tanque Referencia al objeto a settear de tipo Tanque.
	 */

	public void setTanque(Tanque tanque){
		this.tanque=tanque;
	}

	/**
	 * Establece el disparo en la celda, reemplazando el disparo que se encontraba anteriormente. 
	 * @param disparo Referencia al objeto a settear de tipo Disparo. 
	 */

	public void setBala(Disparo disparo){
		bala = disparo;
	}

	/**
	 * Establece el obstaculo en la celda, reemplazando el obstaculo que se encontraba anteriormente. 
	 * @param obstaculo Referencia al objeto a settear de tipo Obstaculo. 
	 */

	public void setObstaculo(Obstaculo obstaculo){
		this.obstaculo = obstaculo; 
	}

	/**
	 * Establece el power up en la celda, reemplazando el power up que se encontraba anteriormente. 
	 * @param power referencia al objeto a settear de tipo PowerUp. 
	 */

	public void setPower(PowerUp power){
		powerUp = power; 
	}

	/**
	 * Bloquea la celda. 
	 */

	public void bloquear(){
		bloqueada = true; 
	}

	/**
	 * Desbloquea la celda. 
	 */

	public void desbloquear(){
		bloqueada = false; 
	}

	/**
	 * Consulta la posicion horizontal de la celda. 
	 * @return posicion horizontal de la celda. 
	 */

	public int getColumna(){
		return columna;
	}

	/**
	 * Consulta la posicion vertical de la celda. 
	 * @return posicion vertical de la celda. 
	 */

	public int getFila(){
		return fila; 
	}

	/**
	 * Consulta si un tanque externo puede ubicarse en la celda. 
	 * @return Verdadero si no hay objetos que impidan su asentamiento, falso en caso contrario. 
	 */

	public boolean permitidoAvanzarTanque(){
		return ((obstaculo==null && !bloqueada) || (obstaculo!=null && obstaculo.atraviesanTanques() && tanque==null && !bloqueada));
	}

	/**
	 * Devuelve el obstaculo que se encuentra en la celda. 
	 * @return obstaculo posicionado en la celda. 
	 */

	public Obstaculo getObstaculo(){
		return obstaculo;
	}

	/**
	 * Devuelve el tanque que se encuentra en la celda. 
	 * @return tanque posicionado en la celda. 
	 */

	public Tanque getTanque(){
		return tanque;
	}

	/**
	 * Devuelve la bala que se encuentra en la celda. 
	 * @return disparo posicionado en la celda. 
	 */

	public Disparo getBala(){
		return bala;
	}

	/**
	 * Devuelve el power up que se encuentra en la celda. 
	 * @return power up posicionado en la celda. 
	 */

	public PowerUp getPower(){
		return powerUp; 
	}
}