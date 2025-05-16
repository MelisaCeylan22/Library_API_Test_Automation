package com.example.library;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApiPOSTTest {

    @Test
    public void testAddNewBook() {
        // API'nin çalıştığı base URL
        String baseUrl = "http://localhost:8080/books";

        // Gönderilecek kitap verisi
        String requestBody = "{\n" +
                "  \"Kitapadi\": \"ANA\",\n" +
                "  \"Yazar\": \"Maksim Gorki\",\n" +
                "  \"Yıl\": 1906\n" +
                "}";

        given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(201) // 201 Created bekleniyor
                .body("Kitapadi", equalTo("ANA"))
                .body("Yıl",equalTo(1906))
                .body("Yazar", equalTo("Maksim Gorki"));
    }
}


