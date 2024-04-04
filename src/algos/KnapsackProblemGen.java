package algos;

import java.util.Random;

public class KnapsackProblemGen {
	
	
	public int[] generateValues(int size, int maxVal) {
		Random rand = new Random();
		int[] randomlist = new int[size];
		for (int i = 0; i< randomlist.length;i++) {
			randomlist[i] = rand.nextInt(maxVal)+1;
		}
		return randomlist;
	}
	
	public int[] generateWeights(int size, int maxVal) {
		Random rand = new Random();
		int[] randomlist = new int[size];
		for (int i = 0; i< randomlist.length;i++) {
			randomlist[i] = rand.nextInt(maxVal)+1;
		}
		return randomlist;
	}
	
	public int[] generateWeights(int size,int[] values) {
		Random rand = new Random();
		int[] randomlist = new int[size];
		for (int i = 0; i< randomlist.length;i++) {
			randomlist[i] = ( (int) rand.nextDouble() ) +1;
		}
		return randomlist;
	}
	
	public int randCapacity(int size, int maxCapacity) {
		Random rand = new Random();
		return rand.nextInt(maxCapacity)*size+1;
	}
	
	
	public static void main(String[] args) {
		KnapsackProblemGen factory = new KnapsackProblemGen();
		int[] values = factory.generateValues(20, 20);
		int[] weights = factory.generateWeights(20, 20);
		int capacity = factory.randCapacity(20,20);
		KnapsackZeroOne zeroOne = new KnapsackZeroOne();
		MinCostKnapsack minCost = new MinCostKnapsack();
		double zeroOneSolution = zeroOne.solve(weights, values, capacity);
		double minCostSolution = minCost.solve(weights, values, capacity);
		if (zeroOneSolution!=minCostSolution) {
			System.out.println("Error");
			System.out.println(zeroOneSolution);
			System.out.println(minCostSolution+"\n");
			for(int i = 0; i< values.length;i++) {
				System.out.print(values[i]+",");
			}
			System.out.println();
			for(int i = 0; i< values.length;i++) {
				System.out.print(weights[i]+",");
			}
			System.out.println();
			System.out.println(capacity);
		}
	}
}
