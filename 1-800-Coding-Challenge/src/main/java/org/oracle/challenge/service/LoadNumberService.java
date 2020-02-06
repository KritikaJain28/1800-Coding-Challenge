package org.oracle.challenge.service;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * This service loads the phone number from the cmd args or standard user input.
 * Single file path should be provided in the command line args else input will be switched to standard user input.  
 * @author kritikjain
 *
 */
public class LoadNumberService {

	static Logger logger = Logger.getLogger(LoadNumberService.class);

	public List<String> LoadPhoneNumbers(String[] args) throws IOException {
		List<String> phoneNumbers = new ArrayList<>();
		if (args.length == 1) {
			logger.debug("Reading file from command line argument");
			FileInputStream fileInput = new FileInputStream(args[0]);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInput))){
				String strLine;
				while ((null != (strLine = br.readLine()))) {
					phoneNumbers.add(strLine.toString());
				}
			} catch(IOException e) {
				logger.error("Command Line args has invalid, please verify");
				e.printStackTrace();
			}
		} else {
			logger.debug("Command Line args not provided or more than one file path mentioned, switching to User input.");
			logger.debug("Please enter phone number for possible matches:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			phoneNumbers.add(reader.readLine());
		}
		return phoneNumbers;
	}
}
