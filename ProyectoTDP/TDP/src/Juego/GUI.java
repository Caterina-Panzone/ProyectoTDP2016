package Juego;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame{
	//Atributos 
	
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected Logica logica;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public GUI() {
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_G){
					generarEnemigo();
				}
				else{
					if(arg0.getKeyCode()==KeyEvent.VK_P){
					  romperPared();	
					} if (arg0.getKeyCode()==KeyEvent.VK_N){
						cambiarNivelJugador(); 
					}else{
					      mover(arg0);
						}
		}}});
		
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logica = new Logica(this);
	}
	
	protected void romperPared(){
		logica.romperPared();
		this.repaint();
	}
	
	protected void generarEnemigo(){
		logica.generarEnemigo();
		this.repaint();
	}
	
	protected void cambiarNivelJugador(){
		logica.cambiarNivelJugador();
	}
	
	protected void mover(KeyEvent key){
		
		//si key es F entonces llamo a disparar
		// sino :
		
		logica.moverJugador(key.getKeyCode());
		this.repaint();
	}
	

}