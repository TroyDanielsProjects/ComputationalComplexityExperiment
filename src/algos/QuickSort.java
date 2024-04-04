package algos;
import java.util.ArrayList;
import java.util.Random;
//Created by Troy Daniels

public class QuickSort {
	
	// array to be passed in and then sorted
	private Edges[] arrayToBeSorted;
	//to keep track of performance
	private int numberOfCompares;
	
	public QuickSort(ArrayList<Edges> arr) {
		// TODO Auto-generated constructor stub
		Edges[] list = new Edges[arr.size()];
		for (int i = 0; i < arr.size();i++) {
			list[i] = arr.get(i);
		}
		this.arrayToBeSorted = list;
	}
	// getter method for the array
	public Edges[] getArray() {
		return arrayToBeSorted;
	}
	// getter Method for the number of compares
	public int getNumberOfCompares() {
		return numberOfCompares;
	}
	// method to sort array. Starter for the recursion
	public Edges[] sort() {
		recursiveCallSort(0, this.arrayToBeSorted.length-1);
		return arrayToBeSorted;
	}
	
	
	// actual recursive calls to do the sorting
	private void recursiveCallSort(int start, int end) {
		//base Case (when the call goes down to 1 or 0 elements)
		numberOfCompares++;
		if (start>=end) {
		}
		// when to keep recursing.
		else {
			// This uses a random index to compare values to to keep a consistant running time even when the list is pre_sorted
			Random rand = new Random();
			int randIndex = (rand.nextInt(end-start+1))+start;
			// switch the random index and the last index ( could be first but I chose last)
			Edges piviotNumber = this.arrayToBeSorted[randIndex];
			this.arrayToBeSorted[randIndex] = this.arrayToBeSorted[end];
			this.arrayToBeSorted[end] = piviotNumber;
			// indexes to keep track of what has been compared and is in place and to increment/decrement (i and J)
			int smallerThanIndex = start;
			int largerThanIndex = end-1;
			// go through each element in the sublist and compare it to the last element 
			while (smallerThanIndex < largerThanIndex) {
				numberOfCompares++;
				// the current element is smaller than the last element just increment forward
				if (this.arrayToBeSorted[smallerThanIndex].weight<=piviotNumber.weight) {
					numberOfCompares++;
					smallerThanIndex++;
				} 
				// Otherwise the number in the two indexes (i and J) need to be switch and j decremented
				else {
					Edges numberToMove = this.arrayToBeSorted[smallerThanIndex];
					numberOfCompares++;
					this.arrayToBeSorted[smallerThanIndex] = this.arrayToBeSorted[largerThanIndex];
					this.arrayToBeSorted[largerThanIndex]= numberToMove;
					largerThanIndex--;
				}
			}
			// after all elements in sublist is compared. Last element must be put into its right place and two more recursibve calls for each sub
			//list to the left and right of the piviot
			//if the number i is currently on is smaller than the last element needs to switch w the number right of i
			if (this.arrayToBeSorted[smallerThanIndex].weight<=piviotNumber.weight) {
				numberOfCompares++;
				this.arrayToBeSorted[end] = this.arrayToBeSorted[smallerThanIndex+1];
				this.arrayToBeSorted[smallerThanIndex+1] = piviotNumber;
				recursiveCallSort(start, smallerThanIndex);
				recursiveCallSort(smallerThanIndex+2, end);
			}
			// if the number i is currently on is larger than the last element just switches with i
			else {
				numberOfCompares++;
				this.arrayToBeSorted[end] = this.arrayToBeSorted[smallerThanIndex];
				this.arrayToBeSorted[smallerThanIndex] = piviotNumber;
				recursiveCallSort(start, smallerThanIndex-1);
				recursiveCallSort(smallerThanIndex+1, end);
			}
		}
		
	}
	// to print out the string
	public String toString() {
		String stringToReturn = "";
		for (int i = 0; i<this.arrayToBeSorted.length;i++) {
			stringToReturn+=this.arrayToBeSorted[i]+",";
		}
		return "The list is: " + stringToReturn.substring(0, stringToReturn.length()-1) +" and the number of compares are "+numberOfCompares;
	}
	
//public static void main(String[] args) {
//		int[] testlist = {2,4,6,4,2,5,7,8,6,4,3,2,4,5,6,7,8,8,6,4,3,14,14,24,54};
//		QuickSort test = new QuickSort(testlist);
//		test.sort();
//		System.out.println(test);
//	}
	
	
}
