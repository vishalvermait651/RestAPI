package WeaterAPI;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

public class Weatherloop {
	
	@SuppressWarnings("serial")
	public static Map<String, Object> jasonToMap(String String) {
		
		Map<String, Object> map =  new Gson().fromJson(String , new TypeToken<HashMap<String, Object>>() {}.getType());
		return map;
	}
	

	public static void main(String[] args) throws Exception {
		
		String API_KEY= "http://api.openweathermap.org/data/2.5/weather?q=London&appid=9d50450a48809637b4862bdcb125927d&units=imperial";
		
		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(API_KEY);
			URLConnection conn = url.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
		
			while ((line = br.readLine())!= null) {
				result.append(line);
			}
			br.close();
			System.out.println(result);
		
			Map<String, Object> resmap =  jasonToMap(result.toString());
			ArrayList<Map<String, Object>> weathers = (ArrayList<Map<String, Object>>)resmap.get("weather");
			Map<String, Object> weatherMap = weathers.get(0);

			System.out.println("Test Id: " + weatherMap.get("id")); 
			System.out.println("Test Main: " + weatherMap.get("main")); 
			System.out.println("Test Description: " + weatherMap.get("description")); 
			System.out.println("Test Icon: " + weatherMap.get("icon")); 
		
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
	  
	}
	
}
