package Tanques;

import Juego.*;
import Niveles.*;
import Obstaculos.Acero;
import Poderes.PowerUp;
import javax.swing.ImageIcon;

public class Jugador extends Tanque {
	//Atributos 
	
	protected int puntos;
	protected int vidas;
	protected Nivel nivel;
	protected int enemigosDestruidos;
	protected boolean invulnerabilidad;
	protected Logica logica;

	
	//Constructor
	
	public Jugador(Celda celda,Logica logica){
		super(celda);
		celda.bloquear(); 
		setDireccion(0);
		
		vidas = 4;
		
		setearImagenes();
		
		cambiarImagenActual(0);
	
		nivel = new Nivel1();
		puntos = 0;
		enemigosDestruidos = 0;
		invulnerabilidad = false;
		
		this.logica=logica;

	}
	
	private void setearImagenes(){
		int jugador=((vidas%3)+1);
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"Arriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"Abajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"Derecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"Izquierda.gif"));
	}
	
	//Comando
	
	public void actuar(PowerUp poder){
		if(poder!=null)
			poder.actuar(this);	
	}
	
	public void reiniciarDestruidos(){
		enemigosDestruidos = 0;
	}
	
	public void setInvulnerabilidad(boolean estado){
		invulnerabilidad = estado;
		if(estado==true){
			int jugador= ((vidas%3)+1);
			imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"InvulnerabilidadArriba.gif"));
			imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"InvulnerabilidadAbajo.gif"));
			imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"InvulnerabilidadDerecha.gif"));
			imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/Jugador"+jugador+"InvulnerabilidadIzquierda.gif"));
			cambiarImagenActual(direccion);
		}
		else{
			setearImagenes();
			cambiarImagenActual(direccion);
		}
	}
	
	protected void aumentarPuntos(int puntos){
		this.puntos += puntos;
	}
	
	public void aumentarNivel(){
		nivel = nivel.siguienteNivel();
	}
	
	public void aumentarVida(){
		vidas++;
	}
	
	public boolean recibirGolpe(Enemigo tanque){
		if(!invulnerabilidad){
			golpesRecibidos++;
		}
		if(nivel.getResistencia()<=golpesRecibidos){
			celda.setTanque(null);
			celda.desbloquear(); 
			celda = null; 
			ponerImagenVacia();
			vidas--;
			logica.resetearVida(vidas);
			if(vidas<=0){
				logica.finalizarJuego();
			}
			else{
				nivel=new Nivel1();
				setearImagenes();
				logica.respawnearJugador();
			}
		}
		return true; 
	}
	
	public boolean recibirGolpe(Jugador tanque){
		return false; 
	}
	
	public boolean dispareTanque(Tanque tanque){
		return tanque.recibirGolpe(this);
	}
	
	
	public void romperAcero(Acero acero){
		nivel.romperAcero(acero); 
	}
	
	public void aumentarEnemigosDestruidos(int puntos){
		enemigosDestruidos++;
		aumentarPuntos(puntos); 
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
	
	public int cantMaximaDisparos(){
		return nivel.getDisparosSimultaneos(); 
	}

	@Override
	public void moverse() {
		// IMPLEMENTAR
		
	}
}