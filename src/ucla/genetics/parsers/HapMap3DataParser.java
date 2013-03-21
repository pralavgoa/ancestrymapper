package ucla.genetics.parsers;

import ucla.genetic.utils.FileIO;
import ucla.genetics.pedigreegenerator.Individual;

public class HapMap3DataParser {

	public static final String FOLDER_PATH = "C:\\Users\\Pralav\\Downloads\\HapmapData\\Hapmap3\\YRI\\unzipped\\";

	private static Individual[] parentsArray;
	private static Individual[] childrenArray;

	public static void reset(String dataFileToUse) {

		System.out.println("Hapmap3 Data Parser initializing...");
		String firstChromosome = FileIO.readFiletoString(FOLDER_PATH
				+ dataFileToUse);

		String[] snipPositions = firstChromosome.split("\\n");

		System.out.println("There are " + snipPositions.length
				+ " snip positions");

		String[] fileHeaders = snipPositions[0].split("\\s");

		System.out.println("Printing the file headers.. ");

		String[] individualIds = new String[(fileHeaders.length - 2) / 2];

		for (int i = 2; i < fileHeaders.length; i++) {

			String chromosomeId = fileHeaders[i];

			String[] chrIdSplit = chromosomeId.split("_");

			if (i % 2 == 0)
				individualIds[(i - 2) / 2] = chrIdSplit[0];

			System.out.print(chromosomeId);
			System.out.print("   ");
		}

		System.out.println("");

		int numberOfColumns = fileHeaders.length;

		System.out.println("The number of columns(individuals + 2) is "
				+ numberOfColumns);

		parentsArray = new Individual[numberOfColumns / 2 - 1];
		childrenArray = new Individual[parentsArray.length / 2];

		// Zero index of snipPositions array are the headers
		for (int i = 1; i < snipPositions.length; i++) {

			String[] snipValuesInIndividuals = snipPositions[i].split("\\s");

			int parentIndex = 0;
			int childrenChromosomeIndex = 0;
			int childrenIndex = 0;
			int columnNumber = 2;
			while (parentIndex < (numberOfColumns / 2) - 1) {

				if (columnNumber % 2 == 0) {
					// it is the untransmitted chromosome of one indiviudal
					if (parentsArray[parentIndex] != null) {
						parentsArray[parentIndex].getChromosome(1, false)
								.append(snipValuesInIndividuals[columnNumber]);
					} else {
						parentsArray[parentIndex] = new Individual(
								individualIds[parentIndex]);
						parentsArray[parentIndex].getChromosome(1, false)
								.append(snipValuesInIndividuals[columnNumber]);
					}
				} else {
					// it is the transmitted chromosome of one individual
					if (parentsArray[parentIndex] != null) {
						parentsArray[parentIndex].getChromosome(1, true)
								.append(snipValuesInIndividuals[columnNumber]);
					} else {
						parentsArray[parentIndex] = new Individual(
								individualIds[parentIndex]);
						parentsArray[parentIndex].getChromosome(1, true)
								.append(snipValuesInIndividuals[columnNumber]);
					}
					parentIndex++;

					// also put this in a child
					if (childrenArray[childrenIndex] == null) {
						childrenArray[childrenIndex] = new Individual("("
								+ individualIds[childrenIndex * 2] + "+"
								+ individualIds[childrenIndex * 2 + 1] + ")");
					}

					if (childrenChromosomeIndex % 2 == 0) {
						childrenArray[childrenIndex].getChromosome(1, true)
								.append(snipValuesInIndividuals[columnNumber]);
						// same child
					} else {
						childrenArray[childrenIndex].getChromosome(1, false)
								.append(snipValuesInIndividuals[columnNumber]);
						childrenIndex++;
					}

					childrenChromosomeIndex++;

				}
				columnNumber++;
			}

		}
	}

	public static Individual[] getParents() {
		return parentsArray;
	}

	public static Individual[] getChildren() {
		return childrenArray;
	}

}
