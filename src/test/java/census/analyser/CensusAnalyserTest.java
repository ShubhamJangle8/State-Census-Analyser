package census.analyser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyserGradel//IndiaStateCensusData.csv";
	private static final String WRONG_FILE_PATH = "C://Users//DELL//eclipse-workspace//IndiaStateCensusData.csv";
	private static final String WRONG_EXTENSION_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyserGradel//IndiaStateCensusData.jpg";
	private static final String CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyserGradel//USCensusData.csv";

	@Test
	public void givenIndianCensusCSVFile_ReturnsCorrectRecords() throws IOException {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}
	
	
	@Test
	public void givenCSVFile_IfWrongFile_ShouldThrowError() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(WRONG_EXTENSION_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

}
