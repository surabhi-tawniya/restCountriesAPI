package com.countryAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class GetVerifyRepublicIndia {

    @Test
    public void validateRepublicIndia() {

        RestAssured.given()
                .baseUri("http://restcountries.eu/rest/v1/name")
                .pathParam("countryName", "India")
                .when()
                .get("/{countryName}")
                .then()
                //.statusCode(200)
                .header("Content-Type", "application/json;charset=utf-8")
                .body("[1].name", equalTo("India"))
                .body("[1].capital", equalTo("New Delhi"))
                .body("[1].altSpellings[2]", containsString("Republic of India"));
        String response = "[\"IN\",\n" +
                "\"Bharat\",\n" +
                "\"Republic of India\",\n" +
                "\"Bharat Ganrajya\"]";

        //String resString = response.asString();
        System.out.println(response);

    }

    @Test
    public void countryCodeXyz() {

        RestAssured.given()
                .baseUri("http://restcountries.eu/rest/v1/name")
                .pathParam("countryName", "xyz")
                .when()
                .get("/{countryName}")
                .then()
                .statusCode(404)
                .and()
                .log()
                .all(true);

    }


    @Test
    public void getNorwayInfo() {

        RestAssured.given()
                .baseUri("http://restcountries.eu/rest/v1/name")
                .pathParam("countryName", "norway")
                .when()
                .get("/{countryName}")
                .then()
                .contentType(ContentType.JSON)
                // .assertThat()
                .body("[0].capital", containsString("Oslo"))
                .log()
                .all(true);

    }


}
