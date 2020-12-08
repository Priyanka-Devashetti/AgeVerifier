package com.example.ageverifier

import com.example.ageverifier.constants.APIConstants
import io.restassured.RestAssured.given
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions

class APITest {

    @Test
    fun `Returns 200 Success Status Code for Valid API Input`() {
        val requestObject = """{"age":32}"""

        val statusCode =
            given()
                .contentType(APIConstants.CONTENT_TYPE)
                .body(requestObject)
            .`when`()
                .post(APIConstants.API_END_POINT)
            .then()
                .statusCode(APIConstants.SUCCESS_STATUS_CODE)
                    .log().body().extract().response().statusCode

       Assert.assertEquals(APIConstants.SUCCESS_STATUS_CODE,statusCode)
    }


    @Test
    fun `Returns True as Response for Age input above 18`() {
        val requestObject = """{"age":32}"""

        val response =
            given()
                .contentType(APIConstants.CONTENT_TYPE)
                .body(requestObject)
            .`when`()
                .post(APIConstants.API_END_POINT)
            .then()
                .statusCode(APIConstants.SUCCESS_STATUS_CODE)
                .log().body().extract().response().asString()

        val expectedResponse = """{"isValid":true}"""
        Assert.assertEquals(expectedResponse,response)
    }

    @Test
    fun `Returns False as Response for Age input below 18`() {
        val requestObject = """{"age":16}"""

        val response =
                given()
                        .contentType(APIConstants.CONTENT_TYPE)
                        .body(requestObject)
                .`when`()
                        .post(APIConstants.API_END_POINT)
                .then()
                        .statusCode(APIConstants.SUCCESS_STATUS_CODE)
                        .log().body().extract().response().asString()

        val expectedResponse = """{"isValid":false}"""
        Assert.assertEquals(expectedResponse,response)
    }

    @Test
    fun `Returns Null as Response for Age input above 51`() {
        val requestObject = """{"age":51}"""

        val response =
                given()
                        .contentType(APIConstants.CONTENT_TYPE)
                        .body(requestObject)
                        .`when`()
                        .post(APIConstants.API_END_POINT)
                        .then()
                        .statusCode(APIConstants.SUCCESS_STATUS_CODE)
                        .log().body().extract().response().asString()

        val expectedResponse = """{"isValid":null}"""
        Assert.assertEquals(expectedResponse,response)
    }


    @Test
    fun `Throws Error for negative age`() {
        val requestObject = """{"age":"-200"}"""

        Assertions.assertThrows(AssertionError::class.java) {
            given()
                .contentType(APIConstants.CONTENT_TYPE)
                .body(requestObject)
            .`when`()
                .post(APIConstants.API_END_POINT)
            .then()
                .statusCode(APIConstants.SUCCESS_STATUS_CODE)
                .log().body().extract().response().statusCode()
        }
    }
}