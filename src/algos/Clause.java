package algos;

import java.util.ArrayList;

public class Clause {
	
	private BooleanVar[] clause;
	private ArrayList<BooleanVar> clauseAL = new ArrayList<BooleanVar>();
	
	public Clause(BooleanVar[] clause) {
		this.clause = clause;
		for (int i = 0; i< clause.length;i++) {
			this.clauseAL.add(clause[i]);
		}
	}
	
	public Clause clone() {
		BooleanVar[] c = new BooleanVar[clauseAL.size()];
		for (int i =0 ; i < clauseAL.size(); i++) {
			c[i] = clauseAL.get(i);
		}
		return new Clause(c);
	}
	
	public Clause(BooleanVar b) {
		BooleanVar[] newB = {b};
		this.clause = newB;
		for (int i = 0; i< this.clause.length;i++) {
			this.clauseAL.add(this.clause[i]);
		}
	}
	
	public boolean isSatisfied() {
		boolean satisfy = false;
		for (BooleanVar var : clause) {
			if(var.getAssignment()) {
				satisfy = true;
			}
		}
		return satisfy;
	}
	
	public int clauseLength() {
		return this.clause.length;
	}
	
	public int clauseLengthAL() {
		return this.clauseAL.size();
	}
	
	public BooleanVar getVar(int index) {
		return clause[index];
	}
	
	public BooleanVar getVarAL(int index) {
		return clauseAL.get(index);
	}
	
	public void removeVarAL(int index) {
		this.clauseAL.remove(index);
	}
	
	public String toString() {
		String s = "( ";
		for (int i = 0;i< this.clauseLength();i++) {
			s+= getVarAL(i)+" ";
		}
		s+=" )";
		return s;
	}

}
