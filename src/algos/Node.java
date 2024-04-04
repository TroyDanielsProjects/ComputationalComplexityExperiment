package algos;
import java.util.ArrayList;

public class Node {

	public Node parent;
	public int rank;
	public int vertex;
	public ArrayList<Node> children = new ArrayList<Node>();
	
	public Node(int vertex) {
		this.rank = 0;
		this.parent = null;
		this.vertex = vertex;
	}
	
	public String toString() {
		return "This is vertex: "+ this.vertex;
	}
}
