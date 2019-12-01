package tests.octavian;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import vo.main.ValidateSchema;

public class SchematronValidationTest {

	private static final String BOOKS_SCHEMATRON = "booksSchematron.xsl";
	
	private static final String BOOKS_FALSE_DATABASE = "bookstoreFalse.xml";
	private static final String BOOKS_SUCCESS_DATABASE = "bookstoreTrue.xml";
	
	private static final String PUBLICATIONS_SCHEMATRON = "pubSchematron.xsl";
	private static final String PUBLICATIONS_FALSE_DATABASE = "pubFalse.xml";
	private static final String PUBLICATIONS_TRUE_DATABASE = "pubTrue.xml";


	@Test
	public void testValidateFalseXML() {

		File schema = new File(BOOKS_SCHEMATRON);
		File database = new File(BOOKS_FALSE_DATABASE);

		try {
			assertFalse(ValidateSchema.validateAgaistSchema(schema, database));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidateTrueXML() {

		File schema = new File(BOOKS_SCHEMATRON);
		File database = new File(BOOKS_SUCCESS_DATABASE);

		try {
			assertTrue(ValidateSchema.validateAgaistSchema(schema, database));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidateMessageErrors() {
		File schema = new File(BOOKS_SCHEMATRON);
		File database = new File(BOOKS_FALSE_DATABASE);
		
		try {
			List<String> list = ValidateSchema.validateMessage(schema, database);
			
			assertNotNull(list);
			assertTrue(list.size() > 1);
			
			System.out.println(list);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testValidateMessageSuccess() {
		File schema = new File(BOOKS_SCHEMATRON);
		File database = new File(BOOKS_SUCCESS_DATABASE);
		
		try {
			List<String> list = ValidateSchema.validateMessage(schema, database);
			
			assertNotNull(list);
			assertTrue(list.size() == 0);
			
			System.out.println(list);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}


}
