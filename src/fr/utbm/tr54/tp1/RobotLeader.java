package fr.utbm.tr54.tp1;

import lejos.utility.Delay;

/**
 * Classe modélisant le robot leader
 * @author Achille
 */
public class RobotLeader extends Robot{
	
	RobotLeader(){
		super();
	}
	
	/**
	 * Gestion du mouvement du robot leader
	 * @param time Durée de marche du robot
	 */
	public void move(int time){
		float speed ;
		int cycle = 0;
		
		speed =  getSpeedMax() * 40f /100 ;
		this.moteursRobot.moveSpeed(speed);
		while(cycle <= 100){
			this.moteursRobot.moveForward();
			Delay.msDelay(time);
			this.moteursRobot.stop();
			Delay.msDelay(time);
			cycle++;
		}		
	}
}
