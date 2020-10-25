package census.analyser1;

public class CSVBuilderFactory {
	@SuppressWarnings("rawtypes")
	public static <E> ICSVBuilder createCSVBuilder() {
		return new OpenCsvBuilder();
	}
}
