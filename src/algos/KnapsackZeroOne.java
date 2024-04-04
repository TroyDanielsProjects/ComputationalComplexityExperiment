package algos;
// This class will be used to dynamically solve the knapsack problem 0-1
public class KnapsackZeroOne {

	
	// solves the problem dynamically
	public int solve(int[] arrOfWeights, int[] arrOfValues, int capacity) {
		
		// create a  bottom up matrix
		int rows = arrOfValues.length+1;
		int columns = capacity+1;
		int[][] bottomUpMatrix = new int[rows][columns];
		// base case is already taken care of, at row I+1 (I = number of items) all values are 0
		// and column 0 (when the weight is 0) is also all 0s
		//loop across each column starting at 1
		for (int currColumn=1;currColumn<columns;currColumn++) {
			// loop across each row starting at the last item (-2 since lists start at 0 and we dont want the last row)
			for (int currRow=rows-2;currRow>=0;currRow--) {
				// add to metric (this is really the only part to be worried about run time wise
//				this.runtime++;
				// check to see if the column(representing the weight left in kanpsack) is lower than weight of the item
				// and that taking the item is worth more than passing over it
				if (currColumn>=arrOfWeights[currRow] && arrOfValues[currRow]+bottomUpMatrix[currRow+1][currColumn-arrOfWeights[currRow]]>bottomUpMatrix[currRow+1][currColumn]) {
					// if so add that value to bottom up matrix
					bottomUpMatrix[currRow][currColumn] = arrOfValues[currRow]+bottomUpMatrix[currRow+1][currColumn-arrOfWeights[currRow]];
				}
				else {
					// otherwise it is just the value if you were to pass over it
					bottomUpMatrix[currRow][currColumn] = bottomUpMatrix[currRow+1][currColumn];
				}
			}
		}
		// answer should be in the top row and most right column (top right)
//		this.maxValue = bottomUpMatrix[0][capacity];
		return bottomUpMatrix[0][capacity];
	}
	
	public static void main(String[] args) {
		int[] weight = {1,2,3};
		int[] values = {6,10,12};
		KnapsackZeroOne test = new KnapsackZeroOne();
		System.out.println(test.solve(weight,values,5));
		System.out.println("------------------------");
		int[] weight2 = {1,2,3,4,5,6,7,8,9};
		int[] values2 = {6,10,12,14,19,21,26,27,30};
		System.out.println(test.solve(weight2,values2,15));
	}
}
