package Tanques;

import java.util.List;

import javax.swing.ImageIcon;
import Juego.*;

public class Disparo extends ObjetoConImagen{

	//Atributos
	
	protected Tanque emisor;
	protected int direccion;
	protected Mapa mapa; 
	
	
	//Constructor
	
	public Disparo(Tanque emisor, int dir, Celda celda, Mapa mapa){
		super(32,celda);
		
		celda.setBala(this); 
		this.mapa = mapa; 
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
		ponerImagenVacia();
		celda.setBala(null);
		emisor.eliminarDisparo(); 
		celda=null;		
		emisor=null;
		disparos.remove(this);
	}
	
	public void avanzar(List<Disparo> disparos){

		Celda nueva = mapa.getCeldaSiguiente(celda, direccion); 
		
		if(nueva!=null){
			Tanque tanqueColision = nueva.getTanque(); 
			if(nueva.getObstaculo()!=null && !nueva.getObstaculo().atraviesanDisparos()){
				//Es ladrillo o acero. 
				nueva.getObstaculo().recibirGolpe();
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
}
