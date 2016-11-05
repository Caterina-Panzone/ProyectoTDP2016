package Juego;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
			public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode()==KeyEvent.VK_SPACE)
					{
					  jugadorDispara();	
					} 
					else {
						if (arg0.getKeyCode()==KeyEvent.VK_N)
						{
						cambiarNivelJugador(); 
						}
						else{
					      mover(arg0);
						}
		}}});
		
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 715);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.GREEN);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logica = new Logica(this);
	}
	
	protected void jugadorDispara(){
		logica.jugadorDispara();
	}
	
	protected void cambiarNivelJugador(){
		logica.cambiarNivelJugador();
	}
	
	protected void mover(KeyEvent key){	
		logica.moverJugador(key.getKeyCode());
		this.repaint();
	}
	
	/*
	 * REINICIA EL JUEGO. 
	 */
	
	public void setInstrucciones(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logica = new Logica(this);
		
		this.repaint(); 
	}
	
	public void traerFrenteDisparo(JLabel imagen){
		contentPane.setComponentZOrder(imagen,2);
	}
	
	public void traerFrentePower(JLabel imagen){
		contentPane.setComponentZOrder(imagen,1); 
	}
}