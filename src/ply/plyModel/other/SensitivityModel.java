package ply.plyModel.other;

/**
 * Cette classe regoupe les sensitivtés pour les tranformations du modèle
 * @author L3
 *
 */
public class SensitivityModel {

	/**
	 * Valeur globale par lequel est mutliplée la valeur de translation si SHIFT est appuyé. La translation est donc moins importante. Valeur conseillée 0.3
	 */
	private double reduceTransSens = 0.3;

	/**
	 * Valeur globale par lequel est mutliplée la valeur de rotation si SHIFT est appuyé. La rotation est donc moins importante. Valeur conseillée 0.3
	 */
	private double reduceRotationSens = 0.3;

	/**
	 * Valeur globale par lequel est mutliplée la valeur de zoom si SHIFT est appuyé. Le zoom est donc moins importante. Valeur conseillée 0.3
	 */
	private double reduceZoomSens = 0.3;

	/**
	 * Valeur globale pour la sensitivité de rotation en degrés. Valeur conseillée 5
	 */
	private double rotationSens = 2.0;

	/**
	 * Valeur globale pour la sensitivté de zoom. Elle est ajouté à 1.0 car on refactorise le modèle par un facteur de 1.0+x. Valeur conseillée 0.08
	 */
	private double zoomSens = 0.08;

	/**
	 * Valeur globale pour la sensitivé de translations lors du zoom. Elle va diviser la distance entre le centre du panel et la souris. Valeur conseillée 5.0
	 */
	private double zoomTransSens = 5.0; // sensitivity of translation to mousepoint when zooming

	
	/**
	 * Valeur conseillé 1.5
	 * @return the rotationSens
	 */
	public double getRotationSens() {
		return rotationSens;
	}

	/**
	 * Valeur conseillé 0.08
	 * @return the zoomSens
	 */
	public double getZoomSens() {
		return zoomSens;
	}

	/**
	 * @return the reduceTransSens
	 */
	public double getReduceTransSens() {
		return reduceTransSens;
	}

	/**
	 * @return the reduceRotationSens
	 */
	public double getReduceRotationSens() {
		return reduceRotationSens;
	}

	/**
	 * @return the reduceZoomSens
	 */
	public double getReduceZoomSens() {
		return reduceZoomSens;
	}

	/**
	 * @return the zoomTransSens
	 */
	public double getZoomTransSens() {
		return zoomTransSens;
	}

	/**
	 * PASS REAL VALUE MULTIPLIED BY 10
	 * Valeur globale pour la sensitivité de rotation en degrés. Valeur conseillée 1.5 (pass 15.0)
	 * @param rotationSens
	 */
	public void setRotationSens(double rotationSens) {
		this.rotationSens = rotationSens / 10;
	}

	/**
	 * PASS REAL VALUE MULTIPLIED BY 100
	 * @param zoomSens Valeur globale pour la sensitivté de zoom. Elle est ajouté à 1.0 car on refactorise le modèle apr un facteur de 1.0+x. <b>Valeur
	 *            conseillée 0.08</b>
	 */
	public void setZoomSens(double zoomSens) {
		this.zoomSens = zoomSens / 100;
	}

}
