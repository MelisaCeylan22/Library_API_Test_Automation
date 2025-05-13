package com.example.library;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class BookApiTest extends BaseTest {

    /** GET /books → 200 + body dizi + ≤1s */
    @Test
    public void shouldListBooks() {
        given().
                when().
                get("/books").
                then().
                statusCode(200).
                body("$", is(notNullValue())).
                time(lessThan(1000L));  // ms
    }

    /** GET /books/1 → 200 + id==1 + ≤1s */
    @Test
    public void shouldReturnExistingBook() {
        given().
                when().
                get("/books/1").
                then().
                statusCode(200).
                body("id", equalTo(1)).
                time(lessThan(1000L));
    }


    /** POST /books → 201 + title=Dune + ≤1s */
    @Test
    public void shouldCreateBook() {
        String requestBody = "{\n" +
                "  \"Kitapadi\": \"ANA\",\n" +
                "  \"Yazar\": \"Maksim Gorki\",\n" +
                "  \"Yıl\": 1906\n" +
                "}";
        given().
                contentType("application/json").
                body(requestBody).
                when().
                post("/books").
                then().
                statusCode(201).
                body("Yazar", equalTo("Maksim Gorki")).
                time(lessThan(1000L));
    }

    /** POST /books (eksik alan) → 400 + ≤1s */
    @Test
    public void shouldFailValidation() {
        given().
                contentType("application/json").
                body("{ \"Kitapadi\":\"Eksik\" }").
                when().
                post("/books").
                then().
                statusCode(400).
                time(lessThan(1000L));
    }
}