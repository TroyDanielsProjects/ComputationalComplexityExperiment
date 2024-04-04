package algos;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;

public class RunningTSP {
	
	// actual data followed by optdata...
	public void runTests(String[] files) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("./TSP_Results", false));
			TSP2approx twoApprox = new TSP2approx();
			TSPProblemGen problemGen = new TSPProblemGen();
			
			for (int i =0; i < files.length;i+=2) {
				double[][] matrix = problemGen.createAdjMatrix(files[i]);
				double optAnswer = problemGen.getOptimalSolution(files[i+1]);
				
				double aveRatio = 0;
				double bestRatio = Double.POSITIVE_INFINITY;
				double worstRatio = 1;
				ArrayList<Double> medianRatio = new ArrayList<Double>();
				
				double aveTime = 0;
				double bestTime = Double.POSITIVE_INFINITY;
				double worstTime = 0;
				ArrayList<Double> medianTime = new ArrayList<Double>();
				
				for (int j = 0; j<100;j++) {
					
					double startTime = System.currentTimeMillis();
					double twoApproxAnswer = twoApprox.solve(matrix);
					double endTime = System.currentTimeMillis();
					
					bestTime = Math.min(bestTime, endTime-startTime);
					worstTime = Math.max(worstTime, endTime-startTime);
					medianTime.add(endTime-startTime);
					aveTime+= endTime-startTime;
					
					bestRatio = Math.min(bestRatio, twoApproxAnswer/optAnswer);
					worstRatio = Math.max(worstRatio, twoApproxAnswer/optAnswer);
					medianRatio.add(twoApproxAnswer/optAnswer);
					aveRatio+= twoApproxAnswer/optAnswer;
					
				}
				
				System.out.println("Tests on file "+files[i]+" have finished" );
				
				aveRatio/= 100;
				aveTime/= 100;
				medianRatio.sort(null);
				medianTime.sort(null);
				double medianRatioAnswer = medianRatio.get(medianRatio.size()/2);
				double medianTimeAnswer = medianTime.get(medianTime.size()/2);
				
				
				writer.write("On the "+files[i]+" dataset\n\n");
				writer.write("The average ratio is: "+aveRatio +"\n");
				writer.write("The average time is: "+aveTime +"\n");
				writer.write("The best time is: "+bestTime + " and the worst time is: "+ worstTime +"\n");
				writer.write("The best ratio is: "+bestRatio + " and the worst ratio is: "+ worstRatio +"\n");
				writer.write("The median time is: "+medianTimeAnswer + " and the median ratio is: "+ medianRatioAnswer +"\n");
				
			}
			writer.close();
			System.out.println("Done!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	public void runTests(String[] files,String[] file) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("./TSP_Results2", false));
			TSP2approx twoApprox = new TSP2approx();
			TSPProblemGen problemGen = new TSPProblemGen();
			
			for (int i =0; i < files.length;i+=2) {
				double[][] matrix = problemGen.createAdjMatrix(files[i]);
				double optAnswer = problemGen.getOptimalSolution(files[i+1]);
				double total = 0;
				double startTime = System.currentTimeMillis();
				for (int j = 0; j<100;j++) {
					double twoApproxAnswer = twoApprox.solve(matrix);
					total+= twoApproxAnswer;
				}
				System.out.println("Tests on file "+files[i]+" have finished" );
				double endTime = System.currentTimeMillis();
				double aveAnswer = total / 100;
				double aveRatio = aveAnswer/optAnswer;
				writer.write("On the "+files[i]+" dataset\n\n");
				writer.write("The 2-approx average computed number is: "+ aveAnswer+ "\n");
				writer.write("The 2-approx average came within a factor of "+ aveRatio +" from the opt answer\n");
				writer.write("The average time each instance ran for is: " + (endTime-startTime)/100+ "\n\n");
			}
			double[][] matrix = problemGen.createAdjMatrix(file[0]);
			double optAnswer = problemGen.getOptimalSolution(file[1]);
			double total = 0;
			double startTime = System.currentTimeMillis();
			for (int j = 0; j<1;j++) {
				double twoApproxAnswer = twoApprox.solve(matrix);
				total+= twoApproxAnswer;
			}
			System.out.println("Tests on file "+file[0]+" have finished" );
			double endTime = System.currentTimeMillis();
			double aveAnswer = total/5;
			double aveRatio = aveAnswer/optAnswer;
			writer.write("On the "+file[0]+" dataset\n\n");
			writer.write("The 2-approx average computed number is: "+ aveAnswer+ "\n");
			writer.write("The 2-approx average came within a factor of "+ aveRatio +" from the opt answer\n");
			writer.write("The average time each instance ran for is: " + (endTime-startTime)/5+ "\n\n");
			writer.close();
			System.out.println("Done!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void main(String[] args) {
		RunningTSP test = new RunningTSP();
		String[] files = {"a280.tsp","a280.opt.tour","berlin52.tsp","berlin52.opt.tour","ch130.tsp","ch130.opt.tour"};
		String[] file = {"pla33810.tsp","pla33810.opt.tour"};
		test.runTests(files,file);
	}
}
