package fr.utbm.tr54.tp1;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

/**
 * Classe de gestion des capteurs du robot
 * @author Achille
 */
public class Capteur {
	private EV3UltrasonicSensor capteurDistance;
	private EV3ColorSensor capteurCouleur;

	/**
	 * Constructeur par défaut, il instancie les différents objets associés aux capteurs
	 */
	public Capteur() {
		this.capteurDistance = new EV3UltrasonicSensor(SensorPort.S3);
		this.capteurCouleur  = new EV3ColorSensor(SensorPort.S2);
	}

	/**
	 * @return la couleur de la surface au-dessous du capteur de couleur
	 */
	public int getCouleurSurface() {
		int a;
		a = this.capteurCouleur.getColorID();
		return a;
	}

	/**
	 * Calcul la distance entre le capteur de position et un obstacle 
	 * @return la distance moyenne entre le capteur de position et un obstacle
	 */
	public float distance() {
		float[] dist = new float[1];
		this.capteurDistance.getDistanceMode().fetchSample(dist, 0);
		return dist[0];
	}

	/**
	 * @param n le nombre d'échantillon utilisé pour calculer la distance
	 * @return la distance entre le capteur de position et un obstacle
	 */
	public float distance(int n) {
		float[] dist = new float[n];
		float distMoy = 0;
		for (int i = 0; i < n; i++) {
			this.capteurDistance.getDistanceMode().fetchSample(dist, i);
		}
		// calcul de la moyenne
		for (int i = 0; i < n; i++) {
			distMoy = distMoy + dist[i] / n;
		}
		return distMoy;
	}

	/**
	 * Ferme tous les capteurs activés sur le robot
	 */
	public void close() {
		this.capteurDistance.close();
		this.capteurCouleur.close();
	}

}
