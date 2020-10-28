package census.analyser1;

public class CensusAnalyserException extends Exception {

	enum ExceptionType{
		INCORRECT_FILE,NO_FILE,UNABLE_TO_PARSE, NO_CENSUS_DATA
	}
	
	ExceptionType type;
	
	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
	
}
