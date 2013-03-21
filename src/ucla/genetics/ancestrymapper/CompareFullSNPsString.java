package ucla.genetics.ancestrymapper;

import java.util.Set;

import ucla.genetic.utils.FileIO;

public class CompareFullSNPsString {

	public static void compareFullSNPsString(
			String[] differentIndividualSameChromosome,
			Set<Integer> locationOfDifferenceInSNPs, String outputFileName) {

		int numberOfPeople = differentIndividualSameChromosome.length;

		StringBuilder matrixWithAllPossibleMatchProbs = new StringBuilder();
		// Find in all possible pairs

		for (int personIndexOne = 0; personIndexOne < numberOfPeople; personIndexOne++) {

			for (int personIndexTwo = 0; personIndexTwo < numberOfPeople; personIndexTwo++) {
				char[] firstFileAsCharArray = differentIndividualSameChromosome[personIndexOne]
						.toCharArray();
				char[] secondFileAsCharArray = differentIndividualSameChromosome[personIndexTwo]
						.toCharArray();
				matrixWithAllPossibleMatchProbs.append(
						CommonDNAFinder.getCommonDNAasPercentage(
								firstFileAsCharArray, secondFileAsCharArray,
								locationOfDifferenceInSNPs)).append(",");
			}
			matrixWithAllPossibleMatchProbs.append("\n");

		}

		System.out.println("Writing "
				+ matrixWithAllPossibleMatchProbs.length()
				+ " probabilities to file");

		FileIO.writeStringToFile(GeneticAncestryEstimator.COMMON_PATH
				+ outputFileName + ".csv",
				matrixWithAllPossibleMatchProbs.toString());

		System.out.println(GeneticAncestryEstimator.COMMON_PATH
				+ outputFileName + ".csv" + " is written successfully");
	}
}
