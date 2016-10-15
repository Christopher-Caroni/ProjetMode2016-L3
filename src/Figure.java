import java.awt.geom.Path2D;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Figure {

	private int nbPoints;
	private int nbFaces;
	private List<Point> points;
	private List<Face> faces;
	private List<Point> ptsTrans;
	private List<Face> facesTrans;
	private List<Path2D> polygones;
	private List<Segment> segments;
	private Lecture lecture;
	
	
	public List<Point> getPtsTrans() {
		return ptsTrans;
	}

	public List<Face> getFacesTrans() {
		return facesTrans;
	}

	public Figure() {
		nbPoints = -1;
		nbFaces = -1;
		points = new ArrayList<>();
		ptsTrans = new ArrayList<>();
		faces = new ArrayList<>();
		facesTrans = new ArrayList<>();
		polygones = new ArrayList<>();
	}
	
	public List<Path2D> getPolygones() {
		return polygones;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public int getNbFaces() {
		return nbFaces;
	}

	public List<Point> getPoints() {
		return points;
	}

	public List<Face> getFaces() {
		return faces;
	}

	public Figure(Path file) {
		lecture = new Lecture(file);
		nbPoints = lecture.getNbPoints();
		nbFaces = lecture.getNbFaces();
		points = lecture.getPoints();
		invertPoints();
		faces = lecture.getFaces();
		ptsTrans = new ArrayList<>(points);
		facesTrans = new ArrayList<>(faces);
		polygones = new ArrayList<>();
	}
	
	public boolean getErreurLecture() {
		if (lecture != null) {
			return lecture.isErreur();
		} else {
			return true;
		}
	}
	
	public void invertPoints() {
		for (Point pt : points) {
			pt.setY(pt.getY() * -1);
		}
	}


}
