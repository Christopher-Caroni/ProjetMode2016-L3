package main.vues;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ply.bdd.vues.ModelBrowser;
import ply.bdd.vues.ModelInfo;

/**
 * Cette classe est un JPanel qui toujours placé à gauche dans le JSplitPane de {@link MainFenetre}
 * 
 * @author L3
 *
 */
public class LeftSidePanel extends JPanel {

	private ModelBrowser modelBrowser;
	private ModelInfo modelInfo;

	/**
	 * @param modelName 
	 * @param dim
	 * @param mainFenetre la fenêtre dans laquelle se situe ce panel. Utilisé pour renvoyer des commandes à partir d'ici. 
	 */
	public LeftSidePanel(String modelName, Dimension dim, MainFenetre mainFenetre) {
		super();

		/* MODEL INFO */
		modelInfo = new ModelInfo(modelName);
		if (modelName != null) {
			modelName = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
		}
		modelInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Informations sur " + modelName + " : "));
		modelInfo.setPreferredSize(new Dimension(dim.width, 100));

		/* MODEL BROWSER */
		modelBrowser = new ModelBrowser(mainFenetre, dim);
		modelBrowser.setBorder(BorderFactory.createLineBorder(Color.black));
		modelBrowser.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Model Browser :"));

		/* THIS PANEL */
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(modelInfo);
		add(modelBrowser);
	}

	public void setNewModelInfo(String newModelName) {
		modelInfo.initModelInfo(newModelName);
	}

	public void setModelInfoBorderTitle(String title) {
		modelInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Informations sur " + title + " : "));
	}
	
}
