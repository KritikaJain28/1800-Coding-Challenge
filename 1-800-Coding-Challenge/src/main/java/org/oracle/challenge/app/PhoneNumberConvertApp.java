package org.oracle.challenge.app;

import java.io.IOException;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.oracle.challenge.constants.PhoneNumberConstants;
import org.oracle.challenge.exception.NoPhoneNumberException;
import org.oracle.challenge.service.LoadNumberService;
import org.oracle.challenge.utility.CartesianProductUtility;
import org.oracle.challenge.utility.GeneralUtility;

/**
 * This application shows a user possible matches for a list
   of provided phone numbers.
 * @author kritikjain
 *
 */
public class PhoneNumberConvertApp {

	final static Logger logger = Logger.getLogger(PhoneNumberConvertApp.class);

	public static void main(String[] args) throws IOException, NoPhoneNumberException {
		
		//Loading Phone number from cmd/user input
		LoadNumberService loadService = new LoadNumberService();
		List<String> phoneNumbers = loadService.LoadPhoneNumbers(args);

		if (CollectionUtils.isEmpty(phoneNumbers)) {
			throw new NoPhoneNumberException(PhoneNumberConstants.NO_PHONE_NUMBER_FOUND);
		} else {
			processPhoneNumbers(phoneNumbers);
		}
	}

	private static void processPhoneNumbers(List<String> phoneNumbers) {
		phoneNumbers.stream().forEach(number -> {
			List<String> mappedValues = GeneralUtility.fetchMappedValues(number);
			if (CollectionUtils.isNotEmpty(mappedValues)) {
				List<String> possibleMatches = CartesianProductUtility.retrievePossibleMatches(mappedValues);
				logger.info(PhoneNumberConstants.RESPONSE_TEXT + number);
				GeneralUtility.displayResponse(possibleMatches);
			} else {
				logger.error(number + PhoneNumberConstants.ERROR);
			}
		});
	}

}
