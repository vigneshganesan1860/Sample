package com.app.sample;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SampleOne {
	
	public static void main(String[] args) {
		try {
			FileReader f= new FileReader(new File("C:\\Users\\HP\\eclipse-workspace"
					+ "\\Develop\\new\\Aug_API\\src\\test\\resources\\sample.json"));
			JSONParser jp = new JSONParser();
			Object object = jp.parse(f);
			JSONObject job = (JSONObject)object;
			String placeid = (String)job.get("place_id");
			System.out.println(placeid);
			JSONArray jr = (JSONArray)job.get("course");
			for (Object object2 : jr) {
				System.out.println(object2);
			}
					
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
