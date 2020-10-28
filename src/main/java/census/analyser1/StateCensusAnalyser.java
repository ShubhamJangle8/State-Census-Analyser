package census.analyser1;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;

import CensusAnalysing.CSVBuilderException;
import CensusAnalysing.CSVBuilderFactory;
import CensusAnalysing.ICSVBuilder;

public class StateCensusAnalyser {
	
	List<CSVStateCensus> censusCsvList = null;
	List<StateCodeCsv> stateCodeCsvList = null;
	
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCsvList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
			int numberOfEntries = censusCsvList.size();
			return numberOfEntries;
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
	public int loadStateCodeCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) { 
			@SuppressWarnings({ "unchecked"})
			ICSVBuilder<StateCodeCsv> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			stateCodeCsvList = csvBuilder.getCSVFileList(reader, StateCodeCsv.class);
			int numberOfEntries = stateCodeCsvList.size();
			return numberOfEntries;
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
}
	}
	
	public <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
	}
	 
	/**
	 * UC3
	 * Sorted according to state name
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getSortedStateCensusData() throws CensusAnalyserException {
		if(stateCodeCsvList == null || stateCodeCsvList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparatorState = Comparator.comparing(census -> census.state);
		this.sortStateName(censusComparatorState);
		String sortedStateCensusJson = new Gson().toJson(censusCsvList);
		return sortedStateCensusJson;
	}
	
	/**
	 * UC4
	 * Sorted according to state code
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getSortedStateCodeData() throws CensusAnalyserException {
		if(stateCodeCsvList == null || stateCodeCsvList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<StateCodeCsv> censusComparatorCode = Comparator.comparing(census -> census.stateCode);
		this.sortStateCode(censusComparatorCode);
		String sortedStateCodeCensusJson = new Gson().toJson(stateCodeCsvList);
		return sortedStateCodeCensusJson;
	}

	private void sortStateName(Comparator<CSVStateCensus> censusComparator) {
		for(int i = 0 ; i < censusCsvList.size(); i++){
			for(int j = 0; j < censusCsvList.size() - i - 1; j++) {
				CSVStateCensus census1 = censusCsvList.get(j);
				CSVStateCensus census2 = censusCsvList.get(j + 1);
				if(censusComparator.compare(census1, census2) > 0) {
					censusCsvList.set(j, census2);
					censusCsvList.set(j + 1, census1);
				}
			}
		}
	}
	
	private void sortStateCode(Comparator<StateCodeCsv> censusComparator) {
		for(int i = 0 ; i < stateCodeCsvList.size(); i++){
			for(int j = 0; j < stateCodeCsvList.size() - i - 1; j++) {
				StateCodeCsv census1 = stateCodeCsvList.get(j);
				StateCodeCsv census2 = stateCodeCsvList.get(j + 1);
				if(censusComparator.compare(census1, census2) > 0) {
					stateCodeCsvList.set(j, census2);
					stateCodeCsvList.set(j + 1, census1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to State Census Analyser");
	}
	
}