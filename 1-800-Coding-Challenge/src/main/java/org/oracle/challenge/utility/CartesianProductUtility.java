package org.oracle.challenge.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

/**
 * This utility retrieves all the possible combination for the mapped values.
 * 
 * @author kritikjain
 *
 */

public class CartesianProductUtility {

	private CartesianProductUtility() {

	}

	public static List<String> retrievePossibleMatches(List<String> mappedValues) {
		List<List<String>> cartesianProductList = new ArrayList<>();
		ListUtils.emptyIfNull(mappedValues).stream().forEach(value -> {
			cartesianProductList.add(Arrays.asList(value.split(",")));
		});
		return fetchMatchedCartProduct(cartesianProductList);
	}

	public static List<String> fetchMatchedCartProduct(List<List<String>> cartList) {
		List<String> combinations = cartList.get(0);
		if (cartList.size() >= 2) {
			for (int i = 1; i < cartList.size(); i++) {
				combinations = performCartProduct(cartList, combinations, i);
			}
		}
		return combinations;
	}

	private static List<String> performCartProduct(List<List<String>> cartList, List<String> first, int i) {
		List<String> cartProducts = new ArrayList<>();
		for (String alpha : first) {
			cartList.get(i).forEach(alpha2 -> {
				cartProducts.add(alpha.concat(alpha2));
			});
		}
		return cartProducts;
	}
}
