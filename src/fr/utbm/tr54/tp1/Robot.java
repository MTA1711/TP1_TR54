package fr.utbm.tr54.tp1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;

/**
 * Classe qui modelise un robot
 * @author Achille
 */
public class Robot {
	
	protected MoveMotors moteursRobot;
	protected Capteur capteurRobot;
	
	/**
	 * Constructeur par défaut qui initialise les composants du robot
	 */
	public Robot() {
		// TODO Auto-generated constructor stub
		this.moteursRobot = new MoveMotors();
		this.capteurRobot = new Capteur();
	}
	/**
	 * 
	 * @return La vitesse maximale du robot
	 */
	float getSpeedMax(){
		float speedMax;
		speedMax = this.moteursRobot.getMaxSpeedMotor();
		return speedMax;
	}
	
	/* Move until the white mark*/
	/**
	 * Fonctionnement des moteurs jusqu'atteindre un marqueur colorer
	 */
	
	public void moveToColor( ){
		boolean marche = true;
		int couleur;
		this.moteursRobot.moveSpeed(150);
		while (marche){
			couleur = this.capteurRobot.getCouleurSurface();
			if (couleur != Color.WHITE){
				this.moteursRobot.moveForward();
			}else{
				marche = false;
				this.moteursRobot.stop();
			}
		}			
	}
	
	/**
	 * Permet la rotation du robot sur lui-même
	 */
	public void rotationRobot(){
		this.moteursRobot.moveSpeed(150);
		this.moteursRobot.rotation(180);
	}
	
	/**
	 * Arrête tous les composants du robot
	 */
	public void shutdown(){
		this.moteursRobot.close();
		this.capteurRobot.close();
	}
	
	/**
	 * Affiche de la distance entre le capteur de position et un obstacle sur le LCD
	 * @param dist distance entre le capteur de position et un obstacle
	 * @param x ligne sur l'écran
	 * @param y colonne sur l'écran
	 */
	public static void lcd_print(Float dist,int x,int y){
		LCD.drawString("distance est : "+dist, x, y); //$NON-NLS-1$
	}

	/**
	 * sauvegarde de la distance dans un fichier CSV
	 * @param dist distance entre le capteur de position et un obstacle
	 * @param file le fichier dans lequel sera sauvegardé la mesure
	 */
	public static  void saveInCVSFile(Float dist, String file){
		try {
			@SuppressWarnings("resource")
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
			@SuppressWarnings("resource")
			PrintWriter pw = new PrintWriter(out);
			pw.write(dist.toString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sauvegarde d'une mesure dans un fichier
	 * @param dist la mesure à sauvegarder
	 * @param file le fichier dans lequel sera sauvegardé la mesure
	 */
	public static void sauvegardeMesure(Float dist, String file){
		File f = new File (file);
		try
		{
		    @SuppressWarnings("resource")
			PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f, true) ));
		    pw.println (dist);
		    pw.close();
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage()); //$NON-NLS-1$
		}
	}
}
