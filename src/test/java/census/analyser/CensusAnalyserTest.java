package census.analyser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyser//IndiaStateCensusData.csv";

	@Test
	public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			assertEquals(29, numOfRecords);
		} catch (IOException e) {
		}
	}

}
