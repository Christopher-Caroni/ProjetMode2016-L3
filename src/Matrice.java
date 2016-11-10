
import java.util.List;

public class Matrice {

	private double[][] matrice;

	public Matrice(int width, int height) {
		matrice = new double[height][width];
		int aRows = this.matrice.length;
		int aColumns = this.matrice[0].length;
		for (int i = 0; i < aRows; i++) {
			for (int j = 0; j < aColumns; j++) {
				this.matrice[i][j] = 0.0;
			}
		}
	}

	public static double[][] multiply(double[][] A, double[][] B) {

		int aRows = A.length;
		int aColumns = A[0].length;
		int bRows = B.length;
		int bColumns = B[0].length;

		if (aColumns != bRows) {
			return null;
		}

		double[][] C = new double[aRows][bColumns];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				C[i][j] = 0.00000;
			}
		}

		for (int i = 0; i < aRows; i++) { // aRow
			for (int j = 0; j < bColumns; j++) { // bColumn
				for (int k = 0; k < aColumns; k++) { // aColumn
					if (j == 0) {
					}
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return C;
	}

	double[][] addMatrices(double[][] matrixA, double[][] matrixB) {
		
		int rows = matrixA.length;
		int cols = matrixA[0].length;

		double[][] sum = new double[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i < 3 && j == 0) {
				}
				sum[i][j] = matrixA[i][j] + matrixB[i][j];
			}
		}
		System.out.println("");
		return sum;
	}

	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}

	public double[][] getMatrice() {
		return matrice;
	}

	public void emptyMatrice(Matrice mat) {
		int aRows = this.matrice.length;
		int aColumns = this.matrice[0].length;
		for (int i = 0; i < aRows; i++) {
			for (int j = 0; j < aColumns; j++) {
				mat.getMatrice()[i][j] = 0.0;
			}
		}
	}

	/**
	 * Rotation autour de l'axe X
	 * 
	 * @param angle
	 */
	public void rotateX(double angle) {
		double rad = Math.toRadians(angle);
		double[][] xRotation = new double[][] { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, Math.cos(rad), -Math.sin(rad), 0.0 }, { 0.0, Math.sin(rad), Math.cos(rad), 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
		this.matrice = multiply(xRotation, this.matrice);
	}

	/**
	 * Rotation autout de l'axe Y
	 * 
	 * @param angle
	 */
	public void rotateY(double angle) {
		double rad = Math.toRadians(angle);
		double[][] yRotation = new double[][] { { Math.cos(rad), 0.0, Math.sin(rad), 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { -Math.sin(rad), 0.0, Math.cos(rad), 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
		this.matrice = multiply(yRotation, this.matrice);
	}

	/**
	 * Rotation autout de l'axe Z
	 * 
	 * @param angle
	 */
	public void rotateZ(double angle) {
		double rad = Math.toRadians(angle);
		double[][] zRotation = new double[][] { { Math.cos(rad), -Math.sin(rad), 0.0, 0.0 }, { Math.sin(rad), Math.cos(rad), 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
		this.matrice = multiply(zRotation, this.matrice);
	}

	/**
	 * Rotation autour de l'axe X
	 * 
	 * @param angle
	 */
	public void rotateXNew(Figure fig, double angle) {
		double rad = Math.toRadians(angle);

		//	@formatter:off
		double[][] xRotation = new double[][] { 
			{ 1.0, 0.0, 0.0, 0.0 }, 
			{ 0.0, Math.cos(rad), -Math.sin(rad), 0.0 }, 
			{ 0.0, Math.sin(rad), Math.cos(rad), 0.0 }, 
			{ 0.0, 0.0, 0.0, 1.0 } };
		//	@formatter:on

		translateMatrix(-fig.getCenter().getX(), -fig.getCenter().getY(), -fig.getCenter().getZ());
		this.matrice = multiply(xRotation, this.matrice);
		translateMatrix(fig.getCenter().getX(), fig.getCenter().getY(), fig.getCenter().getZ());
	}

	/**
	 * Rotation autout de l'axe Y
	 * 
	 * @param angle
	 */
	public void rotateYNew(Figure fig, double angle) {
		double rad = Math.toRadians(angle);

		//	@formatter:off
		double[][] yRotation = new double[][] { 
			{ Math.cos(rad), 0.0, Math.sin(rad), 0.0 }, 
			{ 0.0, 1.0, 0.0, 0.0 }, 
			{ -Math.sin(rad), 0.0, Math.cos(rad), 0.0 }, 
			{ 0.0, 0.0, 0.0, 1.0 } };
		//	@formatter:on

		translateMatrix(-fig.getCenter().getX(), -fig.getCenter().getY(), -fig.getCenter().getZ());
		this.matrice = multiply(yRotation, this.matrice);
		translateMatrix(fig.getCenter().getX(), fig.getCenter().getY(), fig.getCenter().getZ());
	}

	/**
	 * Rotation autout de l'axe Z
	 * 
	 * @param angle
	 */
	public void rotateZNew(Figure fig, double angle) {
		double rad = Math.toRadians(angle);

		//	@formatter:off
		double[][] zRotation = new double[][] { 
			{ Math.cos(rad), -Math.sin(rad), 0.0, 0.0 }, 
			{ Math.sin(rad), Math.cos(rad), 0.0, 0.0 }, 
			{ 0.0, 0.0, 1.0, 0.0 }, 
			{ 0.0, 0.0, 0.0, 1.0 } };
		//	@formatter:on

		translateMatrix(-fig.getCenter().getX(), -fig.getCenter().getY(), -fig.getCenter().getZ());
		this.matrice = multiply(zRotation, this.matrice);
		translateMatrix(fig.getCenter().getX(), fig.getCenter().getY(), fig.getCenter().getZ());
	}
	
	public void translateMatrix(double x, double y, double z) {
	//	@formatter:off
		double[][] translate = new double[][] { 
			{ 1, 0.0, 0.0, x}, 
			{ 0.0, 1, 0.0, y},
			{ 0.0, 0.0, 1, z}, 
			{ 0.0, 0.0, 0.0, 1.0 } };
	// 	@formatter:on
		this.matrice = multiply(translate, this.matrice);
	}

	public static void translateMatrix(Matrice matrice, double x, double y, double z) {
	//	@formatter:off
		double[][] translate = new double[][] { 
			{ 1, 0.0, 0.0, x}, 
			{ 0.0, 1, 0.0, y},
			{ 0.0, 0.0, 1, z}, 
			{ 0.0, 0.0, 0.0, 1.0 } };
	//	@formatter:on
		matrice.matrice = multiply(translate, matrice.matrice);
	}
	
	public static void translateMatrix(Figure fig, double x, double y, double z) {
	//	@formatter:off
		fig.getPtsMat().importPoints(fig.getPtsTrans());
		double[][] translate = new double[][] { 
			{ 1, 0.0, 0.0, x}, 
			{ 0.0, 1, 0.0, y},
			{ 0.0, 0.0, 1, z}, 
			{ 0.0, 0.0, 0.0, 1.0 } };
	//	@formatter:on
		fig.getPtsMat().matrice = multiply(translate, fig.getPtsMat().matrice);
		fig.getPtsMat().exportToPoints(fig.getPtsTrans());
	}
	
	/**
	 * Remplit la matrice de 0.0 et puis stocke des Point dans la matrice. <br>
	 * Stocke jusqu'a 3 coordonnées par point
	 * <br><b>ATTENTION</b> a toujours appliquer {@link #setHomogeneousCoords()} après
	 * 
	 * @param points
	 */
	public void importPoints(List<Point> points) {
		int aRows = this.matrice.length;
		int aColumns = this.matrice[0].length;
		for (int i = 0; i < aRows; i++) {
			for (int j = 0; j < aColumns-1; j++) {
				this.matrice[i][j] = 0.0;
			}
		}

		for (int i = 0; i < aColumns; i++) {
			for (int j = 0; j < 3; j++) {
				Point tmpPoint = points.get(i);
				this.matrice[j][i] = tmpPoint.getCoord(j);
			}
		}
	}

	/**
	 * Stocke la matrice dans une List de Point. <br>
	 * Stocke autant de coordonnées dans le Point que la matrice a de lignes.
	 * 
	 * @param points
	 */
	public void exportToPoints(List<Point> points) {
		int aColumns = this.matrice[0].length;

		for (int i = 0; i < aColumns; i++) {
			Point pt = points.get(i);
			pt.resetCoords();
			for (int j = 0; j < 3; j++) {
				pt.add(this.matrice[j][i]);
			}
		}
	}

	public static String toStringMatrice(double[][] m) {
		String result = "";
		for (int i = 0; i < m.length; i++) {
			// for(int j = 0; j < m[i].length; j++) {
			for (int j = 0; j < 3; j++) {
				result += String.format("%11.2f", m[i][j]);
			}
			result += "\n";
		}
		return "\nMatrice =\n" + result;
	}
	
	public static String toStringMatrice(double[][] m, int length) {
		String result = "";
		for (int row = 0; row < m.length; row++) {
			// for(int j = 0; j < m[i].length; j++) {
			for (int j = 0; j < m[row].length && j < length; j++) {
				result += String.format("%11.2f", m[row][j]);
			}
			result += "\n";
		}
		return "\nMatrice =\n" + result;
	}

	public void setHomogeneousCoords() {
		int lastRow = this.matrice.length - 1;
		int aColumns = this.matrice[0].length;
		for (int i = 0; i < aColumns; i++) {
			this.matrice[lastRow][i] = 1.0;
		}
	}

}
