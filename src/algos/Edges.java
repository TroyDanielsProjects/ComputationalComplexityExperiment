package algos;

public class Edges {
	
	public int vertexA;
	public int vertexB;
	public double weight;
	
	public Edges(int vertexA,int vertexB,double weight) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.weight = weight;
	}
	
	public String toString() {
		return "This is edge: "+ this.vertexA+"-"+this.vertexB+" with weight "+this.weight;
	}

}
