package algos;

import java.util.ArrayList;

public class FPTASKnapsack {
	
	public int solve(int[] arrOfWeights, int[] arrOfValues, int capacity, double e) {
		double maxValue = 0;
		for (int i = 0; i < arrOfValues.length;i++) {
			if (arrOfValues[i]>maxValue) {
				maxValue = arrOfValues[i];
			}
		}
		double length = (double) arrOfValues.length;
		double F = (length /e)/maxValue;
		if (F>1) {
			F = 1;
		}
		//System.out.println(F);
		int scaledArrOfValues[] = new int[arrOfValues.length];
		//double F = arrOfValues.length * (1/e) / maxValue;
		for (int i = 0; i < arrOfValues.length;i++) {
			//arrOfValues[i] = (int) Math.floor( arrOfValues[i] * F);
			scaledArrOfValues[i] = (int) Math.floor(arrOfValues[i] * F);
			
		}
		MinCostKnapsack minCost = new MinCostKnapsack();
		int answer = minCost.solve(arrOfWeights, scaledArrOfValues, capacity);
		ArrayList<Integer> takenItems = minCost.take(answer);
		int solution = 0;
		for (int i = 0; i < takenItems.size();i++) {
			solution+= arrOfValues[takenItems.get(i)];
		}
		//System.out.println(solution);
		return solution;
	}
	
	public static void main(String[] args) {
//		int[] weight = {1,2,3,4,5,6,7,8,9};
//		int[] values = {6,10,12,14,19,21,26,27,30};
		MinCostKnapsack opt = new MinCostKnapsack();
		FPTASKnapsack test = new FPTASKnapsack();
//		int num = test.solve(weight,values,15,0.5);
		
//		int[] weight2 = {1,2,3,4,5,6,7,8,9};
//		int[] values2 = {6,10,12,14,19,21,26,27,30};
//		num = test.solve(weight2,values2,15,0.75);
//		
//		int[] weight3 = {1,2,3,4,5,6,7,8,9};
//		int[] values3 = {6,10,12,14,19,21,26,27,30};
//		num = test.solve(weight3,values3,15,0.9);
//		
//		int[] weight4 = {1,2,3,4,5,6,7,8,9};
//		int[] values4 = {6,10,12,14,19,21,26,27,30};
//		num = test.solve(weight4,values4,15,0.4);
//		
		int[] weight5 = {1,2,3,4,5,6,7,8,9};
		int[] values5 = {6,10,12,14,19,21,26,27,30};
		int num = test.solve(weight5,values5,15,0.3);
		System.out.println(num);
		int optnum = opt.solve(weight5, values5, 15);
		System.out.println(optnum);
	}

}
