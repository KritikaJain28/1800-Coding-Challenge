package org.oracle.challenge.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.oracle.challenge.service.LoadNumberService;

public class LoadNumberServiceTest {
	
	String testFilePath;
	
	LoadNumberService loadNumberService = new LoadNumberService();

	@Before
	public void setUp() throws Exception {
		 testFilePath = System.getProperty("user.dir") + "/src/test/resources/phone-number-test.txt";
	}

	@Test
	public void testLoadPhoneNumberThroughCmdArgs() throws IOException {
		
		List<String> input = Arrays.asList(testFilePath);
		String [] array = input.toArray(new String[0]);
		List<String> actual = loadNumberService.LoadPhoneNumbers(array);
		List<String> expected = Arrays.asList("22","92#71");
		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}
	
}
