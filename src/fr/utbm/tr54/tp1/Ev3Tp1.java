package fr.utbm.tr54.tp1;

/**
 * @author Achille
 */
public class Ev3Tp1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int timeCycle;
		int a;
		int D;
		
		timeCycle = 500;
		a = 2;
		D = 15;
				
		/* Main pour le robot leader
		RobotLeader leader = new RobotLeader();
		leader.move(timeCycle);
		leader.shutdown();
		//*/
		
		/* Main pour le robot suiveur. politique 1
		RobotSuiveur suiveur = new RobotSuiveur();
		suiveur.move();
		suiveur.shutdown();
		//*/
		
		//* Main pour le robot suiveur. politique 2
		RobotSuiveur suiveur = new RobotSuiveur();
		suiveur.move(a,D, timeCycle,2);
		suiveur.shutdown();
		//*/
		
		/* Main pour le robot suiveur. politique 3
		RobotSuiveur suiveur = new RobotSuiveur();
		suiveur.move(a,D, timeCycle,3);
		suiveur.shutdown();
		//*/
	}
	
}
