package algos;

import java.util.ArrayList;

public class MinCostKnapsack {
	
	public double[][] solution;
	public boolean[][] take;
	public int[] arrOfWeights;
	public int[] arrOfValues;
	
	
	
	public double[][] solve(int[] arrOfWeights, int[] arrOfValues) {
		this.arrOfValues = arrOfValues;
		this.arrOfWeights = arrOfWeights;
		int maxValue = 0;
		for (int i = 0; i <arrOfValues.length; i++ ) {
			if (maxValue < arrOfValues[i]) {
				maxValue = arrOfValues[i];
			}
		}
		double[][] minCostArr = new double[arrOfValues.length][arrOfValues.length * maxValue];
		boolean[][] take = new boolean[arrOfValues.length][arrOfValues.length * maxValue];
		for (int i = 1; i<= arrOfValues[0];i++) {
			minCostArr[0][i] = arrOfWeights[0];
			take[0][i] = true;
		}
		for (int i =arrOfValues[0]+1 ; i<minCostArr[0].length ;i++) {
			minCostArr[0][i] = Double.POSITIVE_INFINITY;
			take[0][i] = false;
		}
			
		for (int i = 1; i < minCostArr.length;i++) {
			for (int j = 1; j < minCostArr[i].length; j++) {
				int nextT = Math.max(0, j-arrOfValues[i]);
				if (minCostArr[i-1][j]<= arrOfWeights[i] + minCostArr[i-1][nextT]) {
					minCostArr[i][j] = minCostArr[i-1][j];
					take[i][j] = false;
				}
				else {
					minCostArr[i][j] = arrOfWeights[i] + minCostArr[i-1][nextT];
					take[i][j] = true;
				}
			}
		}
//		String s = "";
//		for (int i =0; i<minCostArr.length;i++ ) {
//			for (int j = 0; j<minCostArr[0].length;j++ ) {
//				s+= minCostArr[i][j]+",";
//			}
//			s+= "\n";
//		}
//		System.out.println(s);
		this.take = take;
		this.solution = minCostArr;
		return minCostArr;
	}
	
	public int solve(int[] arrOfWeights, int[] arrOfValues, int capacity) {
		double[][] answerArr = solve(arrOfWeights,arrOfValues);
		for (int i = 0; i < answerArr[0].length;i++) {
			if (i != 0 && answerArr[answerArr.length-1][i]>capacity) {
				return (i-1);
			}
		}
		return 0;
	}
	
	public ArrayList<Integer> take(int maxVal) {
		int currVal = maxVal;
		ArrayList<Integer> takenItems = new ArrayList<Integer>();
		if (take != null) {
			for (int i =take.length-1; i >= 0;i--) {
				//System.out.println(currVal);
				if (take[i][currVal]) {
					takenItems.add(i);
					currVal -= this.arrOfValues[i];
				}
			}
		}
		return takenItems;
	}
	
	
	public static void main(String[] args) {
		int[] values = {8,8,18,13,3,2,6,1,10,18};
		int[] weight = {17,19,7,2,9,20,1,10,8,1};
		MinCostKnapsack test = new MinCostKnapsack();
		int num = test.solve(weight,values,1);
		System.out.println(num);
		test.take(num);
		System.out.println("-----------------------");
		int[] weight2 = {1,2,3,4,5,6,7,8,9};
		int[] values2 = {6,10,12,14,19,21,26,27,30};
		int num2 = test.solve(weight2,values2,5);
		System.out.println(num2);
		test.take(num2);
	}

}
