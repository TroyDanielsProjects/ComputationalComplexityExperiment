package algos;

import java.util.ArrayList;

public class SATInstance extends AllSat {
	
	
	public SATInstance(ArrayList<Clause> c, int numOfVariables) {
		super.satAL = c;
		super.varAssignments = new boolean[numOfVariables];
		super.numOfClauses = this.sat.length;
		for (int i = 0; i<this.sat.length;i++) {
				for (int j = 0; j< this.sat[i].clauseLength();j++) {
					this.varAssignments[this.sat[i].getVar(j).varNum] = this.sat[i].getVar(j).getAssignment();
			}
		}
	}
}
