package census.analyser;

public class CSVBuilderFactory {
	@SuppressWarnings("rawtypes")
	public static <E> ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
