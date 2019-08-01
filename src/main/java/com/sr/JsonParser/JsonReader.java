package com.sr.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	public Object readData(File file) {

		try {
			//ClassLoader classLoader = getClass().getClassLoader();
	        //InputStream is = classLoader.getResourceAsStream("test.json");
	        //File file = new File(classLoader.getResource("test.json").getFile());
	        
			// Read selected file
	        FileReader fr = new FileReader(file);    
	        int data;    
	        StringBuffer sb = new StringBuffer();
	        while((data=fr.read())!=-1)     {
	        	sb.append((char)data);
	        }
	        fr.close();   
	        String jsonStr = sb.toString().replaceAll("\n", "").replaceAll("\t", "");

	        //Check json format, is it JSON Object or JSON Array?
	        if(jsonStr.trim().startsWith("{")) {
	        	HashMap<String, Object> map = new ObjectMapper().readValue(jsonStr, HashMap.class);
	        	return map;
	        	
	        } else if(jsonStr.trim().startsWith("[")) {
	        	JSONArray jsonArray = new JSONArray(jsonStr);
	        	List<HashMap<String, Object>> results = new ArrayList<HashMap<String,Object>>();
	        	
	        	for (int i=0; i<jsonArray.length(); i++) {
	        		HashMap<String, Object> map = new ObjectMapper().readValue(jsonArray.get(i).toString(), HashMap.class);
	        		results.add(map);
				}
	        	return results;
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
