package ucla.genetics.ancestrymapper;

import ucla.genetics.pedigreegenerator.Population;

public class GeneticAncestryEstimator {

	public static final String COMMON_PATH = "C:\\Users\\Pralav\\Desktop\\Bioinfo project\\data\\population\\";

	public static final String AFRICA = "hapmap3_r2_b36_fwd.consensus.qc.poly.chr22_yri.phased";
	public static final String EUROPE = "hapmap3_r2_b36_fwd.consensus.qc.poly.chr22_ceu.phased";

	public static void main(String[] args) {

		System.out.println("Getting africa data " + COMMON_PATH + "africa\\"
				+ AFRICA);
		Population africa = new Population(COMMON_PATH + "africa\\" + AFRICA);
		System.out.println("Getting europe data " + COMMON_PATH + "europe\\"
				+ EUROPE);
		Population europe = new Population(COMMON_PATH + "europe\\" + EUROPE);

	}

}
