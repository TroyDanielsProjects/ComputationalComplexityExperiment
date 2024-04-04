package algos;
import java.util.ArrayList;
public class Knapsack2Approx {
	
	public int solve(int[] arrOfWeights, int[] arrOfValues, int capacity) {
		ArrayList<Edges> answer = solveForList(arrOfWeights,arrOfValues,capacity);
		int total = 0;
		for (int i =0; i<answer.size();i++) {
			total += answer.get(i).vertexB;
		}
		return total;
	}
	
	// I used the quicksort I wrote for Kruskals. I am not using edges... look at below comment
	public ArrayList<Edges> solveForList(int[] arrOfWeights, int[] arrOfValues, int capacity) {
		
		ArrayList<Edges> density = new ArrayList<Edges>();
		for (int i = 0; i< arrOfWeights.length; i++) {
			// constructor takes (A,B,Weight) for edges and I will use this to keep track of (weights,values,density) in respective order
			// and sort by density
			density.add(new Edges(arrOfWeights[i],arrOfValues[i],arrOfValues[i]/arrOfWeights[i]));
			
		}
		// sort the array
		ArrayList<Edges> takenObjects = new ArrayList<Edges>();
		QuickSort quicksort = new QuickSort(density);
		Edges[] sortedArray = quicksort.sort();
		int usedCapacity = 0;
		int value = 0;
		Edges maxValObj = new Edges(0,0,0);
		int currentIndex = sortedArray.length-1; 
		// go through from largest until no capacity or empty
		while (currentIndex >= 0 && usedCapacity + sortedArray[currentIndex].vertexA<=capacity) {
			// add to G
			usedCapacity += sortedArray[currentIndex].vertexA;
			value += sortedArray[currentIndex].vertexB;
			takenObjects.add(sortedArray[currentIndex]);
			//  keep track of largest item
			if (sortedArray[currentIndex].vertexB > maxValObj.vertexB) {
				maxValObj = sortedArray[currentIndex];
			}
			currentIndex--;
		}
		// find largest item (picks up from above)
		while (currentIndex >= 0) {
			if (sortedArray[currentIndex].vertexB > maxValObj.vertexB) {
				maxValObj = sortedArray[currentIndex];
			}
			currentIndex--;
		}
		// return correct value
		if (value < maxValObj.vertexB && maxValObj.vertexA<=capacity) {
			takenObjects.clear();
			takenObjects.add(maxValObj);
			return takenObjects;
		}
		else {
			return takenObjects;
		}
		
	}
	
	public static void main(String[] args) {
		int[] weight = {17,19,7,2,9,20,1,10,8,1,1,13,19,11,6,11,20,6,15,3};
		int[] values = {8,8,18,13,3,2,6,1,10,18,9,8,18,18,14,20,14,10,20,4};
		Knapsack2Approx test = new Knapsack2Approx();
		MinCostKnapsack opt = new MinCostKnapsack();
		int optnum = opt.solve(weight, values, 6);
		System.out.println(optnum);
		int answer = test.solve(weight, values, 6);
		System.out.println("total value is "+answer);
	}

}
