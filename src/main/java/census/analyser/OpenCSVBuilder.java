package census.analyser;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder {

	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException{
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
	
}
