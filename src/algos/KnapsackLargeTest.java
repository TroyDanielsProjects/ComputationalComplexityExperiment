package algos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class KnapsackLargeTest {
	
public void runTest(int maxSize) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("./KnapsackLargeResults", false));
			
			
			int noSolutions = 0;
			int MinCostZeroOneErrors = 0;
			
			double aveZeroOneTime = 0;
			double aveMinCostTime = 0;
			double aveTwoApproxTime = 0;
			double aveFPTASTimeLowE = 0;
			double aveFPTASTimeHighE = 0;
			
			double bestZeroOneTime = Double.POSITIVE_INFINITY;
			double bestMinCostTime = Double.POSITIVE_INFINITY;
			double bestTwoApproxTime = Double.POSITIVE_INFINITY;
			double bestFPTASTimeLowE = Double.POSITIVE_INFINITY;
			double bestFPTASTimeHighE = Double.POSITIVE_INFINITY;
			
			double worstZeroOneTime = 0;
			double worstMinCostTime = 0;
			double worstTwoApproxTime = 0;
			double worstFPTASTimeLowE = 0;
			double worstFPTASTimeHighE = 0;
			
			double aveTwoApproxOptRatio = 0;
			double aveFPTASOptRatioLowE = 0;
			double aveFPTASOptRatioHighE = 0;
			
			double bestTwoApproxRatio = 0;
			double bestFPTASRatioLowE = 0;
			double bestFPTASRatioHighE = 0;
			
			double worstTwoApproxRatio = 1;
			double worstFPTASRatioLowE = 1;
			double worstFPTASRatioHighE = 1;
			
			ArrayList<Double> medianZeroOneTime = new ArrayList<Double>();
			ArrayList<Double> medianMinCostTime = new ArrayList<Double>();
			ArrayList<Double> medianTwoApproxTime = new ArrayList<Double>();
			ArrayList<Double> medianFPTASLowTime = new ArrayList<Double>();
			ArrayList<Double> medianFPTASHighTime = new ArrayList<Double>();
			
			ArrayList<Double> medianTwoApproxRatio = new ArrayList<Double>();
			ArrayList<Double> medianFPTASLowRatio = new ArrayList<Double>();
			ArrayList<Double> medianFPTASHighRatio = new ArrayList<Double>();
			
			for (int j = 0; j<100; j++) {
				KnapsackZeroOne zeroOne = new KnapsackZeroOne();
				MinCostKnapsack minCost = new MinCostKnapsack();
				Knapsack2Approx twoApprox = new Knapsack2Approx();
				FPTASKnapsack fptas = new FPTASKnapsack();
				FPTASKnapsack fptasHigh = new FPTASKnapsack();
				KnapsackProblemGen factory = new KnapsackProblemGen();
				Random rand = new Random();
				int maxVal = maxSize*3;
				int[] values = factory.generateValues(maxSize, maxVal);
				int[] weights = factory.generateWeights(maxSize, maxVal);
				int capacity = factory.randCapacity(maxSize,maxVal);
				
				
				double zeroOneStart = System.currentTimeMillis();
				double zeroOneSolution = zeroOne.solve(weights, values, capacity);
				double zeroOneEnd = System.currentTimeMillis();
				medianZeroOneTime.add(zeroOneEnd-zeroOneStart);
				
				double minCostStart = System.currentTimeMillis();
				double minCostSolution = minCost.solve(weights, values, capacity);
				double minCostEnd = System.currentTimeMillis();
				medianMinCostTime.add(minCostEnd-minCostStart);
				
				double twoApproxStart = System.currentTimeMillis();
				double twoApproxSolution = twoApprox.solve(weights, values, capacity);
				medianTwoApproxRatio.add(twoApproxSolution/zeroOneSolution);
				double twoApproxEnd = System.currentTimeMillis();
				medianTwoApproxTime.add(twoApproxEnd-twoApproxStart);
				
				double fptasStartLowE = System.currentTimeMillis();
				double fptasSolutionLowE = fptas.solve(weights, values, capacity, 0.4);
				medianFPTASLowRatio.add(fptasSolutionLowE/zeroOneSolution);
				double fptasEndLowE = System.currentTimeMillis();
				medianFPTASLowTime.add(fptasEndLowE-fptasStartLowE);
				
				double fptasStartHighE = System.currentTimeMillis();
				double fptasSolutionHighE = fptasHigh.solve(weights, values, capacity, 0.8);
				medianFPTASHighRatio.add(fptasSolutionHighE/zeroOneSolution);
				double fptasEndHighE = System.currentTimeMillis();
				medianFPTASHighTime.add(fptasEndHighE-fptasStartHighE);
				
				aveZeroOneTime += zeroOneEnd - zeroOneStart;
				aveMinCostTime += minCostEnd - minCostStart;
				aveTwoApproxTime += twoApproxEnd - twoApproxStart;
				aveFPTASTimeLowE += fptasEndLowE - fptasStartLowE;
				aveFPTASTimeHighE += fptasEndHighE - fptasStartHighE;
				
				bestZeroOneTime = Math.min(bestZeroOneTime, zeroOneEnd-zeroOneStart);
				bestMinCostTime = Math.min(bestMinCostTime, minCostEnd-minCostStart);
				bestTwoApproxTime = Math.min(bestTwoApproxTime, twoApproxEnd - twoApproxStart);
				bestFPTASTimeHighE = Math.min(bestFPTASTimeHighE, fptasEndHighE - fptasStartHighE);
				bestFPTASTimeLowE = Math.min(bestFPTASTimeLowE, fptasEndLowE - fptasStartLowE);
				
				worstZeroOneTime = Math.max(worstZeroOneTime, zeroOneEnd-zeroOneStart);
				worstMinCostTime = Math.max(worstMinCostTime, minCostEnd-minCostStart);
				worstTwoApproxTime = Math.max(worstTwoApproxTime, twoApproxEnd - twoApproxStart);
				worstFPTASTimeHighE = Math.max(worstFPTASTimeHighE, fptasEndHighE - fptasStartHighE);
				worstFPTASTimeLowE = Math.max(worstFPTASTimeLowE, fptasEndLowE - fptasStartLowE);
				
				
				
				
				if (zeroOneSolution != 0) {
					aveTwoApproxOptRatio += twoApproxSolution/zeroOneSolution;
					aveFPTASOptRatioLowE += fptasSolutionLowE/zeroOneSolution;
					aveFPTASOptRatioHighE += fptasSolutionHighE/zeroOneSolution;
					
					
					bestTwoApproxRatio = Math.max(bestTwoApproxRatio, twoApproxSolution/zeroOneSolution);
					bestFPTASRatioHighE = Math.max(bestFPTASRatioHighE, fptasSolutionHighE/zeroOneSolution);
					bestFPTASRatioLowE = Math.max(bestFPTASRatioLowE, fptasSolutionLowE - zeroOneSolution);
					
					worstTwoApproxRatio = Math.min(worstTwoApproxRatio, twoApproxSolution/zeroOneSolution);
					worstFPTASRatioHighE = Math.min(worstFPTASRatioHighE, fptasSolutionHighE/zeroOneSolution);
					worstFPTASRatioLowE = Math.min(worstFPTASRatioLowE, fptasSolutionLowE/zeroOneSolution);
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
			
			medianZeroOneTime.sort(null);
			medianMinCostTime.sort(null);
			medianTwoApproxTime.sort(null);
			medianFPTASHighTime.sort(null);
			medianFPTASLowTime.sort(null);
			
			medianTwoApproxRatio.sort(null);
			medianFPTASHighRatio.sort(null);
			medianFPTASLowRatio.sort(null);
			
			double medianZeroOneTimeAnswer = medianZeroOneTime.get(medianZeroOneTime.size()/2);
			double medianMinCostTimeAnswer = medianMinCostTime.get(medianMinCostTime.size()/2);
			double medianTwoApproxTimeAnswer = medianTwoApproxTime.get(medianTwoApproxTime.size()/2);
			double medianFPTASHighTimeAnswer = medianFPTASHighTime.get(medianFPTASHighTime.size()/2);
			double medianFPTASLowTimeAnswer = medianFPTASLowTime.get(medianFPTASLowTime.size()/2);
			
			double medianTwoApproxRatioAnswer = medianTwoApproxRatio.get(medianTwoApproxRatio.size()/2);
			double medianFPTASHighRatioAnswer = medianFPTASHighRatio.get(medianFPTASHighRatio.size()/2);
			double medianFPTASLowRatioAnswer = medianFPTASLowRatio.get(medianFPTASLowRatio.size()/2);
			
			aveZeroOneTime/=100;
			aveMinCostTime/=100;
			aveTwoApproxTime/=100;
			aveFPTASTimeLowE/=100;
			aveFPTASTimeHighE/=100;
			
			aveTwoApproxOptRatio/=100-noSolutions;
			aveFPTASOptRatioLowE/=100-noSolutions;
			aveFPTASOptRatioHighE/=100-noSolutions;
			
			writer.write("Test on knapsack with size: "+maxSize+"\n\n");
			writer.write("The number of differnt solutions between minCost and zeroOne is "+MinCostZeroOneErrors +"\n");
			writer.write("The average Zero One time is: "+aveZeroOneTime+ " , the best is : "+bestZeroOneTime+ " , the worst is:  "+worstZeroOneTime+" , the median is "+ medianZeroOneTimeAnswer+ "\n");
			writer.write("The average min cost time is: "+aveMinCostTime+ " , the best is : "+bestMinCostTime+ " , the worst is:  "+worstMinCostTime+ " , the median is "+ medianMinCostTimeAnswer+  "\n");
			writer.write("The average Two approx time is: "+aveTwoApproxTime + " , the best is : "+bestTwoApproxTime+ " , the worst is:  "+worstTwoApproxTime+" , the median is "+ medianTwoApproxTimeAnswer+  " and the average ratio of Two Approx to opt is: "+aveTwoApproxOptRatio+ " , the best is : "+bestTwoApproxRatio+ " , the worst is:  "+worstTwoApproxRatio+" , the median is "+ medianTwoApproxRatioAnswer+"\n");
			writer.write("The average FPTAS low time is: "+aveFPTASTimeLowE + " , the best is : "+bestFPTASTimeLowE+ " , the worst is:  "+worstFPTASTimeLowE+" , the median is "+  medianFPTASLowTimeAnswer+  " and the average ratio of FPTAS low to opt is: "+aveFPTASOptRatioLowE+  " , the best is : "+bestFPTASRatioLowE+ " , the worst is:  "+worstFPTASRatioLowE+" , the median is "+ medianFPTASLowRatioAnswer+ "\n");
			writer.write("The average FPTAS high time is: "+aveFPTASTimeHighE+ " , the best is : "+bestFPTASTimeHighE+ " , the worst is:  "+worstFPTASTimeHighE+" , the median is "+ medianFPTASHighTimeAnswer+  " and the average ratio of FPTAS high to opt is: "+aveFPTASOptRatioHighE+ " , the best is : "+bestFPTASRatioHighE+ " , the worst is:  "+worstFPTASRatioHighE+" , the median is "+ medianFPTASHighRatioAnswer+  "\n\n");
	
			writer.close();
			System.out.println("Done!");
		}
		catch(Exception e ) {
			System.out.println(e);
		}
		
	}
	
	public static void main(String[] args) {
		KnapsackLargeTest test = new KnapsackLargeTest();
		test.runTest(350);
	}

}
