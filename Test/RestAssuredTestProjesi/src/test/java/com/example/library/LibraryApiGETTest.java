package com.example.library;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class LibraryApiGETTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void testGetBooks_returns200_andValidBody_andFast() {
        Response response = get("/books");

        long elapsedTime = get("/books").time(); // süre
        System.out.println("Cevap süresi: " + elapsedTime + " ms");

        // Durum kodu 200 mü?
        assertEquals(200, response.statusCode());

        // İlk kitap doğru mu?
        assertEquals("Yaşama Uğraşı", response.jsonPath().getString("[0].Kitapadi"));
        assertEquals("Cesare Pavese", response.jsonPath().getString("[0].Yazar"));
        assertEquals(1952, (int) response.jsonPath().getInt("[0].Yıl"));

        // Cevap 1 saniyeden kısa mı sürdü?
        assertTrue("Cevap 1 saniyeden uzun sürdü", elapsedTime <1000);
}
}