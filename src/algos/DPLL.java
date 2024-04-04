package algos;

import java.util.ArrayList;

public class DPLL {
	
	private DPLLObject obj;
	private AllSat sat;
	
	
	public DPLL(DPLLObject obj) {
		this.obj = obj;
	}
	
	public DPLL(AllSat sat) {
		DPLLObject obj = new DPLLObject(sat.satAL, sat.varAssignments);
		this.obj = obj;
		this.sat = sat;
	}
	
	public int solve() {
		boolean[] assignments = solve(this.obj);
		if (assignments == null) {
			return 0;
		}
		for (int i = 0; i < this.sat.numOfVariables; i++) {
			this.sat.setVar(i, assignments[i] );
		}
		return this.sat.numOfSatisfiedClauses();
	}
	
	public boolean[] solve(ArrayList<Clause> form, boolean[] assign) {
		boolean[] assign2 = assign.clone();
		ArrayList<Clause> form2 = new ArrayList<Clause>();
		for (int i = 0; i<form.size();i++) {
			Clause c = form.get(i).clone();
			if (c!=null) {
				form2.add(c);
			}
		}
		DPLLObject newobj = new DPLLObject(form2,assign2);
		return solve(newobj);
	}
	
	// will return the var assignments if satisfiable and null otherwise
	public boolean[] solve(DPLLObject obj) {
		obj = unitPropagation(obj);
		ArrayList<Clause> newFormula = obj.getFormula();
		boolean[] newAssignment = obj.getTruthAssignmentSoFar();
		if (newFormula.size()==0) {
			return newAssignment;
		}
		else if ( (newFormula.size()==1 && newFormula.get(0).clauseLengthAL()==0 ) || containsEmptyClause(newFormula) ) {
			return null;
		}
		BooleanVar newUnitVar = newFormula.get(0).getVarAL(0);
		newUnitVar = new BooleanVar(newUnitVar.varNum,false);
		newFormula.add(new Clause(newUnitVar));
		newAssignment[newUnitVar.varNum] = true;
		boolean[] result = solve(newFormula,newAssignment);
		if (result!=null) {
			return result;
		}
		else {
			newFormula.remove(newFormula.size()-1);
			newUnitVar = newFormula.get(0).getVarAL(0);
			newUnitVar = new BooleanVar(newUnitVar.varNum,true);
			newFormula.add(new Clause(newUnitVar));
			newAssignment[newUnitVar.varNum] = false;
			return solve(newFormula,newAssignment);
		}
	}
	
	public DPLLObject unitPropagation(DPLLObject obj) {
		ArrayList<Clause> formula = obj.getFormula();
		while (containsUnitClause(formula) && !containsEmptyClause(formula)) {
			BooleanVar unitVar = null;
			for (int i = 0; i < formula.size();i++) {
				if (formula.get(i).clauseLengthAL()==1) {
					unitVar = formula.get(i).getVarAL(0);
					break;
				}
			}
			
			if (unitVar.getIsNegated()) {
				obj.getTruthAssignmentSoFar()[unitVar.varNum] = false;
				for (int i = 0; i < formula.size();i++) {
					Clause c = formula.get(i);
					for (int j = 0; j < c.clauseLengthAL();j++) {
						if (c.getVarAL(j).varNum==unitVar.varNum && c.getVarAL(j).getIsNegated()) {
							formula.remove(i);
							i--;
							break;
						}
						else if (c.getVarAL(j).varNum==unitVar.varNum) {
							c.removeVarAL(j);
							j--;
						}
					}
				}
			}
			
			
			else {
				obj.getTruthAssignmentSoFar()[unitVar.varNum] = true;
				for (int i = 0; i < formula.size();i++) {
					Clause c = formula.get(i);
					for (int j = 0; j < c.clauseLengthAL();j++) {
						if (c.getVarAL(j).varNum==unitVar.varNum && c.getVarAL(j).getIsNegated()) {
							c.removeVarAL(j);
							j--;
						}
						else if (c.getVarAL(j).varNum==unitVar.varNum) {
							formula.remove(i);
							i--;
							break;
						}
					}
				}
			}
		}
		return obj;
	}
	
	public boolean containsUnitClause(ArrayList<Clause> formula) {
		boolean answer = false;
		for (int i = 0; i < formula.size();i++) {
			if (formula.get(i).clauseLengthAL()==1) {
				answer = true;
			}
		}
		return answer;
	}
	
	public boolean containsEmptyClause(ArrayList<Clause> formula) {
		boolean answer = false;
		for (int i = 0; i < formula.size();i++) {
			if (formula.get(i).clauseLengthAL()==0) {
				answer = true;
			}
		}
		return answer;
	}
	
	
	// test for unitPropagation. Example from book pg. 154
	public static void main(String[] args) {
		AllSat sat = new ThreeSatInstance(3, 4);
		DPLL test = new DPLL(sat);
		System.out.println(test.solve());
		
//		BooleanVar x1 = new BooleanVar(0,false);
//		BooleanVar nx1 = new BooleanVar(0,true);
//		BooleanVar x2 = new BooleanVar(1,false);
//		BooleanVar nx2 = new BooleanVar(1,true);
//		BooleanVar x3 = new BooleanVar(2,false);
//		BooleanVar nx3 = new BooleanVar(2,true);
//		BooleanVar x4 = new BooleanVar(3,false);
//		
//		ArrayList<Clause> sat = new ArrayList<Clause>();
//		
//		BooleanVar[] bc1 = {x1};
//		Clause c1 = new Clause(bc1);
//		sat.add(c1);
//		
//		BooleanVar[] bc2 = {nx1,x2};
//		Clause c2 = new Clause(bc2);
//		sat.add(c2);
//		
//		BooleanVar[] bc3 = {nx1,nx3,x4};
//		Clause c3 = new Clause(bc3);
//		sat.add(c3);
//		
//		BooleanVar[] bc4 = {nx2,x3};
//		Clause c4 = new Clause(bc4);
//		sat.add(c4);
//		
//		BooleanVar[] bc5 = {x1,x4};
//		Clause c5 = new Clause(bc5);
//		sat.add(c5);
//		
//		boolean[] assign = new boolean[4];
//		DPLLObject obj = new DPLLObject(sat,assign);
//		
//		System.out.println(obj.getFormula());
//		for (int i =0; i< obj.getTruthAssignmentSoFar().length;i++) {
//			System.out.println(obj.getTruthAssignmentSoFar()[i]);
//		}
//		
//		DPLL test = new DPLL(obj);
//		obj = test.unitPropagation(obj);
//		
//		System.out.println(obj.getFormula());
//		for (int i =0; i< obj.getTruthAssignmentSoFar().length;i++) {
//			System.out.println(obj.getTruthAssignmentSoFar()[i]);
//		}
//		
//		System.out.println(obj.getFormula().size());
//		
//		System.out.println("-----------------------");
//		System.out.println("DPLL Test");
//		//test DPLL example from book pg. 156-158
//		
//		x1 = new BooleanVar(0,false);
//		nx1 = new BooleanVar(0,true);
//		x2 = new BooleanVar(1,false);
//		nx2 = new BooleanVar(1,true);
//		x3 = new BooleanVar(2,false);
//		nx3 = new BooleanVar(2,true);
//		x4 = new BooleanVar(3,false);
//		BooleanVar nx4 = new BooleanVar(3,true);
//		
//		sat.clear();
//		
//		BooleanVar[] bbc1 = {nx1,x2};
//		Clause cc1 = new Clause(bbc1);
//		sat.add(cc1);
//		
//		BooleanVar[] bbc2 = {nx1,x3,nx4};
//		Clause cc2 = new Clause(bbc2);
//		sat.add(cc2);
//		
//		BooleanVar[] bbc3 = {nx2,nx3};
//		Clause cc3 = new Clause(bbc3);
//		sat.add(cc3);
//		
//		BooleanVar[] bbc4 = {x4,x3};
//		Clause cc4 = new Clause(bbc4);
//		sat.add(cc4);
//		
//		assign = new boolean[4];
//		obj = new DPLLObject(sat,assign);
//		System.out.println(obj.getFormula());
//		boolean[] answer = test.solve(obj);
//		
//		for (int i =0; i<answer.length;i++) {
//			System.out.println(answer[i]);
//		}
		
	}

}
