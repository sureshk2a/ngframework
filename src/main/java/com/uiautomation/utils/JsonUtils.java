package com.uiautomation.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class JsonUtils {

	public static Object[][] getJSONdata(String JSON_path, String JSON_data ,int JSON_attributes) throws FileNotFoundException, IOException, ParseException
	{
		/**		
		 * JSON_attributes => like Column in Excel, total column hence total attributes need to provide
		 * this is using json simple jar file
		 */
		
		Object obj = new JSONParser().parse(new FileReader(JSON_path));
		JSONObject jo = (JSONObject)obj;
		JSONArray js = (JSONArray)jo.get(JSON_data);
		
		Object [][] arr = new String[js.size()][JSON_attributes]; 
		for (int i = 0; i < js.size(); i++) 
		{ 
			JSONObject obj1 = (JSONObject)js.get(i);
			arr[i][0] = String.valueOf(obj1.get("UserID"));
			arr[i][1] = String.valueOf(obj1.get("Password"));
			arr[i][2] = String.valueOf(obj1.get("ConPassword"));
		}
		return arr;
	}

}
