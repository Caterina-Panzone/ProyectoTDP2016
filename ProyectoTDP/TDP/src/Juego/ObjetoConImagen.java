package Juego;

import javax.swing.Icon;
import javax.swing.JLabel;

public abstract class ObjetoConImagen{

	//Atributos
	
	protected Celda celda; 
	protected JLabel imagenActual;
	protected Icon[] imagenes;
	protected int tamaño; 
	
	//Constructor
	
	public ObjetoConImagen(int tamaño, Celda celda){
		this.celda = celda; 
		this.tamaño=tamaño;
		imagenes= new Icon[4];
		imagenActual= new JLabel();
		imagenActual.setBounds(celda.getColumna(),celda.getFila(),tamaño,tamaño);
	}
	
	//Comandos
	
	public void cambiarImagenActual(int índice){
		imagenActual.setIcon(imagenes[índice]);
		imagenActual.setBounds(celda.getColumna()*32,celda.getFila()*32,tamaño, tamaño);
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
