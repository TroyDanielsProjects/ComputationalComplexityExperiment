package algos;

import java.util.ArrayList;


public class KruskalsAlgo {
	
	private Edges[] sortedEdges;
	private ArrayList<Edges> edgesUsed;
	private Node[] vertexes;
	private double solution;
	private DisjointSetsWPath disjointSets;
	
	public KruskalsAlgo() {
		solution= 0;
	}
	
	public ArrayList<Edges> getEdgesUsed() {
		return this.edgesUsed;
	}
	
	public double solve(double[][] graphMatrix) {
		// add vertexes and edges to a list
		// eavh vertex will be a set
		this.solution =0;
		this.edgesUsed = new ArrayList<Edges>();
		this.disjointSets = new DisjointSetsWPath();
		this.vertexes = new Node[graphMatrix.length];
		ArrayList<Edges> tempList = new ArrayList<Edges>();
		for (int i = 0; i < graphMatrix.length;i++) {
			this.vertexes[i] = disjointSets.makeSet(i);
			for (int j = 0; j < graphMatrix[i].length;j++) {
				if (graphMatrix[i][j] > 0) {
					tempList.add(new Edges(i,j,graphMatrix[i][j]));
				}
			}
		}
		// sort the edges
		 QuickSort quickSort = new QuickSort(tempList);
		 sortedEdges = quickSort.sort();
		 // go through the edges in sorted order
		 // if vertexs from the edge are in different sets union them
		 for (int i =0; i<sortedEdges.length;i++) {
				if (disjointSets.findSet(vertexes[sortedEdges[i].vertexA]) != disjointSets.findSet(vertexes[sortedEdges[i].vertexB])) {
					// keeps track of edges used
					edgesUsed.add(sortedEdges[i]);
					solution+=sortedEdges[i].weight;
					disjointSets.union(vertexes[sortedEdges[i].vertexA],vertexes[sortedEdges[i].vertexB]);
				}
				
			}
		 return solution;
	}
	public String toString() {
		String stringToReturn = "";
		stringToReturn += "The edges used are: ";
		for (int i = 0;i < edgesUsed.size();i++ ) {
			 stringToReturn+= "\n"+edgesUsed.get(i)+"\n";
		 }
		return stringToReturn;
		
	}
	
	public static void main(String[] args) {
		// used the example from the book (pg 633) answer is 37
		double[][] testArr = {{0,4,0,0,0,0,0,8,0},{4,0,8,0,0,0,0,11,0},{0,8,7,0,0,0,0,0,2},{0,0,7,0,9,14,0,0,0},{0,0,0,9,0,10,0,0,0},{0,0,4,14,10,0,2,0,0},{0,0,0,0,0,2,0,1,6},{8,11,0,0,0,0,2,0,7},{0,0,2,0,0,0,6,7,0}};
		KruskalsAlgo test = new KruskalsAlgo();
		test.solve(testArr);
		System.out.println(test);
		System.out.println("--------------------");
		//from Tom's book exercise 8.8
		double[][] testArr2 = {{0,1,4,7,6,2},{1,0,5,8,7,3},{4,5,0,3,2,6},{7,8,3,0,5,9},{6,7,2,5,0,8},{2,3,6,9,8,0}};
		test.solve(testArr2);
		System.out.println(test);
	}
}
