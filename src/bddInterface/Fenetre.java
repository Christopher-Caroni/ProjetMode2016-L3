package bddInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 3259687165838481557L;
	private JPanel mainPanel;
	private DescriptionPanel descPanel;
	private DescriptionPanelTable descPanelTable;
	
	private Dimension dim = new Dimension(600, 300);

	public Fenetre(String title, ResultSet rs, ResultSet rs2) {
		super();
		
		/* DESCRIPTION PANEL */
//		descPanel = new DescriptionPanel(rs);
		descPanelTable = new DescriptionPanelTable(rs, rs2);
		
		
		/* PANNEAU PRINCIPAL */
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(dim);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createTitledBorder(title));
//		mainPanel.add(descPanel);
		mainPanel.add(descPanelTable);
		
		/* FENETRE */
		String output = title.substring(0, 1).toUpperCase() + title.substring(1);
		setTitle(output);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setSize(dim);
		add(mainPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void setBorderTitle(String title) {
		mainPanel.setBorder(BorderFactory.createTitledBorder(title));
	}

}
