package com.practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class Api_Automation {
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response= given().log().all().queryParams("key", "qaclick123").header("Content-Type", "application/json")
		 .body("{\r\n"
		 		+ "  \"location\": {\r\n"
		 		+ "    \"lat\": -38.383494,\r\n"
		 		+ "    \"lng\": 33.427362\r\n"
		 		+ "  },\r\n"
		 		+ "  \"accuracy\": 50,\r\n"
		 		+ "  \"name\": \"Frontline house\",\r\n"
		 		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
		 		+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
		 		+ "  \"types\": [\r\n"
		 		+ "    \"shoe park\",\r\n"
		 		+ "    \"shop\"\r\n"
		 		+ "  ],\r\n"
		 		+ "  \"website\": \"http://google.com\",\r\n"
		 		+ "  \"language\": \"French-IN\"\r\n"
		 		+ "}")
		 .when().post("maps/api/place/add/json")
		 .then().log().all().statusCode(200).extract().asString();
		System.out.println(response);
		
		JsonPath jp=new JsonPath(response);
		String placeid=jp.getString("place_id");
		System.out.println(placeid);
		
		String putResp=given().log().all().queryParams("place_id", placeid).header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\"5 amcn  nsgar, chennai\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().statusCode(200).extract().asString();
		System.out.println(putResp);
		String getResp=given().log().all().queryParams("place_id", placeid).header("Content-Type", "application/json")
		.when().get("maps/api/place/get/json")
		.then().log().all().statusCode(200).extract().asString();
		System.out.println(getResp);
		
	}
	
	

}


 