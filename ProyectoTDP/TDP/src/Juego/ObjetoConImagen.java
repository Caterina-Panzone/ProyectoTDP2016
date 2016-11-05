package Juego;

import javax.swing.Icon;
import javax.swing.JLabel;

public abstract class ObjetoConImagen{

	//Atributos
	
	protected Celda celda; 
	protected JLabel imagenActual;
	protected Icon[] imagenes;
	protected int tama�o; 
	
	//Constructor
	
	public ObjetoConImagen(Celda celda){
		this.celda = celda; 
		this.tama�o=48;
		imagenes= new Icon[4];
		imagenActual= new JLabel();
		imagenActual.setBounds(celda.getColumna(),celda.getFila(),tama�o,tama�o);
	}
	
	//Comandos
	
	protected void ponerImagenVacia(){
		imagenActual.setIcon(null);
	}
	
	public void cambiarImagenActual(int indice){
		imagenActual.setIcon(imagenes[indice]);
		imagenActual.setBounds(celda.getColumna()*tama�o,celda.getFila()*tama�o,tama�o, tama�o);
		imagenActual.setOpaque(false);
	}
	
	public void setCelda(Celda celda){
		this.celda = celda;
	}
	
	//Consultas
	
	public JLabel getImagenActual(){
		return imagenActual;
	}
	
	public int getFila(){
		return celda.getFila(); 
	}
	
	public int getColumna(){
		return celda.getColumna(); 
	}
}
