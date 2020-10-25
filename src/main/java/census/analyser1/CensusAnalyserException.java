package census.analyser1;

public class CensusAnalyserException extends Exception {

	enum ExceptionType{
		INCORRECT_FILE,NO_FILE,WRONG_FILE
	}
	
	ExceptionType type;
	
	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
	
}
