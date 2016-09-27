import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) throws IOException {
		Fenetre fen = new Fenetre(false, true, true);

		Path path = Paths.get("ply/cube.ply");
		Lecture lect = new Lecture(path);
		
		if (!lect.isErreur()) {
			List<Point> points = new ArrayList<>();
			List<Face> faces = new ArrayList<>();
			List<Segment> segments = new ArrayList<>();
			points = lect.getPoints();
			faces = lect.getFaces();
			segments = lect.getSegments();

			fen.setVisible(true);
			fen.setPoints(points, 2.5);
			fen.setFaces(faces);
			fen.setSegments(segments);
			fen.repaint();
	
			System.out.println("Nombre de points = " + lect.getNbPoints() + "\n");
			System.out.println("Nombre de faces = " + lect.getNbFaces() + "\n");
			
			System.out.println("\n Liste des points\n");
			for (Point pt : points) {
				System.out.println(pt.toString());
			}
	
			System.out.println("\n Liste des Faces\n");
			for (int i = 0; i < faces.size(); i++) {
				System.out.println("Face n=" + i + "  " + faces.get(i));
			}
			
			
			System.out.println("\n Liste des Segments\n");
			for (int i = 0; i < segments.size(); i++) {
				System.out.println("Segment n=" + i + "  " + segments.get(i));
			}
		} else {
			fen.setVisible(false);
			JOptionPane.showMessageDialog(fen, "Erreur lors de la lecture du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
