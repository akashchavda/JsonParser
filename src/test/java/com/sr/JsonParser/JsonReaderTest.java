package com.sr.JsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonReaderTest {
	
	/**
	 * TDD - 1 Check file is exists or not
	 */
	@Test
	public void shouldCheckFileExists() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("jsonArray.json").getFile());
		Assert.assertNotNull(file);
	}
	
	/**
	 * TDD - 2 File not found handling.
	 * Scenario : 
	 * - Change file name to test1.json
	 * - It should throws a null pointer exception so handled it. 
	 */
	@Test(expected = NullPointerException.class)
	public void fileNotFound() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("jsonArray1.json").getFile());
		Assert.assertNotNull(file);
	}
	
	/**
	 * TDD - 3 Check Json Array
	 *  Scenario :
	 *  - Passing jsonArray.json file that contains list of objects.
	 *  - Test size of objects 
	 *  - Test return type of method. 
	 *  - Test Array Object contains name key
	 *  - Test first country name
	 *  - Test invalid country name in first record
	 */
	@Test
	public void shouldCheckJsonArray() {
		JsonReader jsonReader = new JsonReader();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("jsonArray.json").getFile());
		Object response = jsonReader.readData(file);
		
		//Test return type of method
		Assert.assertEquals(response.getClass(), ArrayList.class);
		List<HashMap<String, Object>> results = (List<HashMap<String, Object>>) response;
		
		//Test size of objects 
		Assert.assertEquals(244, results.size());
		
		// Test Array Object contains name key
		HashMap<String, Object> map = results.get(0);
		Assert.assertTrue(map.keySet().contains("name"));
		
		// Test first country name
		Assert.assertEquals("Afghanistan", map.get("name"));
		
		// Test invalid country name in first record
		Assert.assertNotEquals("USA", map.get("name"));
	}
	
	/**
	 * TDD - 4 Check Json Object
	 *  Scenario :
	 *  - Passing jsonObject.json file that contains object.
	 *  - Test address property value type
	 *  - Test role property value 
	 *  - Test json object contains cities property
	 *  - Test cities contains values
	 *  - Test role property value type
	 *  - Test cities property value type
	 */
	@Test
	public void shouldCheckJsonObject() {
		JsonReader jsonReader = new JsonReader();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("jsonObject.json").getFile());
		Object response = jsonReader.readData(file);
		Assert.assertEquals(response.getClass(), HashMap.class);
		HashMap<String, Object> map = (HashMap<String, Object>) response;
		
		// Test json object contains cities property
		Assert.assertTrue(map.keySet().contains("cities"));
		
		// Test cities contains values
		List<String> cities = new ArrayList<String>();
		cities.add("Los Angeles");
		cities.add("New York");
		Assert.assertEquals(map.get("cities"), cities);
		
		//Test address property value type
		Assert.assertEquals(map.get("address").getClass(), LinkedHashMap.class);
		
		//Test role property value type
		Assert.assertEquals(map.get("role").getClass(), String.class);
		
		//Test cities property value type
		Assert.assertEquals(map.get("cities").getClass(), ArrayList.class);
		
		//Test role property value 
		Assert.assertEquals(map.get("role"), "Manager");
	}
	
	/**
	 * TDD - 5 Check Json Blank
	 *  Scenario :
	 *  - Passing jsonBlank.json file that no any data.
	 *  - Method returns null
	 */
	@Test
	public void shouldCheckJsonBlank() {
		JsonReader jsonReader = new JsonReader();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("jsonBlank.json").getFile());
		Object response = jsonReader.readData(file);
		Assert.assertEquals(response, null);
	}
}
