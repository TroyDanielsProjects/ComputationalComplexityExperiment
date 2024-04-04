package algos;

public class DisjointSetsWPath {
	
	public Node makeSet(int vertex) {
		Node node = new Node(vertex);
		node.parent = node;
		node.rank = 0;
		return node;
	}
	
	public void link(Node x, Node y) {
		if (x.rank>y.rank) {
			y.parent = x;
		}
		else {
			x.parent = y;
			if (x.rank == y.rank) {
				y.rank+=1;
			}
		}
	}
	
	public Node findSet(Node x) {
		if (x.parent !=x) {
			x.parent = findSet(x.parent);
		}
		return x.parent;
	}
	
	public void union(Node x, Node y) {
		 link(findSet(x),findSet(y));
	}
}
