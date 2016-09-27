
import java.util.List;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	
	Panneau panneau;
	
	public Fenetre(boolean drawPoints, boolean drawSegments, boolean drawFaces) {
		panneau = new Panneau(drawPoints, drawSegments, drawFaces);
		this.setTitle("Ma première fenêtre Java");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panneau);
	}
		
	public void setPoints(List<Point> points, double zoomLevel) {
		panneau.setPoints(points, zoomLevel);
	}
	
	public void setSegments(List<Segment> segments) {
		panneau.setSegments(segments);
	}
	
	public void setFaces(List<Face> faces) {
		panneau.setFaces(faces);
	}
}