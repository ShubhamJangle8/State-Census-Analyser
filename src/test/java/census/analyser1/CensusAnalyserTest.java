package census.analyser1;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;

import CensusAnalysing.CSVBuilderException;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyserGradel//IndiaStateCensusData.csv";
	private static final String WRONG_FILE_PATH = "C://Users//DELL//eclipse-workspace//IndiaStateCensusData.csv";
	private static final String WRONG_EXTENSION_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyserGradel//IndiaStateCensusData.jpg";
	private static final String CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyserGradel//USCensusData.csv";
	private static final String STATE_CODE_CSV_FILE_PATH = "C://Users//DELL//eclipse-workspace//CensusAnalyserGradel//IndiaStateCode.csv";
	
	/**
	 * UC1
	 * Test 1 for a correct count for Census CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenIndianCensusCSVFile_ReturnsCorrectRecords() throws IOException, CSVBuilderException {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			System.out.println(numOfRecords);
			assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	/**
	 * UC1
	 * Test 2 for a WrongFilePath Exception for Census CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenCSVFile_IfWrongFile_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC1
	 * Test 3 for a Incorrect Extension Exception for Census CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenCSVFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(WRONG_EXTENSION_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC1
	 * Test 4 for Incorrect Delimiter exception for Census CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenCSVFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC1
	 * Test for Incorrect Header Exception for Census CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenCSVFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadIndiaCensusData(CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}
	
	/**
	 * UC2
	 * Test for a proper count of records for State Code CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenStateCodeCSVFile_ReturnsCorrectCount() throws IOException, CSVBuilderException {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfStateRecords = stateCensusAnalyser.loadStateCodeCensusData(STATE_CODE_CSV_FILE_PATH);
			System.out.println(numOfStateRecords);
			assertEquals(38, numOfStateRecords);
		}catch(CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * UC2
	 * Test for Wrong File Exception for state code CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenStateCodeCSVFile_IfWrongFile_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadStateCodeCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC2
	 * Test for Incorrect Extension Exception for state code CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenStateCodeCSVFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadStateCodeCensusData(WRONG_EXTENSION_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC2
	 * Test for Incorrect Delimiter Exception for State Code CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenStateCodeCSVFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadStateCodeCensusData(INDIA_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC2
	 * Test for Incorrect Header Exception For State Code CSV
	 * @throws IOException
	 * @throws CSVBuilderException 
	 */
	@Test
	public void givenStateCodeCSVFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.loadStateCodeCensusData(CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}
	
	/**
	 * UC3
	 * Sort and check for 1st element
	 * @throws CSVBuilderException 
	 * @throws IOException 
	 * @throws CensusAnalyserException 
	 */
	@Test
	public void GivenStateCensusData_WhenSortedOnState_ShouldReturnSortedResultWith1stElement() throws IOException, CSVBuilderException {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			String sortedCensusData = stateCensusAnalyser.getSortedStateCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
			assertEquals("Andhra Pradesh", censusCsv[0].state);
		} catch(CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	/**
	 * UC3
	 * Sort and check for last element
	 * @throws IOException
	 * @throws CSVBuilderException
	 */
	@Test
	public void GivenStateCensusData_WhenSortedOnState_ShouldReturnSortedResultWithLastElement() throws IOException, CSVBuilderException {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			String sortedCensusData = stateCensusAnalyser.getSortedStateCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
			assertEquals("West Bengal", censusCsv[censusCsv.length-1].state);
		} catch(CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
}
