package algos;

public class BooleanVar {
	
	private boolean assignment;
	public int varNum;
	private boolean isNegated;
	
	public BooleanVar(int varNum, boolean isNegated) {
		this.varNum = varNum;
		this.isNegated = isNegated;
	}
	
	public void setAssignment(boolean assign) {
		this.assignment = assign;
	}
	
	public boolean getAssignment() {
		if (this.isNegated) {
			return !this.assignment;
		}
		else {
			return this.assignment;
		}
	}
	
	public boolean getIsNegated() {
		return this.isNegated;
	}
	
	public String toString() {
		String s = "";
		if (this.isNegated) {
			s+="!";
		}
		s+="X"+this.varNum;
		return s;
	}
	
}
