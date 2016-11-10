import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

public class MouseControler extends MouseAdapter {
	
	private VisualisationPanel visPanel;
	
	private int transX, transY;
	private int rotX, rotY;
	private double rotationSens = 5;
	private int notches;
	private int nextX, nextY;
	
	public MouseControler(VisualisationPanel visualisationPanel) {
		super();
		
		visPanel = visualisationPanel;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		/**
		 * Zoom into mouse cursor = moving the center of figure nearer to the
		 * mouse cursor
		 */
		double zoomSens = 0.1;
		double zoomTransSens = 10.0; // sensitivity of translation to mousepoint when zooming
		int distToMouseX = (visPanel.getWidth() / 2) - e.getX(); // distance from center of panel to mouse x
		int distToMouseY = (visPanel.getHeight() / 2) - e.getY(); // distance from center of panel to mouse y

		visPanel.refreshFigDims();
		notches = e.getWheelRotation() * -1;
		
		double zoom = 1.0 + (zoomSens * notches);
		visPanel.zoom(zoom);

		if (e.getWheelRotation() > 0) {
			Calculations.translatePoints(visPanel.getFigure(), (-distToMouseX / zoomTransSens) * zoom, (-distToMouseY / zoomTransSens) * zoom, 0);
		} else {
			// zoom in
			Calculations.translatePoints(visPanel.getFigure(), (distToMouseX / zoomTransSens) * zoom, (distToMouseY / zoomTransSens) * zoom, 0);
		}
		
		visPanel.refreshObject();
		visPanel.repaint();
		/* End Zoom */
	}

	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			transX = e.getX();
			transY = e.getY();
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			rotX = e.getX();
			rotY = e.getY();
		}
		visPanel.refreshFigDims();
	}

	public void mouseDragged(MouseEvent e) {
		/* Translate Figure */
		if (SwingUtilities.isLeftMouseButton(e)) {
			nextX = e.getX();
			nextY = e.getY();
			double moveX = (transX - nextX) * -1;
			double moveY = (transY - nextY) * -1;
			Calculations.translatePoints(visPanel.getFigure().getPtsTrans(), moveX, moveY, 0);
			visPanel.refreshObject();
			visPanel.repaint();
			transX = nextX;
			transY = nextY;
		}
		/* End Translate Figure */

		/* Rotate Figure */
		if (SwingUtilities.isRightMouseButton(e)) {
			nextX = e.getX();
			nextY = e.getY();
			if (Math.abs(nextY - rotY) > Math.abs(nextX - rotX)) {
				// rotation autour de l'axe X entend un mouvement haut/bas donc
				// Y
				if (nextY > rotY) {
					Calculations.rotateXByPoint(visPanel.getFigure(), rotationSens);
				} else {
					Calculations.rotateXByPoint(visPanel.getFigure(), -rotationSens);
				}
			} else {
				if (nextX > rotX) {
					Calculations.rotateYByPoint(visPanel.getFigure(), rotationSens);
				} else {
					Calculations.rotateYByPoint(visPanel.getFigure(), -rotationSens);
				}
			}

			visPanel.refreshObject();
			visPanel.repaint();
			rotX = nextX;
			rotY = nextY;
		}
		/* End Rotate Figure */
	}
}