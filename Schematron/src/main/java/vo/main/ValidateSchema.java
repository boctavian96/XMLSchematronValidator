package vo.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.xml.transform.stream.StreamSource;

import org.oclc.purl.dsdl.svrl.FailedAssert;
import org.oclc.purl.dsdl.svrl.SuccessfulReport;

import com.helger.schematron.ISchematronResource;
import com.helger.schematron.pure.SchematronResourcePure;

public class ValidateSchema {

	// ERROR MESSAGES.
	public static final String INVALID = "Invalid Schematron";

	// THE API.

	/**
	 * Validates the xml agaist the schema.
	 * 
	 * @param schematron
	 * @param XML
	 * @return True if it's valid. False if it's invalid.
	 * @throws Exception
	 */
	public static boolean validateAgaistSchema(String schematron, String XML) throws Exception {
		return validateAgaistSchema(new File(schematron), new File(XML));
	}

	/**
	 * Validates the xml agaist the schema.
	 * 
	 * @param schematron
	 * @param XML
	 * @return True if it's valid. False if it's invalid.
	 * @throws Exception
	 */
	public static boolean validateAgaistSchema(File schematron, File XML) throws Exception {
		final ISchematronResource schemaResource = SchematronResourcePure.fromFile(schematron);
		if (!schemaResource.isValidSchematron()) {
			throw new IllegalArgumentException(INVALID);
		}
		return schemaResource.getSchematronValidity(new StreamSource(XML)).isValid();
	}

	/**
	 * Returns error messages from schematron validation. If the xml document is
	 * valid, it will return null.
	 * 
	 * @param schematron
	 * @param XML
	 * @return Messages if the xml have errors. Null if the xml is valid.
	 * @throws Exception
	 */
	public static List<String> validateMessage(File schematron, File XML) throws Exception {
		final ISchematronResource schemaResource = SchematronResourcePure.fromFile(schematron);
		if (!schemaResource.isValidSchematron()) {
			throw new IllegalArgumentException(INVALID);
		}

		int actors = schemaResource.applySchematronValidationToSVRL(new StreamSource(XML))
				.getActivePatternAndFiredRuleAndFailedAssertCount();
		System.out.println("Number of actors: " + actors);

		List<Object> allActors = schemaResource.applySchematronValidationToSVRL(new StreamSource(XML))
				.getActivePatternAndFiredRuleAndFailedAssert();

		List<String> fails_messages = new ArrayList<>();

		for (Object obj : allActors) {
			if (ValidateSchema.objectIsFail(obj)) {
				fails_messages.add((ValidateSchema.objectMessage(obj)));
			}
		}

		return fails_messages;

	}

	// HELPERS

	private static boolean objectIsFail(Object obj) {
		return obj instanceof FailedAssert || obj instanceof SuccessfulReport;
	}

	@Nullable
	private static String objectMessage(Object obj) {

		if (obj instanceof FailedAssert) {
			return ((FailedAssert) obj).getText() + " Path: " + ((FailedAssert) obj).getLocation();
		}

		if (obj instanceof SuccessfulReport) {
			return ((SuccessfulReport) obj).getText() + " Path: " + ((SuccessfulReport) obj).getLocation();
		}

		return null;
	}
}
