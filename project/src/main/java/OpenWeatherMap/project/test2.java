package OpenWeatherMap.project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class test2 {
	public static Map<String , Object> jsonToMap(String str){
		Map<String , Object > map =new Gson().fromJson(str,new TypeToken<HashMap<String , Object >>() {} .getType());
		return map;
	}
	public static void main(String[] args)  {
		System.out.println("Welecome to weather finder");
		String API_KEY="13e9153d2b6bd48e218f6c1a37fe5ac4";
		Scanner scan =new Scanner(System.in);
		System.out.println("Enter the latitude and longitude of location");
		String lat=scan.nextLine();
		String lon=scan.nextLine();
		System.out.println("Please select the one of the following options");
		System.out.println("1.Current weather data");
		System.out.println("2.FIVE day forecast data");
		System.out.println("3.Current Air Pollution data");
		int option=scan.nextInt();
		
		if(option==1) {
		String url_Current_Weather="https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon="+ lon +"&units=metric"+ "&appid="+API_KEY;
		//"&exclude="+part+
		StringBuilder result_Current_Weather=new StringBuilder();
		URL url1;
		try {
			url1 = new URL(url_Current_Weather);
			 HttpsURLConnection conn = (HttpsURLConnection)url1.openConnection();
			 conn.setRequestMethod("GET");
			//URLConnection conn=url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine())!=null) {
				result_Current_Weather.append(line);
			}
			rd.close();
			//System.out.println(result_Current_Weather);
			System.out.println("CURRENT WEATHER FOR LOCATION " +lat+ "LATITUDE " +lon +"LONGITUDE" );
			Map<String, Object> respMap=jsonToMap(result_Current_Weather.toString());
			Map<String, Object> mainMap=jsonToMap(respMap.get("main").toString());
			Map<String, Object> windMap=jsonToMap(respMap.get("wind").toString());
			System.out.println("Temperature_FEEL: "+ mainMap.get("temp")+" Celsius");
			System.out.println("Temperature_MINIMUM: "+ mainMap.get("temp_min")+" Celsius");
			System.out.println("Temperature_MAXIMUM: "+ mainMap.get("temp_max")+" Celsius");
			System.out.println("Humidity: "+ mainMap.get("humidity")+"%");
			System.out.println("Pressure: "+ mainMap.get("pressure")+"hpa");
			System.out.println("Current windspeed: "+ windMap.get("speed")+"meter/sec");
			JSONObject newObj = new JSONObject(result_Current_Weather.toString());
		    JSONArray items = newObj.getJSONArray("weather");
		    for (int it = 0; it < items.length(); it++) {
	            JSONObject contactItem = items.getJSONObject(it);
	            System.out.println("Current Weather is: "+contactItem.getString("main"));
	        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		if(option==2) {
			String urlString="https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon="+ lon+ "&cnt=5"+"&units=metric" + "&appid="+API_KEY;
			StringBuilder result=new StringBuilder();
			URL url;
			try {
				url = new URL(urlString);
				 HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
				 conn.setRequestMethod("GET");
				//URLConnection conn=url.openConnection();
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine())!=null) {
					result.append(line);
				}
				rd.close();
				Map<String, Object> respMap=jsonToMap(result.toString());
				String daywise=respMap.get("list").toString();
				System.out.println("Five days forecast is: "+ daywise);
				JSONObject newObj = new JSONObject(result.toString());
			    JSONArray items = newObj.getJSONArray("list");
			    for (int it = 0; it < items.length(); it++) {
		            JSONObject contactItem = items.getJSONObject(it);
		            System.out.println("DATE is: "+contactItem.getString("dt_txt"));
		            System.out.println("Temperature_FEEL: "+ contactItem.getJSONObject("main").getBigDecimal("temp")+" Celsius");
					System.out.println("Temperature_MINIMUM: "+ contactItem.getJSONObject("main").getBigDecimal("temp_min")+" Celsius");
					System.out.println("Temperature_MAXIMUM: "+ contactItem.getJSONObject("main").getBigDecimal("temp_max")+" Celsius");
					System.out.println("Humidity: "+ contactItem.getJSONObject("main").getInt("humidity")+"%");
					System.out.println("Pressure: "+ contactItem.getJSONObject("main").getInt("pressure")+"hpa");
					System.out.println("Current windspeed: "+ contactItem.getJSONObject("wind").getBigDecimal("speed")+"meter/sec");
					JSONArray itemsweather = contactItem.getJSONArray("weather");
					for (int itw = 0; itw < itemsweather.length(); itw++) {
					JSONObject contactItemWeather = itemsweather.getJSONObject(itw);
		            System.out.println("Weather is: "+contactItemWeather.getString("main"));
					}
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		if(option==3) {
			String url_Current_Pollution="http://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon="+ lon + "&appid="+API_KEY;
			//"&exclude="+part+
			StringBuilder result_Current_Pollution=new StringBuilder();
			URL url2;
			try {
				url2 = new URL(url_Current_Pollution);
				 HttpURLConnection conn = (HttpURLConnection)url2.openConnection();
				 conn.setRequestMethod("GET");
				//URLConnection conn=url.openConnection();
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine())!=null) {
					result_Current_Pollution.append(line);
				}
				rd.close();
				//System.out.println(result_Current_Pollution);
				//Map<String, Object> respMap=jsonToMap(result_Current_Pollution.toString());
				//String mainMap=respMap.get("list").toString();
				//System.out.println("Current Temperature: "+ mainMap);
				System.out.println("CURRENT AIR QUALITY FOR LOCATION " +lat+ "LATITUDE " +lon +"LONGITUDE" );
				   JSONObject newObj = new JSONObject(result_Current_Pollution.toString());
			       JSONArray items = newObj.getJSONArray("list");
			        for (int it = 0; it < items.length(); it++) {
			            JSONObject contactItem = items.getJSONObject(it);
			            System.out.println("Current Air Quality Index is" +contactItem.getJSONObject("main"));
			            System.out.println("current concentration of gases in air is" +contactItem.getJSONObject("components"));
			            System.out.println("Air Quality Index. Possible values: 1, 2, 3, 4, 5. Where 1 = Good, 2 = Fair, 3 = Moderate, 4 = Poor, 5 = Very Poor.");
			        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}
