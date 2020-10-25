package census.analyser1;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStateCensus> censusCsvIterator = csvBuilder.
														 getCSVFileIterator(reader, CSVStateCensus.class);
			int numberOfEntries = getCount(censusCsvIterator);
			return numberOfEntries;
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), 
					  						  CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
	public int loadStateCodeCensusData(String csvFilePath) throws IOException, CensusAnalyserException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) { 
			@SuppressWarnings({ "unchecked"})
			ICSVBuilder<StateCodeCsv> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<StateCodeCsv> stateCodeCsvIterator = csvBuilder.
														  getCSVFileIterator(reader, StateCodeCsv.class);
			int numberOfEntries = getCount(stateCodeCsvIterator);
			return numberOfEntries;
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
