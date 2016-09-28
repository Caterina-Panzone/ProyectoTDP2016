package Tanques;

import Juego.*;
import Niveles.*;
import Poderes.PowerUp;
import javax.swing.ImageIcon;

public class Jugador extends Tanque {
	//Atributos 
	
	protected int puntos;
	protected int vidas;
	protected Nivel nivel;
	protected int enemigosDestruidos;
	protected boolean invulnerabilidad;

	
	//Constructor
	
	public Jugador(Celda celda){
		super('A',celda);
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorArriba.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorAbajo.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorDerecha.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/JugadorIzquierda.png"));
		cambiarImagenActual(0);
		
		vidas = 4;
		nivel = new Nivel1();
		puntos = 0;
		enemigosDestruidos = 0;
		invulnerabilidad = false;
	}
	
	//Comando
	
	public boolean actuar(PowerUp poder){
		if(poder!=null){
			poder.actuar(this);	
			return true;
		}
		return false; 
	}
	
	public void reiniciarDestruidos(){
		enemigosDestruidos = 0;
	}
	
	public void setInvulnerabildiad(boolean estado){
		invulnerabilidad = estado;
	}
	
	public void aumentarPuntos(int puntos){
		this.puntos += puntos;
	}
	
	public void aumentarNivel(){
		nivel = nivel.siguienteNivel();
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
		nivel = new Nivel1(); 
		return true; 
	}
	
	//Consultas
	
	public int getVidas(){
		return vidas;
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
	
	public int getVelocidadMovimiento(){
		return nivel.getVelocidadMovimiento();
	}
	
	public int getVelocidadDisparo(){
		return nivel.getVelocidadDisparo(); 
	}
	
	public int getResistencia(){
		return nivel.getResistencia();
	}
	
	public int getDisparosSimultaneos(){
		return nivel.getDisparosSimultaneos(); 
	}
}