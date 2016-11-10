package TDisparo;

import java.util.List;

import javax.swing.ImageIcon;
import Juego.*;
import Tanques.ObjetoDesplazable;
import Tanques.Tanque;

public class Disparo extends ObjetoDesplazable{

	//Atributos
	
	protected Tanque emisor;
	protected int direccion;
	protected Mapa mapa; 
	protected List<Disparo> disparos; 
	
	
	//Constructor
	
	public Disparo(Tanque emisor, int dir, Celda celda, Logica logica){
		super(celda,2);
		celda.setBala(this); 
		this.mapa = logica.getMapa();
		disparos = logica.getListaDisparos();		
		this.emisor=emisor;
		direccion=dir;
		cambiarImagenActual(direccion);
	}
	
	//Comandos 
	
	public void setearImagenes(){
		String tematica = Tematica.getTematica(); 
		imagenes[0]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaArriba.gif"));
		imagenes[1]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaAbajo.gif"));
		imagenes[2]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaDerecha.gif"));
		imagenes[3]= new ImageIcon(this.getClass().getResource("/Imagenes/"+tematica+"/BalaIzquierda.gif"));
	}
	
	public void destruirse(List<Disparo> disparos){
		if(celda!=null){
			cambiarImagenActual(direccion);
			celda.setBala(null);
		}
		ponerImagenVacia();
		
		emisor.eliminarDisparo(); 
		celda=null;		
		emisor=null;
		lock = 0; 
		disparos.remove(this);
	}
	
	private void avanzar(){
		
		Celda nueva = mapa.getCeldaSiguiente(celda, direccion); 
		
		if(nueva!=null){
			Tanque tanqueColision = nueva.getTanque(); 
			if(nueva.getObstaculo()!=null && !nueva.getObstaculo().atraviesanDisparos()){
				//Es ladrillo o acero. 
				nueva.getObstaculo().recibirGolpe(emisor);
				destruirse(disparos);
			}else{
				if(nueva.getObstaculo()==null || nueva.getObstaculo()!=null && nueva.getObstaculo().atraviesanTanques()){
					//Es bosque o piso. 
					if(nueva.getBala()!=null || nueva.getTanque()!=null){
						//Bala y tanque. 
						if(nueva.getBala()!=null && nueva.getTanque()!=null){
							//Colisiona con el tanque y las balas.  
							getEmisor().dispareTanque(tanqueColision);
							nueva.getBala().destruirse(disparos);
							destruirse(disparos);
						}
						else { 
							//Bala o tanque
							if(nueva.getBala()!=null){
								nueva.getBala().destruirse(disparos); 
								destruirse(disparos);
							}
							if(tanqueColision!=null){
								if(getEmisor()==null) {
									//Si destruyeron al tanque antes de el disparo colisione o se vaya fuera de limite. 
									concretarMovimientoDisparo(nueva); 
								}
								else {
									boolean seRompio = getEmisor().dispareTanque(tanqueColision);
									if(seRompio)
										destruirse(disparos); 
									else 
										concretarMovimientoDisparo(nueva); 
								}
							}
						}
					}
					else{
						concretarMovimientoDisparo(nueva); 
					}	
				}
				else{
					//Hay agua
					if(nueva.getBala()!=null){
						nueva.getBala().destruirse(disparos); 
						destruirse(disparos);
					}
					else{
						concretarMovimientoDisparo(nueva);
					}
				}
			}
		}
		else { //Se fue del mapa. 
			destruirse(disparos);
		}
	}
	
	public void moverse(){	
		//Si el disparo dej� de moverse.
		
		if(lock<=0){
			lock = tama�o/(getVelocidadMovimiento()*aumento);  	
		}
		//Posiciona el disparo en la fila y columna final del movimiento. 
		if(lock==1) {
			if((lock == (tama�o/(getVelocidadMovimiento()*aumento))/2)){
				avanzar(); 
			}
			if(lock!=0){
				x = celda.getColumna()*tama�o;
				y = celda.getFila()*tama�o; 
				cambiarImagenActual(direccion);  
			}
		}
		else {
			if(lock == (tama�o/(getVelocidadMovimiento()*aumento))/2){ 
				avanzar();
			}
			//Genera el movimiento continuo del tanque con posiciones, dependiendo de la velocidad de cada tanque. 
			if(lock!=0){
				moverseGraficamente();
			}
		}
		lock--;
	}
	
	/*
	 * Setea el disparo en la nueva celda. 
	 */
	
	private void concretarMovimientoDisparo(Celda nueva){
		celda.setBala(null); 
		celda=nueva;
		celda.setBala(this);
		cambiarImagenActual(direccion);
	}
	
	//Consultas
	
	public Tanque getEmisor(){
		return emisor; 
	}
	
	public int getDireccion(){
		return direccion; 
	}
	
	public int getVelocidadMovimiento(){
		return emisor.getVelocidadDisparo();
	}
}
