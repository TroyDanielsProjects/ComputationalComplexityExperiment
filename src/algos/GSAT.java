package algos;

public class GSAT {
	
	public AllSat satInstance;
	public int numOfVariables;
	public int numOfClauses;
	public boolean[] bestAssignments;
	public int maxClausesSatisfied;
	
	public GSAT(int numOfClauses, int numOfVariables) {
		this.satInstance = new ThreeSatInstance(numOfClauses,numOfVariables);
		this.numOfClauses = numOfClauses;
		this.numOfVariables = numOfVariables;
		this.bestAssignments = this.satInstance.GetVarAssignments();
		this.maxClausesSatisfied = this.satInstance.numOfSatisfiedClauses();
	}
	
	public GSAT(AllSat inst) {
		this.satInstance = inst;
		this.numOfClauses = inst.GetNumClauses();
		this.numOfVariables = inst.GetVarAssignments().length;
		this.bestAssignments = this.satInstance.GetVarAssignments();
		this.maxClausesSatisfied = this.satInstance.numOfSatisfiedClauses();
	}
	
	public int solve(int numOfIterations) {
		this.satInstance.randomAssignments();
		for (int i = 0; i< numOfIterations;i++) {
			int[] countOfVariables = new int[this.numOfVariables];
			for (int j = 0; j < this.numOfClauses;j++) {
				if (!this.satInstance.getClause(j).isSatisfied()) {
					Clause c = this.satInstance.getClause(j);
					for (int var = 0; var < c.clauseLength();var++) {
						countOfVariables[c.getVar(var).varNum]++;
					}
				}
			}
			int maxNumVar = 0;
			int maxNum = 0;
			for (int j = 0;j<countOfVariables.length;j++) {
				if (countOfVariables[j] > maxNum) {
					maxNumVar = j;
					maxNum = countOfVariables[j];
				}
			}
			this.satInstance.setVar(maxNumVar);
			int newClasuesSat = this.satInstance.numOfSatisfiedClauses();
			if (newClasuesSat > this.maxClausesSatisfied) {
				this.maxClausesSatisfied = newClasuesSat;
				this.bestAssignments = this.satInstance.GetVarAssignments();
			}
			
		}
		
		return maxClausesSatisfied;
	}
	
	public void applyGSAT(int iterationsOfGSAt, int iterationsPerGSAT) {
		for (int i = 0; i<iterationsOfGSAt;i++) {
			solve(iterationsPerGSAT);
		}
	}
	
	public static void main(String[] args) {
		GSAT test = new GSAT(3,5);
		test.solve(3);
		System.out.println(test.satInstance);
		System.out.println(test.maxClausesSatisfied);
		for (int i =0; i < test.bestAssignments.length;i++) {
			System.out.println(test.bestAssignments[i]);
		}
	}

}
