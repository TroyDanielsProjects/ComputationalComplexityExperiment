package algos;

import java.util.ArrayList;
import java.util.Random;

public abstract class AllSat {
	protected Clause[] sat;
	protected int numOfClauses;
	protected boolean[] varAssignments;
	protected ArrayList<Clause> satAL = new ArrayList<Clause>();
	protected int numOfVariables;
	
	
	public boolean[] GetVarAssignments() {
		return this.varAssignments;
	}
	
	public void randomAssignments() {
		Random rand = new Random();
		for (int i = 0; i<varAssignments.length;i++) {
			double num = rand.nextDouble();
			if (num>=0.5) {
				setVar(i);
			}
		}
	}
	
	public int numOfSatisfiedClauses() {
		int num = 0;
		for (int i = 0; i<this.sat.length;i++) {
			if (this.sat[i].isSatisfied()) {
				num++;
			}
		}
		return num;
	}
	
	public Clause getClause(int i) {
		if (i >=0 && i < this.sat.length) {
			return this.sat[i];
		}
		else {
			return null;
		}
	}
	
	public void setVar(int varNum, boolean assign) {
		this.varAssignments[varNum] = assign;
		updateAssignments();
	}
	
	public void setVar(int varNum) {
		this.varAssignments[varNum] = !this.varAssignments[varNum];
		updateAssignments();
	}
	
	public int GetNumClauses() {
		return this.numOfClauses;
	}
	
	public int GetNumVars() {
		return this.numOfVariables;
	}
	
	public void updateAssignments() {
		for (int i = 0; i<this.sat.length;i++) {
			Clause clause = this.sat[i];
			for (int j = 0; j<clause.clauseLength();j++) {
				clause.getVar(j).setAssignment(this.varAssignments[clause.getVar(j).varNum]);
			}
		}
	}
	
	public void printVarAssign() {
		String s = "";
		for (int i =0 ; i<this.varAssignments.length;i++) {
			s+= this.varAssignments[i]+",";
		}
		System.out.println(s.substring(0, s.length()-1));
	}
	
	
	public String toString() {
		String s = "";
		for (int i = 0; i<this.sat.length;i++) {
			s+= this.sat[i];
		}
		return s;
	}
	
}
