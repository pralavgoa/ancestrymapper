package ucla.genetics.pedigreegenerator;

public class Individual {

	private final String individualId;

	private final StringBuilder[] untransmittedChromosomes = new StringBuilder[23];
	private final StringBuilder[] transmittedChromosomes = new StringBuilder[23];

	private final StringBuilder[] recombinedChromosomes = new StringBuilder[23];

	public Individual(String individualId) {
		this.individualId = individualId;
	}

	public String getIndividualId() {
		return this.individualId;
	}

	public boolean setChromosome(int chromosomeNumber, String bases,
			boolean isTransmitted) {
		if (chromosomeNumber > 23 || chromosomeNumber < 1) {
			return false;
		}

		if (isTransmitted) {
			transmittedChromosomes[chromosomeNumber] = new StringBuilder(bases);
		} else {
			untransmittedChromosomes[chromosomeNumber] = new StringBuilder(
					bases);
		}

		return true;

	}

	public StringBuilder getChromosome(int chromosomeNumber,
			boolean isTransmitted) {

		if (isTransmitted) {
			if (transmittedChromosomes[chromosomeNumber] != null) {
				return transmittedChromosomes[chromosomeNumber];
			} else {
				transmittedChromosomes[chromosomeNumber] = new StringBuilder();
				return transmittedChromosomes[chromosomeNumber];
			}
		} else {
			if (untransmittedChromosomes[chromosomeNumber] != null) {
				return untransmittedChromosomes[chromosomeNumber];
			} else {
				untransmittedChromosomes[chromosomeNumber] = new StringBuilder();
				return untransmittedChromosomes[chromosomeNumber];
			}
		}

	}

	public void recombineDNA() {

		System.out.println("Recombination of chromosomes for individual "
				+ getIndividualId());
		// replicate the recombination process, which contructs a chromosome
		// from the exising chromosome by mimicking recombination

		// Select three points at which to break, for starters, let the points
		// be defined

		long chromosomeLength = untransmittedChromosomes[1].length();

		if (chromosomeLength == 0) {
			System.out.println("Error in chromosome");
			return;
		}

		recombinedChromosomes[1] = new StringBuilder();

		long firstBreakInChr = chromosomeLength / 3;
		long secondBreakInChr = chromosomeLength * 2 / 3;

		// pick a chromosome to begin with
		if (Math.random() < 0.5) {
			int baseIndex = 0;
			while (baseIndex < firstBreakInChr) {

				recombinedChromosomes[1].append(untransmittedChromosomes[1]
						.charAt(baseIndex));

				baseIndex++;
			}

			while (baseIndex < secondBreakInChr) {
				recombinedChromosomes[1].append(transmittedChromosomes[1]
						.charAt(baseIndex));
				baseIndex++;
			}

			while (baseIndex < chromosomeLength) {
				recombinedChromosomes[1].append(untransmittedChromosomes[1]
						.charAt(baseIndex));
				baseIndex++;
			}
		} else {
			int baseIndex = 0;
			while (baseIndex < firstBreakInChr) {
				recombinedChromosomes[1].append(transmittedChromosomes[1]
						.charAt(baseIndex));
				baseIndex++;
			}

			while (baseIndex < secondBreakInChr) {
				recombinedChromosomes[1].append(untransmittedChromosomes[1]
						.charAt(baseIndex));
				baseIndex++;
			}

			while (baseIndex < chromosomeLength) {
				recombinedChromosomes[1].append(transmittedChromosomes[1]
						.charAt(baseIndex));
				baseIndex++;
			}
		}

		System.out.println("The length of the recombined chromosome is "
				+ recombinedChromosomes[1].length());

	}

	public StringBuilder getRecombinedChromosome(int chromosomeNumber) {
		return recombinedChromosomes[chromosomeNumber];
	}

	@Override
	public String toString() {

		return untransmittedChromosomes[1].toString();
	}
}
