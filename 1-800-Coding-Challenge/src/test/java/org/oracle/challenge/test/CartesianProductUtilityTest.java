package org.oracle.challenge.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.oracle.challenge.utility.CartesianProductUtility;

public class CartesianProductUtilityTest {

	/**
	 * Valid mapped values product scenario
	 */
	@Test
	public void testRetrievePossibleMatches() {
		List<String> input = Arrays.asList("A,B,C", "D,E,F");
		List<String> expected = Arrays.asList("AD","AE","AF","BD","BE","BF","CD","CE","CF");
		List<String> actual = CartesianProductUtility.retrievePossibleMatches(input);
		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
	/**
	 * Mapped and non mapped values product scenario
	 */
	@Test
	public void testRetrievePossibleMatchesNoMapping() {
		List<String> input = Arrays.asList("A,B,C", "0", "D,E,F");
		List<String> expected = Arrays.asList("A0D","A0E","A0F","B0D","B0E","B0F","C0D","C0E","C0F");
		List<String> actual = CartesianProductUtility.retrievePossibleMatches(input);
		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
	@Test
	public void testFetchMatchedCartProduct() {
		List<String> list1 = Arrays.asList("A","B","C");
		List<String> list2 = Arrays.asList("D","E","F");
		List<List<String>> cartList = Arrays.asList(list1,list2);
		List<String> expected = Arrays.asList("AD","AE","AF","BD","BE","BF","CD","CE","CF");
		List<String> actual = CartesianProductUtility.fetchMatchedCartProduct(cartList);
		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}

}
