package algos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;


public class RunningKnapsack {
	
	
	public void runTest(int maxSize) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("./KnapsackResults2", false));
			
			for (int i =50 ; i <= maxSize; i+=50) {
				
				int noSolutions = 0;
				int MinCostZeroOneErrors = 0;
				
				double aveZeroOneTime = 0;
				double aveMinCostTime = 0;
				double aveTwoApproxTime = 0;
				double aveFPTASTimeLowE = 0;
				double aveFPTASTimeHighE = 0;
				
				
				double aveTwoApproxOptRatio = 0;
				double aveFPTASOptRatioLowE = 0;
				double aveFPTASOptRatioHighE = 0;
				
				
				for (int j = 0; j<100; j++) {
					KnapsackZeroOne zeroOne = new KnapsackZeroOne();
					MinCostKnapsack minCost = new MinCostKnapsack();
					Knapsack2Approx twoApprox = new Knapsack2Approx();
					FPTASKnapsack fptas = new FPTASKnapsack();
					FPTASKnapsack fptasHigh = new FPTASKnapsack();
					KnapsackProblemGen factory = new KnapsackProblemGen();
					Random rand = new Random();
					int maxVal = i*3;
					int[] values = factory.generateValues(i, maxVal);
					int[] weights = factory.generateWeights(i, maxVal);
					int capacity = factory.randCapacity(i,maxVal);
					
					
					double zeroOneStart = System.currentTimeMillis();
					double zeroOneSolution = zeroOne.solve(weights, values, capacity);
					double zeroOneEnd = System.currentTimeMillis();
					
					double minCostStart = System.currentTimeMillis();
					double minCostSolution = minCost.solve(weights, values, capacity);
					double minCostEnd = System.currentTimeMillis();
					
					double twoApproxStart = System.currentTimeMillis();
					double twoApproxSolution = twoApprox.solve(weights, values, capacity);
					double twoApproxEnd = System.currentTimeMillis();
					
					double fptasStartLowE = System.currentTimeMillis();
					double fptasSolutionLowE = fptas.solve(weights, values, capacity, 0.4);
					double fptasEndLowE = System.currentTimeMillis();
					
					double fptasStartHighE = System.currentTimeMillis();
					double fptasSolutionHighE = fptasHigh.solve(weights, values, capacity, 0.8);
					double fptasEndHighE = System.currentTimeMillis();
					
					aveZeroOneTime += zeroOneEnd - zeroOneStart;
					aveMinCostTime += minCostEnd - minCostStart;
					aveTwoApproxTime += twoApproxEnd - twoApproxStart;
					aveFPTASTimeLowE += fptasEndLowE - fptasStartLowE;
					aveFPTASTimeHighE += fptasEndHighE - fptasStartHighE;
					
					
					if (zeroOneSolution != 0) {
						aveTwoApproxOptRatio += twoApproxSolution/zeroOneSolution;
						aveFPTASOptRatioLowE += fptasSolutionLowE/zeroOneSolution;
						aveFPTASOptRatioHighE += fptasSolutionHighE/zeroOneSolution;
					}
					else if (zeroOneSolution !=  minCostSolution) {
						System.out.println("Min cost and Zero One had different answers!");
						MinCostZeroOneErrors++;
					}
					else {
						System.out.println("answer was 0");
						noSolutions++;
					}
				}
				
				
				aveZeroOneTime/=100;
				aveMinCostTime/=100;
				aveTwoApproxTime/=100;
				aveFPTASTimeLowE/=100;
				aveFPTASTimeHighE/=100;
				
				aveTwoApproxOptRatio/=100-noSolutions;
				aveFPTASOptRatioLowE/=100-noSolutions;
				aveFPTASOptRatioHighE/=100-noSolutions;
				
				writer.write("Test on knapsack with size: "+i+"\n\n");
				writer.write("The number of differnt solutions between minCost and zeroOne is "+MinCostZeroOneErrors +"\n");
				writer.write("The average Zero One time is: "+aveZeroOneTime+ "\n");
				writer.write("The average min cost time is: "+aveMinCostTime+ "\n");
				writer.write("The average Two approx time is: "+aveTwoApproxTime +" and the average ratio of Two Approx to opt is: "+aveTwoApproxOptRatio+"\n");
				writer.write("The average FPTAS low time is: "+aveFPTASTimeLowE + " and the average ratio of FPTAS low to opt is: "+aveFPTASOptRatioLowE+"\n");
				writer.write("The average FPTAS high time is: "+aveFPTASTimeHighE+ " and the average ratio of FPTAS high to opt is: "+aveFPTASOptRatioHighE+"\n\n");
				
				System.out.println( "Test size "+ i +" has finished");
	
			}
			writer.close();
			System.out.println("Done!");
		}
		catch(Exception e ) {
			System.out.println(e);
		}
		
	}
	
	public static void main(String[] args) {
		RunningKnapsack test = new RunningKnapsack();
		test.runTest(300);
	}

}
