package algos;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateGraph {
	
	public int[][] create(String fname) {
		File file = new File(fname);
		Pattern pattern = Pattern.compile("(\\d| )+");
		int[][] graph = null;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().strip();
				Matcher matcher = pattern.matcher(line);
				if (line.startsWith("DIMENSION")) {
					String[] info = line.split(":");
					int dim = Integer.parseInt(info[1].strip());
					graph = new int[dim][dim];
				}
				else if (matcher.matches()) {
					System.out.println("hello");
					String[] info = line.split(" ");
					int VertexA = Integer.parseInt(info[1].strip());
					int VertexB = Integer.parseInt(info[2].strip());
					int weight = (int) Math.sqrt( (Math.pow(VertexA, 2)) + (Math.pow(VertexB,2)) );
					if (graph != null) {
						graph[VertexA][VertexB] = weight;
					}
					else {
						System.out.println("graph not made");
						throw(new Exception());
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return graph;
		
	}
	
	public static void main(String[] args) {
		CreateGraph test = new CreateGraph();
		int[][] testGraph = test.create("graph1.txt");
		String s = "";
		for (int i =0;i <testGraph.length;i++) {
			for (int j =0;j <testGraph.length;j++) {
				s+= testGraph[i][j] + ",";
			}
			s+= "\n";
		}
	}

}
