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
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> censusCsvList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
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
			Iterator<StateCodeCsv> stateCodeCsvIterator = csvBuilder.getCSVFileIterator(reader, StateCodeCsv.class);
			int numberOfEntries = getCount(stateCodeCsvIterator);
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
	 
	public static void main(String[] args) {
		System.out.println("Welcome to State Census Analyser");
	}

	public String getSortedStateCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> censusCsvList = csvBuilder.
												 getCSVFileList(reader, CSVStateCensus.class);
			Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
			this.sort(censusCsvList, censusComparator);
			String sortedStateCensusJson = new Gson().toJson(censusCsvList);
			return sortedStateCensusJson;
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
						     				  CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), 
											  CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}

	private void sort(List<CSVStateCensus> censusList, Comparator<CSVStateCensus> censusComparator) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < censusList.size(); i++){
			for(int j = 0; j < censusList.size() - i - 1; j++) {
				CSVStateCensus census1 = censusList.get(j);
				CSVStateCensus census2 = censusList.get(j + 1);
				if(censusComparator.compare(census1, census2) > 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}
				
			}
		}
	}
}
