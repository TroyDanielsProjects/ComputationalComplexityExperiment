package algos;

import java.util.ArrayList;

public class TSP2approx {
	
	public ArrayList<Edges> edgesInTSP;
	double[][] graphMatrix;
	int loc;
	
	public int solve(double[][] graphMatrix) {
		this.loc = 0;
		edgesInTSP = new ArrayList<Edges>();
		this.graphMatrix = graphMatrix;
		KruskalsAlgo mst  = new KruskalsAlgo();
		double solutionMST = mst.solve(graphMatrix);
		ArrayList<Edges> edgesInMST = mst.getEdgesUsed();
		for (int i =0; i<edgesInMST.size();i++) {
			edgesInMST.get(i).weight = Math.min(edgesInMST.get(i).vertexA, edgesInMST.get(i).vertexB);
		}
		QuickSort sort = new QuickSort(edgesInMST);
		Edges[] sortedEdgesInMST = sort.sort();
		Node[] nodes = new Node[graphMatrix.length];
		for ( int i =0; i< nodes.length;i++) {
			nodes[i] = new Node(i);
			nodes[i].vertex = i;
		}
		
		for ( int i = 0; i< sortedEdgesInMST.length;i++) {
			int maxNum = Math.max(sortedEdgesInMST[i].vertexA, sortedEdgesInMST[i].vertexB);
			int minNum = Math.min(sortedEdgesInMST[i].vertexA, sortedEdgesInMST[i].vertexB);
			if (nodes[maxNum].parent==null) {
				nodes[minNum].children.add(nodes[maxNum]);
				nodes[maxNum].parent = nodes[minNum];
				//System.out.println(minNum + " is gaining child "+ maxNum);
			}
			else if (nodes[minNum].parent==null && nodes[maxNum].parent != null) {
				nodes[maxNum].children.add(nodes[minNum]);
				nodes[minNum].parent = nodes[maxNum];
				//System.out.println(maxNum + " is gaining child "+ minNum);
			}
			else {
				Node currNode = nodes[maxNum];
				while (currNode.parent!= null) {
					currNode = currNode.parent;
				}
				if (currNode.vertex == 0 ) {
					currNode = nodes[minNum];
					while (currNode.parent!= null) {
						currNode = currNode.parent;
					}
					nodes[maxNum].children.add(currNode);
					currNode.parent = nodes[maxNum];
				}
				else {
					nodes[minNum].children.add(currNode);
					currNode.parent = nodes[minNum];
				}
			}
			
		}
		Node root = nodes[0];
		transverse(root);
		edgesInTSP.add(new Edges(loc,0,graphMatrix[loc][0]));
		int totalTour = 0;
		for (int i =0; i<edgesInTSP.size();i++) {
			//System.out.println(edgesInTSP.get(i));
			totalTour+= edgesInTSP.get(i).weight;
		}
		return totalTour;
	}
	
	public void transverse(Node node) {
		for (int i =0; i<node.children.size();i++) {
			edgesInTSP.add(new Edges(this.loc,node.children.get(i).vertex, this.graphMatrix[this.loc][node.children.get(i).vertex]));
			this.loc = node.children.get(i).vertex;
			transverse(node.children.get(i));
		}
	}
	
	
	public static void main(String[] args) {
		TSP2approx test = new TSP2approx();
		double[][] testArr2 = {{0,1,4,7,6,2},{1,0,5,8,7,3},{4,5,0,3,2,6},{7,8,3,0,5,9},{6,7,2,5,0,8},{2,3,6,9,8,0}};
		double sol = test.solve(testArr2);
		System.out.println(sol);
	}

}
