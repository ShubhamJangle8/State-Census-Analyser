package census.analyser1;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@SuppressWarnings("rawtypes")
public class OpenCsvBuilder<E> implements ICSVBuilder{

	@SuppressWarnings("unchecked")
	public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException{
		try { 
			CsvToBeanBuilder<E> csvBuilder = new CsvToBeanBuilder<>(reader);
			csvBuilder.withType(csvClass);
			CsvToBean<E> csvToBean = csvBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CSVBuilderException(e.getMessage(), 
					CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
		} 
	}
	
}
