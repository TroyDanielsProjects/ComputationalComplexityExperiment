package algos;

public class SevenEighthsApproxSAT {
	
	private AllSat sat;
	
	public SevenEighthsApproxSAT(AllSat sat) {
		this.sat = sat;
	}
	
	public int solve() {
		
		sat.randomAssignments();
		return sat.numOfSatisfiedClauses();
	}
	
	
	public static void main(String[] args) {
		
		ThreeSatInstance sat = new ThreeSatInstance(10, 15);
		SevenEighthsApproxSAT test = new SevenEighthsApproxSAT(sat);
		System.out.println(test.solve());
	}

}
