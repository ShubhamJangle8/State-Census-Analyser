package census.analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
			Iterator<CSVStateCensus> censusCsvIterator = this.getCSVFileIterator(reader, CSVStateCensus.class);
			int numberOfEntries = getCount(censusCsvIterator);
			return numberOfEntries;
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), 
					  						  CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
	public int loadStateCodeCensusData(String csvFilePath) throws IOException, CensusAnalyserException {
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) { 
			Iterator<StateCodeCsv> stateCodeCsvIterator = this.getCSVFileIterator(reader, StateCodeCsv.class);
			int numberOfEntries = getCount(stateCodeCsvIterator);
			return numberOfEntries;
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), 
											  CensusAnalyserException.ExceptionType.NO_FILE);
		} 
	}

	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException{
		try { 
			CsvToBeanBuilder<E> csvBuilder = new CsvToBeanBuilder<>(reader);
			csvBuilder.withType(csvClass);
			csvBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvBuilder.build();
			return csvToBean.iterator();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), 
											  CensusAnalyserException.ExceptionType.INCORRECT_FILE);
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
