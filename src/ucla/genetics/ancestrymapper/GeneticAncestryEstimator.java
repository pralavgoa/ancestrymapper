package ucla.genetics.ancestrymapper;

import ucla.genetic.utils.FileIO;

public class GeneticAncestryEstimator {

	public static final String BASE_FOLDER = "C:\\Users\\Pralav\\Downloads\\HapmapData\\";
	public static final String OUTPUT_FOLDER_PATH = "C:\\Users\\Pralav\\workspace\\GeneticRelatednessEstimator\\output\\";

	public static void main(String[] args) {

		String firstFile = FileIO.readFiletoString(BASE_FOLDER
				+ "genotypes_chr1_CEU_r22_nr.b36_fwd.phase");

		String[] firstChromosomeSplitOnNewLine = firstFile.split("\\n");

	}

}
