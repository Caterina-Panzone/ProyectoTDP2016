package Tanques;

import Juego.*;
import Niveles.*;
import Obstaculos.Acero;
import Poderes.PowerUp;
import javax.swing.ImageIcon;

public class Jugador extends Tanque implements Runnable{
	//Atributos 
	
	protected int puntos;
	protected int vidas;
	protected Nivel nivel;
	protected int enemigosDestruidos;
	protected boolean invulnerabilidad;
	protected Logica logica;
	protected int jugador = 3; 
	protected Thread t; 
	protected Celda nueva; 

	
	//Constructor
	
	public Jugador(Celda celda,Logica logica){
		super(celda);
		celda.bloquear(); 
		setDireccion(0);
		vidas = 4;
		nivel = new Nivel1();
		puntos = 0;
		enemigosDestruidos = 0;
		invulnerabilidad = false;
		this.logica=logica; 
		cambiarImagenActual(0);
		lock=0;
	}
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica();
		
		if(tematica == "EdEdd&Eddy"){
			switch(jugador){
				case 0:{
					jugador = 1;
					break; 
				}
				case 1: {
					jugador = 2;
					break;
				}
				case 2: {
					jugador = 3; 
					break;
				}
				case 3: {
					jugador = 1; 
					break;
				}
			}
		}
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Arriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Abajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Derecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"Izquierda.gif"));
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
		String tematica = Tematica.getTematica(); 
		invulnerabilidad = estado;
		if(estado==true){
			int jugador = 1; 
			if(tematica == "EdEdd&Eddy"){
				jugador=((vidas%3)+1);
			}
			imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadArriba.gif"));
			imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadAbajo.gif"));
			imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadDerecha.gif"));
			imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/Jugador"+jugador+"InvulnerabilidadIzquierda.gif"));
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
		logica.resetearVida(vidas);
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
	
	public boolean bloqueado(){
		return lock!=0; 
	}

	public void moverse(Celda nueva) {
		this.nueva = nueva;
		nueva.bloquear(); 
		lock = tama�o/(getVelocidadMovimiento()*aumento); 
		t = new Thread(this); 
		t.start(); 
	}
	
	public void run() {	
		Celda vieja = celda; 
		
		try {
			for (int i=lock; i>0; i--) {
				if(i==1){
					vieja.desbloquear(); 
					x = celda.getColumna()*tama�o;
					y = celda.getFila()*tama�o; 
					cambiarImagenActual(direccion);  
				} else {
					if(i==(tama�o/(getVelocidadMovimiento()*aumento))/2){ 
						logica.getMapa().concretarMovimientoTanque(celda, nueva);
						vieja.bloquear();
						celda.desbloquear(); 
					}
				}
				moverseGraficamente(); 
				Thread.sleep(30); 
			}
			lock=0; 
		} catch (InterruptedException e) {}
	}
}