package fr.utbm.tr54.tp1;


/**
 * Classe qui modelise le robot suiveur, elle etend la classe Robot
 * @author Achille
 */
public class RobotSuiveur extends Robot{
	RobotSuiveur(){
		super();
	}
	/**
	 * Gestion du mouvement du robot suiveur
	 * Politique du tout ou rien
	 */
	@SuppressWarnings("boxing")
	public void move(){
		float speed;
		float dist;
		String file =  new String ("robotSuiveur.csv");  //$NON-NLS-1$
		
		speed = (float) ( 0.5*this.getSpeedMax() );
		
		lcd_print(speed,0,0);

		sauvegardeMesure(speed, file );
		this.moteursRobot.moveSpeed(0);
		dist = this.capteurRobot.distance();
		
		/*Politique tout ou rien */
		while(true){
			dist = this.capteurRobot.distance();
			sauvegardeMesure(dist, file);
			while(dist>0.15){
				this.moteursRobot.moveForward();
				dist = this.capteurRobot.distance();
			}
			this.moteursRobot.stop();
		}
		
	}
	
	/**
	 * Calcul de la vitesse à 1 point
	 * @param a paramètre de calul du pourcentage à appliquer sur la vitesse max du véhicule
	 * @param D Distance référence entre les 2 robots
	 * @param timeCycle temps de cycle
	 * @return vitesse 
	 */
	@SuppressWarnings("boxing")
	public float calculVitesse (int a, float D, int timeCycle){
		float r;
		float percent;
		String file  =  new String ("robotSuiveurVitesse_"+timeCycle +"_"+a+"_"+" "+D+".csv");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		String file2 =  new String ("robotSuiveurdistance_"+timeCycle +"_"+a+"_"+" "+D+".csv");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		//String file2 =  new String ("robotSuiveurdistance.csv"); //$NON-NLS-1$00
		
		float dist = this.capteurRobot.distance();
		sauvegardeMesure(dist, file);
		
		percent = Math.max(Math.min(50,  a * (dist - D )), 0);
		r= percent * getSpeedMax();
		
		sauvegardeMesure(r, file2);
		return r;
	}
	
	/**
	 * Gestion du mouvement du robot suiveur
	 * @param a paramètre de calul du pourcentage à appliquer sur la vitesse max du véhicule
	 * @param D Distance référence entre les 2 robots
	 * @param timeCycle temps de cycle
	 *
	public void move(int a, float D, int timeCycle){
		float speed;
		while(true){
			speed = this.calculVitesse(a, D, timeCycle);
			this.moteursRobot.moveSpeed(speed);
			this.moteursRobot.moveForward();
		}
	}*/
	
	/**
	 * Calcul de la vitesse à 2 points
	 * @param a Paramètre de calul du pourcentage à appliquer sur la vitesse max du véhicule
	 * @param D Distance référence entre les 2 robots
	 * @param timeCycle Temps de cycle
	 * @return vitesse 
	 */
	@SuppressWarnings("boxing")
	public float calculVitesse2 (int a, float D, int timeCycle){
		float r;
		float percent;
		float previousSpeed;
		
		String file  =  new String ("robotSuiveurVitesse_"+timeCycle +"_"+a+"_"+" "+D+"_pol2.csv");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		String file2 =  new String ("robotSuiveurdistance_"+timeCycle +"_"+a+"_"+" "+D+"_pol2.csv");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		
		float dist = this.capteurRobot.distance();
		sauvegardeMesure(dist, file);
		
		previousSpeed = this.moteursRobot.getMoveSpeed();		
		percent = (float) Math.min(  Math.max( 2.5 * (dist-20) , Math.min(Math.max(a*(dist-D) , 0), previousSpeed ) ), 50 );
		r= percent * getSpeedMax();
		sauvegardeMesure(r, file2);
		
		return r;
	}
	
	/**
	 * Gestion du mouvement du robot suiveur
	 * @param a paramètre de calul du pourcentage à appliquer sur la vitesse max du véhicule
	 * @param D Distance référence entre les 2 robots
	 * @param timeCycle temps de cycle
	 * @param politique Politique de suivi
	 */
	public void move(int a, float D, int timeCycle, int politique){
		float speed;
		speed = 0;
		while(true){
			if( politique == 2 ){
				speed = this.calculVitesse(a, D, timeCycle);
			}else if (politique == 3){
				speed = this.calculVitesse2(a, D,timeCycle);
			}
			this.moteursRobot.moveSpeed(speed);
			this.moteursRobot.moveForward();
		}
	}
}
