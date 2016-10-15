import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	
	private static boolean showPoints = false;
	private static boolean showFaces= false;
	private static boolean showSegments = false;
	private static Fenetre fen;
	private static boolean displayOption = false;
	private static boolean fileChosen = false;
	private static boolean paramsOK = true;
	private static Path path;
	private static Figure figure;
	
	public static void main(String[] args) {
	
		parseArguments(args);
		
		if (paramsOK && !figure.getErreurLecture()) {
			
			createViewer();
			
			//diagnose();
		}
	}
	
	private static void createViewer() {
		fen = new Fenetre(showPoints, showSegments, showFaces);

		
		fen.setPoints(figure.getPoints(), 1.0);
		fen.setFaces(figure.getFaces());
		fen.setSegments(figure.getSegments());
		fen.setVisible(true);
		fen.repaint();
	}
	
	private static void parseArguments(String[] args) {
		if (args.length > 0) {
			for (int i=0;i<args.length;i++) {
				String extension = args[i].substring(args[i].lastIndexOf(".") + 1, args[i].length());
				if (args[i].equals("-f")) {
					if (!displayOption) {
						showFaces = true;
						displayOption = true;
					} else {
						paramsOK = false;
						System.out.println("Erreur : Paramètres d'affichages contradictoires");
					}
				} else if (args[i].equals("-s")) {
					if (!displayOption) {
						showSegments = true;
						displayOption = true;
					} else {
						paramsOK = false;
						System.out.println("Erreur : Paramètres d'affichages contradictoires");
					}
				} else if (extension.equals("ply")) {
					if (!fileChosen) {
						path = Paths.get(args[i]);
						fileChosen = true;
					} else {
						paramsOK = false;
						System.out.println("Erreur : Fichiers multiples");
					}
				} else {
					paramsOK = false;
					System.out.println("Paramètre non reconnu");
				}
			}
			if (!displayOption) {
				showFaces = true;
				showSegments = true;
			}
			if (paramsOK) {
				figure = new Figure(path);
			}
		} else {
			System.out.println("Aucun fichier précisé");
		}
	}
	
	/*
	private static void diagnose() {
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
	}
	*/
}
