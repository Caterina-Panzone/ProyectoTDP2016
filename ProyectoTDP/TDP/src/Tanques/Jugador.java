package Tanques;

import javax.swing.Icon;
import Juego.*;
import javax.swing.ImageIcon;

public class Jugador extends Tanque {
	//Atributos 
	
	protected int puntos;
	protected int vidas;
	protected int nivel;
	protected int enemigosDestruidos;
	protected boolean invulnerabilidad;
	protected int disparosSimultaneos;
	
	//Constructor
	
	public Jugador(Celda celda){
		super(2,1,1,'A',celda);
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorIzquierda.png"));
		cambiarImagenActual(0);
		disparosSimultaneos=1;
		vidas = 4;
		nivel = 1;
		puntos = 0;
		enemigosDestruidos = 0;
		invulnerabilidad = false;
	}
	
	//Comando
	
	public void reiniciarDestruidos(){
		enemigosDestruidos = 0;
	}
	
	public void setInvulnerabildiad(boolean estado){
		invulnerabilidad = estado;
	}
	
	public void aumentarPuntos(int puntos){
		this.puntos = puntos;
	}
	
	public void aumentarNivel(){
		switch(nivel){
			case 1:{
				nivel++;
				velocidadMovimiento=3; 
				velocidadDisparo=2;
				break;
			}
			case 2:{
				nivel++;
				resistencia = 2;
				velocidadMovimiento = 2;
				disparosSimultaneos = 2; 
				break; 
			}
			case 3: {
				nivel++;
				resistencia = 4;
				disparosSimultaneos = 3;
				velocidadDisparo = 3; 
				break; 
			}
		}
	}
	
	public void aumentarVida(){
		vidas++;
	}
	
	/**
	 * 
	 * @return Retorna falso si no tiene mas vidas, sino retorna verdadero. 
	 */
	
	public boolean revivir(){
		vidas--; 
		if(vidas<=0)
			return false;
		nivel = 1;
		resistencia = 1;
		velocidadMovimiento = 2; 
		velocidadDisparo = 1;
		disparosSimultaneos = 1;
		return true; 
	}
	
	//Consultas
	
	public int getVidas(){
		return vidas;
	}
	
	public int getNivel(){
		return nivel;
	}
	
	public int getPuntos(){
		return puntos;
	}
	
	public boolean getInvulnerabilidad(){
		return invulnerabilidad;
	}
	
	public int getEnemigosDestruidos(){
		return enemigosDestruidos; 
	}
}