package fr.utbm.tr54.tp1;


import lejos.hardware.port.MotorPort;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

/**
 * Classe de gestion des moteurs du robot
 * @author Achille
 */
public class MoveMotors {
	private  EV3LargeRegulatedMotor motorC;
	private  EV3LargeRegulatedMotor motorB;
	
	/**
	 * Constructeur par défaut, il instancie les différents objets associés aux moteurs
	 */
	public MoveMotors(){
		this.motorC = new EV3LargeRegulatedMotor(MotorPort.C);
		this.motorB = new EV3LargeRegulatedMotor(MotorPort.B);

	}
	/**
	 * Permet au moteur de faire avancer le robot
	 */
	public void moveForward(){
		/* Motor forward */
		this.motorB.forward();
		this.motorC.forward();
	}
	/**
	 * Arrêt des moteurs
	 */
	public void stop(){
		this.motorB.stop(true);
		this.motorC.stop(true);
	}
	/**
	 * Applique une vitesse de mouvement aux moteurs
	 * @param speed Vitesse à laquelle les moteurs tournent
	 */
	public void moveSpeed(float speed) {
		this.motorB.setSpeed(speed);
		this.motorC.setSpeed(speed);
	}
	/**
	 * Gère la rotation des moteurs suivant un angle
	 * @param angle Angle de rotation des moteurs
	 */
	public void rotation(int angle){
		this.motorB.rotate(angle, true);
		this.motorC.rotate(-1 * angle, true);
	}
	
	/**
	 * @return La vitesse max du moteur
	 */
	public float getMaxSpeedMotor(){
		return this.motorB.getMaxSpeed();
	}
	
	/**
	 * @return La vitesse à laquelle le moteur tourne
	 */
	public float getMoveSpeed(){
		return this.motorB.getSpeed();
	}
	
	/**
	 * Arrête tous les moteurs actifs sur le robot
	 */
	public  void close(){
		this.motorB.close();
		this.motorC.close();
	}
	
}
