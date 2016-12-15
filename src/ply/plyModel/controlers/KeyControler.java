package ply.plyModel.controlers;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import main.vues.ModelPanel;
import ply.plyModel.modeles.FigureModel;

public class KeyControler implements KeyEventDispatcher {

	private ModelPanel fenetre;
	private FigureModel figureModel;
	private double translationSens;
	private long old;
	private long now;

	public KeyControler(ModelPanel fenetre) {
		super();
		this.fenetre = fenetre;
		figureModel = fenetre.getFigure();
		translationSens = 10;
		old = System.currentTimeMillis();
		now = System.currentTimeMillis();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			figureModel.refreshFigDims(fenetre.getVisPanel());
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				figureModel.translatePoints(0, translationSens, 0);
				break;
			case KeyEvent.VK_UP:
				figureModel.translatePoints(0, -translationSens, 0);
				break;
			case KeyEvent.VK_RIGHT:
				figureModel.translatePoints(translationSens, 0, 0);
				break;
			case KeyEvent.VK_LEFT:
				figureModel.translatePoints(-translationSens, 0, 0);
				break;
			case KeyEvent.VK_D:
				figureModel.rotateYByPoint(5);
				break;
			case KeyEvent.VK_Q:
				figureModel.rotateYByPoint(-5);
				break;
			case KeyEvent.VK_S:
				figureModel.rotateXByPoint(5);
				break;
			case KeyEvent.VK_Z:
				figureModel.rotateXByPoint(-5);
				break;
			case KeyEvent.VK_C:
				figureModel.prepareForWindow(fenetre.getVisPanel(), 1.0);
				break;
			case KeyEvent.VK_R:
				now = System.currentTimeMillis();
				if ((now - old) > 5000) {
					fenetre.resetModel();
					old = now;
				}
				break;
			default:
				break;
			}
		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
		} else if (e.getID() == KeyEvent.KEY_TYPED) {
		}
		return false;
	}

}
