package com.one;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class postCall {
	
	
	@Test
	public void readJSONFile() throws IOException {
		
		String path1 = System.getProperty("user.dir") + "\\src\\main\\java\\com\\one\\new1.json";
//		FileInputStream readFile = new FileInputStream(path);
//		String readingFile = readFile.toString();
//		System.out.println(readingFile);
		
		String jsonbody = "";
		jsonbody = new String(Files.readAllBytes(Paths.get(path1)));
		
//		System.out.println(data);
		
		RestAssured.baseURI = "https://reqres.in/";
		
		Response response = null;

        try {
            response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonbody)
                .post("/api/users");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals(201, response.getStatusCode());
		
	}

}
