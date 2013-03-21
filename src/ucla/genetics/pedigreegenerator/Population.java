package ucla.genetics.pedigreegenerator;

import ucla.genetics.parsers.HapMap3DataParser;

public class Population {

	public static final int NUM_PEOPLE = 10;

	public Population(String folderWithFiles) {

		HapMap3DataParser.reset(folderWithFiles);

	}

}
