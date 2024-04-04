package algos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class RunningSat {
	
	public void runTests(int maxSize, double clauseVarRatio, boolean newFile) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("./SAT_Results", newFile));
			
			writer.write("\n\nThe clause to variable Ratio for this set of tests is "+ clauseVarRatio+"\n");
			
			for (int i = 100; i <= maxSize; i+=100) {
				
				int DPLLCouldntSolve = 0;
				
				double aveDPLLSatifiedClausesRatio = 0;
				double aveGSATSatifiedClausesRatio = 0;
				double aveRandomRoundingSatifiedClausesRatio = 0;
				double aveSevenApproxRatio = 0;
				
				double aveDPLLRuntime = 0;
				double aveGSATRuntime = 0;
				double aveRandomRoundingRuntime = 0;
				double aveApproxSevenRuntime = 0;
				
				double bestDPLLTime = Double.POSITIVE_INFINITY;
				double bestGSatTime = Double.POSITIVE_INFINITY;
				double bestRandomRoundingTime = Double.POSITIVE_INFINITY;
				double bestSevenApproxTime = Double.POSITIVE_INFINITY;
				
				double worstDPLLTime = 0;
				double worstGSatTime = 0;
				double worstRandomRoundingTime = 0;
				double worstSevenApproxTime = 0;
				
				double bestDPLLRatio = 0;
				double bestGSatRatio = 0;
				double bestRandomRoundingRatio = 0;
				double bestSevenApproxRatio = 0;
				
				double worstDPLLRatio = 1;
				double worstGSatRatio = 1;
				double worstRandomRoundingRatio = 1;
				double worstSevenApproxRatio = 1;
				
				ArrayList<Double> medianDPLLTime = new ArrayList<Double>();
				ArrayList<Double> medianGSatTime = new ArrayList<Double>();
				ArrayList<Double> medianRandomRoundingTime = new ArrayList<Double>();
				ArrayList<Double> medianSevenApproxTime = new ArrayList<Double>();
				
				ArrayList<Double> medianGSatRatio = new ArrayList<Double>();
				ArrayList<Double> medianRandomRoundingRatio = new ArrayList<Double>();
				ArrayList<Double> medianSevenApproxRatio = new ArrayList<Double>();
				
				for (int j = 0 ; j< 100 ; j++) {
					
					int numOfClauses = (int) (i*clauseVarRatio);
					AllSat sat = new ThreeSatInstance( numOfClauses ,i );
					
					DPLL dpll = new DPLL(sat);
					GSAT gsat = new GSAT(sat);
					RandomizedRounding3SAT randomRound = new RandomizedRounding3SAT(sat);
					SevenEighthsApproxSAT sevenEighths = new SevenEighthsApproxSAT(sat);
					
					
					double DPLLStart = System.currentTimeMillis();
					double DPLLClausesSatisfied = dpll.solve();
					double DPLLEnd = System.currentTimeMillis();
					
					double GSATStart = System.currentTimeMillis();
					double GSATClausesSatisfied = gsat.solve(10);
					double GSATEnd = System.currentTimeMillis();
					
					double randomRoundingStart = System.currentTimeMillis();
					double randomRoundingClausesSatisfied = randomRound.solve();
					double randomRoundingEnd = System.currentTimeMillis();
					
					double sevenEighthsStart = System.currentTimeMillis();
					double sevenEighthsClausesSatisfied = sevenEighths.solve();
					double sevenEighthsEnd = System.currentTimeMillis();
					
					if (DPLLClausesSatisfied == 0 ) {
						DPLLCouldntSolve++;
					}
					else {
						aveDPLLSatifiedClausesRatio += DPLLClausesSatisfied/numOfClauses;
						bestDPLLRatio = Math.max(bestDPLLRatio, DPLLClausesSatisfied/numOfClauses);
						worstDPLLRatio = Math.min(worstDPLLRatio, DPLLClausesSatisfied/numOfClauses);
					}
					aveGSATSatifiedClausesRatio += GSATClausesSatisfied/numOfClauses;
					aveRandomRoundingSatifiedClausesRatio += randomRoundingClausesSatisfied/numOfClauses;
					aveSevenApproxRatio += sevenEighthsClausesSatisfied/numOfClauses;
					
					aveDPLLRuntime += DPLLEnd - DPLLStart;
					aveGSATRuntime += GSATEnd - GSATStart;
					aveRandomRoundingRuntime += randomRoundingEnd - randomRoundingStart;
					aveApproxSevenRuntime += sevenEighthsEnd - sevenEighthsStart;
					
					
					bestDPLLTime = Math.min(bestDPLLTime,  DPLLEnd - DPLLStart);
					bestGSatTime = Math.min(bestGSatTime, GSATEnd - GSATStart);
					bestRandomRoundingTime = Math.min(bestRandomRoundingTime, randomRoundingEnd - randomRoundingStart);
					bestSevenApproxTime = Math.min(bestSevenApproxTime, sevenEighthsEnd - sevenEighthsStart);
					
					worstDPLLTime = Math.max(worstDPLLTime,  DPLLEnd - DPLLStart);
					worstGSatTime = Math.max(worstGSatTime, GSATEnd - GSATStart);
					worstRandomRoundingTime = Math.max(worstRandomRoundingTime, randomRoundingEnd - randomRoundingStart);
					worstSevenApproxTime = Math.max(worstSevenApproxTime, sevenEighthsEnd - sevenEighthsStart);
					
					
					bestGSatRatio = Math.max(bestGSatRatio, GSATClausesSatisfied/numOfClauses);
					bestRandomRoundingRatio = Math.max(bestRandomRoundingRatio, randomRoundingClausesSatisfied/numOfClauses);
					bestSevenApproxRatio = Math.max(bestSevenApproxRatio, sevenEighthsClausesSatisfied/numOfClauses);
					
					worstGSatRatio = Math.min(worstGSatRatio, GSATClausesSatisfied/numOfClauses);
					worstRandomRoundingRatio = Math.min(worstRandomRoundingRatio, randomRoundingClausesSatisfied/numOfClauses);
					worstSevenApproxRatio = Math.min(worstSevenApproxRatio, sevenEighthsClausesSatisfied/numOfClauses);
					
					medianDPLLTime.add(DPLLEnd - DPLLStart);
					medianGSatTime.add(GSATEnd - GSATStart);
					medianRandomRoundingTime.add(randomRoundingEnd - randomRoundingStart);
					medianSevenApproxTime.add(sevenEighthsEnd - sevenEighthsStart);
					
					medianGSatRatio.add(GSATClausesSatisfied/numOfClauses);
					medianRandomRoundingRatio.add(randomRoundingClausesSatisfied/numOfClauses);
					medianSevenApproxRatio.add(sevenEighthsClausesSatisfied/numOfClauses);
				}
				
				medianDPLLTime.sort(null);
				medianGSatTime.sort(null);
				medianRandomRoundingTime.sort(null);
				medianSevenApproxTime.sort(null);
				
				medianGSatRatio.sort(null);
				medianRandomRoundingRatio.sort(null);
				medianSevenApproxRatio.sort(null);
				
				double medianDPLLTimeAnswer = medianDPLLTime.get(medianDPLLTime.size()/2);
				double medianGSatTimeAnswer = medianGSatTime.get(medianGSatTime.size()/2);
				double medianRandomRoundingTimeAnswer = medianRandomRoundingTime.get(medianRandomRoundingTime.size()/2);
				double medianSevenApproxTimeAnswer = medianSevenApproxTime.get(medianSevenApproxTime.size()/2);
				
				double medianGSatRatioAnswer = medianGSatRatio.get(medianGSatRatio.size()/2);
				double medianRandomRoundingRatioAnswer = medianRandomRoundingRatio.get(medianRandomRoundingRatio.size()/2);
				double medianSevenApproxRatioAnswer = medianSevenApproxRatio.get(medianSevenApproxRatio.size()/2);
				
				aveDPLLSatifiedClausesRatio/=100-DPLLCouldntSolve;
				aveGSATSatifiedClausesRatio/=100;
				aveRandomRoundingSatifiedClausesRatio/=100;
				aveSevenApproxRatio/=100;
				
				aveDPLLRuntime/=100;
				aveGSATRuntime/=100;
				aveRandomRoundingRuntime/=100;
				aveApproxSevenRuntime/=100;
				
				writer.write("\nTest with "+i+" number of variables\n");
				writer.write("DPLL had a satisfied clause ratio of "+ aveDPLLSatifiedClausesRatio + " and a average running time of " +aveDPLLRuntime +", best time of: "+bestDPLLTime+", worst time of: "+worstDPLLTime+", median runtime of: "+medianDPLLTimeAnswer+" and couln't solve "+DPLLCouldntSolve+" instances\n");
				writer.write("GSAT had a satisfied clause ratio of "+ aveGSATSatifiedClausesRatio + ", best ratio of: "+bestGSatRatio+", worst ratio of: "+worstGSatRatio+", median ratio of: "+medianGSatRatioAnswer+" and a average running time of " +aveGSATRuntime+", best time of: "+bestGSatTime+", worst time of: "+worstGSatTime+", median runtime of: "+medianGSatTimeAnswer+"\n");
				writer.write("Randomized rounding had a satisfied clause ratio of "+ aveRandomRoundingSatifiedClausesRatio  +", best ratio of: "+bestRandomRoundingRatio+", worst ratio of: "+worstRandomRoundingRatio+", median ratio of: "+medianRandomRoundingRatioAnswer+ " and a average running time of " +aveRandomRoundingRuntime+", best time of: "+bestRandomRoundingTime+", worst time of: "+worstRandomRoundingTime+", median runtime of: "+medianRandomRoundingTimeAnswer+"\n");
				writer.write("Seven Eighths Approx had a satisfied clause ratio of "+ aveSevenApproxRatio +", best ratio of: "+bestSevenApproxRatio+", worst ratio of: "+worstSevenApproxRatio+", median ratio of: "+medianSevenApproxRatioAnswer+ " and a average running time of " +aveApproxSevenRuntime+", best time of: "+bestSevenApproxTime+", worst time of: "+worstSevenApproxTime+", median runtime of: "+medianSevenApproxTimeAnswer+"\n");
				System.out.println("Test size "+ i+ " has finished");
			}
			writer.close();
			System.out.println("Done!");
			
		}
		catch(Exception e ) {
			System.out.println(e);
		}
		
	}
	
	public static void main(String[] args) {
		RunningSat test = new RunningSat();
		test.runTests(40, 0.5, false);
		test.runTests(400, 1, true);
		test.runTests(80, 2, true);
		test.runTests(80, 4.26, true);
		test.runTests(80, 6, true);
	}

}
