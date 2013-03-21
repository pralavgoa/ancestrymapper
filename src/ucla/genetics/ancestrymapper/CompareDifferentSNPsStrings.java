package ucla.genetics.ancestrymapper;

import java.util.HashSet;

public class CompareDifferentSNPsStrings {

	public static void compareDifferentSNPsStrings(
			String[] firstChromosomeSplitOnNewLine) {

		HashSet<Integer> snipPositions = new HashSet<Integer>();

		for (int i = 0; i < firstChromosomeSplitOnNewLine.length; i++) {

			char[] currentIndividual = firstChromosomeSplitOnNewLine[i]
					.toCharArray();

			for (int j = i + 1; j < firstChromosomeSplitOnNewLine.length; j++) {

				System.out.println("Comparing individuals " + i + " and " + j);
				char[] compareWithIndividual = firstChromosomeSplitOnNewLine[j]
						.toCharArray();

				int minimumChromosomeLength = (currentIndividual.length > compareWithIndividual.length) ? compareWithIndividual.length
						: currentIndividual.length;

				System.out.println("Minimum Chromosome Length is "
						+ minimumChromosomeLength);
				int loopIterator = minimumChromosomeLength - 1;
				while (loopIterator >= 0) {
					if (currentIndividual[loopIterator] != compareWithIndividual[loopIterator]) {
						if (snipPositions.contains(loopIterator)) {
							// ignore
						} else {
							snipPositions.add(loopIterator);
						}
					}
					loopIterator--;
				}

			}

		}
		System.out.println("There are " + snipPositions.size() + " SNPs");

		CompareFullSNPsString.compareFullSNPsString(
				firstChromosomeSplitOnNewLine, snipPositions,
				"secondMethodResults");

	}
}
