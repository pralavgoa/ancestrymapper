package ucla.genetics.ancestrymapper;

import java.util.Set;

import ucla.genetics.pedigreegenerator.Individual;

public class CommonDNAFinder {

	public static double getCommonDNAasPercentage(char[] first, char[] second,
			Set<Integer> snipLocations) {

		long commonDNACounter = 0;

		for (int i = 0; i < first.length; i++) {

			if (snipLocations == null) {
				if (first[i] == second[i]) {
					commonDNACounter++;
				} else {
					// ignore
				}
			} else {
				if (snipLocations.contains(i)) {
					if (first[i] == second[i]) {
						commonDNACounter++;
					} else {
						// ignore
					}
				}
			}

		}

		double commonDNAasPercentage = 0;
		if (snipLocations != null) {
			commonDNAasPercentage = (double) commonDNACounter
					/ snipLocations.size() * 100;
		} else {
			commonDNAasPercentage = (double) commonDNACounter / first.length
					* 100;
		}

		return commonDNAasPercentage;

	}

	public static double getCommonDNAasPercentage(char[] first, char[] second) {
		return getCommonDNAasPercentage(first, second, null);
	}

	public static double getSimillarityPercentageBetweenTwoIndividuals(
			Individual firstIndividual, Individual secondIndividual) {

		System.out.println("Between " + firstIndividual.getIndividualId()
				+ " and " + secondIndividual.getIndividualId());

		char[] firstIndividualChromosomeA = firstIndividual
				.getChromosome(1, true).toString().toCharArray();
		char[] firstIndividualChromosomeB = firstIndividual
				.getChromosome(1, false).toString().toCharArray();

		char[] secondIndividualChromosomeA = secondIndividual
				.getChromosome(1, true).toString().toCharArray();
		char[] secondIndividualChromosomeB = secondIndividual
				.getChromosome(1, false).toString().toCharArray();

		double simillarityPercentageAA = CommonDNAFinder
				.getCommonDNAasPercentage(firstIndividualChromosomeA,
						secondIndividualChromosomeA);

		double simillarityPercentageAB = CommonDNAFinder
				.getCommonDNAasPercentage(firstIndividualChromosomeA,
						secondIndividualChromosomeB);

		double simillarityPercentageBA = CommonDNAFinder
				.getCommonDNAasPercentage(firstIndividualChromosomeB,
						secondIndividualChromosomeA);

		double simillarityPercentageBB = CommonDNAFinder
				.getCommonDNAasPercentage(firstIndividualChromosomeB,
						secondIndividualChromosomeB);

		return (simillarityPercentageAA + simillarityPercentageAB
				+ simillarityPercentageBA + simillarityPercentageBB) / 4.0;

	}
}
