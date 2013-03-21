package ucla.genetics.ancestrymapper;

import java.io.File;
import java.util.ArrayList;

import ucla.genetics.parsers.HapMap3DataParser;
import ucla.genetics.pedigreegenerator.Individual;

public class HapmapThreeDataTrial {

	public static void main(String[] args) {
		// hapmap3_r2_b36_fwd.consensus.qc.poly.chr1_yri.phased

		// compareIndividualsOnAllChromosomes();
		mateTwoIndividualsAndProduceOffspring();
	}

	public static void mateTwoIndividualsAndProduceOffspring() {

		HapMap3DataParser
				.reset("hapmap3_r2_b36_fwd.consensus.qc.poly.chr1_yri.phased");

		Individual[] children = HapMap3DataParser.getChildren();

		Individual firstChild = children[0];
		Individual secondChild = children[1];

		Individual thirdChild = children[2];
		Individual fourthChild = children[3];

		firstChild.recombineDNA();
		secondChild.recombineDNA();
		thirdChild.recombineDNA();
		fourthChild.recombineDNA();

		Individual childOfFirstAndSecond = new Individual("Offspring_1");
		Individual childOfThirdAndFourth = new Individual("Offspring_2");

		childOfFirstAndSecond.setChromosome(1, firstChild
				.getRecombinedChromosome(1).toString(), true);
		childOfFirstAndSecond.setChromosome(1, secondChild
				.getRecombinedChromosome(1).toString(), false);

		childOfThirdAndFourth.setChromosome(1, thirdChild
				.getRecombinedChromosome(1).toString(), true);
		childOfThirdAndFourth.setChromosome(1, fourthChild
				.getRecombinedChromosome(1).toString(), false);

		compareTrioByCommonDNA(firstChild, secondChild, childOfFirstAndSecond);
		compareTrioByCommonDNA(thirdChild, fourthChild, childOfThirdAndFourth);

		childOfFirstAndSecond.recombineDNA();
		childOfThirdAndFourth.recombineDNA();

		Individual childOf_childOfFirstAndSecond_and_childOfThirdAndFourth = new Individual(
				"GrandChild_1");

		childOf_childOfFirstAndSecond_and_childOfThirdAndFourth.setChromosome(
				1, childOfFirstAndSecond.getRecombinedChromosome(1).toString(),
				true);
		childOf_childOfFirstAndSecond_and_childOfThirdAndFourth.setChromosome(
				1, childOfThirdAndFourth.getRecombinedChromosome(1).toString(),
				false);

		compareTrioByCommonDNA(childOfFirstAndSecond, childOfThirdAndFourth,
				childOf_childOfFirstAndSecond_and_childOfThirdAndFourth);

		ArrayList<Individual> listOfAllIdividuals = new ArrayList<Individual>();

		listOfAllIdividuals.add(firstChild);
		listOfAllIdividuals.add(secondChild);
		listOfAllIdividuals.add(thirdChild);
		listOfAllIdividuals.add(fourthChild);
		listOfAllIdividuals.add(childOfFirstAndSecond);
		listOfAllIdividuals.add(childOfThirdAndFourth);
		listOfAllIdividuals
				.add(childOf_childOfFirstAndSecond_and_childOfThirdAndFourth);

		compareAllIndividualsByCommonDNA(listOfAllIdividuals);

	}

	public static void compareAllIndividualsByCommonDNA(
			ArrayList<Individual> allIndividualsInArray) {

		for (int i = 0; i < allIndividualsInArray.size(); i++) {

			for (int j = i + 1; j < allIndividualsInArray.size(); j++) {

				System.out.println(CommonDNAFinder
						.getSimillarityPercentageBetweenTwoIndividuals(
								allIndividualsInArray.get(i),
								allIndividualsInArray.get(j)));

			}

		}
	}

	public static void compareTrioByCommonDNA(Individual parentA,
			Individual parentB, Individual childOfAandB) {

		System.out.println(CommonDNAFinder
				.getSimillarityPercentageBetweenTwoIndividuals(parentA,
						childOfAandB));
		System.out.println(CommonDNAFinder
				.getSimillarityPercentageBetweenTwoIndividuals(parentB,
						childOfAandB));
		System.out
				.println(CommonDNAFinder
						.getSimillarityPercentageBetweenTwoIndividuals(parentA,
								parentB));

	}

	public static void compareIndividualsOnAllChromosomes() {

		String path = HapMap3DataParser.FOLDER_PATH;

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			System.out.println("Filename is: " + file.getName());
			compareIndividualsOnSingleChromosome(file.getName());
		}

	}

	public static void compareIndividualsOnSingleChromosome(String filename) {
		HapMap3DataParser.reset(filename);

		Individual[] parentsArray = HapMap3DataParser.getParents();
		Individual[] childrenArray = HapMap3DataParser.getChildren();

		System.out.println("There are " + parentsArray.length + " parents");
		System.out.println("There are " + childrenArray.length + "children");

		System.out.println("Printing all parents:");
		for (Individual individual : parentsArray) {

			System.out.print(individual.getIndividualId());
			System.out.print(", ");

		}

		System.out.println("Printing all children:");
		for (Individual individual : childrenArray) {
			System.out.print(individual.getIndividualId());
			System.out.print(", ");
		}
		System.out.println("");

		StringBuilder matrixWithAllPossibleSimillarityPercentages = new StringBuilder();
		for (int i = 0; i < parentsArray.length; i++) {

			double maximumSimilarity = 0;
			int maximumSimilarityIndex = -1;

			for (int j = 0; j < childrenArray.length; j++) {

				double simillarityBetweenParentChild = CommonDNAFinder
						.getSimillarityPercentageBetweenTwoIndividuals(
								parentsArray[i], childrenArray[j]);

				if (simillarityBetweenParentChild > maximumSimilarity) {

					maximumSimilarityIndex = j;
					maximumSimilarity = simillarityBetweenParentChild;

				}
				matrixWithAllPossibleSimillarityPercentages
						.append(simillarityBetweenParentChild + ", ");
			}
			matrixWithAllPossibleSimillarityPercentages.append("\n");

			System.out.println("Parent " + i + " the child is: "
					+ maximumSimilarityIndex);
		}
		/*
		 * FileIO.writeStringToFile(GeneticRelatednessEstimator.OUTPUT_FOLDER_PATH
		 * + "matrixWithAllPossibleSimillarityPercentages.csv",
		 * matrixWithAllPossibleSimillarityPercentages.toString());
		 * 
		 * System.out.println("Successfully wrote file to " +
		 * GeneticRelatednessEstimator.OUTPUT_FOLDER_PATH +
		 * "matrixWithAllPossibleSimillarityPercentages.csv");
		 */
	}
}
