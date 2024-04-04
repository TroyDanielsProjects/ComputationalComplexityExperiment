package algos;

import java.io.File;
import java.util.Scanner;

public class TSPProblemGen {
	
	private double[][] matrix;
	
	public double[][] createAdjMatrix(String fileName) {
		double[][] matrix = null;
		int dimension = 0;
		VertexCoord[] vertexes = null;
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			String line;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine().strip();
				//System.out.println(line);
				if (line.startsWith("DIMENSION:")) {
					dimension = Integer.parseInt(line.split(": ")[1]);
					vertexes = new VertexCoord[dimension];
				}
				else if (line.matches("[ \\d.]+")) {
					String[] edgeString = line.strip().split(" +");
					vertexes[Integer.parseInt(edgeString[0].strip())-1] = new VertexCoord(Double.parseDouble(edgeString[1].strip()), Double.parseDouble(edgeString[2].strip()));
				}
			}
			scanner.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		matrix = new double[dimension][dimension];
		for (int i = 0; i < dimension;i++) {
			for (int j = 0; j < dimension;j++) {
				matrix[i][j] = Math.sqrt( Math.pow( (vertexes[i].x - vertexes[j].x), 2) + Math.pow((vertexes[i].y - vertexes[j].y), 2) );
				if (fileName.startsWith("pla")) {
					matrix[i][j] = Math.ceil(matrix[i][j]);
				}
			}
			
		}
		
//		for (int i = 0; i < dimension;i++) {
//			System.out.println();
//			for (int j = 0; j < dimension;j++) {
//				System.out.print(" "+ matrix[i][j]+" ");
//			}
//		}
		this.matrix = matrix;
		return matrix;
	}
	
	
	public double getOptimalSolution(String fName) {
		double optSolution = 0;
		try {
			File file = new File(fName);
			Scanner scanner = new Scanner(file);
			String line;
			int currentVertex = 0;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine().strip();
				if (line.matches("[\\d]+")) {
					int nextVertex = Integer.parseInt(line)-1;
					optSolution += this.matrix[currentVertex][nextVertex];
					currentVertex = nextVertex;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return optSolution;
	}
	
	public static void main(String[] args) {
		TSPProblemGen  test = new TSPProblemGen();
		test.createAdjMatrix("pla33810.tsp");
		System.out.println(test.getOptimalSolution("pla33810.opt.tour"));
	}
	
	public class VertexCoord {
		public double x;
		public double y;
		
		public VertexCoord(double x,double y) {
			this.x = x;
			this.y = y;
		}
	}

}
