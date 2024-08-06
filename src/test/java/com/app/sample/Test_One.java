package com.app.sample;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test_One {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// POST
		String response = given().log().all().queryParams("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
						+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n"
						+ "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n"
						+ "  \"website\": \"http://google.com\",\r\n" + "  \"language\": \"French-IN\"\r\n" + "}")
				.when().post("/maps/api/place/add/json").then().log().all().statusCode(200).extract().asString();
		System.out.println(response);
		JsonPath jp = new JsonPath(response);
		String place_id = jp.getString("place_id");
		System.out.println(""+place_id+"");
		
		
		//Update
		String getResp=	given().log().all().queryParam("place_id", place_id).queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().statusCode(200).extract().asString();
		System.out.println(getResp);
		
		//Get
		String Resp=	given().log().all().queryParam("place_id", place_id)
				.queryParam("key", "qaclick123")	
				.when().get("/maps/api/place/get/json")
				.then().log().all().statusCode(200).extract().asString();
				System.out.println(getResp);
		

	}

}
