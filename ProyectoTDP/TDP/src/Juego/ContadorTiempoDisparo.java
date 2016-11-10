package Juego;

public class ContadorTiempoDisparo extends Thread{
	private GUI gui;
	
	public ContadorTiempoDisparo(GUI gui){
		this.gui = gui;
		
	}
	
	public void run(){
		try {
			Thread.sleep(500);
			gui.habilitarDisparo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
