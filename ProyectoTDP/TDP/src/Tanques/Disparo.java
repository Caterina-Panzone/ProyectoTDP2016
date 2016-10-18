package Tanques;

import java.util.List;

import javax.swing.ImageIcon;
import Juego.*;

public class Disparo extends ObjetoConImagen{

	//Atributos
	
	protected Tanque emisor;
	protected int direccion;
	
	//Constructor
	
	public Disparo(Tanque emisor, int dir, Celda celda){
		super(32,celda);
		this.emisor=emisor;
		direccion=dir;
		
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaA.png"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaB.png"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaD.png"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/BalaI.png"));
		
		cambiarImagenActual(direccion);
	}
	
	//Comandos 
	
	public void destruirse(List<Disparo> disparos){
		cambiarImagenActual(direccion);
		emisor.eliminarDisparo();
		emisor=null;
		celda.setBala(null);
		celda=null;
		disparos.remove(this);
		ponerImagenVacia();
	}
	
	public void setCelda(Celda celda){
		this.celda = celda;  
	}
	
	public void avanzar(Mapa mapa, List<Disparo> disparos){

		Celda nueva = mapa.getCeldaSiguiente(celda, direccion); 
				
		if(nueva!=null){
			if(nueva.getObstaculo()!=null && nueva.getObstaculo().atraviesanDisparos()){
				auxiliar(nueva,disparos); 
			}
			else{
				//Ver lo de la pared de acero.
				if(nueva.getObstaculo()!=null){ 
					nueva.getObstaculo().recibirGolpe();
					destruirse(disparos);
				}
				else{
//					auxiliar(nueva,disparos); 
					if(nueva.getTanque()!= null && getEmisor().dispareTanque(nueva.getTanque())){ 
						destruirse(disparos);
					}
					else {
						concretarMovimientoDisparo(nueva); 
					}
				}
			}
		}
		else{
			destruirse(disparos); 
		}
			
	}
	
	/*
	 * Setea el disparo en la nueva celda. 
	 */
	
	private void concretarMovimientoDisparo(Celda nueva){
		nueva.setBala(this);
		celda=nueva;
		cambiarImagenActual(direccion);
	}
	
	/*
	 * aux
	 */
	private void auxiliar(Celda nueva, List<Disparo> disparos){
		if(nueva.getBala()==null){
			if(nueva.getTanque()!= null && getEmisor().dispareTanque(nueva.getTanque())){ 
				destruirse(disparos);
			}
			else {
				concretarMovimientoDisparo(nueva); 
			}
		}
		else{ //Colisi�n de dos disparos. 
			System.out.println("Colision entre disparos");
			//nueva.getBala().destruirse(disparos); 
			destruirse(disparos); 
		}
	}
	
	//Consultas
	
	public Tanque getEmisor(){
		return emisor; 
	}
	
	public int getDireccion(){
		return direccion; 
	}
}
