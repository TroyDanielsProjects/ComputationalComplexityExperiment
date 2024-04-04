package algos;

import java.util.Random;

public class ThreeSatInstance extends AllSat {
	
	
	public ThreeSatInstance(int numOfClauses, int numOfVariables) {
		super.sat = new Clause[numOfClauses];
		super.varAssignments = new boolean[numOfVariables];
		super.numOfClauses = numOfClauses;
		super.numOfVariables = numOfVariables;
		for (int i = 0; i<this.sat.length;i++) {
			Random rand = new Random();
			double num;
			boolean isNegated;
			BooleanVar[] clause = new BooleanVar[3];
			for (int j =0; j<3; j++) {
				num = rand.nextDouble();
				if (num>=0.5) {
					isNegated = false;
				}
				else {
					isNegated = true;
				}
				clause[j] = new BooleanVar(rand.nextInt(numOfVariables),isNegated);
				clause[j].setAssignment(false);
			}
			this.sat[i] = new Clause(clause);
			this.satAL.add(new Clause(clause));
		}
	}
	
	
	
	public static void main(String[] args) {
		ThreeSatInstance test = new ThreeSatInstance(2,5);
		System.out.println(test);
		test.printVarAssign();
		System.out.println("-------------------");
		for (int i = 0; i<test.numOfClauses;i++) {
			Clause c = test.getClause(i);
			System.out.println(c.getVar(0).getAssignment());
			System.out.println(c.getVar(1).getAssignment());
			System.out.println(c.getVar(2).getAssignment());
		}
		System.out.println("-------------------");
		test.randomAssignments();
		test.printVarAssign();
		for (int i = 0; i<test.numOfClauses;i++) {
			Clause c = test.getClause(i);
			System.out.println(c.getVar(0).getAssignment());
			System.out.println(c.getVar(1).getAssignment());
			System.out.println(c.getVar(2).getAssignment());
		}
		System.out.println(test.numOfSatisfiedClauses());
	}

}
