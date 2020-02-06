package org.oracle.challenge.utility;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.oracle.challenge.constants.PhoneNumberConstants;

public class GeneralUtility {

	private GeneralUtility() {
	}

	static Logger logger = Logger.getLogger(GeneralUtility.class);

	public static Properties dictionaryDataProp = new Properties();

	static {
		loadDictionaryData(PhoneNumberConstants.MAPPING_URL);
	}

	/**
	 * This method checks whether the input phone number is applicable for
	 * replacement or not. If there are two consecutive digits having no dictionary
	 * mapping then the input is not applicable for replacement.
	 * 
	 * @param phoneNumber
	 * @return List of Mapped dictionary values
	 */
	public static List<String> fetchMappedValues(String phoneNumber) {
		List<String> mappedValues = new ArrayList<>();
		char[] numbers = (removeWhiteSpacesAndPunctuation(phoneNumber)).toCharArray();

		for (int i = 1; i < numbers.length; i++) {
			if (null == digitHasReplacement(String.valueOf(numbers[i - 1]))
					&& null == digitHasReplacement(String.valueOf(numbers[i]))) {
				logger.error("Digits not having mapping " + numbers[i - 1] + " and " + numbers[i]);
				logger.error("Cannot have consecutive non-mapping digits");
				return Collections.emptyList();
			}
			// If valid number entered then adding the mapped values for each digit in
			// mappedValues List.
			mappedValues
					.add(null == digitHasReplacement(String.valueOf(numbers[i - 1])) ? String.valueOf(numbers[i - 1])
							: digitHasReplacement(String.valueOf(numbers[i - 1])));
			if (i == (numbers.length - 1)) {
				mappedValues.add(null == digitHasReplacement(String.valueOf(numbers[i])) ? String.valueOf(numbers[i])
						: digitHasReplacement(String.valueOf(numbers[i])));
			}
		}
		return mappedValues;
	}

	/**
	 * Returns the mapping for the entered digit.
	 * 
	 * @param number
	 * @return Mapped value
	 */
	public static String digitHasReplacement(String number) {
		return dictionaryDataProp.getProperty(number);
	}

	/**
	 * Loads the dictionary data from the constant mapping url.
	 */
	public static void loadDictionaryData(String dictionaryPath) {

		File file = new File(dictionaryPath);
		try (FileInputStream fileInput = new FileInputStream(file)) {
			dictionaryDataProp.load(fileInput);
			logger.info("Dictionary loaded from path : " + PhoneNumberConstants.MAPPING_URL);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Replaces '.' with '-'
	 * 
	 * @param string
	 * @return updated string
	 */
	public static String replaceDotWithHyphen(String string) {
		return string.replaceAll("[.]", "-");
	}

	/**
	 * Removes white spaces and punctuation mark except '.'
	 * 
	 * @param string
	 * @return updated string
	 */
	public static String removeWhiteSpacesAndPunctuation(String string) {
		return string.replaceAll("\\s", "").replaceAll("[\\p{Punct}&&[^'-.]]+", "");
	}

	/**
	 * Displays the input match list and if match contains '.' replaced it with '-'
	 * 
	 * @param matches
	 */
	public static void displayResponse(List<String> matches) {
		matches.stream().forEach(match -> {
			if (match.contains(".")) {
				match = replaceDotWithHyphen(match);
			}
			logger.debug(match.toUpperCase());
		});
	}
}
