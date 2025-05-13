package com.example.library;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/** Ortak ayarlar. */
public abstract class BaseTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port    = 8080;
        // log().all() eklemek isterseniz:
        // RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}