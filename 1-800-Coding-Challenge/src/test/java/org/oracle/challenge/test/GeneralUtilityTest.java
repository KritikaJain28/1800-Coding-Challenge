package org.oracle.challenge.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.oracle.challenge.utility.GeneralUtility;

public class GeneralUtilityTest {

	// Valid data
	@Test
	public void testFetchMappedValuesForValidData() {
		String input = "23";
		List<String> expectedOutput = Arrays.asList("A,B,C", "D,E,F");
		List<String> actualOutput = GeneralUtility.fetchMappedValues(input);
		Assert.assertArrayEquals(expectedOutput.toArray(), actualOutput.toArray());
	}

	// Single non mapping scenario
	@Test
	public void testFetchMappedValuesForInValidData() {
		String input = "231";
		List<String> expectedOutput = Arrays.asList("A,B,C", "D,E,F", "1");
		List<String> actualOutput = GeneralUtility.fetchMappedValues(input);
		Assert.assertArrayEquals(expectedOutput.toArray(), actualOutput.toArray());
	}

	// Consecutive non mapping digit scenario
	@Test
	public void testFetchMappedValuesForConsecutiveInValidData() {
		String input = "233015";
		List<String> actualOutput = GeneralUtility.fetchMappedValues(input);
		Assert.assertArrayEquals(Collections.emptyList().toArray(), actualOutput.toArray());
	}

	// Invalid but not consecutive
	@Test
	public void testFetchMappedValuesForInValidDataNotConsecutive() {
		String input = "2041";
		List<String> expectedOutput = Arrays.asList("A,B,C", "0", "G,H,I", "1");
		List<String> actualOutput = GeneralUtility.fetchMappedValues(input);
		Assert.assertArrayEquals(expectedOutput.toArray(), actualOutput.toArray());
	}

	// Single invalid data
	@Test
	public void testFetchMappedValuesForSingleInValidData() {
		String input = "2";
		List<String> actualOutput = GeneralUtility.fetchMappedValues(input);
		Assert.assertArrayEquals(Collections.emptyList().toArray(), actualOutput.toArray());
	}

	@Test
	public void testDigitTestReplacementValid() {
		String input = "5";
		String actualOutput = GeneralUtility.digitHasReplacement(input);
		String expectedOutput = "J,K,L";
		Assert.assertEquals(expectedOutput, actualOutput);
	}

	// Data having no replacement
	@Test
	public void testDigitTestReplacementInValid() {
		String input = "0";
		String actualOutput = GeneralUtility.digitHasReplacement(input);
		Assert.assertEquals(null, actualOutput);
	}

	// Data having punctuation and white spaces
	@Test
	public void testRemoveWhiteSpacesAndPunctuation() {
		String input = "2$2?5  5!63";
		String actual = GeneralUtility.removeWhiteSpacesAndPunctuation(input);
		String expected = "225563";
		Assert.assertEquals(expected, actual);
	}

	// Data having '.'
	@Test
	public void testReplaceDotWithHyphen() {
		String input = "KRITIKA.JAIN";
		String expected = "KRITIKA-JAIN";
		String actual = GeneralUtility.replaceDotWithHyphen(input);
		Assert.assertEquals(expected, actual);
	}

	//Consecutive non mapping digit scenario
	@Test
	public void testFetchMappedValuesForJunkData() {
		String input = ".#!@#^";
		List<String> actualOutput = GeneralUtility.fetchMappedValues(input);
		Assert.assertArrayEquals(Collections.emptyList().toArray(), actualOutput.toArray());
	}

}
