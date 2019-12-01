package vo.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogWriter {

	private static final String DATE_PATTERN = "dd_MM_YYYY";

	
	/**
	 * Writes a list of schematron messages to the log file. @See generateLogFileName().
	 * @param messages List of messages.
	 * @throws IOException
	 */
	public static void writeLog(List<String> messages) throws IOException {
		FileWriter writer;

		writer = new FileWriter(new File(LogWriter.generateLogFileName()));

		for (String message : messages) {
			writer.write(message + '\n');
		}

		writer.close();
	}
	
	/**
	 * Just to generate the name of the log.
	 * @return dd_MM_YYYY_SchemaValidation.log
	 */
	private static String generateLogFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat(LogWriter.DATE_PATTERN);
		return sdf.format(new Date()) + "_SchematronValidation.log";

	}
}
