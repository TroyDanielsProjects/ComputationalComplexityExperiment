package algos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class RandomizedRounding3SAT {
	
	private AllSat sat;
	
	public RandomizedRounding3SAT(AllSat sat) {
		this.sat = sat;
	}
	
	public int solve() {
		double[] optVariables = new double[this.sat.GetNumClauses() + this.sat.numOfVariables ];
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
		for (int i = 0; i < optVariables.length;i++) {
			double[] constraint = new double[optVariables.length];
			constraint[i] = 1;
			constraints.add(new LinearConstraint(constraint,Relationship.GEQ,0));
			constraints.add(new LinearConstraint(constraint,Relationship.LEQ,1));
			if ( i < this.sat.GetNumClauses() ) {
				optVariables[i] = 1;
			}
		}
		
		for (int i = 0 ; i < this.sat.GetNumClauses(); i++) {
			int totalNotVars = 0;
			double[] clauseVarConstraintL = new double[optVariables.length];
			double[] clauseVarConstraintR = new double[optVariables.length];
			
			for (int j = 0; j< 3;j++) {
				if (sat.getClause(i).getVar(j).getIsNegated()) {
					clauseVarConstraintL[this.sat.GetNumClauses()+sat.getClause(i).getVar(j).varNum] = -1; 
					totalNotVars++;
				}
				else {
					clauseVarConstraintL[this.sat.GetNumClauses()+sat.getClause(i).getVar(j).varNum] = 1;
				}
				clauseVarConstraintR[i] = 1;
			}
			constraints.add(new LinearConstraint(clauseVarConstraintL,totalNotVars,Relationship.GEQ,clauseVarConstraintR,0));
		}
		LinearObjectiveFunction optEquation = new LinearObjectiveFunction(optVariables, 0);
		PointValuePair solution = null;
        solution = new SimplexSolver().optimize(optEquation, new LinearConstraintSet(constraints), GoalType.MAXIMIZE);
        
        if (solution != null) {
        	Random rand = new Random();
        	for (int i =0; i< this.sat.numOfVariables;i++) {
        		double probality = solution.getPoint()[i+this.sat.numOfClauses];
        		if (rand.nextDouble() <= probality) {
        			this.sat.setVar(i, true);
        		}
        		else {
        			this.sat.setVar(i, false);
        		}
        	}
        	return this.sat.numOfSatisfiedClauses();
        }
        System.out.println("Error");
        return 0;
		
	}
	
	public static void main(String[] args) {
        //describe the optimization problem
		ThreeSatInstance sat = new ThreeSatInstance(8,5);
		System.out.println(sat);
		RandomizedRounding3SAT test = new RandomizedRounding3SAT(sat);
		test.solve();
	}

}
