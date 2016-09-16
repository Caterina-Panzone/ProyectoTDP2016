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
	
	public ObjetoConImagen(int tama�o, Celda celda){
		this.celda = celda; 
		this.tama�o=tama�o;
		imagenes= new Icon[4];
		imagenActual= new JLabel();
		imagenActual.setBounds(celda.getColumna(),celda.getFila(),tama�o,tama�o);
	}
	
	//Comandos
	
	public void cambiarImagenActual(int �ndice){
		imagenActual.setIcon(imagenes[�ndice]);
		imagenActual.setBounds(celda.getColumna()*32,celda.getFila()*32,tama�o, tama�o);
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
