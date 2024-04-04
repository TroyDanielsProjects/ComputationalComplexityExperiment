package algos;

import java.util.ArrayList;

public class DPLLObject {
	
	private ArrayList<Clause> formula;
	private boolean[] truthAssignmentSoFar;
	
	public DPLLObject(ArrayList<Clause> formula, boolean[] truthAssignmentSoFar) {
		this.formula = formula;
		this.truthAssignmentSoFar = truthAssignmentSoFar;
	}
	
	public ArrayList<Clause> getFormula() {
		return formula;
	}

	public void setFormula(ArrayList<Clause> formula) {
		this.formula = formula;
	}

	public boolean[] getTruthAssignmentSoFar() {
		return truthAssignmentSoFar;
	}

	public void setTruthAssignmentSoFar(boolean[] truthAssignmentSoFar) {
		this.truthAssignmentSoFar = truthAssignmentSoFar;
	}


}
