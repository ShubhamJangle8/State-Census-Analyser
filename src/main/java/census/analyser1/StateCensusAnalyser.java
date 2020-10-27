package census.analyser1;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import CensusAnalysing.CSVBuilderException;
import CensusAnalysing.CSVBuilderFactory;
import CensusAnalysing.ICSVBuilder;

public class StateCensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> censusCsvList = csvBuilder.
												 getCSVFileList(reader, CSVStateCensus.class);
			int numberOfEntries = censusCsvList.size();
			return numberOfEntries;
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
						     				  CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), 
											  CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
	public int loadStateCodeCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) { 
			@SuppressWarnings({ "unchecked"})
			ICSVBuilder<StateCodeCsv> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<StateCodeCsv> stateCodeCsvIterator = csvBuilder.
														  getCSVFileIterator(reader, StateCodeCsv.class);
			int numberOfEntries = getCount(stateCodeCsvIterator);
			return numberOfEntries;
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
											  CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), 
											  CensusAnalyserException.ExceptionType.NO_FILE);
}
	}
	
	public <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
	}
	 
	public static void main(String[] args) {
		System.out.println("Welcome to State Census Analyser");
	}
}
