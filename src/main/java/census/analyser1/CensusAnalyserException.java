package census.analyser1;

public class CensusAnalyserException extends Exception {

	enum ExceptionType{
		INCORRECT_FILE,NO_FILE,UNABLE_TO_PARSE
	}
	
	ExceptionType type;
	
	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
	
}
